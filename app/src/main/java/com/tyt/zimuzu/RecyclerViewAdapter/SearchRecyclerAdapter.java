package com.tyt.zimuzu.RecyclerViewAdapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;
import com.tyt.data.data.SearchResult;
import com.tyt.zimuzu.DetailActivity;
import com.tyt.zimuzu.R;

import java.util.ArrayList;

import butterknife.ButterKnife;

/**
 * Created by TYT on 2016/5/30.
 */

public class SearchRecyclerAdapter extends RecyclerView.Adapter<SearchRecyclerAdapter.SearchHolder> {
    private ArrayList<SearchResult> data = null;
    private Context mContext;


    public SearchRecyclerAdapter(Context context) {
        mContext=context;
    }

    @Override
    public int getItemCount() {
        return data==null?0:data.size();
    }

    @Override
    public SearchHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_item,parent,false);
        SearchHolder searchHolder = new SearchHolder(view);
        //TODO layout
        return searchHolder;
    }

    @Override
    public void onBindViewHolder(SearchHolder holder, final int position) {
        holder.drawee.setImageURI(Uri.parse(data.get(position).getImgUrl()));
        holder.sort.setText(data.get(position).getSort());
        holder.name.setText(data.get(position).getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, DetailActivity.class);
                intent.putExtra("URL",data.get(position).getUrl());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }
        });
    }

    public class SearchHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView drawee;
        AppCompatTextView sort;
        AppCompatTextView name;

        SearchHolder(View view) {
            super(view);
            drawee = ButterKnife.findById(view,R.id.drawee);
            sort = ButterKnife.findById(view,R.id.sort);
            name = ButterKnife.findById(view,R.id.name);
        }
    }

    public void setData(ArrayList<SearchResult> data) {
        this.data = data;
        notifyDataSetChanged();
    }
}
