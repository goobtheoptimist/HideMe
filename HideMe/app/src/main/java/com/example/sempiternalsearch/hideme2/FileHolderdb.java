package com.example.sempiternalsearch.hideme2;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.io.File;

public class FileHolderdb extends SQLiteOpenHelper {


    public static class FileEntry implements BaseColumns {
        public static final String TABLE_NAME = "images";
        public static final String COLUMN_FILENAME = "filename";
        public static final String COLUMN_FILELOCATION = "filelocation";
        public static final String COLUMN_FILEDATA = "filedata";
    }

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + FileEntry.TABLE_NAME + " (" +
                    FileEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    FileEntry.COLUMN_FILENAME + " TEXT," +
                    FileEntry.COLUMN_FILELOCATION + " TEXT," +
                    FileEntry.COLUMN_FILEDATA + " BLOB)";


    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + FileEntry.TABLE_NAME;




    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "fileholder.db";



    public FileHolderdb(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public boolean insertData(String filename, String fileLocation, byte[] fileData){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();



        values.put(FileEntry.COLUMN_FILENAME, filename);
        values.put(FileEntry.COLUMN_FILELOCATION, fileLocation);
        values.put(FileEntry.COLUMN_FILEDATA, fileData);

        long newRowId = db.insert(FileEntry.TABLE_NAME, null, values);

        if(newRowId == -1){
            return false;
        }
        return true;
    }
}
