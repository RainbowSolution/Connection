package com.connection;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.connection.userModule.holyPlacesPkg.holyPlaceAdapter.HolyPlacesAdapter;

public class OptionsBaseFragment extends Fragment implements View.OnClickListener, HolyPlacesAdapter.HolyPlacesAppOnClickListener {

    private RecyclerView rvholyplaceslist;
    private HolyPlacesAdapter holyPlacesAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_options_base, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        rvholyplaceslist = view.findViewById(R.id.rvholyplaceslistId);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        rvholyplaceslist.setLayoutManager(layoutManager);
        holyPlacesAdapter = new HolyPlacesAdapter(getActivity(), this);
        rvholyplaceslist.setAdapter(holyPlacesAdapter);
    }

    @Override
    public void onClick(View v) {
    }

  /*  @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_options_base);
       // init();
    }*/

    private void replaceFragement(Fragment fragment) {
        FragmentTransaction home = getActivity().getSupportFragmentManager().beginTransaction();
        home.replace(R.id.flCategroyId, fragment);
        home.commit();
    }


  /*  private void init() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        expandableListView = findViewById(R.id.expandableListView);
        ivLogoHome = findViewById(R.id.ivLogoHomeId);
        ivLogoHome.setOnClickListener(this);
        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        crossImageId = navigationView.getHeaderView(0).findViewById(R.id.ivclosemenuId);
        crossImageId.setOnClickListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        prepareMenuData();
        populateExpandableList();
    }*/

   /* private void prepareMenuData() {
        MenuModel menuModel = new MenuModel("Home", true, true, R.drawable.home); //Menu of Android Tutorial. No sub menus
        headerList.add(menuModel);

        menuModel = new MenuModel("Rides", true, true, R.drawable.rides); //Menu of Java Tutorials
        headerList.add(menuModel);

        menuModel = new MenuModel("Accommodation", true, true, R.drawable.accommdation); //Menu of Python Tutorials
        headerList.add(menuModel);

        menuModel = new MenuModel("Tiffin Center", true, true, R.drawable.tiffincenter); //Menu of Python Tutorials
        headerList.add(menuModel);

        menuModel = new MenuModel("Holy Place", true, true, R.drawable.holyplace); //Menu of Python Tutorials
        headerList.add(menuModel);

        menuModel = new MenuModel("Festivals & Events", true, true, R.drawable.festivalsevents); //Menu of Python Tutorials
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


    }*/

    /*private void populateExpandableList() {
        navigationExpandableListAdapter = new NavigationExpandableListAdapter(this, headerList, childList);
        expandableListView.setAdapter(navigationExpandableListAdapter);
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                if (headerList.get(groupPosition).isGroup) {
                    if (groupPosition == 0) {
                        drawer.closeDrawer(Gravity.LEFT);
                    } else if (groupPosition == 1) {
                        //Toast.makeText(MainActivity.this, "Under Development", Toast.LENGTH_SHORT).show();
                        Next_Back_Screen.newScreen(OptionsBaseFragment.this, RidesActivity.class);
                        drawer.closeDrawer(Gravity.LEFT);
                    } else if (groupPosition == 2) {
                        *//*try {
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
                        }*//*
                        Next_Back_Screen.newScreen(OptionsBaseFragment.this, AccommodationFragment.class);
                    } else if (groupPosition == 3) {
                        Next_Back_Screen.newScreen(OptionsBaseFragment.this, TiffinCenterFragment.class);
                        drawer.closeDrawer(Gravity.LEFT);

                    } else if (groupPosition == 4) {
                         Next_Back_Screen.newScreen(OptionsBaseFragment.this, HolyPlacesFragment.class);
                        drawer.closeDrawer(Gravity.LEFT);
                    } else if (groupPosition == 5) {
                      //  Next_Back_Screen.newScreen(OptionsBaseFragment.this, HolyPlacesFragment.class);
                        drawer.closeDrawer(Gravity.LEFT);
                    } else if (groupPosition == 6) {
                        Next_Back_Screen.newScreen(OptionsBaseFragment.this, IndiansStoresActivity.class);
                        drawer.closeDrawer(Gravity.LEFT);
                    } else if (groupPosition == 7) {
                        // Next_Back_Screen.newScreen(MainActivity.this, IndiansStoresActivity.class);
                        drawer.closeDrawer(Gravity.LEFT);
                    } else if (groupPosition == 8) {
                        //Next_Back_Screen.newScreen(MainActivity.this, HolyPlacesFragment.class);
                        drawer.closeDrawer(Gravity.LEFT);
                    } else if (groupPosition == 9) {
                        //Next_Back_Screen.newScreen(MainActivity.this, HolyPlacesFragment.class);
                        drawer.closeDrawer(Gravity.LEFT);
                    } else if (groupPosition == 10) {
                        //Next_Back_Screen.newScreen(MainActivity.this, HolyPlacesFragment.class);
                        drawer.closeDrawer(Gravity.LEFT);

                    } else if (groupPosition == 11) {
                        // Next_Back_Screen.newScreen(MainActivity.this, SplashActivity.class);
                        drawer.closeDrawer(Gravity.LEFT);

                    } else if (groupPosition == 12) {
                        Next_Back_Screen.newScreen(OptionsBaseFragment.this, SplashActivity.class);
                        drawer.closeDrawer(Gravity.LEFT);
                    }
                }
                return false;
            }
        });
    }
*/

}
