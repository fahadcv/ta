package com.andriod.tailorassist;

import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MeasurementsEntryActivity extends FragmentActivity implements ActionBar.TabListener {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide fragments for each of the
     * sections. We use a {@link android.support.v4.app.FragmentPagerAdapter} derivative, which will
     * keep every loaded fragment in memory. If this becomes too memory intensive, it may be best
     * to switch to a {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    ViewPager mViewPager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
    	try{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_measurements_entry);
        // Create the adapter that will return a fragment for each of the three primary sections
        // of the app.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the action bar.
        final ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        
        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        // When swiping between different sections, select the corresponding tab.
        // We can also use ActionBar.Tab#select() to do this if we have a reference to the
        // Tab.
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
            }
        });

        // For each of the sections in the app, add a tab to the action bar.
        for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
            // Create a tab with text corresponding to the page title defined by the adapter.
            // Also specify this Activity object, which implements the TabListener interface, as the
            // listener for when this tab is selected.
        	Tab tab = actionBar.newTab();
        	tab.setText(mSectionsPagerAdapter.getPageTitle(i));
//        	tab.setIcon(icon)
        	tab.setTabListener(this);
            actionBar.addTab(tab);
        }
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    }

    public void extraBtnclick(View v){
    	
    	Button clickedBtn = (Button)findViewById(v.getId());    	
    	String btnTxt = clickedBtn.getText().toString();
    	
    	if(btnTxt.contentEquals("Down")){
    		
    		btnTxt = "\n";
    	}
    	
    	EditText measurementField;
    	
    	measurementField = (EditText)findViewById(R.id.editText_shirtMeasurement);    				
    	
    	/*String currentMesurementText = measurementField.getText().toString();
    	currentMesurementText = currentMesurementText + btnTxt+"\t";  */ 
    	
    	measurementField.append(btnTxt + "\t"); 	
    	
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_measurements_entry, menu);
        return true;
    }

    

    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        // When the given tab is selected, switch to the corresponding page in the ViewPager.
        mViewPager.setCurrentItem(tab.getPosition());
        
    }

    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    	
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to one of the primary
     * sections of the app.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {
    	List<Fragment> tabs;
        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
            tabs = new ArrayList<Fragment>(3);
            tabs.add(new MeasurmentFragment(getString(R.string.measurment_tab1_text)));
            tabs.add(new MeasurmentFragment(getString(R.string.measurement_tab2_text)));
            tabs.add(new MeasurmentFragment(getString(R.string.measuremnet_tab3_text)));
        }

        @Override
        public Fragment getItem(int i) {

        	MeasurmentFragment fragment = (MeasurmentFragment)tabs.get(i);
//        	EditText measurementField;
//        	
//        	measurementField = (EditText)findViewById(R.id.editText_shirtMeasurement);    				
//        	measurementField.setHint(fragment.getCaption());
            return fragment;
        }

        @Override
        public int getCount() {
            return tabs.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
        	
            switch (position) {
                case 0: return getString(R.string.measurment_tab1_text);
                case 1: return getString(R.string.measurement_tab2_text);
                case 2: return getString(R.string.measuremnet_tab3_text);
            }
            return null;
        }
    }


}
