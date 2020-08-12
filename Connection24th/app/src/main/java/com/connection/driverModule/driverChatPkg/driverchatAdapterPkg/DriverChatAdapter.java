package com.connection.driverModule.driverChatPkg.driverchatAdapterPkg;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.connection.R;
import com.connection.driverModule.driverChatPkg.ChatModle;
import com.connection.driverModule.driverChatPkg.DriverChatActivity;
import com.connection.utility.AppSession;
import com.connection.utility.Constants;

import java.util.ArrayList;

public class DriverChatAdapter extends RecyclerView.Adapter<DriverChatAdapter.ViewHolder> {
    private Context context;
    private ChatAppOnClickListener chatAppOnClickListener;
    private ArrayList<ChatModle> chatModleArrayList;
    private String userId;


    public DriverChatAdapter(Context context, DriverChatActivity chatAdapter) {
        this.context = context;
        this.chatAppOnClickListener = chatAppOnClickListener;
        userId = AppSession.getStringPreferences(context, Constants.USER_ID);
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.chat_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;



    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (userId.equalsIgnoreCase(chatModleArrayList.get(position).getUserId())) {
            holder.rlSenderContaner.setVisibility(View.VISIBLE);
            holder.rlReceiverContainer.setVisibility(View.GONE);
            holder.sendermsg.setText(chatModleArrayList.get(position).getMessage());
            holder.tvSendertime.setText(Constants.chatTimeFormat(chatModleArrayList.get(position).getDateTime()));
        } else {
            holder.rlSenderContaner.setVisibility(View.GONE);
            holder.rlReceiverContainer.setVisibility(View.VISIBLE);
            holder.tvReceivertime.setText(Constants.chatTimeFormat(chatModleArrayList.get(position).getDateTime()));
            holder.tvReceiverNameChat.setText(chatModleArrayList.get(position).getMessage());
        }
      /*  holder.tvReceivertime.setText(chatModleArrayList.get(position).getDateTime());
        holder.tvReceiverNameChat.setText(chatModleArrayList.get(position).getMessage());
        holder.sendermsg.setText(chatModleArrayList.get(position).getMessage());
        holder.tvSendertime.setText(chatModleArrayList.get(position).getDateTime());*/
    }


    @Override
    public int getItemCount() {
        return chatModleArrayList == null ? 0 : chatModleArrayList.size();
    }

    public void addChatList(ArrayList<ChatModle> chatModleArrayList) {
        this.chatModleArrayList = chatModleArrayList;
        notifyDataSetChanged();
    }


    public interface ChatAppOnClickListener {
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private AppCompatTextView tvReceivertime, tvReceiverNameChat, sendermsg, tvSendertime;
        private RelativeLayout rlSenderContaner, rlReceiverContainer;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rlSenderContaner = itemView.findViewById(R.id.rlSenderContanerId);
            rlReceiverContainer = itemView.findViewById(R.id.rlReceiverContainerId);
            tvReceivertime = itemView.findViewById(R.id.tvReceivertimeId);
            tvReceiverNameChat = itemView.findViewById(R.id.tvReceiverNameChatId);
            sendermsg = itemView.findViewById(R.id.sendermsgid);
            tvSendertime = itemView.findViewById(R.id.tvSendertimeId);

        }
    }
}
