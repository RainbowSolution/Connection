package com.connection.chatPkg;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.connection.MainActivity;
import com.connection.R;
import com.connection.chatPkg.Adapter.ChatAdapter;


@SuppressLint("Registered")
public class ChatActivity extends AppCompatActivity implements ChatAdapter.ChatAppOnClickListener, View.OnClickListener {
    private RecyclerView rvmsglist;
    private ChatAdapter chatAdapter;
    private AppCompatImageView ivbackId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        init();
    }

    private void init() {
        rvmsglist = findViewById(R.id.rvChatId);
        ivbackId = findViewById(R.id.ivbackId);
        ivbackId.setOnClickListener(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        rvmsglist.setLayoutManager(layoutManager);
        ChatAdapter chatAdapter = new ChatAdapter(getApplicationContext(), this);
        rvmsglist.setAdapter(chatAdapter);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(ChatActivity.this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivbackId:
                onBackPressed();
                break;
        }
    }

}
