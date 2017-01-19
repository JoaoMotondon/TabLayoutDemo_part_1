package com.motondon.tablayoutdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.motondon.tablayoutdemo_part1.R;

/**
 * Created by Joca on 4/6/2016.
 */
public class ChatFragment extends BaseFragment {

    public static final int ICON_ID = R.drawable.ic_chat_white_24dp;
    public static final String ITEM_TEXT = "Chat";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_chat, container, false);

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
