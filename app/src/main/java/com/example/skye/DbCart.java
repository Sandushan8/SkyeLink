package com.example.skye;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbCart extends SQLiteOpenHelper {
    //DB
    private static final  int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "mobileDB.db";

    //Table
    private static final String TABLE_NAME ="cart";
    //columns
    private static final String COUNT = "count";
    private static final String ID = "ID";
    private SQLiteDatabase sqLiteDatabase;

    //creating table query
    private static final String CREATE_TABLE = " create table " + TABLE_NAME + " ( "+ COUNT + " INTEGER PRIMARY KEY AUTOINCREMENT," + ID + " TEXT NOT NULL); ";

    //constructor
    public DbCart(Context context){
        super(context , DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    db.execSQL((CREATE_TABLE));
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    onCreate(db);
    }
    //add data
    public void addID(addCart addCart){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DbCart.ID,addCart.getID());
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.insert(DbCart.TABLE_NAME, null,contentValues);
    }


}
