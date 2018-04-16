package com.example.sempiternalsearch.hideme2;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.content.DialogInterface;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.nio.channels.FileChannel;
import java.util.ArrayList;

public class Gallery extends AppCompatActivity {

    public static final int FILE_PICKER_REQUEST_CODE = 1;

    FloatingActionButton addfilesbtn;

    GridView gridView;
    Drawable img;
    AlertDialog.Builder alertDialog;
    String folderName;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gallery);
        ArrayList<ArrayList<String>> imageUrl = new ArrayList<>();
         gridView = (GridView) findViewById(R.id.gridView);

        int iDisplayWidth = getResources().getDisplayMetrics().widthPixels ;
        Resources resources = getApplicationContext().getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float dp = iDisplayWidth / (metrics.densityDpi / 160f);
        //Log.d("Hello Mom", "thrkele");
        //MediaLoader mLoader = new MediaLoader();
        //mLoader.execute();
        //Log.d("Hello Mom", "kdjfkd");
        final ArrayList<GalleryModel> galleryModel = new ArrayList<>();
        img = (Drawable) getResources().getDrawable(R.drawable.add);
        galleryModel.add(new GalleryModel("New Folder",img));
        gridView.setAdapter(new GalleryAdapter(this, R.layout.gallery, galleryModel));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("GridView", ""+gridView.getItemAtPosition(position));
                ImageView imageView = (ImageView) findViewById(R.id.imageView);
                folderName = galleryModel.get(position).getFileName();
                Log.d("Selection",""+ folderName);
                if (folderName == "New Folder" && galleryModel.get(position).getfImage() == img ){
                    alertDialog = new AlertDialog.Builder(Gallery.this);
                    EditText text = new EditText(getApplicationContext());
                    text.setInputType(InputType.TYPE_CLASS_TEXT);
                    alertDialog.setView(text);
                    alertDialog.setTitle("New Folder");
                    alertDialog.setMessage("Enter the name of the new folder");
                    alertDialog.setPositiveButton("Enter", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    alertDialog.show();
                }
                //Intent intent = new Intent(Gallery.this, Files.class);
                //if(gridView.getItemAtPosition(position) == )
            }

            /*@Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {
                if (lView.getItemAtPosition(i) == "Quick Access") {
                    Intent intent = new Intent(context, MenuClicks.class);
                    stopSelf();
                    startService(intent);
                    Toast.makeText(getApplicationContext(), "Hello you selected quick access", Toast.LENGTH_LONG).show();
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                }
            }*/
        });


        FloatingActionButton addfilesbtn  = findViewById(R.id.addfilesbtn);
        addfilesbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFileChooser();
            }
        });
    }

    private void showFileChooser() {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);

        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("*/*");
        startActivityForResult(intent, FILE_PICKER_REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode,
                                 Intent resultData) {
        File source;
        File destination;

        if (requestCode == FILE_PICKER_REQUEST_CODE && resultCode == Activity.RESULT_OK) {


            Uri content_describer = resultData.getData();
            String src = content_describer.getPath();
            String filename = content_describer.getLastPathSegment();

            destination = new File(getFilesDir().getAbsolutePath() + "/storage/" + filename);


            source = new File(src);


            try {
                copy(source,destination);
            } catch (IOException e) {
                Log.d("Failed copy","....");
                e.printStackTrace();

            }

            }

        }




    void savefile(Uri sourceuri)
    {
        String filename="file";

        Cursor returnCursor = getContentResolver().query(sourceuri, null, null, null, null);
        int nameIndex = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);

        filename = returnCursor.getString(nameIndex);

        File folder = new File(getFilesDir().getAbsolutePath() + File.separatorChar + "storage");


        if (!folder.exists()) {
            folder.mkdir();
        }



        String fileDestLocation = getFilesDir().getAbsolutePath() + "/storage/" + filename;
        String sourceFilename= sourceuri.getPath();
        String destinationFilename = fileDestLocation;
                //android.os.Environment.getExternalStorageDirectory().getPath()+File.separatorChar+"abc.mp3";

        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;

        try {
            bis = new BufferedInputStream(new FileInputStream(sourceFilename));
            bos = new BufferedOutputStream(new FileOutputStream(destinationFilename, false));
            byte[] buf = new byte[1024];
            bis.read(buf);
            do {
                bos.write(buf);
            } while(bis.read(buf) != -1);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bis != null) bis.close();
                if (bos != null) bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void copy(File source, File destination) throws IOException {
        FileChannel in = new FileInputStream(source).getChannel();
        FileChannel out = new FileOutputStream(destination).getChannel();

        try {
            in.transferTo(0, in.size(), out);
        } catch(Exception e){
            Log.d("Exception", e.toString());
        } finally {
            if (in != null)
                in.close();
            if (out != null)
                out.close();
        }


    }

    private String queryName(ContentResolver resolver, Uri uri) {
        Cursor returnCursor =
                resolver.query(uri, null, null, null, null);
        assert returnCursor != null;
        int nameIndex = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
        returnCursor.moveToFirst();
        String name = returnCursor.getString(nameIndex);
        returnCursor.close();
        return name;
    }

    public String getPath(Uri uri) {

        String path = null;
        String[] projection = { MediaStore.Files.FileColumns.DATA };
        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);

        if(cursor == null){
            path = uri.getPath();
        }
        else{
            cursor.moveToFirst();
            int column_index = cursor.getColumnIndexOrThrow(projection[0]);
            path = cursor.getString(column_index);
            cursor.close();
        }

        return ((path == null || path.isEmpty()) ? (uri.getPath()) : path);
    }






}
