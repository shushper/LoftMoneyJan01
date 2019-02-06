package com.shushper.loftmoneyjan01;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ItemsFragment extends Fragment {


    public static ItemsFragment newInstance(int type) {
        ItemsFragment fragment = new ItemsFragment();

        Bundle bundle = new Bundle();
        bundle.putInt(KEY_TYPE, type);

        fragment.setArguments(bundle);

        return fragment;
    }


    public static final int TYPE_UNKNOWN = 0;
    public static final int TYPE_INCOMES = 1;
    public static final int TYPE_EXPENSES = 2;

    public static final String KEY_TYPE = "type";


    private ItemsAdapter adapter;
    private int type;


    public ItemsFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        adapter = new ItemsAdapter();

        Bundle arguments = getArguments();

        if (arguments == null) {
            throw new IllegalStateException("You mast set fragment type");

        }

        type = getArguments().getInt(KEY_TYPE, TYPE_UNKNOWN);

        if (type == TYPE_UNKNOWN) {
            throw new IllegalStateException("Unknown fragment type");
        }


        Log.d("ItemsFragment", "type = " + type);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_items, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {


        RecyclerView recycler = view.findViewById(R.id.recycler);

        recycler.setAdapter(adapter);
        recycler.setLayoutManager(new LinearLayoutManager(requireContext()));


        DividerItemDecoration divider = new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL);
        divider.setDrawable(requireContext().getDrawable(R.drawable.divider));
        recycler.addItemDecoration(divider);


        adapter.setItems(createTempItems());
    }


    private List<Item> createTempItems() {
        List<Item> items = new ArrayList<>();
        items.add(new Item("Молоко", "70"));
        items.add(new Item("Зубная щетка", "70"));
        items.add(new Item("Сковородка с антипригарным покрытием", "1670"));
        items.add(new Item("Зубочистка", "2"));
        items.add(new Item("Tiguan", "2000000"));
        items.add(new Item("iPhone", "80000"));

        return items;
    }

}
