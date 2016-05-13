package com.tyt.zimuzu.RecyclerViewAdapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.tyt.zimuzu.R;

import butterknife.ButterKnife;

/**
 * Created by TYT on 2016/5/13.
 */
public class DetailRecyclerAdapter extends RecyclerView.Adapter<DetailRecyclerAdapter.MyHolder> {


    @Override
    public int getItemCount() {
        return 0;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {

    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    public class MyHolder extends RecyclerView.ViewHolder{

        MyHolder(View view){
            super(view);

        }
    }
}
