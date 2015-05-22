package com.victuallist.tabfragments.fragments;


import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.victuallist.tabfragments.MainActivity;
import com.victuallist.tabfragments.R;

/**
 * Created by john on 4/15/15.
 */
public class TabFragment extends Fragment implements View.OnClickListener {

    View rootView;
    Button button;
    int position;
    String title;
    int width;
    int textSizeSM, textSizeMD, textSizeLG;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.tab_layout, container, false);

        Bundle bundle = getArguments();
        if(bundle != null){

            position = bundle.getInt("position", 1);
            title = bundle.getString("title");
            width = bundle.getInt("width");
            textSizeSM = bundle.getInt("textsizeSM");
            textSizeMD = bundle.getInt("textsizeMD");
            textSizeLG = bundle.getInt("textsizeLG");

        }

        rootView.setMinimumWidth(width);

        button = (Button) rootView.findViewById(R.id.tabButton);
        button.setTransformationMethod(null);
        button.setText(title);
        button.setTextSize(textSizeSM);
        button.setOnClickListener(this);
        button.setTextColor(getResources().getColor(R.color.tab_text_color_inactive));

        if(position == 0){
            setIndicator();
        }

        return rootView;
    }

    @Override
    public void onClick(View v) {

        ((MainActivity) getActivity()).onTabClicked(this);

    }

    @Override
    public void onResume(){
        super.onResume();
    }

    public void unsetIndicator(){

        button.setBackgroundResource(R.drawable.tab_button_background_rect_inactive);
        button.setTextColor(getResources().getColor(R.color.tab_text_color_inactive));

    }

    public void setIndicator(){

        button.setBackgroundResource(R.drawable.tab_button_background_rect_active);
        button.setTextColor(getResources().getColor(R.color.tab_text_color_active));
    }

    public int getViewX(){
        return (int) rootView.getX() - width;
    }

    public String getTitle(){
        return this.title;
    }

    public int getWidth(){
        return this.width;
    }

    public int getPosition(){
        return this.position;
    }

}
