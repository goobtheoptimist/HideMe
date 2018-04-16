package com.example.sempiternalsearch.hideme2;

import android.graphics.drawable.Drawable;

public class GalleryModel {
    String folderName;
    Drawable fImage;
    public GalleryModel(String folderName, Drawable fImage){
        this.folderName = folderName;
        this.fImage = fImage;
    }

    public Drawable getfImage() {
        return fImage;
    }

    public String getFileName() {
        return folderName;
    }

    public void setFileName(String fileName) {
        this.folderName = fileName;
    }

    public void setfImage(Drawable fImage) {
        this.fImage = fImage;
    }
}
