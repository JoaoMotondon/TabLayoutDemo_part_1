package com.motondon.tablayoutdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.motondon.tablayoutdemo_part1.R;

/**
 * Created by Joca on 4/6/2016.
 */
public class WalkFragment extends BaseFragment {

    public static final int ICON_ID = R.drawable.ic_directions_walk_white_24dp;
    public static final String ITEM_TEXT = "Walk";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_walk, container, false);

        return root;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.walk_menu, menu);
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
