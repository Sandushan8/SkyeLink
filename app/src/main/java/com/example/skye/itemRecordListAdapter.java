package com.example.skye;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.skye.database.DBHelper;

import java.util.ArrayList;

public class itemRecordListAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private ArrayList<itemModel> recordList;

    public itemRecordListAdapter(Context context, int layout, ArrayList<itemModel> recordList) {
        this.context = context;
        this.layout = layout;
        this.recordList = recordList;
        Log.d("workflow1","itemRecordListAdapter constructor");
    }



    @Override
    public int getCount() {
        return recordList.size();
    }

    @Override
    public Object getItem(int i) {
        return recordList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    private  class ViewHolder{


        ImageView imageView;
        TextView txtItemName,txtItemCategory,txtItemDesc,txtPrice;

    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View row = view;
        ViewHolder holder  = new ViewHolder();
        Log.d("workflow1","inside getView method");

        if(row == null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout,null);
            holder.txtItemName = row.findViewById(R.id.txtItemName);
            holder.txtItemCategory = row.findViewById(R.id.txtItemCategory);
            holder.txtItemDesc = row.findViewById(R.id.txtItemDesc);
            holder.txtPrice = row.findViewById(R.id.txtPrice);
            holder.imageView = row.findViewById(R.id.imgIcon);
            row.setTag(holder);
            Log.d("workflow1","row equal nll");

        }else{
            holder = (ViewHolder) row.getTag();
        }

        itemModel model = recordList.get(i);

        holder.txtItemName.setText(model.getItemName());
        holder.txtItemCategory.setText(model.getItemCategory());
        holder.txtPrice.setText(String.valueOf(model.getItemSellPrice()));
        holder.txtItemDesc.setText(model.getItemDescription());
        byte[] recordImage = model.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(recordImage,0,recordImage.length);
        holder.imageView.setImageBitmap(bitmap);
        Log.d("workflow1",model.getItemName());

        return row;
    }
}
