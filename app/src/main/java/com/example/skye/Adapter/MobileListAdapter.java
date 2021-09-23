package com.example.skye.Adapter;

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

import com.example.skye.R;
import com.example.skye.admin.itemModel;

import java.util.ArrayList;

public class MobileListAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private ArrayList<itemModel> foodList;

    public MobileListAdapter(Context context, int layout, ArrayList<itemModel> foodList) {
        this.context = context;
        this.layout = layout;
        this.foodList = foodList;
        Log.d("workflow1","MobileListAdapter constructor");
    }



    @Override
    public int getCount() {
        return foodList.size();
    }

    @Override
    public Object getItem(int position) {
        return foodList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private  class ViewHolder{


        ImageView imageView;
        TextView txtMobileName,txtItemDesc,txtPrice;

    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View row = view;
        ViewHolder holder  = new ViewHolder();
        Log.d("workflow1","inside getView method mobile");

        if(row == null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout,null);
            holder.txtMobileName = row.findViewById(R.id.txtMobileName1);
            holder.txtItemDesc = row.findViewById(R.id.itemDescMobile1);
            holder.txtPrice = row.findViewById(R.id.txtPrice1);
            holder.imageView = row.findViewById(R.id.imageMobile1);
            row.setTag(holder);
            Log.d("workflow1","row equal nll");

        }else{
            holder = (ViewHolder) row.getTag();
        }

        itemModel model = foodList.get(position);

        holder.txtMobileName.setText(model.getItemName());
        holder.txtPrice.setText(String.valueOf(model.getItemSellPrice()));
        holder.txtItemDesc.setText(model.getItemDescription());
        byte[] recordImage = model.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(recordImage,0,recordImage.length);
        holder.imageView.setImageBitmap(bitmap);
        Log.d("workflow1",model.getItemName());

        return row;
    }
}
