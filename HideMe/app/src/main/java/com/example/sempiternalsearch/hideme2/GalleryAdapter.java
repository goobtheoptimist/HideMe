package com.example.sempiternalsearch.hideme2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class GalleryAdapter extends ArrayAdapter<GalleryModel> {
    String folderName;
    String folderImage;
    int resource;
    Context context;
    ArrayList<GalleryModel> folderList;
    LayoutInflater inflater = null;
    public GalleryAdapter(Context context, int resource, ArrayList<GalleryModel> objects){
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        folderList =objects;
        inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.folders, parent, false);
        TextView textFolder = (TextView) convertView.findViewById(R.id.gallery_title);
        ImageView image =  (ImageView) convertView.findViewById(R.id.galleryImage);
        if (folderList.get(position).getFileName() == "New Folder") {
            image.setImageResource(R.drawable.add);
            textFolder.setText("New Folder");
        }
        return convertView;
    }
}
