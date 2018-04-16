package com.example.sempiternalsearch.hideme2;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.bumptech.glide.load.engine.Resource;

import java.util.ArrayList;
import java.util.List;

public class Files extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gallery_images);
        GridView gridView = (GridView) findViewById(R.id.imageGridView);

    }
    public class FilesModel  {
        Drawable img;
        public FilesModel(Drawable img){
            this.img = img;
        }

        public void setImg(Drawable img) {
            this.img = img;
        }

        public Drawable getImg() {
            return img;
        }
    }
    public class FilesAdapter extends ArrayAdapter<FilesModel>{
        Context context;
        View convertView;
        Resource resource;
        ArrayList<FilesModel> objects;
        public FilesAdapter(Context context, int resource, ArrayList<FilesModel> objects) {
            super(context, resource, objects);
        }
    }

}
