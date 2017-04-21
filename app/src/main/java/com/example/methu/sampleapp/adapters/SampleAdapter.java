package com.example.methu.sampleapp.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.methu.sampleapp.R;
import com.example.methu.sampleapp.models.DataModel;

import java.util.List;

/**
 * Created by Methu on 4/21/2017.
 */

public class SampleAdapter extends BaseAdapter {

    Context context;

    protected List<DataModel> listC;
    LayoutInflater inflater;

    public SampleAdapter(Context context, List<DataModel> listComments) {
        this.listC = listComments;
        this.inflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public int getCount() {
        return listC.size();
    }

    @Override
    public Object getItem(int position) {
        return listC.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {

            holder = new ViewHolder();
            convertView = this.inflater.inflate(R.layout.single_row_display,
                    parent, false);

            holder.txtTitle = (TextView) convertView
                    .findViewById(R.id.txtTitle);
            holder.imgPic = (ImageView) convertView
                    .findViewById(R.id.imgPic);


            convertView.setTag(holder);


        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        DataModel c = listC.get(position);
        String pic_path = c.getPicture();


        holder.txtTitle.setText(c.getName().toString().toUpperCase());
        Log.e("#######@@@@@@@@   ", "   "+pic_path);
        Glide
                .with(context)
                .load(pic_path)
                .into(holder.imgPic);

        return convertView;
    }

    private class ViewHolder {
        TextView txtTitle;
        ImageView imgPic;
    }
}
