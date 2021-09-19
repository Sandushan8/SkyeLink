package com.example.skye;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.skye.database.DBHelper;

import java.util.ArrayList;

public class itemRecordList extends AppCompatActivity {

    ListView mListView;
    ArrayList<itemModel> mList;
    itemRecordListAdapter mAdapter = null;
    DBHelper dbHelper ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_record_list);

        dbHelper = new DBHelper(this);

        mListView = findViewById(R.id.listView);
        mList = new ArrayList<>();
        mAdapter = new itemRecordListAdapter(this,R.layout.row,mList);
        mListView.setAdapter(mAdapter);

        // get all data from sqlite
        Cursor cursor = dbHelper.getData("select * from Items");
        mList.clear();
        while (cursor.moveToNext()){
            int id  = cursor.getInt(0);
            String itemName = cursor.getString(1);
            String itemCategory = cursor.getString(2);
            String itemDescription = cursor.getString(3);
            double itemSellPrice = cursor.getDouble(4);
            byte[] image = cursor.getBlob(5);

            mList.add(new itemModel(id,itemName,itemCategory,itemSellPrice,itemDescription,image));
           Log.d("workflow",cursor.getString(4));
           Log.d("workflow",cursor.getString(3));
        }
        mAdapter.notifyDataSetChanged();
        if(mList.size()==0){
            Log.d("workflow","GGGGGGGGGGGGG");
            //if there is no record in table of database which means listview is empty
            Toast.makeText(this,"No Item Found",Toast.LENGTH_SHORT).show();
        }

      mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
          @Override
          public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
              return false;
          }
      });

    }
}

//design row for the listView
//create class model
//create custom adapter for listView