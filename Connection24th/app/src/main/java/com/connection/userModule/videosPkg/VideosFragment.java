package com.connection.userModule.videosPkg;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.connection.R;
import com.connection.userModule.videosPkg.videoAdapter.VideoAdapter;

public class VideosFragment extends Fragment implements View.OnClickListener, VideoAdapter.VideoAppOnClickListener {
    private RecyclerView rvvideoslist;
    private VideoAdapter videoAdapter;
    private AppCompatImageView ivbackid;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_videos, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        rvvideoslist = view.findViewById(R.id.rvvideoslistId);
        ivbackid = view.findViewById(R.id.ivbackid);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        rvvideoslist.setLayoutManager(layoutManager);
        videoAdapter = new VideoAdapter(getActivity(), this);
        rvvideoslist.setAdapter(videoAdapter);
        ivbackid.setOnClickListener(this);
    }


    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videos);
        init();

    }
*/

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivbackid:
                getActivity().onBackPressed();
                break;
        }
    }
}
