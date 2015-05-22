# Android-Dynamic-Tab-Controller
Basic structure for an Android tab layout

This project is the basic structure for a tab-based layout for Android.  I created it because I wanted a simple way to have my app generate tabs dynamically on start up.  

The tabs in this project are fragments.  Each tab's position is synced with a content fragment's position (displayed through a ViewPager) through my TabAndContentController class.  

The tabs can scroll independently from the swiping action of the ViewPager.  

Clicking a tab will cycle the ViewPager to the approriate content fragment.

Swiping the ViewPager will activate and scroll to the approriate tab fragment.


