package com.victuallist.tabfragments.controllers;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.victuallist.tabfragments.fragments.TabFragment;

import java.util.ArrayList;

/**
 * Created by john on 5/21/15.
 */
public class TabAndContentController {

    int displayWidth;
    int tabWidth;
    int NUMBER_OF_TABS_TO_DISPLAY = 3;

    ArrayList<TabFragment> tabFragments = new ArrayList<>();
    ArrayList<Fragment> contentFragments = new ArrayList<>();

    int textWidthSM, textWidthMD, textWidthLG;
    double textModifierSM = .02;
    double textModifierMD = .0225;
    double textModifierLG = .025;


    public TabAndContentController(int dWidth){

        this.displayWidth = dWidth;

        //Using the display width, the constructor determines the width of each tab
        //the default number of tabs to be visible on screen at a time is 3
        this.tabWidth = (int) (displayWidth / NUMBER_OF_TABS_TO_DISPLAY);

        if(displayWidth > 999){
            textModifierSM = .015;
            textModifierMD = .0175;
            textModifierLG = .02;
        }

        //Using the display width, the constructor also set some sizes for fonts
        //this can be manipulated so ensure that tab titles fit inside the tabs
        textWidthSM = (int) (displayWidth * textModifierSM);
        textWidthMD = (int) (displayWidth * textModifierMD);
        textWidthLG = (int) (displayWidth * textModifierLG);


    }

    public void addTabAndContent(String tabTitle, Fragment content){

        //This function receives the tab title and the associated
        //content fragment.  It determines the tab position based
        //on the size of the tabFragment array list

        int tabPosition = tabFragments.size();

        //Instantiate a tab fragment with the tab title received
        //the tab position determined above and the tabWidth and text
        //sizes established when the TabAndContentController was instantiated
        TabFragment tabToCreate = new TabFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("position", tabPosition);
        bundle.putString("title", tabTitle);
        bundle.putInt("width", tabWidth);
        bundle.putInt("textsizeSM", textWidthSM);
        bundle.putInt("textsizeMD", textWidthMD);
        bundle.putInt("textsizeLG", textWidthLG);
        tabToCreate.setArguments(bundle);

        //Add the instantiated tab fragment to the tab ArrayList
        tabFragments.add(tabToCreate);
        //Add the content fragment to the content ArrayList
        contentFragments.add(content);

    }

    public TabFragment getTabFragment(int position){
        return tabFragments.get(position);
    }

    public Fragment getContentFragment(int position){
        return contentFragments.get(position);
    }

    public ArrayList<TabFragment> getAllTabFragments(){
        return tabFragments;
    }

    public ArrayList<Fragment> getAllContentFragments(){
        return contentFragments;
    }

}
