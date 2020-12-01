package com.wdwy.ftp_connect.ui.home;

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

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    Context context;

    ArrayList<Item> items = new ArrayList<Item>();
    private MyAdapter.OnItemClickListener mListener = null;
    public void setOnItemClickListener(MyAdapter.OnItemClickListener onItemClickListener) {
        this.mListener = onItemClickListener;
    }
    public  static interface  OnItemClickListener{
       void onItemClick(View v, int pos) throws JSONException;
    }

    public MyAdapter(Context context){
        this.context =  context;
    }
    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // context 와 parent.getContext() 는 같다.
        View view = LayoutInflater.from(context)
                .inflate(R.layout.list_item_main, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Item item = items.get(position);

        Glide.with(holder.itemView.getContext())
                .load(item.getImage())
                .into(holder.image);
        holder.name.setText(item.getName());
        holder.no.setText(item.getNo());
        holder.no2.setText(item.getNo2());
        holder.time.setText(item.getTime());

    }


    public  void addItem(Item item){
        items.add(item);
    }

    public void addItems(ArrayList<Item> items){
        this.items = items;
    }


    public Item getItem(int position){
        return  items.get(position);
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView no;
        TextView no2;
        ImageView image;
        TextView time;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.class_name);
            no = itemView.findViewById(R.id.class_no);
            no2 = itemView.findViewById(R.id.class_no2);
            image = (ImageView) itemView.findViewById(R.id.class_image);
            time = itemView.findViewById(R.id.class_time);
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