package com.motondon.tablayoutdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.motondon.tablayoutdemo_part1.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Joca on 4/6/2016.
 *
 * This fragment contains a RecyclerView with some items which is used to demonstrate how to expand AppBarLayout
 * programmatically.
 *
 */
public class CarsFragment extends BaseFragment {

    public static final int ICON_ID = R.drawable.ic_time_to_leave_white_24dp;
    public static final String ITEM_TEXT = "Cars";

    static final String[] CAR_BRAND = new String[] { "Alfa Romeo", "Audi", "BMW",
            "Bugatti", "Chrysler", "Dodge", "Ferrari", "Jaguar",
            "Jeep", "Lamborghini", "Land Rover", "Maserati", "Mercedes Benz", "Mitsubishi", "Porsche", "Volvo" };

    @BindView(R.id.list_view)
    RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_cars, container, false);

        ButterKnife.bind(this, root);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewAdapter = new RecyclerViewAdapter(getContext(), CAR_BRAND);
        recyclerView.setAdapter(recyclerViewAdapter);

        return root;
    }

    @Override
    public String getFragmentName() {
        return ITEM_TEXT;
    }

    @Override
    public int getFragmentIconId() {
        return ICON_ID;
    }
}
