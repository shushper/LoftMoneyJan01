package com.shushper.loftmoneyjan01;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class MainPagesAdapter extends FragmentPagerAdapter {

    private static final int PAGE_INCOMES = 0;
    private static final int PAGE_EXPENSES = 1;

    private static final int PAGES_COUNT = 2;

    private Context context;

    public MainPagesAdapter(@NonNull FragmentManager fm, Context context) {
        super(fm);

        this.context = context;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {


        switch (position) {
            case PAGE_INCOMES:
                return ItemsFragment.newInstance(Item.TYPE_INCOME);
            case PAGE_EXPENSES:
                return ItemsFragment.newInstance(Item.TYPE_EXPENSE);
            default:
                return new ItemsFragment();
        }

    }

    @Override
    public int getCount() {
        return PAGES_COUNT;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        switch (position) {
            case PAGE_INCOMES:
                return context.getString(R.string.main_tab_incomes);
            case PAGE_EXPENSES:
                return context.getString(R.string.main_tab_expenses);
            default:
                return "";
        }

    }
}
