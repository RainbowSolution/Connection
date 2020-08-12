package com.connection.userModule.ridesPkg;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.connection.R;
import com.connection.utility.Constants;

public class RidesSearchFragmentSecond  extends Fragment implements View.OnClickListener{
    CardView ride_card,passengers_card;
    AppCompatImageView ivbackid;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ride_second, container, false);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        /// android:windowSoftInputMode="adjustPan|adjustResize"
        init(view);
        return view;
    }
    private void init(View view) {
        ride_card = view.findViewById(R.id.ride_card);
        passengers_card = view.findViewById(R.id.passengers_card);
        ivbackid = view.findViewById(R.id.ivbackid);
        ride_card.setOnClickListener(this);
        passengers_card.setOnClickListener(this);
        ivbackid.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ride_card:
               // replaceFragement(new RidesFragment());
                addFragment(new RidesFragment(),true, Constants.RIDE_FRAGMENT);
                break;
            case R.id.passengers_card:
                addFragment(new PassangerFragment(),true, Constants.RIDE_FRAGMENT);
                break;
            case R.id.ivbackid:
                getActivity().onBackPressed();
                break;
        }
    }
    private void replaceFragement(Fragment fragment) {
        Bundle bundle = new Bundle();
        FragmentTransaction home = getActivity().getSupportFragmentManager().beginTransaction();
        home.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_left, R.anim.slide_in_right, R.anim.slide_out_right);
        home.replace(R.id.flHomeId, fragment);
        home.addToBackStack(null);
        home.commit();
    }
    public void addFragment(Fragment fragment, boolean addToBackStack, String tag) {
        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        ft.addToBackStack(null);
        if (addToBackStack) {
            ft.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_left, R.anim.slide_in_right, R.anim.slide_out_right);
            ft.addToBackStack(tag);
        }
        ft.replace(R.id.flHomeId, fragment, tag);
        ft.commitAllowingStateLoss();
    }
    public void removeThisFragment() {
         final FragmentManager manager = getActivity().getSupportFragmentManager();
         manager.popBackStackImmediate();
    }
}
