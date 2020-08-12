package com.connection.userModule.aboutusPkg;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;

import com.connection.R;

public class AboutUsFragment extends Fragment implements View.OnClickListener {

    private AppCompatImageView ivbackid;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.about_us_fragment, container, false);
        ivbackid = view.findViewById(R.id.ivbackid);
        ivbackid.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivbackid:
                getActivity().onBackPressed();
                break;
        }
    }
}
