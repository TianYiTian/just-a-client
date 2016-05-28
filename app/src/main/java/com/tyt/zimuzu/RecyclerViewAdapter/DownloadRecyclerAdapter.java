package com.tyt.zimuzu.RecyclerViewAdapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.tyt.data.data.Download;
import com.tyt.data.data.SeasonDownload;
import com.tyt.zimuzu.R;

import java.util.ArrayList;

import butterknife.ButterKnife;

/**
 * Created by admin on 2016/5/28.
 */
public class DownloadRecyclerAdapter extends RecyclerView.Adapter<DownloadRecyclerAdapter.DownloadHolder> {
    private Context mContext;
    private ArrayList<SeasonDownload> data = null;
    private int season = 0;

    public DownloadRecyclerAdapter(Context context) {
        mContext = context;
    }

    public void setData(ArrayList<SeasonDownload> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public void setSeason(int season) {
        this.season = season;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.get(season).getDownloads().size();
    }

    @Override
    public DownloadHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.download_item, parent, false);
        DownloadHolder downloadHolder = new DownloadHolder(view);
        return downloadHolder;
    }

    @Override
    public void onBindViewHolder(DownloadHolder holder, int position) {
        final Download download = data.get(season).getDownloads().get(position);
        holder.name.setText(download.getName());
        if (download.getDianlv() != null) {
            holder.dianlv.setEnabled(true);
            holder.dianlv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(download.getDianlv()));
                        intent.addCategory("android.intent.category.DEFAULT");
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        mContext.startActivity(intent);
                    } catch (Exception e) {
                        Toast.makeText(mContext, "无下载软件", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else {
            holder.dianlv.setEnabled(false);
        }
        if (download.getXunlei() != null) {
            holder.xunlei.setEnabled(true);
            holder.xunlei.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(download.getXunlei()));
                        intent.addCategory("android.intent.category.DEFAULT");
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        mContext.startActivity(intent);
                    } catch (Exception e) {
                        Toast.makeText(mContext, "无下载软件", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else {
            holder.xunlei.setEnabled(false);
        }
        if (download.getCili() != null) {
            holder.cili.setEnabled(true);
            holder.cili.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(download.getCili()));
                        intent.addCategory("android.intent.category.DEFAULT");
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        mContext.startActivity(intent);
                    } catch (Exception e) {
                        Toast.makeText(mContext, "无下载软件", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else {
            holder.cili.setEnabled(false);
        }
        if (download.getXiaomi() != null) {
            holder.xiaomi.setEnabled(true);
            holder.xiaomi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(download.getXiaomi()));
                        intent.addCategory("android.intent.category.DEFAULT");
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        mContext.startActivity(intent);
                    } catch (Exception e) {
                        Toast.makeText(mContext, "无下载软件", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else {
            holder.xiaomi.setEnabled(false);
        }
        if (download.getBaidu() != null) {
            holder.baidu.setEnabled(true);
            holder.baidu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(download.getBaidu()));
                        intent.addCategory("android.intent.category.DEFAULT");
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        mContext.startActivity(intent);
                    } catch (Exception e) {
                        Toast.makeText(mContext, "无下载软件", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else {
            holder.baidu.setEnabled(false);
        }
    }

    public class DownloadHolder extends RecyclerView.ViewHolder {
        AppCompatButton dianlv;
        AppCompatButton xunlei;
        AppCompatButton baidu;
        AppCompatButton xiaomi;
        AppCompatButton cili;
        AppCompatTextView name;

        DownloadHolder(View view) {
            super(view);
            dianlv = ButterKnife.findById(view, R.id.dianlv);
            xunlei = ButterKnife.findById(view, R.id.xunlei);
            baidu = ButterKnife.findById(view, R.id.baidu);
            xiaomi = ButterKnife.findById(view, R.id.xiaomi);
            cili = ButterKnife.findById(view, R.id.cili);
            name = ButterKnife.findById(view, R.id.name);
        }
    }
}
