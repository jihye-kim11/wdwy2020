package com.wdwy.ftp_connect.details.fragment2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.wdwy.ftp_connect.R;

import org.json.JSONException;

import java.util.ArrayList;

public class MyAdapter_fragment2 extends RecyclerView.Adapter<MyAdapter_fragment2.ViewHolder> {
    Context context;

    ArrayList<Item_fragment2> items = new ArrayList<Item_fragment2>();
    private MyAdapter_fragment2.OnItemClickListener mListener = null;
    public void setOnItemClickListener(MyAdapter_fragment2.OnItemClickListener onItemClickListener) {
        this.mListener = onItemClickListener;
    }
    public  static interface  OnItemClickListener{
       void onItemClick(View v, int pos) throws JSONException;
    }

    public MyAdapter_fragment2(Context context){
        this.context =  context;
    }
    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = LayoutInflater.from(context)
                .inflate(R.layout.list_item_fragment2, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Item_fragment2 item = items.get(position);

        Glide.with(holder.itemView.getContext())
                .load(item.getImage())
                .into(holder.image);
        holder.name.setText(item.getName());
        holder.b.setText(item.getb());
        holder.c.setText(item.getc());

    }


    public  void addItem(Item_fragment2 item){
        items.add(item);
    }

    public void addItems(ArrayList<Item_fragment2> items){
        this.items = items;
    }


    public Item_fragment2 getItem(int position){
        return  items.get(position);
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView b;
        TextView c;
        ImageView image;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.user_id);
            b = itemView.findViewById(R.id.board_contents);
            c = itemView.findViewById(R.id.comment_contents);
            image = (ImageView) itemView.findViewById(R.id.user_image);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if(pos != RecyclerView.NO_POSITION ){

                        try {
                            mListener.onItemClick(v, pos);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }

    }
}