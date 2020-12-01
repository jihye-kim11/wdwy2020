package com.wdwy.ftp_connect.ui.home.search;

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


public class Myadapter_search extends RecyclerView.Adapter<com.wdwy.ftp_connect.ui.home.search.Myadapter_search.ViewHolder> {
    Context context;

        ArrayList<Item_search> items = new ArrayList<Item_search>();
       //*com.example.ftp_connect.ui.home.search.Myadapter_search.OnItemClickListener listener;
    private OnItemClickListener mListener = null;
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mListener = onItemClickListener;
    }

    public  static interface  OnItemClickListener{
          //*  public void onItemClick(com.example.ftp_connect.ui.home.search.Myadapter_search.ViewHolder holder, View view, int position);

            void onItemClick(View v, int pos) throws JSONException;
        }

        public  Myadapter_search(Context context){
            this.context =  context;
        }
        @Override
        public int getItemCount() {
            return items.size();
        }

        @Override
        public com.wdwy.ftp_connect.ui.home.search.Myadapter_search.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = LayoutInflater.from(context)
                    .inflate(R.layout.list_item_search, parent, false);

            return new com.wdwy.ftp_connect.ui.home.search.Myadapter_search.ViewHolder(view);
        }

    @Override
        public void onBindViewHolder(@NonNull com.wdwy.ftp_connect.ui.home.search.Myadapter_search.ViewHolder holder, int position) {
        Item_search item = items.get(position);

            Glide.with(holder.itemView.getContext())
                    .load(item.getImage())
                    .into(holder.image);
            holder.name.setText(item.getName());
            holder.no.setText(item.getNo());
            holder.no2.setText(item.getNo2());
            holder.u_name.setText(item.getU_Name());
            holder.time.setText(item.getTime());
            holder.price.setText(item.getPrice());
            holder.start.setText(item.getStart());
            /*
            holder.setOnItemClickListener(new View.OnClickListener(){
            @Override
                public void onClick(View v) {
                    Intent intent = new Intent( v.getContext(), category.class);
                    String c="액티비티";
                    intent.putExtra("category",c);//문자열 넘기기
                    v.getContext().startActivity(intent);
                Toast.makeText(v.getContext(), "클릭 되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });

             */
        }

        public  void addItem(Item_search item){
            items.add(item);
        }
        public void addItems(ArrayList<Item_search> items){
            this.items = items;
        }


        public Item_search getItem(int position){
            return  items.get(position);
        }

//com.example.ftp_connect.ui.home.search.Myadapter_search.
        public class ViewHolder extends RecyclerView.ViewHolder {

            TextView name;
            TextView no;
            TextView no2;
            ImageView image;
            TextView u_name;
            TextView time;
            TextView price;
            TextView start;
          //  com.example.ftp_connect.ui.home.search.Myadapter_search.OnItemClickListener listener; //클릭이벤트처리관련 변수

            public ViewHolder(@NonNull final View itemView) {
                super(itemView);

                name = itemView.findViewById(R.id.class_name);
                no = itemView.findViewById(R.id.class_no);
                no2 = itemView.findViewById(R.id.class_no2);
                image = (ImageView) itemView.findViewById(R.id.class_image);
                u_name = itemView.findViewById(R.id.user_name);
                time = itemView.findViewById(R.id.class_time);
                price = itemView.findViewById(R.id.class_price);
                start = itemView.findViewById(R.id.class_start);

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pos = getAdapterPosition();
                       if (pos != RecyclerView.NO_POSITION) {
                         //  Intent intent = new Intent( context, details.class);
                          //String a=items.get(pos).getNo2();
                           //  RecyclerItem item = mData.get(pos) ;

                           try {
                               mListener.onItemClick(v, pos);
                           } catch (JSONException e) {
                               e.printStackTrace();
                           }


                       }
                        //if(listenr != null ){
                       //     listenr.onItemClick(com.example.ftp_connect.ui.home.search.Myadapter_search.ViewHolder.this, itemView, position);
                        //    Intent intent = new Intent( context, details.class);
                        //    context.startActivity(intent);
                       // }
                    }
                });




            }

  //  public void setOnItemClickListener(View.OnClickListener onClickListener) {
 //   }

    //클릭이벤트처리
          //  public void setOnItemClickListener(com.example.ftp_connect.ui.home.search.Myadapter_search.OnItemClickListener listenr){
          //      this.listener = listenr;
          //  }

        }

    }
