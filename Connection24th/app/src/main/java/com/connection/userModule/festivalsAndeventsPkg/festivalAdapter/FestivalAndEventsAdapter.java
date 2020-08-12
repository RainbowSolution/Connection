package com.connection.userModule.festivalsAndeventsPkg.festivalAdapter;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.connection.R;


public class FestivalAndEventsAdapter extends RecyclerView.Adapter<FestivalAndEventsAdapter.ViewHolder> {
    private Context context;
    private FestivalAndEventsAppOnClickListener festivalAndEventsAppOnClickListener;


    public FestivalAndEventsAdapter(Context context, FestivalAndEventsAppOnClickListener festivalAndEventsAppOnClickListener) {
        this.context = context;
        this.festivalAndEventsAppOnClickListener = festivalAndEventsAppOnClickListener;
    }


    @NonNull
    @Override
    public FestivalAndEventsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.activity_festival_events_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FestivalAndEventsAdapter.ViewHolder holder, int position) {
        holder.rlBooknowId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                festival_diloge();
            }
        });
    }

    @Override
    public int getItemCount() {
        return 8;
    }

    public void festival_diloge() {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.CENTER;
        dialog.setContentView(R.layout.dilog_festival);
        dialog.setCanceledOnTouchOutside(false);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.PauseDialogAnimation;
        ImageView ivlogoutClose = dialog.findViewById(R.id.cross);
        ivlogoutClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        dialog.show();

    }

    public interface FestivalAndEventsAppOnClickListener {
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private RelativeLayout rlBooknowId;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rlBooknowId = itemView.findViewById(R.id.rlBooknowId);
        }
    }
}
