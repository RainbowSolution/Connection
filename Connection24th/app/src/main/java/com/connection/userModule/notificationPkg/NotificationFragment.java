package com.connection.userModule.notificationPkg;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.connection.R;
import com.connection.userModule.notificationPkg.notificationAdapter.NotificationAdapter;

public class NotificationFragment extends Fragment implements View.OnClickListener, NotificationAdapter.NotificationOnClickListener {
    private RecyclerView rvnotificationId;
    private NotificationAdapter notificationAdapter;
    private AppCompatImageView ivbackid;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_notification, container, false);
        init(view);
        return view;
    }


    private void init(View view) {
        rvnotificationId = view.findViewById(R.id.rvnotificationId);
        ivbackid= view.findViewById(R.id.ivbackid);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        rvnotificationId.setLayoutManager(layoutManager);
        notificationAdapter = new NotificationAdapter(getActivity(), this);
        rvnotificationId.setAdapter(notificationAdapter);
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

    @Override
    public void onClick(View view, int position) {

    }
}
