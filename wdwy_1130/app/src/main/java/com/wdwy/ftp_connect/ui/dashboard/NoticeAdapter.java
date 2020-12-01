package com.wdwy.ftp_connect.ui.dashboard;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wdwy.ftp_connect.R;

import org.json.JSONException;

import java.util.ArrayList;

public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.ViewHolder> {

    Context context;

    private ArrayList<NoticeTitle> mData = new ArrayList<>();
    private String class_no2;

    private NoticeAdapter.OnItemClickListener mListener = null;
    public void setOnItemClickListener(NoticeAdapter.OnItemClickListener onItemClickListener) {
        this.mListener = (OnItemClickListener) onItemClickListener;
    }
    public  static interface  OnItemClickListener{
        void onItemClick(View v, int pos) throws JSONException;
    }


    // 아이템 뷰를 저장하는 뷰홀더 클래스.
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView1 ;

        TextView title;
        TextView writer;


        ViewHolder(View itemView) {
            super(itemView) ;

            title = itemView.findViewById(R.id.text1);
            writer = itemView.findViewById(R.id.text4);

            // 뷰 객체에 대한 참조. (hold strong reference)
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition() ;
                    if (pos != RecyclerView.NO_POSITION) {
                        //mData.set(pos, "item clicked. pos=" + pos)
                        //notifyItemChanged(pos) ;
                        //NoticeWrite activity 호출
                        //int index = Arrays.asList(mData).indexOf(pos);

                        Intent intent = new Intent(context, NoticeRead.class);
                        intent.putExtra("class_no2", class_no2);
                        intent.putExtra("title", mData.get(pos).title);
                        //intent.putExtra("pos", pos);
                        context.startActivity(intent);
                    }
                }
            });

            // 뷰 객체에 대한 참조. (hold strong reference)
            //textView1 = itemView.findViewById(R.id.text1) ;
        }
    }

    // 생성자에서 데이터 리스트 객체를 전달받음.
    NoticeAdapter(Context context){
        this.context =  context;
    }

    // onCreateViewHolder() - 아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴.
    @Override
    public NoticeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext() ;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;

        View view = inflater.inflate(R.layout.recyclerview_item, parent, false);
        NoticeAdapter.ViewHolder vh = new NoticeAdapter.ViewHolder(view) ;

        return vh ;
    }

    // onBindViewHolder() - position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시.
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        NoticeTitle titles = mData.get(position);
        holder.title.setText(titles.get_title());
        holder.writer.setText(titles.get_writer());
        //class_no2 = titles.getClass_no2();
    }


    // getItemCount() - 전체 데이터 갯수 리턴.
    @Override
    public int getItemCount() {
        return mData.size() ;
    }

    public  void addItem(NoticeTitle title){ mData.add(title); }


}