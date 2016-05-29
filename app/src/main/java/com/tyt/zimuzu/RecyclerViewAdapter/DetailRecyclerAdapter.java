package com.tyt.zimuzu.RecyclerViewAdapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tyt.data.data.Detail;
import com.tyt.zimuzu.R;
import com.tyt.zimuzu.SearchActivity;

import butterknife.ButterKnife;

/**
 * Created by TYT on 2016/5/13.
 */
public class DetailRecyclerAdapter extends RecyclerView.Adapter<DetailRecyclerAdapter.DetailHolder> {
    private Detail data=null;
    private Context mContext;


    public DetailRecyclerAdapter(Context context){
        this.mContext = context;
    }
    public void setData(Detail detail){
        data=detail;
        this.notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return data!=null?data.getCount():0;
    }

    @Override
    public void onBindViewHolder(DetailHolder holder, int position) {
        if (data!=null) {
            switch (position) {
                case 0:
                    holder.info.setText("名称");
                    holder.detail.setText(data.getName());
                    break;
                case 1:
                    holder.info.setText("说明");
                    holder.detail.setText(data.getNote());
                    break;
                case 2:
                    holder.info.setText("年代");
                    holder.detail.setText(data.getYear());
                    break;
                case 3:
                    holder.info.setText("类型");
                    holder.detail.setText(data.getCategory());
                    break;
                case 4:
                    holder.info.setText("地区");
                    holder.detail.setText(data.getArea());
                    break;
                case 5:
                    holder.info.setText("制作方");
                    holder.detail.setText(data.getStation());
                    break;
                case 6:
                    holder.info.setText("语言");
                    holder.detail.setText(data.getLanguage());
                    break;
                case 7:
                    holder.info.setText("首映");
                    holder.detail.setText(data.getFirstShow());
                    break;
                case 8:
                    holder.info.setText("英文名");
                    holder.detail.setText(data.getEnName());
                    break;
                case 9:
                    holder.info.setText("编剧");
                    if (data.getScreenwriter()==null){
                        holder.detail.setText("暂无");
                    }else{
                        int size = data.getScreenwriter().size();
                        int[] length = new int[size];
                        for (int i =0;i<size;i++){
                            length[i] = data.getScreenwriter().get(i).getName().length();
                        }
                        ClickableSpan clickableSpan;
                        StringBuilder stringBuilder = new StringBuilder();
                        for (int i=0;i<size;i++){
                            stringBuilder.append(data.getScreenwriter().get(i).getName());
                            if (i!=size-1){
                                stringBuilder.append(",");
                            }
                        }
                        SpannableString spannableString = new SpannableString(stringBuilder.toString());
                        for (int i=0;i<size;i++){
                            int count=0;
                            for (int j=0;j<i;j++){
                                count+=length[j];
                                count+=1;
                            }
                            final int where=i;
                            clickableSpan = new ClickableSpan() {
                                @Override
                                public void onClick(View widget) {
                                    Intent intent = new Intent(mContext,SearchActivity.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    intent.putExtra("url",data.getScreenwriter().get(where).getURL());
                                    mContext.startActivity(intent);
                                }
                            };
                            spannableString.setSpan(clickableSpan,count,count+length[i],Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                        }
                        holder.detail.setText(spannableString);
                    }
                    break;
                case 10:
                    holder.info.setText("导演");
                    if (data.getDirector()==null){
                        holder.detail.setText("暂无");
                    }else{
                        int size = data.getDirector().size();
                        int[] length = new int[size];
                        for (int i =0;i<size;i++){
                            length[i] = data.getDirector().get(i).getName().length();
                        }
                        ClickableSpan clickableSpan;
                        StringBuilder stringBuilder = new StringBuilder();
                        for (int i=0;i<size;i++){
                            stringBuilder.append(data.getDirector().get(i).getName());
                            if (i!=size-1){
                                stringBuilder.append(",");
                            }
                        }
                        SpannableString spannableString = new SpannableString(stringBuilder.toString());
                        for (int i=0;i<size;i++){
                            int count=0;
                            for (int j=0;j<i;j++){
                                count+=length[j];
                                count+=1;
                            }
                            final int where=i;
                            clickableSpan = new ClickableSpan() {
                                @Override
                                public void onClick(View widget) {
                                    Intent intent = new Intent(mContext,SearchActivity.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    intent.putExtra("url",data.getDirector().get(where).getURL());
                                    mContext.startActivity(intent);
                                }
                            };
                            spannableString.setSpan(clickableSpan,count,count+length[i],Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                        }
                        holder.detail.setText(spannableString);
                    }
                    break;
                case 11:
                    holder.info.setText("主演");
                    if (data.getActor()==null){
                        holder.detail.setText("暂无");
                    }else{
                        int size = data.getActor().size();
                        int[] length = new int[size];
                        for (int i =0;i<size;i++){
                            length[i] = data.getActor().get(i).getName().length();
                        }
                        ClickableSpan clickableSpan;
                        StringBuilder stringBuilder = new StringBuilder();
                        for (int i=0;i<size;i++){
                            stringBuilder.append(data.getActor().get(i).getName());
                            if (i!=size-1){
                                stringBuilder.append(",");
                            }
                        }
                        SpannableString spannableString = new SpannableString(stringBuilder.toString());
                        for (int i=0;i<size;i++){
                            int count=0;
                            for (int j=0;j<i;j++){
                                count+=length[j];
                                count+=1;
                            }
                            final int where=i;
                            clickableSpan = new ClickableSpan() {
                                @Override
                                public void onClick(View widget) {
                                    Intent intent = new Intent(mContext,SearchActivity.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    intent.putExtra("url",data.getActor().get(where).getURL());
                                    mContext.startActivity(intent);
                                }
                            };
                            spannableString.setSpan(clickableSpan,count,count+length[i],Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                        }
                        holder.detail.setText(spannableString);
                    }
                    break;
                case 12:
                    holder.info.setText("IMDB");
                    if (data.getImdb()==null){
                        holder.detail.setText("暂无");
                    }else{
                        SpannableString spannableString = new SpannableString(data.getImdb());
                        ClickableSpan clickableSpan = new ClickableSpan() {
                            @Override
                            public void onClick(View widget) {
                                Intent it = new Intent(Intent.ACTION_VIEW, Uri.parse(data.getImdb()));
                                it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                mContext.startActivity(it);
                            }
                        };
                        spannableString.setSpan(clickableSpan,0,spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                        holder.detail.setText(spannableString);
                        holder.detail.setMovementMethod(LinkMovementMethod.getInstance());
                    }
                    break;
                case 13:
                    holder.info.setText("官网");
                    if (data.getWebsite()==null){
                        holder.detail.setText("暂无");
                    }else{
                        SpannableString spannableString = new SpannableString(data.getWebsite());
                        ClickableSpan clickableSpan = new ClickableSpan() {
                            @Override
                            public void onClick(View widget) {
                                Intent it = new Intent(Intent.ACTION_VIEW, Uri.parse(data.getWebsite()));
                                mContext.startActivity(it);
                            }
                        };
                        spannableString.setSpan(clickableSpan,0,spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                        holder.detail.setText(spannableString);
                        holder.detail.setMovementMethod(LinkMovementMethod.getInstance());
                    }
                    break;
                case 14:
                    holder.info.setText("简介");
                    holder.detail.setText("  "+data.getSummary());
                    break;
            }
        }
    }

    @Override
    public DetailHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.detail_item,parent,false);
        DetailHolder detailHolder = new DetailHolder(view);
        return detailHolder;
    }

    public class DetailHolder extends RecyclerView.ViewHolder{
        TextView info;
        TextView detail;
        DetailHolder(View view){
            super(view);
            info = ButterKnife.findById(view,R.id.info);
            detail = ButterKnife.findById(view,R.id.detail);
        }
    }
}
