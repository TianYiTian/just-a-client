package com.tyt.zimuzu.RecyclerViewAdapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.tyt.data.data.Info;
import com.tyt.zimuzu.DetailActivity;
import com.tyt.zimuzu.R;

import java.util.ArrayList;

import butterknife.ButterKnife;

/**
 * Created by admin on 2016/5/10.
 */
public class InfoRecyclerAdapter extends RecyclerView.Adapter<InfoRecyclerAdapter.MyHolder> {
    private ArrayList<Info> dataList;
    private Intent mIntent;
    private Context mContext;

    public InfoRecyclerAdapter(Context context){
        dataList= new ArrayList<Info>();
        mContext=context;
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
    public void onBindViewHolder(MyHolder holder, final int position) {
        holder.mTextView.getPaint().setFlags(Paint. UNDERLINE_TEXT_FLAG );
        holder.mTextView.getPaint().setAntiAlias(true);
        holder.mTextView.setText(dataList.get(position).getName());
        holder.mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIntent = new Intent(mContext, DetailActivity.class);
                mIntent.putExtra("URL",dataList.get(position).getInfoURL());
                mContext.startActivity(mIntent);
            }
        });
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
