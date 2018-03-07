package com.example.chatapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;



public class SomeoneAdapter extends ArrayAdapter<Someone> {
    private int resourceId;
    public SomeoneAdapter(Context context, int textViewResourceId, List<Someone> objects)
    {
        super(context,textViewResourceId,objects);
        resourceId=textViewResourceId;
    }

    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {
        Someone someone=getItem(position);
        View view;
        SomeoneViewHolder viewHolder;
        if (convertView==null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            viewHolder=new SomeoneViewHolder();
            viewHolder.someoneImage=(ImageView)view.findViewById(R.id.someone_image);
            viewHolder.someoneName=(TextView)view.findViewById(R.id.someone_name);
            view.setTag(viewHolder);
        }else {
            view = convertView;
            viewHolder=(SomeoneViewHolder)view.getTag();
        }
        viewHolder.someoneImage.setImageResource(someone.getImageId());
        viewHolder.someoneName.setText(someone.getName());
        return  view;
    }
}
class SomeoneViewHolder {
    ImageView someoneImage;
    TextView someoneName;
}