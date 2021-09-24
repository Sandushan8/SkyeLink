package com.example.skye.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;


import com.example.skye.addCart;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "mobileDB.db";
    private static final String TABLE_NAME1 ="cart1";
    //columns
    private static final String COUNT = "count";
    private static final String ID = "ID";
    private static final String Name = "Name";
    private SQLiteDatabase sqLiteDatabase;

    //creating table query
    private static final String CREATE_TABLE1 = " create table " + TABLE_NAME1 + " ( "+ COUNT + " INTEGER PRIMARY KEY AUTOINCREMENT," + ID + " TEXT NOT NULL," +Name+ " TEXT NOT NULL); ";


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
                        + ItemMaster.ItemsT.COLUMN_ItemDescription +
                        " TEXT, "
                        + ItemMaster.ItemsT.COLUMN_ItemSellPrice +
                        " REAL, "
                        + ItemMaster.ItemsT.COLUMN_ItemImageView +
                        " BLOB" + ")";

        db.execSQL(SQL_CREATE_ITEMS);//Execute the table creation
        Log.d("DBcreation",SQL_CREATE_ITEMS );

        db.execSQL((CREATE_TABLE1));
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("workflow", "DB Onupgrade method Called");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME1);
        onCreate(db);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public long addItem(String itemname, String ItemCategory, double sellprice, String itemdescription,byte[] image) //enter all the parameter to be added to DB
    {
        Log.d("workflow", "DB addItems method Called");


        SQLiteDatabase db = getWritableDatabase();// get the data repository in writable mode

        ContentValues values = new ContentValues();  //create a new map of values , where column names the key
        values.put(ItemMaster.ItemsT.COLUMN_ItemName, itemname);
        values.put(ItemMaster.ItemsT.COLUMN_ItemCategory, ItemCategory);
        values.put(ItemMaster.ItemsT.COLUMN_ItemSellPrice, sellprice);
        values.put(ItemMaster.ItemsT.COLUMN_ItemDescription, itemdescription);
        values.put(ItemMaster.ItemsT.COLUMN_ItemImageView, image);


        long newRowID = db.insert(ItemMaster.ItemsT.TABLE_NAME, null, values); //Insert a new row and returning the primary
        //key values of the new row

        Log.d("workflow", "DB addItem method Called finished");

        return newRowID;
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public int updateItem(int id, String itemname, String ItemCategory, double sellprice, String itemdescription,byte[] image) { //define the attributes and parameters to be sent

        Log.d("workflow", "DB update item method Called");
        //  update route set is_default=0 where is_default=1
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();


        values.put(ItemMaster.ItemsT.COLUMN_ItemName, itemname);
        values.put(ItemMaster.ItemsT.COLUMN_ItemCategory, ItemCategory);
        values.put(ItemMaster.ItemsT.COLUMN_ItemSellPrice, sellprice);
        values.put(ItemMaster.ItemsT.COLUMN_ItemDescription, itemdescription);
        values.put(ItemMaster.ItemsT.COLUMN_ItemImageView, image);

        String selection = ItemMaster.ItemsT.COLUMN_ItemCode + " = ? ";
        String[] selectionArgs = {String.valueOf(id)};

        int count = db.update(ItemMaster.ItemsT.TABLE_NAME,
                values,
                selection,
                selectionArgs);

        return count;
    }

    public void deleteItem(int itemcode) {
        Log.d("workflow", "DB delete item method Called");

        SQLiteDatabase db = getWritableDatabase();
        String selection = ItemMaster.ItemsT.COLUMN_ItemCode + " = ? ";
        String[] selectionArgs = {String.valueOf(itemcode)};
        db.delete(ItemMaster.ItemsT.TABLE_NAME,   //table name
                selection,                         //where clause
                selectionArgs                       //selection clause
        );

    }

    public  Cursor getData(String sql){
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery(sql,null);
    }





    //add data
    public void addID(addCart addCart){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.ID,addCart.getID());
        contentValues.put(DBHelper.Name,addCart.getName());
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.insert(DBHelper.TABLE_NAME1, null,contentValues);
    }

    public List<addCart> getCartList(){
        String sql = " select * from  " + TABLE_NAME1;
        sqLiteDatabase = this.getReadableDatabase();
        List<addCart> storeCart = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(sql,null);
        if(cursor.moveToFirst()){
            do{
                int count = Integer.parseInt(cursor.getString(0));
                String ID = cursor.getString(1);
                String Name = cursor.getString(2);
                storeCart.add(new addCart(count,ID,Name));
            }while(cursor.moveToNext());
        }
        cursor.close();
        return storeCart;
    }

    public void updateCart(addCart AddCart){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.ID,AddCart.getID());
        contentValues.put(DBHelper.Name,AddCart.getName());
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.update(TABLE_NAME1,contentValues,COUNT+ " = ? " , new String[]
                {String.valueOf(AddCart.getCount())});
    }

    public void deleteCart(int count){
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(TABLE_NAME1, COUNT + " = ?", new String[]
                {String.valueOf(count)});
    }


}
