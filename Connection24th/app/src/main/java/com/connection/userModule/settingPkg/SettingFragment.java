package com.connection.userModule.settingPkg;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;

import com.connection.R;
import com.connection.userModule.userProfilePkg.UserProfileActivity;

public class SettingFragment extends Fragment implements View.OnClickListener{
    AppCompatTextView editProfileId;
    RelativeLayout rlbackId;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.setting_fragment, container, false);
        editProfileId = view.findViewById(R.id.editProfileId);
        rlbackId = view.findViewById(R.id.rlbackId);
        rlbackId.setOnClickListener(this);
        editProfileId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), UserProfileActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
            }
        });
        // init(view);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rlbackId:
                getActivity().onBackPressed();
                break;
        }
    }
}
