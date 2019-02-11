package com.shushper.loftmoneyjan01;


import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class ItemsFragment extends Fragment {

    private static final String TAG = "ItemsFragment";

    private static final int ADD_ITEM_REQUEST_CODE = 111;


    static ItemsFragment newInstance(String type) {
        ItemsFragment fragment = new ItemsFragment();

        Bundle bundle = new Bundle();
        bundle.putString(KEY_TYPE, type);

        fragment.setArguments(bundle);

        return fragment;
    }


    private static final String KEY_TYPE = "type";


    private SwipeRefreshLayout refresh;

    private String token = "$2y$10$MI9aJHOPZNR1WLHMPoRkx.6geJcwuzU/JxArRxeOoK9KXyPs3DzfG";

    private ItemsAdapter adapter;
    private String type;

    private Api api;

    public ItemsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        adapter = new ItemsAdapter();


        type = getArguments().getString(KEY_TYPE);


        Application application = getActivity().getApplication();
        App app = (App) application;
        api = app.getApi();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_items, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {


        refresh = view.findViewById(R.id.refresh);
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadItems();
            }
        });


        RecyclerView recycler = view.findViewById(R.id.recycler);

        recycler.setAdapter(adapter);
        recycler.setLayoutManager(new LinearLayoutManager(requireContext()));


        DividerItemDecoration divider = new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL);
        divider.setDrawable(requireContext().getDrawable(R.drawable.divider));
        recycler.addItemDecoration(divider);

        loadItems();
    }

    private void loadItems() {

        Call call = api.getItems(type, token);

        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                refresh.setRefreshing(false);
                List<Item> items = (List<Item>) response.body();
                adapter.setItems(items);
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                refresh.setRefreshing(false);
                Log.e(TAG, "loadItems: ", t);
            }
        });

    }

    void onFabClick() {
        Intent intent = new Intent(requireContext(), AddItemActivity.class);
        startActivityForResult(intent, ADD_ITEM_REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == ADD_ITEM_REQUEST_CODE && resultCode == Activity.RESULT_OK) {

            if (data != null) {
                String name = data.getStringExtra(AddItemActivity.KEY_NAME);
                String price = data.getStringExtra(AddItemActivity.KEY_PRICE);

                Log.d(TAG, "onActivityResult: name = " + name);
                Log.d(TAG, "onActivityResult: price = " + price);

                Item item = new Item(name, Double.valueOf(price), type);
                adapter.addItem(item);
            }

        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }


}
