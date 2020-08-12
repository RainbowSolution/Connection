package com.connection.userModule.videosPkg.videoAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.connection.R;


public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder> {
    private Context context;
    private VideoAppOnClickListener videoAppOnClickListener;


    public VideoAdapter(Context context, VideoAppOnClickListener videoAppOnClickListener) {
        this.context = context;
        this.videoAppOnClickListener = videoAppOnClickListener;
    }

    @NonNull
    @Override
    public VideoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.activity_videos_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull VideoAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 8;
    }

    public interface VideoAppOnClickListener {
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
