package com.wdwy.ftp_connect.ui.dashboard.qa_answer;

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


public class Myadapter_qa_answer extends RecyclerView.Adapter<Myadapter_qa_answer.ViewHolder> {
    Context context;

        ArrayList<Item_qa_answer> items = new ArrayList<Item_qa_answer>();
       //*com.example.ftp_connect.ui.home.search.Myadapter_search.OnItemClickListener listener;
    private OnItemClickListener mListener = null;
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mListener = onItemClickListener;
    }

    public  static interface  OnItemClickListener{
          //*  public void onItemClick(com.example.ftp_connect.ui.home.search.Myadapter_search.ViewHolder holder, View view, int position);

            void onItemClick(View v, int pos) throws JSONException;
        }

        public Myadapter_qa_answer(Context context){
            this.context =  context;
        }
        @Override
        public int getItemCount() {
            return items.size();
        }

        @Override
        public Myadapter_qa_answer.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = LayoutInflater.from(context)
                    .inflate(R.layout.list_item_qa_answer, parent, false);
            return new Myadapter_qa_answer.ViewHolder(view);
        }

    @Override
        public void onBindViewHolder(@NonNull Myadapter_qa_answer.ViewHolder holder, int position) {
        Item_qa_answer item = items.get(position);

            Glide.with(holder.itemView.getContext())
                    .load(item.getImage())
                    .into(holder.image);
            holder.name.setText(item.getName());
            holder.no.setText(item.getNo());
            holder.u_name.setText(item.getU_Name());
            holder.qa.setText(item.getqa());
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

        public  void addItem(Item_qa_answer item){
            items.add(item);
        }
        public void addItems(ArrayList<Item_qa_answer> items){
            this.items = items;
        }
    public  void clearItem(){
        items.clear();
    }

        public Item_qa_answer getItem(int position){
            return  items.get(position);
        }

//com.example.ftp_connect.ui.home.search.Myadapter_search.
        public class ViewHolder extends RecyclerView.ViewHolder {

            TextView name;
            TextView no;
            TextView qa;
            ImageView image;
            TextView u_name;
          //  com.example.ftp_connect.ui.home.search.Myadapter_search.OnItemClickListener listener; //클릭이벤트처리관련 변수

            public ViewHolder(@NonNull final View itemView) {
                super(itemView);

                name = itemView.findViewById(R.id.class_name);
                no = itemView.findViewById(R.id.class_no);
                qa = itemView.findViewById(R.id.qa);
                image = (ImageView) itemView.findViewById(R.id.class_image);
                u_name = itemView.findViewById(R.id.user_name);

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
