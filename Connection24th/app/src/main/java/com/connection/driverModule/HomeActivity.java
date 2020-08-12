package com.connection.driverModule;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ExpandableListView;
import android.widget.RelativeLayout;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.connection.MainActivity;
import com.connection.R;
import com.connection.driverModule.homedriverpkg.HomeFragmentDriver;
import com.connection.driverModule.notificationPkg.DriverNotificationActivity;
import com.connection.driverModule.riderequestPkg.RideRequestListFragment;
import com.connection.userModule.aboutusPkg.AboutUsFragment;
import com.connection.userModule.accommodationPkg.AccommodationFragment;
import com.connection.userModule.festivalsAndeventsPkg.FestivalAndEventsFragment;
import com.connection.userModule.holyPlacesPkg.HolyPlacesFragment;
import com.connection.userModule.indianstoresPkg.IndiansStoresFragment;
import com.connection.userModule.notificationPkg.NotificationFragment;
import com.connection.userModule.settingPkg.SettingFragment;
import com.connection.userModule.tiffincenterPkg.TiffinCenterFragment;
import com.connection.userModule.videosPkg.VideosFragment;
import com.connection.utility.AppSession;
import com.connection.utility.Constants;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    private static FragmentManager fragmentManager;
    AppCompatImageView ivLogoHome, crossImageId, ivnotificationId;
    List<MenuModelHomeDriver> headerList = new ArrayList<>();
    HashMap<MenuModelHomeDriver, List<MenuModelHomeDriver>> childList = new HashMap<>();
    NavigationExpandableListAdapterHomeDriver navigationExpandableListAdapter;
    ExpandableListView expandableListView;
    DrawerLayout drawer;
    NavigationView navigationView;
    private RelativeLayout rlUserProfile, rlHomeToolbarId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_driver);
        fragmentManager = getSupportFragmentManager();
        addFragment(new HomeFragmentDriver(), false, Constants.HOME_FRAGMENT);
        init();
    }

    private void init() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ivLogoHome = findViewById(R.id.ivLogoHomeId);
        expandableListView = findViewById(R.id.expandableListView);
        rlHomeToolbarId = findViewById(R.id.rlHomeToolbarId);
        ivnotificationId = findViewById(R.id.ivnotificationId);
        ivLogoHome.setOnClickListener(this);
        ivnotificationId.setOnClickListener(this);
        prepareMenuData();
        populateExpandableList();
        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        crossImageId = navigationView.getHeaderView(0).findViewById(R.id.ivclosemenuId);
        crossImageId.setOnClickListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        SwitchCompat onOffSwitch = (SwitchCompat) findViewById(R.id.switch3);
        onOffSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked) {
                    AppSession.setStringPreferences(getApplicationContext(),Constants.STATUS,"Usersign");
                    Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
                    finish();
                }
            }

        });
        // scrooling(3);
    }


    @SuppressLint("RtlHardcoded")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivLogoHomeId:
                if (drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.closeDrawer(Gravity.LEFT);
                } else {
                    drawer.openDrawer(Gravity.LEFT);
                }
                break;
            case R.id.ivclosemenuId:
                drawer.closeDrawer(Gravity.LEFT);
                break;
            case R.id.ivnotificationId:
                Intent intent = new Intent(HomeActivity.this, DriverNotificationActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
                break;

        }
    }


    private void prepareMenuData() {
        MenuModelHomeDriver menuModel = new MenuModelHomeDriver("Home", true, true, R.drawable.home); //Menu of Android Tutorial. No sub menus
        headerList.add(menuModel);
        menuModel = new MenuModelHomeDriver("Rides Request", true, true, R.drawable.rides); //Menu of Java Tutorials
        headerList.add(menuModel);
        menuModel = new MenuModelHomeDriver("Accommodation", true, true, R.drawable.accommdation); //Menu of Python Tutorials
        headerList.add(menuModel);
        menuModel = new MenuModelHomeDriver("Tiffin Centers", true, true, R.drawable.tiffincenter); //Menu of Python Tutorials
        headerList.add(menuModel);
        menuModel = new MenuModelHomeDriver("Holy Places", true, true, R.drawable.holyplace); //Menu of Python Tutorials
        headerList.add(menuModel);
        menuModel = new MenuModelHomeDriver("Festival & Events", true, true, R.drawable.festivalsevents); //Menu of Python Tutorials
        headerList.add(menuModel);
        menuModel = new MenuModelHomeDriver("Indian Stores", true, true, R.drawable.indianstores); //Menu of Python Tutorials
        headerList.add(menuModel);
        menuModel = new MenuModelHomeDriver("Video", true, true, R.drawable.video); //Menu of Python Tutorials
        headerList.add(menuModel);
        menuModel = new MenuModelHomeDriver("About", true, true, R.drawable.about); //Menu of Python Tutorials
        headerList.add(menuModel);
        menuModel = new MenuModelHomeDriver("Settings", true, true, R.drawable.settings); //Menu of Python Tutorials
        headerList.add(menuModel);
        menuModel = new MenuModelHomeDriver("Notification", true, true, R.drawable.notification); //Menu of Python Tutorials
        headerList.add(menuModel);
        menuModel = new MenuModelHomeDriver("Logout", true, true, R.drawable.logout); //Menu of Python Tutorials
        headerList.add(menuModel);
    }


    private void populateExpandableList() {
        navigationExpandableListAdapter = new NavigationExpandableListAdapterHomeDriver(this, headerList, childList);
        expandableListView.setAdapter(navigationExpandableListAdapter);
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @SuppressLint("RtlHardcoded")
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                if (headerList.get(groupPosition).isGroup) {
                    if (groupPosition == 0) {
                        drawer.closeDrawer(Gravity.LEFT);
                    } else if (groupPosition == 1) {
                        //Toast.makeText(MainActivity.this, "Under Development", Toast.LENGTH_SHORT).show();
                        // Next_Back_Screen.newScreen(MainActivity.this, OptionsBaseFragment.class);
                        drawer.closeDrawer(Gravity.LEFT);
                        Intent intent = new Intent(HomeActivity.this, RideRequestListFragment.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
                    } else if (groupPosition == 2) {
                        /*try {
                            Intent shareIntent = new Intent(Intent.ACTION_SEND);
                            shareIntent.setType("text/plain");
                            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "My application name");
                            String shareMessage = "\nLet me recommend you this application\n\n";
                            shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID + "\n\n";
                            shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                            startActivity(Intent.createChooser(shareIntent, "choose one"));
                            drawer.closeDrawer(Gravity.LEFT);
                        } catch (Exception e) {
                            //e.toString();
                        }*/
                        //  Next_Back_Screen.newScreen(MainActivity.this, OptionsBaseFragment.class);
                        // rlHomeToolbarId.setBackgroundResource(R.drawable.halfbackg);
                        drawer.closeDrawer(Gravity.LEFT);
                        removeThisFragment();
                        addFragment(new AccommodationFragment(), false, Constants.OPTIONS_BASE_FRAGMENT);
                    } else if (groupPosition == 3) {
                        //rlHomeToolbarId.setBackgroundResource(R.drawable.halfbackg);
                        drawer.closeDrawer(Gravity.LEFT);
                        removeThisFragment();
                        addFragment(new TiffinCenterFragment(), false, Constants.TIFFIN_CENTER_FRAGMENT);
                        // Next_Back_Screen.newScreen(MainActivity.this, TiffinCenterFragment.class);
                    } else if (groupPosition == 4) {
                        // rlHomeToolbarId.setBackgroundResource(R.drawable.halfbackg);
                        drawer.closeDrawer(Gravity.LEFT);
                        removeThisFragment();
                        addFragment(new HolyPlacesFragment(), false, Constants.HOLY_PLACE_FRAGMENT);
                    } else if (groupPosition == 5) {
                        // rlHomeToolbarId.setBackgroundResource(R.drawable.halfbackg);
                        drawer.closeDrawer(Gravity.LEFT);
                        removeThisFragment();
                        addFragment(new FestivalAndEventsFragment(), false, Constants.FESTIVAL_AND_EVENTS_FRAGMENT);
                    } else if (groupPosition == 6) {
                        // rlHomeToolbarId.setBackgroundResource(R.drawable.halfbackg);
                        removeThisFragment();
                        addFragment(new IndiansStoresFragment(), false, Constants.INDIAN_STORE_FRAGMENT);
                        drawer.closeDrawer(Gravity.LEFT);
                    } else if (groupPosition == 7) {
                        // rlHomeToolbarId.setBackgroundResource(R.drawable.halfbackg);
                        removeThisFragment();
                        addFragment(new VideosFragment(), false, Constants.VIDEOS_FRAGMENT);
                        drawer.closeDrawer(Gravity.LEFT);
                    } else if (groupPosition == 8) {
                        // rlHomeToolbarId.setBackgroundResource(R.drawable.halfbackg);
                        removeThisFragment();
                        addFragment(new AboutUsFragment(), false, Constants.ABOUT_US_FRAGMENT);
                        drawer.closeDrawer(Gravity.LEFT);
                    } else if (groupPosition == 9) {
                        //  rlHomeToolbarId.setBackgroundResource(R.drawable.hometoolbar);
                        removeThisFragment();
                        addFragment(new SettingFragment(), false, Constants.SETTING_FRAGMENT);
                        drawer.closeDrawer(Gravity.LEFT);
                    } else if (groupPosition == 10) {
                        removeThisFragment();
                        addFragment(new NotificationFragment(), false, Constants.NOTIFICATION_FRAGMENT);
                        drawer.closeDrawer(Gravity.LEFT);
                    } else if (groupPosition == 11) {
                        //log_out();
                        drawer.closeDrawer(Gravity.LEFT);
                    }
                }
                return false;
            }
        });
    }


    public void addFragment(Fragment fragment, boolean addToBackStack,
                            String tag) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        ft.addToBackStack(null);
        if (addToBackStack) {
            ft.addToBackStack(tag);
        }
        ft.replace(R.id.flHomeId, fragment, tag);
        ft.commitAllowingStateLoss();
    }

    @Override
    public void onBackPressed() {
        Fragment HOME_FRAGMENT = fragmentManager.findFragmentByTag(Constants.HOME_FRAGMENT);
        Fragment RIDES_SEARCH_FRAGMENT = fragmentManager.findFragmentByTag(Constants.RIDES_SEARCH_FRAGMENT);
        Fragment OPTIONS_BASE_FRAGMENT = fragmentManager.findFragmentByTag(Constants.OPTIONS_BASE_FRAGMENT);
        Fragment TIFFIN_CENTER_FRAGMENT = fragmentManager.findFragmentByTag(Constants.TIFFIN_CENTER_FRAGMENT);
        Fragment HOLY_PLACE_FRAGMENT = fragmentManager.findFragmentByTag(Constants.HOLY_PLACE_FRAGMENT);
        Fragment FESTIVAL_AND_EVENTS_FRAGMENT = fragmentManager.findFragmentByTag(Constants.FESTIVAL_AND_EVENTS_FRAGMENT);
        Fragment INDIAN_STORE_FRAGMENT = fragmentManager.findFragmentByTag(Constants.INDIAN_STORE_FRAGMENT);
        Fragment VIDEOS_FRAGMENT = fragmentManager.findFragmentByTag(Constants.VIDEOS_FRAGMENT);
        Fragment ABOUT_US_FRAGMENT = fragmentManager.findFragmentByTag(Constants.ABOUT_US_FRAGMENT);
        Fragment SETTING_FRAGMENT = fragmentManager.findFragmentByTag(Constants.SETTING_FRAGMENT);
        Fragment NOTIFICATION_FRAGMENT = fragmentManager.findFragmentByTag(Constants.NOTIFICATION_FRAGMENT);
        // Fragment MY_MISSION_DISPUTE_ACTIVITY = fragmentManager.findFragmentByTag(Constants.MY_MISSION_DISPUTE_ACTIVITY);
        if (HOME_FRAGMENT != null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Are you sure you want to exit the activity?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            finishAffinity();
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
        } else if (RIDES_SEARCH_FRAGMENT != null) {
            addFragment(new HomeFragmentDriver(), false, Constants.HOME_FRAGMENT);
        } else if (OPTIONS_BASE_FRAGMENT != null) {
            addFragment(new HomeFragmentDriver(), false, Constants.HOME_FRAGMENT);
        } else if (TIFFIN_CENTER_FRAGMENT != null) {
            addFragment(new HomeFragmentDriver(), false, Constants.HOME_FRAGMENT);
        } else if (HOLY_PLACE_FRAGMENT != null) {
            addFragment(new HomeFragmentDriver(), false, Constants.HOME_FRAGMENT);
        } else if (FESTIVAL_AND_EVENTS_FRAGMENT != null) {
            addFragment(new HomeFragmentDriver(), false, Constants.HOME_FRAGMENT);
        } else if (INDIAN_STORE_FRAGMENT != null) {
            addFragment(new HomeFragmentDriver(), false, Constants.HOME_FRAGMENT);
        } else if (VIDEOS_FRAGMENT != null) {
            addFragment(new HomeFragmentDriver(), false, Constants.HOME_FRAGMENT);
        } else if (ABOUT_US_FRAGMENT != null) {
            addFragment(new HomeFragmentDriver(), false, Constants.HOME_FRAGMENT);
        } else if (SETTING_FRAGMENT != null) {
            addFragment(new HomeFragmentDriver(), false, Constants.HOME_FRAGMENT);
        } else if (NOTIFICATION_FRAGMENT != null) {
            addFragment(new HomeFragmentDriver(), false, Constants.HOME_FRAGMENT);
        }


    }

    public void removeThisFragment() {
        final FragmentManager manager = getSupportFragmentManager();
        manager.popBackStackImmediate();
    }

}
