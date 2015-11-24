package com.theoreticsinc.schoolapp.utils;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.theoreticsinc.schoolapp.R;

import java.util.List;

public class LazyAdapter extends BaseAdapter {
    
    private Activity activity;
    private List<String> imgData;
    private List<String> titleData;
    private static LayoutInflater inflater=null;
    public ImageLoader imageLoader;
    
    public LazyAdapter(Activity a, List<String> d, List<String> name) {
        activity = a;
        imgData = d;
        titleData = name;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        imageLoader=new ImageLoader(activity.getApplicationContext());
    }

    public int getCount() {
        return imgData.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }
    
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi=convertView;
        if(convertView==null)
            vi = inflater.inflate(R.layout.item, null);

        TextView text=(TextView)vi.findViewById(R.id.text);
        ImageView listimage=(ImageView)vi.findViewById(R.id.listimage);
        text.setText(titleData.get(position));
        imageLoader.DisplayImage(imgData.get(position), listimage);
        return vi;
    }
}