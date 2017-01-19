package com.motondon.tablayoutdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.motondon.tablayoutdemo_part1.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Joca on 4/6/2016.
 *
 * This fragment demonstrate how to select a tab programmatically
 *
 */
public class BirthdayFragment extends BaseFragment {

    public static final int ICON_ID = R.drawable.ic_cake_white_24dp;
    public static final String ITEM_TEXT = "Birthday";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_birthday, container, false);

        ButterKnife.bind(this, root);
        return root;
    }

    @OnClick(R.id.go_to_cars_tab)
    public void navigateToPicturesTab() {
        // In a real application we should avoid accessing activity this way, but using a more decoupled way such as creating an interface and
        // implementing it in the activities, but this is out of the scope for this demo app.
        ((MainActivity)getActivity()).selectTab(CarsFragment.ITEM_TEXT);
    }

    @OnClick(R.id.go_to_chat_tab)
    public void navigateToChatTab() {
        ((MainActivity)getActivity()).selectTab(ChatFragment.ITEM_TEXT);
    }

    @OnClick(R.id.go_to_walk_tab)
    public void navigateToWalkTab() {
        ((MainActivity)getActivity()).selectTab(WalkFragment.ITEM_TEXT);
    }

    @OnClick(R.id.go_to_people_tab)
    public void navigateToPeopleTab() {
        ((MainActivity)getActivity()).selectTab(PeopleFragment.ITEM_TEXT);
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
