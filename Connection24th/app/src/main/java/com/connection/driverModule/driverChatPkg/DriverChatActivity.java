package com.connection.driverModule.driverChatPkg;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.connection.R;
import com.connection.driverModule.HomeActivity;
import com.connection.driverModule.driverChatPkg.driverchatAdapterPkg.DriverChatAdapter;
import com.connection.utility.AppSession;
import com.connection.utility.Constants;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class DriverChatActivity extends AppCompatActivity implements DriverChatAdapter.ChatAppOnClickListener, View.OnClickListener {
    private RecyclerView rvmsglist;
    private AppCompatImageView ivbackHomeId, ivChatArrow;
    private String userId, receiver_userId;
    private AppCompatEditText ettypemsg;
    private Consersation consersation;
    private DriverChatAdapter chatAdapter;
    private LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_chat);
        userId = AppSession.getStringPreferences(getApplicationContext(), Constants.USER_ID);
        receiver_userId = getIntent().getStringExtra("receiver_userId");
        consersation = new Consersation();
        init();
        chatDataSanpchat();
    }

    private void init() {
        ettypemsg = findViewById(R.id.ettypemsgid);
        ivChatArrow = findViewById(R.id.ivChatArrowId);
        ivbackHomeId = findViewById(R.id.ivbackHomeId);
        rvmsglist = findViewById(R.id.rvChatId);


        layoutManager = new LinearLayoutManager(getApplicationContext());
        rvmsglist.setLayoutManager(layoutManager);
        chatAdapter = new DriverChatAdapter(DriverChatActivity.this, this);
        rvmsglist.setAdapter(chatAdapter);

        ivbackHomeId.setOnClickListener(this);
        ivChatArrow.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //Intent intent = new Intent(DriverChatActivity.this, HomeActivity.class);
       // startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivChatArrowId:
                String content = ettypemsg.getText().toString().trim();
                final String message = ettypemsg.getText().toString();
                if (content.length() > 0) {
                    ettypemsg.setText("");
                    String driverRecordinsertFormat = "sender_" + userId + "_" + "receiver_" + receiver_userId;
                    String userRecordinsertFormat = "sender_" + receiver_userId + "_" + "receiver_" + userId;
                    ChatModle newMessage = new ChatModle(userId, receiver_userId, Constants.currentDateAndTime(), message);
                    FirebaseDatabase.getInstance().getReference().child("message/" + driverRecordinsertFormat).push().setValue(newMessage);
                    FirebaseDatabase.getInstance().getReference().child("message/" + userRecordinsertFormat).push().setValue(newMessage);
                }
                break;
            case R.id.ivbackHomeId:
                onBackPressed();
                break;
        }
    }


    private void chatDataSanpchat() {
        String userRecordinsertFormat = "sender_" + userId + "_" + "receiver_" + receiver_userId;
        FirebaseDatabase.getInstance().getReference().child("message/" + userRecordinsertFormat).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                if (dataSnapshot.getValue() != null) {

                    HashMap mapMessage = (HashMap) dataSnapshot.getValue();
                    ChatModle chatModle = new ChatModle((String) mapMessage.get("userId"),
                            (String) mapMessage.get("client"),
                            (String) mapMessage.get("dateTime"),
                            (String) mapMessage.get("message"));

                    consersation.getListMessageData().add(chatModle);
                    chatAdapter.addChatList(consersation.getListMessageData());
                    chatAdapter.notifyDataSetChanged();
                    layoutManager.scrollToPosition(consersation.getListMessageData().size() - 1);

                }

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }


            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        //  CustomProgressbar.hideProgressBar();
        rvmsglist.setAdapter(chatAdapter);

    }

}
