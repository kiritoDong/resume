package com.example.dong.news;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.BitmapCallback;

import java.util.List;

import okhttp3.Call;

import static android.R.attr.bitmap;
import static android.R.attr.targetActivity;


public class Adapter extends BaseAdapter {
    private LayoutInflater inflater;
    private  List<news.NewslistBean> list;
    private Context mcontext;
    public Adapter(List lists, Context mcontext) {
        this.mcontext = mcontext;
        this.list = lists;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            convertView =  inflater.from(mcontext).inflate(R.layout.activity_adapter, null);
            final View finalConvertView = convertView;
            Log.d("TAG","-----------------"+list.get(position).getPicUrl()+"------------------");
            if(list.get(position).getPicUrl()=="") {
                holder.vv = (ImageView) finalConvertView.findViewById(R.id.imageView);
                holder.vv.setImageResource(R.drawable.nopic);
            }else{
            OkHttpUtils.get().url(list.get(position).getPicUrl()).build().execute(new BitmapCallback() {
                @Override
                public void onError(Request request, Exception e) {
                }

                @Override
                public void onResponse(Bitmap bitmap) {
                    try {
                        holder.vv = (ImageView) finalConvertView.findViewById(R.id.imageView);
                        holder.vv.setImageBitmap(bitmap);
                    } catch (Exception e) {
                    }
                }
            });}
            holder.title = (TextView) convertView.findViewById(R.id.title);
            holder.title.setText(list.get(position).getTitle());
            holder.title.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent inte=new Intent();
                    Bundle bundle=new Bundle();
                    bundle.putSerializable("detail",list.get(position));
                    inte.putExtras(bundle);
                    inte.setClass(mcontext,NewDetails.class);
                    mcontext.startActivity(inte);
                }
            });
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.title.setText(list.get(position).getTitle());
        return convertView;
    }
    public final class ViewHolder {
        private TextView title;
        private ImageView vv;
    }
}
