package com.example.skye;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CartAdapterClass extends RecyclerView.Adapter<CartAdapterClass.ViewHolder> {

    List<addCart> cart;
    Context context;
    DbCart dbCart;

    public CartAdapterClass(List<addCart> cart, Context context) {
        this.cart = cart;
        this.context = context;
        dbCart = new DbCart(context);

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater= LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.cart_item_list,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        final addCart addCart = cart.get(position);

        holder.textViewID.setText(Integer.toString(addCart.getCount()));
        holder.editText_ID.setText(addCart.getID());
        holder.editText_Name.setText(addCart.getName());

        holder.button_Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stringID =  holder.editText_ID.getText().toString();
                String stringName = holder.editText_Name.getText().toString();


                dbCart.updateCart(new addCart(addCart.getCount(),stringID,stringName));
                notifyDataSetChanged();
                ((Activity) context).finish();
                context.startActivity(((Activity) context).getIntent());


            }
        });

        holder.button_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbCart.deleteCat(addCart.getCount());
                cart.remove(position);
                notifyDataSetChanged();

            }
        });

    }

    @Override
    public int getItemCount() {
        return cart.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView textViewID;
        EditText editText_ID;
        EditText editText_Name;
        Button button_Edit;
        Button button_delete;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewID = itemView.findViewById(R.id.text_id);
            editText_ID = itemView.findViewById(R.id.editText_ID);
            editText_Name = itemView.findViewById(R.id.editText_name);
            button_delete = itemView.findViewById(R.id.button_delete);
            button_Edit = itemView.findViewById(R.id.button_edit);
        }
    }

}
