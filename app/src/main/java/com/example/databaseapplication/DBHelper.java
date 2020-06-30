package com.example.databaseapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final int DB_Version = 1;
    public static final String DB_name = "Hamro Database";
    public static final String TABLE_NAME = "Products";
    public static final String COL_ONE = "product_id";
    public static final String COL_TWO = "product_name";


    //constructor
    public DBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DB_name, factory, DB_Version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //table creation ko kam yaha garne
        String query = " CREATE TABLE " + TABLE_NAME + "(" + COL_ONE + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_TWO + " TEXT" + ");";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //naya version db banaune
        db.execSQL("Drop Table if Exists " + TABLE_NAME);   //purano delte db
        onCreate(db);     //naya db baaanako

    }

    public void insertProduct(Products obj) {
        ContentValues values = new ContentValues();
        values.put(COL_TWO, obj.getProduct_name());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public void removeProduct(String entered_product) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_NAME, COL_TWO + "=?", new String[]{entered_product});
    }


    public String db_Display() {
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "Select * From " + TABLE_NAME;
        Cursor c = db.rawQuery(query, null);

        if (c.moveToFirst()) {
            do {
                dbString = dbString + c.getString(c.getColumnIndex(COL_TWO));
                dbString = dbString + "\n";

            } while (c.moveToNext());
        }
        c.close();
        db.close();
        return dbString;

    }
}
