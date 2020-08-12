package com.connection;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.connection.authenticationPkg.SplashActivity;
import com.connection.driverModule.HomeActivity;
import com.connection.driverModule.driverPkgdetails.DriverRegistrationFragment;
import com.connection.driverModule.driverPkgdetails.StartSharingFragment;
import com.connection.driverModule.homedriverpkg.HomeFragmentDriver;
import com.connection.driverModule.riderequestPkg.RideRequestListFragment;
import com.connection.userModule.aboutusPkg.AboutUsFragment;
import com.connection.userModule.accommodationPkg.AccommodationFragment;
import com.connection.userModule.festivalsAndeventsPkg.FestivalAndEventsFragment;
import com.connection.userModule.holyPlacesPkg.HolyPlacesFragment;
import com.connection.userModule.indianstoresPkg.IndiansStoresFragment;
import com.connection.userModule.notificationPkg.NotificationFragment;
import com.connection.userModule.ridesPkg.RidesSarchFragment;
import com.connection.userModule.ridesPkg.RidesSearchFragmentSecond;
import com.connection.userModule.settingPkg.SettingFragment;
import com.connection.userModule.tiffincenterPkg.TiffinCenterFragment;
import com.connection.userModule.userProfilePkg.UserProfileActivity;
import com.connection.userModule.videosPkg.VideosFragment;
import com.connection.utility.AppSession;
import com.connection.utility.Constants;
import com.connection.utility.Next_Back_Screen;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,
        HomeFragmentDriver.NotificationInterFace,
        StartSharingFragment.AddTitleInterFace {
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    private static FragmentManager fragmentManager;
    AppCompatImageView ivLogoHome,ivLogoHomeId_second, crossImageId;
    List<MenuModel> headerList = new ArrayList<>();
    HashMap<MenuModel, List<MenuModel>> childList = new HashMap<>();
    NavigationExpandableListAdapter navigationExpandableListAdapter;
    ExpandableListView expandableListView;
    DrawerLayout drawer;
    NavigationView navigationView;
    private RelativeLayout rlUserProfile, rlHomeToolbarId, rlNotification;
    private String status;
    public AppCompatTextView tvToolbarTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        addFragment(new HomeFragment(), false, Constants.HOME_FRAGMENT);
        init();
        status = AppSession.getStringPreferences(MainActivity.this, Constants.STATUS);
    }


    private void init() {
        tvToolbarTitle = findViewById(R.id.tvToolbarTitleId);
        rlUserProfile = findViewById(R.id.rlUserProfileId);
        rlNotification = findViewById(R.id.rlNotificationId);
        rlUserProfile.setOnClickListener(this);
        rlNotification.setOnClickListener(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ivLogoHome = findViewById(R.id.ivLogoHomeId);
        ivLogoHomeId_second = findViewById(R.id.ivLogoHomeId_second);
        expandableListView = findViewById(R.id.expandableListView);
        rlHomeToolbarId = findViewById(R.id.rlHomeToolbarId);
        ivLogoHome.setOnClickListener(this);
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
                    addFragment(new HomeFragment(), false, Constants.HOME_FRAGMENT);                    drawer.closeDrawer(Gravity.LEFT);
                    tvToolbarTitle.setVisibility(View.GONE);
                    rlUserProfile.setVisibility(View.VISIBLE);
                    rlNotification.setVisibility(View.GONE);
                   // showToolbar();
                    //  Toast.makeText(MainActivity.this, "as" + isChecked, Toast.LENGTH_SHORT).show();
                } else {
                    addFragment(new HomeFragmentDriver(), false, Constants.HOME_FRAGMENT_DRIVER);
                    drawer.closeDrawer(Gravity.LEFT);
                    tvToolbarTitle.setText("SUBMIT YOUR DETAILS");
                    rlUserProfile.setVisibility(View.INVISIBLE);
                    showToolbar();
                }
            }

        });
        statusCheck();
    }

    private void prepareMenuData() {
        MenuModel menuModel = new MenuModel("Home", true, true, R.drawable.home); //Menu of Android Tutorial. No sub menus
        headerList.add(menuModel);
        menuModel = new MenuModel("Rides", true, true, R.drawable.rides); //Menu of Java Tutorials
        headerList.add(menuModel);
        menuModel = new MenuModel("Accommodation", true, true, R.drawable.accommdation); //Menu of Python Tutorials
        headerList.add(menuModel);
        menuModel = new MenuModel("Tiffin Centers", true, true, R.drawable.tiffincenter); //Menu of Python Tutorials
        headerList.add(menuModel);
        menuModel = new MenuModel("Holy Places", true, true, R.drawable.holyplace); //Menu of Python Tutorials
        headerList.add(menuModel);
        menuModel = new MenuModel("Festival & Events", true, true, R.drawable.festivalsevents); //Menu of Python Tutorials
        headerList.add(menuModel);
        menuModel = new MenuModel("Indian Stores", true, true, R.drawable.indianstores); //Menu of Python Tutorials
        headerList.add(menuModel);
        menuModel = new MenuModel("Video", true, true, R.drawable.video); //Menu of Python Tutorials
        headerList.add(menuModel);
        menuModel = new MenuModel("About", true, true, R.drawable.about); //Menu of Python Tutorials
        headerList.add(menuModel);
        menuModel = new MenuModel("Settings", true, true, R.drawable.settings); //Menu of Python Tutorials
        headerList.add(menuModel);
        menuModel = new MenuModel("Notification", true, true, R.drawable.notification); //Menu of Python Tutorials
        headerList.add(menuModel);
        menuModel = new MenuModel("Logout", true, true, R.drawable.logout); //Menu of Python Tutorials
        headerList.add(menuModel);
    }

    private void populateExpandableList() {
        navigationExpandableListAdapter = new NavigationExpandableListAdapter(this, headerList, childList);
        expandableListView.setAdapter(navigationExpandableListAdapter);
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @SuppressLint("RtlHardcoded")
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                if (headerList.get(groupPosition).isGroup) {
                    if (groupPosition == 0) {
                        drawer.closeDrawer(Gravity.LEFT);
                        showToolbar();
                    } else if (groupPosition == 1) {
                        drawer.closeDrawer(Gravity.LEFT);
                        removeThisFragment();
                        hideToolbar();
                        addFragment(new RidesSearchFragmentSecond(), false, Constants.RIDES_SEARCH_FRAGMENT);
                    } else if (groupPosition == 2) {
                        drawer.closeDrawer(Gravity.LEFT);
                        removeThisFragment();
                        hideToolbar();
                        addFragment(new AccommodationFragment(), false, Constants.OPTIONS_BASE_FRAGMENT);
                    } else if (groupPosition == 3) {
                        drawer.closeDrawer(Gravity.LEFT);
                        removeThisFragment();
                        hideToolbar();
                        addFragment(new TiffinCenterFragment(), false, Constants.TIFFIN_CENTER_FRAGMENT);
                    } else if (groupPosition == 4) {
                        drawer.closeDrawer(Gravity.LEFT);
                        removeThisFragment();
                        hideToolbar();
                        addFragment(new HolyPlacesFragment(), false, Constants.HOLY_PLACE_FRAGMENT);
                    } else if (groupPosition == 5) {
                        drawer.closeDrawer(Gravity.LEFT);
                        removeThisFragment();
                        addFragment(new FestivalAndEventsFragment(), false, Constants.FESTIVAL_AND_EVENTS_FRAGMENT);
                        hideToolbar();
                    } else if (groupPosition == 6) {
                        removeThisFragment();
                        addFragment(new IndiansStoresFragment(), false, Constants.INDIAN_STORE_FRAGMENT);
                        drawer.closeDrawer(Gravity.LEFT);
                        hideToolbar();
                    } else if (groupPosition == 7) {
                        removeThisFragment();
                        addFragment(new VideosFragment(), false, Constants.VIDEOS_FRAGMENT);
                        drawer.closeDrawer(Gravity.LEFT);
                        hideToolbar();
                    } else if (groupPosition == 8) {
                        removeThisFragment();
                        addFragment(new AboutUsFragment(), false, Constants.ABOUT_US_FRAGMENT);
                        drawer.closeDrawer(Gravity.LEFT);
                        hideToolbar();
                    } else if (groupPosition == 9) {
                        removeThisFragment();
                        addFragment(new SettingFragment(), false, Constants.SETTING_FRAGMENT);
                        drawer.closeDrawer(Gravity.LEFT);
                        hideToolbar();
                    } else if (groupPosition == 10) {
                        removeThisFragment();
                        addFragment(new NotificationFragment(), false, Constants.NOTIFICATION_FRAGMENT);
                        drawer.closeDrawer(Gravity.LEFT);
                        hideToolbar();
                    } else if (groupPosition == 11) {
                        log_out();
                        drawer.closeDrawer(Gravity.LEFT);
                    }
                }
                return false;
            }
        });
    }

    public void addFragment(Fragment fragment, boolean addToBackStack, String tag) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        ft.addToBackStack(null);
        if (addToBackStack) {
            ft.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_left, R.anim.slide_in_right, R.anim.slide_out_right);
            ft.addToBackStack(tag);
        }
        ft.replace(R.id.flHomeId, fragment, tag);
        ft.commitAllowingStateLoss();
    }

    public void log_out() {
        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.log_out);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.PauseDialogAnimation;
        ImageView ivlogoutClose = dialog.findViewById(R.id.ivlogoutCloseId);
        RelativeLayout rllogout = dialog.findViewById(R.id.rllogoutId);
        ivlogoutClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        rllogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppSession.clearAllSharedPreferences(getApplicationContext());
                Intent intent = new Intent(MainActivity.this, SplashActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
                finish();
               /* switch (status) {
                    case "3": {
                        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                                .requestEmail()
                                .requestProfile()
                                .build();
                        GoogleSignInClient mGoogleSignInClient = GoogleSignIn.getClient(MainActivity.this, gso);
                        mGoogleSignInClient.signOut();
                        YourPreference yourPrefrence = YourPreference.getInstance(MainActivity.this);
                        yourPrefrence.saveData("status", "");
                        Intent intent = new Intent(MainActivity.this, SplashActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
                        finish();
                        break;
                    }
                  *//*  case "2": {
                        LoginManager.getInstance().logOut();
                        YourPreference yourPrefrence = YourPreference.getInstance(MainActivity.this);
                        yourPrefrence.saveData("status", "");
                        Intent intent = new Intent(MainActivity.this, SplashActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
                        finish();
                        break;
                    }
                    case "1": {
                        YourPreference yourPrefrence = YourPreference.getInstance(MainActivity.this);
                        yourPrefrence.saveData("status", "");
                        Intent intent = new Intent(MainActivity.this, SplashActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
                        finish();
                        break;
                    }*//*
                }*/
            }
        });
        dialog.show();

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
            case R.id.rlUserProfileId:
                Next_Back_Screen.newScreen(this, UserProfileActivity.class);
                break;
            case R.id.rlNotificationId:
                addFragment(new RideRequestListFragment(), false, Constants.RIDE_LIST);
                break;
            case R.id.ivLogoHomeId_second:
                if (drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.closeDrawer(Gravity.LEFT);
                } else {
                    drawer.openDrawer(Gravity.LEFT);
                }
                break;
        }
    }

    private void hideToolbar() {
        rlHomeToolbarId.setVisibility(View.GONE);
    }

    private void showToolbar() {
        rlHomeToolbarId.setVisibility(View.VISIBLE);
        //rlUserProfile.setVisibility(View.VISIBLE);
    }

    @Override
    public void onBackPressed() {
        FragmentManager fm = getSupportFragmentManager();
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
        Fragment RIDE_FRAGMENT = fragmentManager.findFragmentByTag(Constants.RIDE_FRAGMENT);
        Fragment PASSANGER_FRAGMENT = fragmentManager.findFragmentByTag(Constants.PASSANGER_FRAGMENT);
     //   Fragment MY_MISSION_DISPUTE_ACTIVITY = fragmentManager.findFragmentByTag(Constants.MY_MISSION_DISPUTE_ACTIVITY);

        if (HOME_FRAGMENT != null) {
             if (RIDES_SEARCH_FRAGMENT != null) {
                  if (RIDE_FRAGMENT !=null){
                     hideToolbar();
                     fm.popBackStack();
                  }else if (PASSANGER_FRAGMENT != null){
                     hideToolbar();
                     fm.popBackStack();
                 } else{
                      showToolbar();
                      fm.popBackStack();
                  }

            } else if (OPTIONS_BASE_FRAGMENT != null) {
                 showToolbar();
                 fm.popBackStack();
             } else if (TIFFIN_CENTER_FRAGMENT != null) {
                 showToolbar();
                 fm.popBackStack();
             }else if (HOLY_PLACE_FRAGMENT != null) {
                 showToolbar();
                 fm.popBackStack();
             }else if (FESTIVAL_AND_EVENTS_FRAGMENT != null) {
                 showToolbar();
                 fm.popBackStack();
             }else if (INDIAN_STORE_FRAGMENT != null) {
                 showToolbar();
                 fm.popBackStack();
             }else if (VIDEOS_FRAGMENT != null) {
                 showToolbar();
                 fm.popBackStack();
             } else if (ABOUT_US_FRAGMENT != null) {
                 showToolbar();
                 fm.popBackStack();
             }else if (SETTING_FRAGMENT != null) {
                 showToolbar();
                 fm.popBackStack();
             }else if (NOTIFICATION_FRAGMENT != null) {
                 showToolbar();
                 fm.popBackStack();
             }else {
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
             }

        }


    }

    public void removeThisFragment() {
        // final FragmentManager manager = getSupportFragmentManager();
        //  manager.popBackStackImmediate();
    }

    public void statusCheck() {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(MainActivity.this,
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                //Location Permission already granted
            } else {
                //Request Location Permission
                checkLocationPermission();
            }
        }
    }

    private void checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Location Permission Needed")
                        .setMessage("This app needs the Location permission, please accept to use location functionality")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Prompt the user once explanation has been shown
                                ActivityCompat.requestPermissions(MainActivity.this,
                                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                        MY_PERMISSIONS_REQUEST_LOCATION);
                            }
                        })
                        .create()
                        .show();


            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
        }
    }

    @Override
    public void passData(String data) {
        tvToolbarTitle.setText(data);
        tvToolbarTitle.setVisibility(View.VISIBLE);
    }

    @Override
    public void showNotification(String data) {
        rlNotification.setVisibility(View.VISIBLE);
    }
}
