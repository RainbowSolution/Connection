package com.connection.userModule.home_adapter_pkg;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.connection.ApiPkg.RetrofitClient;
import com.connection.R;
import com.connection.userModule.ModelClass.getCategoryPkg.Category;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder> {

    private List<Category> categoryLists;
    private Context context;


    public CategoryAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_list_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Category homeModel = categoryLists.get(position);
        holder.text2.setText(homeModel.getCategoryName());
       // Picasso.get().load(RetrofitClient.CATEGORY_URL + homeModel.getCatImages()).into(holder.ivhomeId);
        if (position == 0) {
            holder.cvSelectorcategoryId.setCardBackgroundColor(Color.parseColor("#5650C9"));
        } else if (position == 1) {
            holder.cvSelectorcategoryId.setCardBackgroundColor(Color.parseColor("#E0434F"));
        } else if (position == 2) {
            holder.cvSelectorcategoryId.setCardBackgroundColor(Color.parseColor("#F79E3F"));
        } else if (position == 3) {
            holder.cvSelectorcategoryId.setCardBackgroundColor(Color.parseColor("#09A08D"));
        } else if (position == 4) {
            holder.cvSelectorcategoryId.setCardBackgroundColor(Color.parseColor("#D07D4D"));
        } else if (position == 5) {
            holder.cvSelectorcategoryId.setCardBackgroundColor(Color.parseColor("#8DB2BC"));
        } else if (position == 6) {
            holder.cvSelectorcategoryId.setCardBackgroundColor(Color.parseColor("#4BCEDC"));
        }

    }

    public void categoryList(List<Category> categoryList) {
        this.categoryLists = categoryList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        // return 10;
        return categoryLists == null ? 0 : categoryLists.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        AppCompatTextView text2, tvTotalCount;
        AppCompatImageView ivhomeId;
        CardView cvSelectorcategoryId;

        MyViewHolder(View view) {
            super(view);
            tvTotalCount = (AppCompatTextView) view.findViewById(R.id.tvTotalCountId);
            text2 = (AppCompatTextView) view.findViewById(R.id.text2);
            ivhomeId = (AppCompatImageView) view.findViewById(R.id.ivhomeId);
            cvSelectorcategoryId = (CardView) view.findViewById(R.id.cvSelectorcategoryId);
        }
    }

}