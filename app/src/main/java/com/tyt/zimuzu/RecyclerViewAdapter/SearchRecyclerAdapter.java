package com.tyt.zimuzu.RecyclerViewAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tyt.data.data.SearchResult;
import com.tyt.zimuzu.R;

import java.util.ArrayList;

/**
 * Created by TYT on 2016/5/30.
 */

public class SearchRecyclerAdapter extends RecyclerView.Adapter<SearchRecyclerAdapter.SearchHolder> {
    private ArrayList<SearchResult> data = null;
//    private Context mContext;


    public SearchRecyclerAdapter(/*Context context*/) {
//        mContext=context;
    }

    @Override
    public int getItemCount() {
        return data==null?0:data.size();
    }

    @Override
    public SearchHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search,parent,false);
        SearchHolder searchHolder = new SearchHolder(view);
        //TODO layout
        return searchHolder;
    }

    @Override
    public void onBindViewHolder(SearchHolder holder, int position) {

    }

    public class SearchHolder extends RecyclerView.ViewHolder {

        SearchHolder(View view) {
            super(view);
        }
    }

    public void setData(ArrayList<SearchResult> data) {
        this.data = data;
        notifyDataSetChanged();
    }
}
