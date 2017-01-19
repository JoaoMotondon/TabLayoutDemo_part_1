package com.motondon.tablayoutdemo;

import android.support.v4.app.Fragment;

import java.io.Serializable;

/**
 * Created by Joca on 12/20/2016.
 */

public abstract class BaseFragment extends Fragment implements Serializable {

    public abstract String getFragmentName();
    public abstract int getFragmentIconId();
}
