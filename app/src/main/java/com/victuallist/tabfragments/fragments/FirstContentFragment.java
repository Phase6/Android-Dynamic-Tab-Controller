package com.victuallist.tabfragments.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.victuallist.tabfragments.R;

/**
 * Created by john on 5/21/15.
 */
public class FirstContentFragment extends Fragment {

    View rootView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.first_content_fragment, container, false);

        return rootView;

    }


}
