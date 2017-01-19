package com.motondon.tablayoutdemo;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.motondon.tablayoutdemo_part1.R;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.motondon.tablayoutdemo_part1.R.id.itemText;

/**
 * This project is intended to demonstrate how to use Material Design TabLayout.
 *
 * It creates some pre-defined fragments and adds them to a viewPager. Also it will show how to:
 *   - Change tabs icon position
 *   - Change tabs style
 *   - Change tab indicator size
 *   - Change tab indicator height
 *
 * It also shows how to handle orientation change in order to keep all options changed by the user after a screen rotate
 *
 */
public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    private static final String CURRENT_ICON_POSITION = "CURRENT_ICON_POSITION";
    private static final String CURRENT_TAB_STYLE = "CURRENT_TAB_STYLE";
    private static final String CURRENT_TAB_INDICATOR_COLOR = "CURRENT_TAB_INDICATOR_COLOR";
    private static final String CURRENT_TAB_INDICATOR_SIZE = "CURRENT_TAB_INDICATOR_SIZE";

    @BindView(R.id.coordinationLayout) CoordinatorLayout mCoordinationLayout;
    @BindView(R.id.tabanim_toolbar) Toolbar toolbar;
    @BindView(R.id.tabanim_tabs) TabLayout tabLayout;
    @BindView(R.id.tabanim_viewpager) ViewPager viewPager;
    @BindView(R.id.app_bar) AppBarLayout appBarLayout;

    private ViewPagerAdapter viewPagerAdapter;

    // These flags are used to hold the current menu options for each group. They will be used during an orientation change.
    private int mCurrentIconPosition;
    private int mCurrentTabStyle;
    private int mCurrentTabIndicatorColor;
    private int mCurrentTabIndicatorSize;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate()");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        //Create our ViewPagerAdapter and add some fragments to it.
        setupViewPager(viewPager);

        // Assigns the ViewPager to TabLayout
        tabLayout.setupWithViewPager(viewPager);

        if (savedInstanceState == null) {
            // These are the default values we set on our XML menu file.
            mCurrentIconPosition = R.id.menu_icon_position_no_icon;
            mCurrentTabStyle = R.id.menu_scrollable_tab;
            mCurrentTabIndicatorColor = R.id.menu_tab_indicator_white;
            mCurrentTabIndicatorSize = R.id.menu_tab_indicator_medium;

            // Set tab indicator color to white by default
            tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#FFFFFF"));

            // Set tab indicator to medium by default
            tabLayout.setSelectedTabIndicatorHeight((int) (3 * getResources().getDisplayMetrics().density));

        } else {

            // savedInstanceState is not null? Retrieve all saved configuration such as icon position, tab indicator color, etc.
            mCurrentIconPosition = savedInstanceState.getInt(CURRENT_ICON_POSITION);
            mCurrentTabStyle = savedInstanceState.getInt(CURRENT_TAB_STYLE);
            mCurrentTabIndicatorColor = savedInstanceState.getInt(CURRENT_TAB_INDICATOR_COLOR);
            mCurrentTabIndicatorSize = savedInstanceState.getInt(CURRENT_TAB_INDICATOR_SIZE);
        }

        setupTabs();

        // When using custom views tabs, we need to provide a listener in order to change icon and caption color based on whether they are selected or not.
        setupTabListener();

        if (savedInstanceState == null) {
            // Select by default the first tab.
            TabLayout.Tab tab = tabLayout.getTabAt(0);
            tab.select();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {

        // Save all settings such as tab style, icon position etc, during an screen rotate.
        savedInstanceState.putInt(CURRENT_ICON_POSITION, mCurrentIconPosition);
        savedInstanceState.putInt(CURRENT_TAB_STYLE, mCurrentTabStyle);
        savedInstanceState.putInt(CURRENT_TAB_INDICATOR_COLOR, mCurrentTabIndicatorColor);
        savedInstanceState.putInt(CURRENT_TAB_INDICATOR_SIZE, mCurrentTabIndicatorSize);

        super.onSaveInstanceState(savedInstanceState);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	Log.d(TAG, "onCreateOptionsMenu()");
    	
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);

        MenuItem menuItem = menu.findItem(mCurrentIconPosition);
        menuItem.setChecked(true);

        menuItem = menu.findItem(mCurrentTabStyle);
        menuItem.setChecked(true);

        menuItem = menu.findItem(mCurrentTabIndicatorColor);
        menuItem.setChecked(true);

        menuItem = menu.findItem(mCurrentTabIndicatorSize);
        menuItem.setChecked(true);

        return true;
    }
    
    /**
     * When user change any property on the menu, this method will be called. Then it will set some properties and call setupTabs() method,
     * which will take care of configure tabs according to the user's choice.
     * 
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d(TAG, "onOptionsItemSelected() - itemId: " + item.getItemId());

        switch (item.getItemId()) {
            case R.id.menu_icon_position_top:
            	Log.d(TAG, "onOptionsItemSelected() -R.id.menu_icon_position_top");
                mCurrentIconPosition = R.id.menu_icon_position_top;
                break;

            case R.id.menu_icon_position_right:
            	Log.d(TAG, "onOptionsItemSelected() - R.id.menu_icon_position_right");
            	mCurrentIconPosition = R.id.menu_icon_position_right;
                break;

            case R.id.menu_icon_position_bottom:
            	Log.d(TAG, "onOptionsItemSelected() - R.id.menu_icon_position_bottom");
            	mCurrentIconPosition = R.id.menu_icon_position_bottom;
                break;

            case R.id.menu_icon_position_left:
            	Log.d(TAG, "onOptionsItemSelected() - R.id.menu_icon_position_left");
            	mCurrentIconPosition = R.id.menu_icon_position_left;
                break;

            case R.id.menu_icon_position_no_icon:
            	Log.d(TAG, "onOptionsItemSelected() -R.id.menu_icon_position_no_icon");
            	mCurrentIconPosition = R.id.menu_icon_position_no_icon;
                break;

            case R.id.menu_icon_position_only_icon:
            	Log.d(TAG, "onOptionsItemSelected() - R.id.menu_icon_position_only_icon");
            	mCurrentIconPosition = R.id.menu_icon_position_only_icon;
                break;

            case R.id.menu_fixed_fill_tab:
            	Log.d(TAG, "onOptionsItemSelected() - R.id.menu_fixed_fill_tab");
            	mCurrentTabStyle = R.id.menu_fixed_fill_tab;
                break;

            case R.id.menu_fixed_center_tab:
            	Log.d(TAG, "onOptionsItemSelected() - R.id.menu_fixed_center_tab");
            	mCurrentTabStyle = R.id.menu_fixed_center_tab;
                break;

            case R.id.menu_scrollable_tab:
            	Log.d(TAG, "onOptionsItemSelected() - R.id.menu_scrollable_tab");
            	mCurrentTabStyle = R.id.menu_scrollable_tab;
                break;

            case R.id.menu_tab_indicator_white:
            	Log.d(TAG, "onOptionsItemSelected() - R.id.menu_tab_indicator_white");
            	mCurrentTabIndicatorColor = R.id.menu_tab_indicator_white;
            	break;
                
            case R.id.menu_tab_indicator_black:
            	Log.d(TAG, "onOptionsItemSelected() - R.id.menu_tab_indicator_black");
            	mCurrentTabIndicatorColor  =R.id.menu_tab_indicator_black;
            	break;
            	
            case R.id.menu_tab_indicator_small:
            	Log.d(TAG, "onOptionsItemSelected() - R.id.menu_tab_indicator_small");
            	mCurrentTabIndicatorSize=R.id.menu_tab_indicator_small;
            	break;
            	
            case R.id.menu_tab_indicator_medium:
            	Log.d(TAG, "onOptionsItemSelected() - R.id.menu_tab_indicator_medium");
            	mCurrentTabIndicatorSize= R.id.menu_tab_indicator_medium;
            	break;
            	
            case R.id.menu_tab_indicator_large:
            	Log.d(TAG, "onOptionsItemSelected() - R.id.menu_tab_indicator_large");
            	mCurrentTabIndicatorSize= R.id.menu_tab_indicator_large;
            	break;
            	
            default:
                return super.onOptionsItemSelected(item);
        }

        // Force selected item to be checked
        item.setChecked(true);

        // And setup tab properties according to the user choice
        setupTabs();

        return true;
    }

    /**
     * This method basically centralize all changes for the tabs, such as tabStyle, tabIndicatoSize, tabIndicatorColor as well as icon positioning.
     *
     */
    private void setupTabs() {
        Log.d(TAG, "setupTabs()");

        LinearLayout tabStrip = (LinearLayout) tabLayout.getChildAt(0);

        // First clean up any customView that might exist
        for (int i = 0; i < tabStrip.getChildCount(); i++) {
            tabLayout.getTabAt(i).setCustomView(null);
        }
        
        // Set tab style according to the user's choice
        switch (mCurrentTabStyle) {
		    case R.id.menu_fixed_fill_tab:
		        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
		        tabLayout.setTabMode(TabLayout.MODE_FIXED);
		        break;
		
		    case R.id.menu_fixed_center_tab:
		    	tabLayout.setTabGravity(TabLayout.GRAVITY_CENTER);
		        tabLayout.setTabMode(TabLayout.MODE_FIXED);
		        break;
		
		    case R.id.menu_scrollable_tab:
		        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
		        break;
        }
        
        // Set tab indicator color according to the user's choice
        switch (mCurrentTabIndicatorColor) {
		    case R.id.menu_tab_indicator_white:
		    	tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#FFFFFF"));
		    	break;
		        
		    case R.id.menu_tab_indicator_black:
		    	tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#000000"));
		    	break;
        }	   
        
        // Set tab indicator size according to the user's choice
        switch(mCurrentTabIndicatorSize) {
		    case R.id.menu_tab_indicator_small:
		    	tabLayout.setSelectedTabIndicatorHeight((int) (1 * getResources().getDisplayMetrics().density));
		    	break;
		    	
		    case R.id.menu_tab_indicator_medium:
		    	tabLayout.setSelectedTabIndicatorHeight((int) (3 * getResources().getDisplayMetrics().density));
		    	break;
		    	
		    case R.id.menu_tab_indicator_large:
		    	tabLayout.setSelectedTabIndicatorHeight((int) (6 * getResources().getDisplayMetrics().density));
		    	break;
        }        
        
        // Set tab icon position according to the user's choice
        switch (mCurrentIconPosition) {
            case R.id.menu_icon_position_top:
                for (int i = 0; i < tabStrip.getChildCount(); i++) {
                    BaseFragment frag = ((BaseFragment)viewPagerAdapter.getItem(i));
                    setCustomTab(i, frag, R.layout.icon_position_top);
                }
                break;

            case R.id.menu_icon_position_right:
                for (int i = 0; i < tabStrip.getChildCount(); i++) {
                    BaseFragment frag = ((BaseFragment)viewPagerAdapter.getItem(i));
                    setCustomTab(i, frag, R.layout.icon_position_right);
                }
                break;

            case R.id.menu_icon_position_bottom:
                for (int i = 0; i < tabStrip.getChildCount(); i++) {
                    BaseFragment frag = ((BaseFragment)viewPagerAdapter.getItem(i));
                    setCustomTab(i, frag, R.layout.icon_position_bottom);
                }
                break;

            case R.id.menu_icon_position_left:
                for (int i = 0; i < tabStrip.getChildCount(); i++) {
                    BaseFragment frag = ((BaseFragment)viewPagerAdapter.getItem(i));
                    setCustomTab(i, frag, R.layout.icon_position_left);
                }
                break;

            case R.id.menu_icon_position_no_icon:
                for (int i = 0; i < tabStrip.getChildCount(); i++) {
                    Log.d(TAG, "setupTabs() - NO ICON - Fragment: " + ((BaseFragment)viewPagerAdapter.getItem(i)).getFragmentName());
                    tabLayout.getTabAt(i).setText(((BaseFragment)viewPagerAdapter.getItem(i)).getFragmentName());
                    tabLayout.getTabAt(i).setIcon(null);
                }
                break;

            case R.id.menu_icon_position_only_icon:
                for (int i = 0; i < tabStrip.getChildCount(); i++) {
                    Log.d(TAG, "setupTabs() - ICON ONLY - Fragment: " + ((BaseFragment)viewPagerAdapter.getItem(i)).getFragmentName());
                    tabLayout.getTabAt(i).setIcon(((BaseFragment)viewPagerAdapter.getItem(i)).getFragmentIconId());
                    tabLayout.getTabAt(i).setText("");
                }
                break;
        }
    }

    /**
     * This is a helper method which will set custom tabs. Basically it sets the icon, the caption as well as the views colors
     * based on whether tab is selected or not.
     *
     * @param tabPosition
     * @param baseFragment
     * @param layout
     * @return
     */
    private void setCustomTab(int tabPosition, BaseFragment baseFragment, int layout) {
        Log.d(TAG, "setCustomTab() - tabPosition: ");

        View customView;
        ImageView icon;
        TextView caption;
        int tabIconColor;

        int iconId = baseFragment.getFragmentIconId();
        String itemText = baseFragment.getFragmentName();

        // First inflate custom layout
        customView = getLayoutInflater().inflate(layout, null);
        icon = (ImageView) customView.findViewById(R.id.itemImage);
        caption = (TextView) customView.findViewById(R.id.itemText);

        // Then, set the icon and the text to it.
        icon.setImageResource(iconId);
        caption.setText(itemText);

        // When changing icon position type, we need this conditional block here in order to keep the right color for all
        // unselected tab as well as for the selected one.
        if (tabLayout.getSelectedTabPosition() == tabPosition) {
            // Is this the current selected tab? Set its icon and text color to white...
            tabIconColor = ContextCompat.getColor(getApplicationContext(), android.R.color.white);
            icon.setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN);
            caption.setTextColor(ContextCompat.getColor(getApplicationContext(), android.R.color.white));
        } else {
            // ... otherwise change icon and text color to R.color.unselected_tab color
            tabIconColor = ContextCompat.getColor(getApplicationContext(), R.color.unselected_tab);
            icon.setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN);
            caption.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.unselected_tab));
        }

        // After create a custom tab, add it to the TabLayout.
        tabLayout.getTabAt(tabPosition).setCustomView(customView);
    }


    /**
     * For custom views tabs, we need to provide a listener in order to change icon and caption color based on whether they are
     * selected or not.
     *
     */
    private void setupTabListener() {
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            // When selecting a tab, set its icon and caption color to white
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());

                View customView = tab.getCustomView();

                // customView will be null when user selects NO_ICON or ONLY_ICON types. For such case there is nothing to do here, since
                // we are using system colors for selected/unselected tabs (and this is handled by the system).
                if (customView != null) {
                    ImageView icon = (ImageView) customView.findViewById(R.id.itemImage);
                    TextView caption = (TextView) customView.findViewById(itemText);

                    int tabIconColor = ContextCompat.getColor(getApplicationContext(), android.R.color.white);
                    icon.setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN);
                    caption.setTextColor(ContextCompat.getColor(getApplicationContext(), android.R.color.white));
                }

                // Force appBar to be expanded whenever a tab is selected. This is useful when appBar is collapsed and we select a tab
                // with no list (or a list with just a few items). On that case, without this line, appBar would keep collapsed until we
                // select back to the first tab and scroll the list down.
                appBarLayout.setExpanded(true, true);
            }

            // When un-selecting a tab, set its icon and caption color to the R.color.unselected_tab color
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                View customView = tab.getCustomView();

                // customView will be null when user selects NO_ICON or ONLY_ICON types. For such case there is nothing to do here, since
                // we are using system colors for selected/unselected tabs (and this is handled by the system).
                if (customView != null) {
                    ImageView icon = (ImageView) customView.findViewById(R.id.itemImage);
                    TextView caption = (TextView) customView.findViewById(itemText);

                    int tabIconColor = ContextCompat.getColor(getApplicationContext(), R.color.unselected_tab);
                    icon.setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN);
                    caption.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.unselected_tab));
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                // Nothing to do here.
            }
        });
    }

    /**
     * This method is responsible to init the view pager adapter and add all the fragments to it.
     * @param viewPager
     *
     */
    private void setupViewPager(ViewPager viewPager) {
        Log.d(TAG, "setupViewPager()");
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        viewPagerAdapter.addTabPage(new BirthdayFragment());
        viewPagerAdapter.addTabPage(new CarsFragment());
        viewPagerAdapter.addTabPage(new ChatFragment());
        viewPagerAdapter.addTabPage(new WalkFragment());
        viewPagerAdapter.addTabPage(new PeopleFragment());
        viewPagerAdapter.addTabPage(new PicturesFragment());
        viewPagerAdapter.addTabPage(new CallsFragment());
        viewPagerAdapter.addTabPage(new VideosFragment());

        viewPager.setAdapter(viewPagerAdapter);
    }

    /**
     * This is a helper method used only for the BirthdayFragment which contains some links that allows selecting tabs
     * programmatically. Since we known the tab name we want to select, we just iterate over all tabs and select that
     * one we want.
     *
     * @param fragName
     */
    public void selectTab(String fragName) {
        Log.d(TAG, "selectTab() - fragName: " + fragName);

        LinearLayout tabStrip = (LinearLayout) tabLayout.getChildAt(0);
        for (int i = 0; i < tabStrip.getChildCount(); i++) {
            BaseFragment frag = ((BaseFragment)viewPagerAdapter.getItem(i));

            if (frag.getFragmentName().equals(fragName)) {
                TabLayout.Tab tab = tabLayout.getTabAt(i);
                tab.select();
                Log.d(TAG, "selectTab() - selecting fragment: " + fragName);

                break;
            }
        }
    }
}
