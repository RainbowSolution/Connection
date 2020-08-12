package com.connection;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.connection.ApiPkg.ApiServices;
import com.connection.ApiPkg.RetrofitClient;
import com.connection.userModule.ModelClass.getCategoryPkg.Category;
import com.connection.userModule.ModelClass.getCategoryPkg.CategoryListModle;
import com.connection.userModule.home_adapter_pkg.CategoryAdapter;
import com.connection.userModule.home_adapter_pkg.FirstBannerAdapter;
import com.connection.userModule.ridesPkg.RidesSarchFragment;
import com.connection.utility.Constants;
import com.connection.utility.RecyclerItemClickListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment implements View.OnClickListener, FirstBannerAdapter.BannerOnClickListener {
    private ViewPager vpHomeFirstBanner;

    private Handler handler;
    private int currentPage = 0;
    private Timer timer;
    private List<String> imageList;
    private long DELAY_MS = 500;
    private long PERIOD_MS = 2000;
    private LinearLayout llBookYourCabNow, llRideSharing;
    private FirstBannerAdapter firstBannerAdapter;
    private CategoryAdapter categoryAdapter;
    private RecyclerView recyclerView, rvslideryFragId;
    private GridLayoutManager mLayoutManager;
    private ApiServices apiServices;
    private List<Category> categoryList;
    private ProgressBar pbLoginId;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);
        apiServices = RetrofitClient.getClient().create(ApiServices.class);
        categoryList = new ArrayList<>();
        render(view);
        return view;
    }


    private void render(View view) {
        rvslideryFragId = view.findViewById(R.id.rvslideryFragId);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false);
        rvslideryFragId.setLayoutManager(layoutManager);
        firstBannerAdapter = new FirstBannerAdapter(getActivity(), this);
        rvslideryFragId.setAdapter(firstBannerAdapter);
        categoryAdapter = new CategoryAdapter(getActivity());
        recyclerView = view.findViewById(R.id.rvcategoryFragId);
        mLayoutManager = new GridLayoutManager(getActivity(), 3);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        pbLoginId = view.findViewById(R.id.pbLoginId);
        recyclerView.setAdapter(categoryAdapter);
        recyclerView.setOnClickListener(this);
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Category orderList = categoryList.get(position);
                String categoryname = orderList.getCategoryName();
                if (categoryname.equalsIgnoreCase("Rides")) {
                    addFragment(new RidesSarchFragment(), false, Constants.RIDES_SEARCH_FRAGMENT);
                } else {
                    Toast.makeText(getActivity(), "Under Working...", Toast.LENGTH_SHORT).show();
                }
                //Toast.makeText(getActivity(), "Under Working...", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));
        getCategoryList();

        //  prepareMovieData();
    }

  /*  private void prepareMovieData() {
        categoryLists.add(new HomeModel("Accommodation", R.drawable.accommdation, "100 Total", "#5650C9"));
        categoryLists.add(new HomeModel("Tiffin Center", R.drawable.tiffincenter, "100 Total", "#E0434F"));
        categoryLists.add(new HomeModel("Rides", R.drawable.rides, "100 Total", "#F79E3F"));
        categoryLists.add(new HomeModel("Festival Events", R.drawable.festivalsevents, "100 Total", "#09A08D"));
        categoryLists.add(new HomeModel("Holy Places", R.drawable.holyplace, "100 Total", "#D07D4D"));
        categoryLists.add(new HomeModel("Indian Stores", R.drawable.indianstores, "100 Total", "#8DB2BC"));
        categoryLists.add(new HomeModel("Videos", R.drawable.video, "100 Total", "#4BCEDC"));


    }*/

    public void addFragment(Fragment fragment, boolean addToBackStack, String tag) {
        FragmentManager manager = getFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        ft.addToBackStack(null);
        if (addToBackStack) {
            ft.addToBackStack(tag);
        }
        ft.replace(R.id.flHomeId, fragment, tag);
        ft.commitAllowingStateLoss();
    }

    private void scrooling(final int length) {
        handler = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if ((currentPage + 1) > length) {
                    currentPage = 0;
                } else {
                    currentPage++;
                }
                vpHomeFirstBanner.setCurrentItem(currentPage++, true);
            }
        };

        TimerTask timertask = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        handler.post(runnable);
                    }
                });
            }
        };
        timer = new Timer();
        timer.schedule(timertask, DELAY_MS, PERIOD_MS);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        }


/*
    private void homeFirstBanner() {
        apiServices.getBanner().enqueue(new Callback<TopBannerModle>() {
            @Override
            public void onResponse(Call<TopBannerModle> call, Response<TopBannerModle> response) {
                if (response.isSuccessful()) {
                    TopBannerModle topBannerModle = response.body();
                    title = topBannerModle.getImages();
                    Log.d("rs", String.valueOf(title));
                    firstBannerAdapter = new FirstBannerAdapter(getActivity(), title);
                    vpHomeFirstBanner.setAdapter(firstBannerAdapter);
                    tlHomeFirstBanner.setupWithViewPager(vpHomeFirstBanner);
                    scrooling(title.size());
                }
            }
            @Override
            public void onFailure(Call<TopBannerModle> call, Throwable t) {
            }
        });
    }*/

    }

    @Override
    public void onClick(View view, int position) {

    }

    private void getCategoryList() {
        pbLoginId.setVisibility(View.VISIBLE);
        apiServices.getCategoryList().enqueue(new Callback<CategoryListModle>() {
            @Override
            public void onResponse(Call<CategoryListModle> call, Response<CategoryListModle> response) {
                if (response.isSuccessful()) {
                    pbLoginId.setVisibility(View.GONE);
                    CategoryListModle topBannerModle = response.body();
                    categoryList = topBannerModle.getData();
                    categoryAdapter.categoryList(categoryList);
                }
            }

            @Override
            public void onFailure(Call<CategoryListModle> call, Throwable t) {
                pbLoginId.setVisibility(View.GONE);
            }
        });
    }


}
