package com.example.skye.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "mobileDB.db";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }
    //change the DB version when upgrading the DB

    @Override
    public void onCreate(SQLiteDatabase db) {     //creating the table

        Log.d("workflow", "DB onCreate method Called");
        String SQL_CREATE_ITEMS =
                "CREATE TABLE "
                        + ItemMaster.ItemsT.TABLE_NAME +
                        " ("
                        + ItemMaster.ItemsT.COLUMN_ItemCode +
                        " INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + ItemMaster.ItemsT.COLUMN_ItemName +
                        " TEXT, "
                        + ItemMaster.ItemsT.COLUMN_ItemCategory +
                        " TEXT, "
                        + ItemMaster.ItemsT.COLUMN_ItemSellPrice +
                        " REAL, "
                        + ItemMaster.ItemsT.COLUMN_ItemDescription +
                        " TEXT" + ")";

        db.execSQL(SQL_CREATE_ITEMS);//Execute the table creation
        Log.d("DBcreation",SQL_CREATE_ITEMS );


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("workflow", "DB Onupgrade method Called");
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public long addItem(String itemname, String ItemCategory, double sellprice, String itemdescription) //enter all the parameter to be added to DB
    {
        Log.d("workflow", "DB addItems method Called");


        SQLiteDatabase db = getWritableDatabase();// get the data repository in writable mode

        ContentValues values = new ContentValues();  //create a new map of values , where column names the key
        values.put(ItemMaster.ItemsT.COLUMN_ItemName, itemname);
        values.put(ItemMaster.ItemsT.COLUMN_ItemCategory, ItemCategory);
        values.put(ItemMaster.ItemsT.COLUMN_ItemSellPrice, sellprice);
        values.put(ItemMaster.ItemsT.COLUMN_ItemDescription, itemdescription);

        long newRowID = db.insert(ItemMaster.ItemsT.TABLE_NAME, null, values); //Insert a new row and returning the primary
        //key values of the new row

        Log.d("workflow", "DB addItem method Called finished");

        return newRowID;
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public int updateItem(String id, String itemname, String ItemCategory, double sellprice, String itemdescription) { //define the attributes and parameters to be sent

        Log.d("workflow", "DB update item method Called");
        //  update route set is_default=0 where is_default=1
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();


        values.put(ItemMaster.ItemsT.COLUMN_ItemName, itemname);
        values.put(ItemMaster.ItemsT.COLUMN_ItemCategory, ItemCategory);
        values.put(ItemMaster.ItemsT.COLUMN_ItemSellPrice, sellprice);
        values.put(ItemMaster.ItemsT.COLUMN_ItemDescription, itemdescription);

        String selection = ItemMaster.ItemsT.COLUMN_ItemCode + " = ? ";
        String[] selectionArgs = {id};

        int count = db.update(ItemMaster.ItemsT.TABLE_NAME,
                values,
                selection,
                selectionArgs);

        return count;
    }

    public void deleteItem(String itemcode) {
        Log.d("workflow", "DB delete item method Called");

        SQLiteDatabase db = getWritableDatabase();
        String selection = ItemMaster.ItemsT.COLUMN_ItemCode + " = ? ";
        String[] selectionArgs = {itemcode};
        db.delete(ItemMaster.ItemsT.TABLE_NAME,   //table name
                selection,                         //where clause
                selectionArgs                       //selection clause
        );

    }


}
