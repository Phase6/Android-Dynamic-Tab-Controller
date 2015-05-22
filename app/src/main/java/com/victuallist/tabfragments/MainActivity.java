package com.victuallist.tabfragments;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.victuallist.tabfragments.adapters.CustomPagerAdapter;
import com.victuallist.tabfragments.controllers.TabAndContentController;
import com.victuallist.tabfragments.fragments.FifthContentFragment;
import com.victuallist.tabfragments.fragments.FirstContentFragment;
import com.victuallist.tabfragments.fragments.FourthContentFragment;
import com.victuallist.tabfragments.fragments.SecondContentFragment;
import com.victuallist.tabfragments.fragments.TabFragment;
import com.victuallist.tabfragments.fragments.ThirdContentFragment;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity implements ViewPager.OnPageChangeListener {

    TabAndContentController tcController;
    LinearLayout tabContainer;
    private static int TAB_VIEW_ID;
    HorizontalScrollView tabScrollView;
    ViewPager contentPager;
    PagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Get the tab container and its id
        tabContainer = (LinearLayout) findViewById(R.id.tabContainer);
        TAB_VIEW_ID = tabContainer.getId();

        //Get scroll view containing tab container for scrolling
        tabScrollView = (HorizontalScrollView) findViewById(R.id.tabScrollView);
        //Get ViewPager for content fragments
        contentPager = (ViewPager) findViewById(R.id.viewpager);

        //Get display width - used to determine the width of each tab
        WindowManager wm = (WindowManager) getSystemService((Context.WINDOW_SERVICE));
        Display display = wm.getDefaultDisplay();
        int displayWidth = display.getWidth();

        //Instantiate the TabAndContentController
        //it takes the display width as a parameter to set the width of the tabs
        tcController = new TabAndContentController(displayWidth);

        setTabAndContentController();

        setTabs();

        setContentFragments();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void setTabAndContentController(){

        //In this function, instantiate the content fragments.
        //Pass the content fragments along with the corresponding
        //tab title to the TabAndContentController

        Fragment firstContentFragment = new FirstContentFragment();

        tcController.addTabAndContent("First", firstContentFragment);

        Fragment secondContentFragment = new SecondContentFragment();

        tcController.addTabAndContent("Second", secondContentFragment);

        Fragment thirdContentFragment = new ThirdContentFragment();

        tcController.addTabAndContent("Third", thirdContentFragment);

        Fragment fourthContentFragment = new FourthContentFragment();

        tcController.addTabAndContent("Fourth", fourthContentFragment);

        Fragment fifthContentFragment = new FifthContentFragment();

        tcController.addTabAndContent("Fifth", fifthContentFragment);

    }

    public void setTabs(){

        //This function adds the TabFragments created in the TabAndContentController
        //to the tabContainer
        for(int i = 0; i < tcController.getAllTabFragments().size(); i++){

            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add(TAB_VIEW_ID, tcController.getAllTabFragments().get(i), "tabfragment");
            ft.commit();

        }

    }

    public void setContentFragments(){
        //This function gets the content fragments from the TabAndContentController
        //and adds them to ViewPager as well as set the adapter for the ViewPager
        //and the on page change listener
        mPagerAdapter = new CustomPagerAdapter(getSupportFragmentManager(), tcController.getAllContentFragments());
        contentPager.setAdapter(mPagerAdapter);
        contentPager.setOnPageChangeListener(this);

    }

    public void onTabClicked(TabFragment fragment){
        //This function is called directly from the TabFragments.
        //Using the position of the TabFragment, the ViewPager is advanced
        //to the corresponding content fragment
        contentPager.setCurrentItem(fragment.getPosition());

    }

    public void setTabIndicatorAndScroll(TabFragment fragment){

        //First, set all tabs to "inactive"

        for(int i = 0; i < tcController.getAllTabFragments().size(); i++){

            tcController.getAllTabFragments().get(i).unsetIndicator();

        }

        //Second, set the selected tab or tab related to selected content to active
        fragment.setIndicator();
        //Third, move the scroll view so that the selected tab is centered, if possible,
        //to the center of the screen
        tabScrollView.smoothScrollTo(fragment.getViewX(), 0);

    }

    @Override
    public void onPageScrolled(int i, float v, int i2) {

    }

    @Override
    public void onPageSelected(int i) {
        //When the ViewPager changes content, activate and
        //display the corresponding tab
        setTabIndicatorAndScroll(tcController.getTabFragment(i));
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }
}
