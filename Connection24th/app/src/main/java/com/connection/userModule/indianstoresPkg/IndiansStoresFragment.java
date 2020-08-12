package com.connection.userModule.indianstoresPkg;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.connection.R;
import com.connection.userModule.indianstoresPkg.indianStoreAdapter.IndianStoresAdapter;

public class IndiansStoresFragment extends Fragment implements View.OnClickListener, IndianStoresAdapter.IndianStoresAppOnClickListener {
    private RecyclerView rvindianstoreslist;
    private IndianStoresAdapter indianStoresAdapter;
    private AppCompatImageView ivbackid;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_indians_stores, container, false);
        init(view);
        return view;
    }


    private void init(View view) {
        rvindianstoreslist = view.findViewById(R.id.rvindianstoreslistId);
        ivbackid = view.findViewById(R.id.ivbackid);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        rvindianstoreslist.setLayoutManager(layoutManager);
        indianStoresAdapter = new IndianStoresAdapter(getActivity(), this);
        rvindianstoreslist.setAdapter(indianStoresAdapter);
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
}
