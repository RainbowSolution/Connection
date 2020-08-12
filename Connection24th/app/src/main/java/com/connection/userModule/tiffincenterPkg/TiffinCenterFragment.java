package com.connection.userModule.tiffincenterPkg;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.connection.R;
import com.connection.userModule.tiffincenterPkg.tiffinAdapter.TiffinCenterAdapter;

public class TiffinCenterFragment extends Fragment implements View.OnClickListener, TiffinCenterAdapter.TiffinCenterAppOnClickListener {
    private RecyclerView rvtiffincenterslist;
    private TiffinCenterAdapter tiffinCenterAdapter;
    private AppCompatImageView ivbackid;
    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tiffin_center);
        init();
    }*/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_tiffin_center, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        rvtiffincenterslist = view.findViewById(R.id.rvtiffincenterslistId);
        ivbackid = view.findViewById(R.id.ivbackid);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        rvtiffincenterslist.setLayoutManager(layoutManager);
        tiffinCenterAdapter = new TiffinCenterAdapter(getActivity(), this);
        rvtiffincenterslist.setAdapter(tiffinCenterAdapter);
        ivbackid.setOnClickListener(this);
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
