package com.connection.userModule.holyPlacesPkg.holyPlaceAdapter;

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

public class HolyPlacesAdapter extends RecyclerView.Adapter<HolyPlacesAdapter.ViewHolder> {
    private Context context;
    private HolyPlacesAppOnClickListener holyPlacesAppOnClickListener;
    private RelativeLayout rlknowmoreId;


    public HolyPlacesAdapter(Context context, HolyPlacesAppOnClickListener holyPlacesAppOnClickListener) {
        this.context = context;
        this.holyPlacesAppOnClickListener = holyPlacesAppOnClickListener;
    }


    @NonNull
    @Override
    public HolyPlacesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.activity_holy_places_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolyPlacesAdapter.ViewHolder holder, int position) {
        holder.rlknowmoreId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Holy_place_diloge();
            }
        });
    }

    @Override
    public int getItemCount() {
        return 8;
    }

    public void Holy_place_diloge() {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.CENTER;
        dialog.setContentView(R.layout.dilog_holy_place);
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

    public interface HolyPlacesAppOnClickListener {
    }

    public class
    ViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout rlknowmoreId;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rlknowmoreId = itemView.findViewById(R.id.rlknowmoreId);
        }
    }
}
