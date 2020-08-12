package com.connection.driverModule.notificationPkg;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.connection.R;
import com.connection.driverModule.notificationPkg.adapterPkg.DriverNotificationAdapter;

public class DriverNotificationActivity extends AppCompatActivity implements DriverNotificationAdapter.NotificationOnClickListener, View.OnClickListener {
    private RecyclerView rvnotification;
    private DriverNotificationAdapter notificationAdapter;
    private AppCompatImageView ivbackId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.driver_activity_notification);
        init();
    }

    private void init() {
        ivbackId = findViewById(R.id.ivbackId);
        rvnotification = findViewById(R.id.rvnotificationId);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rvnotification.setLayoutManager(layoutManager);
        notificationAdapter = new DriverNotificationAdapter(this, this);
        rvnotification.setAdapter(notificationAdapter);
        ivbackId.setOnClickListener(this);

    }

    @Override
    public void onClick(View view, int position) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivbackId:
                onBackPressed();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
        finish();
    }
}
