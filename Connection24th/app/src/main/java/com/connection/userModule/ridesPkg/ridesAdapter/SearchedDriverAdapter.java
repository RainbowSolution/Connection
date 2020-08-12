package com.connection.userModule.ridesPkg.ridesAdapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.connection.R;
import com.connection.chatPkg.ChatActivity;

public class SearchedDriverAdapter extends RecyclerView.Adapter<SearchedDriverAdapter.ViewHolder> {
    private Context context;
    private RidesAppOnClickListener ridesAppOnClickListener;


    public SearchedDriverAdapter(Context context, RidesAppOnClickListener ridesAppOnClickListener) {
        this.context = context;
        this.ridesAppOnClickListener = ridesAppOnClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.activity_rides_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.rlBooknowId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                log_out();
            }
        });

    }

    @Override
    public int getItemCount() {
        return 6;
    }

    public void log_out() {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dilog_rides);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.PauseDialogAnimation;
        ImageView ivlogoutClose = dialog.findViewById(R.id.ivcrossId);
        AppCompatButton rllogout = dialog.findViewById(R.id.ivacceptionId);
        ivlogoutClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        rllogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity mainAct = (Activity) context;
                Intent intent = new Intent(context, ChatActivity.class);
                mainAct.startActivity(intent);
                mainAct.overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
                mainAct.finish();
            }
        });
        dialog.show();

    }

    public interface RidesAppOnClickListener {
        void onClick(View view, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private AppCompatImageView rlBooknowId;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rlBooknowId = itemView.findViewById(R.id.ivacceptId);
            rlBooknowId.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            ridesAppOnClickListener.onClick(v, getAdapterPosition());
        }
    }
}
