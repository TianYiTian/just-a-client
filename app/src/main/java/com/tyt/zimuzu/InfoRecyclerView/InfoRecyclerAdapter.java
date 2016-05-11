package com.tyt.zimuzu.InfoRecyclerView;

import android.net.Uri;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.tyt.data.html.Info;
import com.tyt.zimuzu.R;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by admin on 2016/5/10.
 */
public class InfoRecyclerAdapter extends RecyclerView.Adapter<InfoRecyclerAdapter.MyHolder> {
    private ArrayList<Info> dataList;
    public InfoRecyclerAdapter(){
        dataList= new ArrayList<Info>();
    }

    public void setDataList(ArrayList<Info> arrayList){
        if (arrayList!=null){
            this.notifyItemRangeRemoved(0,dataList.size());
            dataList=arrayList;
//            this.notifyDataSetChanged();
            this.notifyItemRangeInserted(0,dataList.size());
        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        holder.mTextView.setText(dataList.get(position).getName());
        holder.mSimpleDraweeView.setImageURI(Uri.parse(dataList.get(position).getImgURL()));
    }

    @Override
    public void onViewRecycled(MyHolder holder) {
        Log.w("test","recycled"+holder.toString());
        super.onViewRecycled(holder);
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.info_item,parent,false);
        MyHolder myHolder = new MyHolder(view);
        return myHolder;
    }

    public class MyHolder extends RecyclerView.ViewHolder{
        SimpleDraweeView mSimpleDraweeView;
        TextView mTextView;
        MyHolder(View view){
            super(view);
            mSimpleDraweeView = ButterKnife.findById(view,R.id.drawee);
            mTextView = ButterKnife.findById(view,R.id.text);
        }
    }
}
