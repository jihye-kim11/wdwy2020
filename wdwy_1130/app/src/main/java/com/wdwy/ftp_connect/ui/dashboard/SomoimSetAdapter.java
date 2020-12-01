package com.wdwy.ftp_connect.ui.dashboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wdwy.ftp_connect.R;

import org.json.JSONException;

import java.util.ArrayList;

public class SomoimSetAdapter extends RecyclerView.Adapter<com.wdwy.ftp_connect.ui.dashboard.SomoimSetAdapter.ViewHolder>  {
    Context context;

    ImageView image;
    String myJSON;

    char[] mon_sch_ = new char[5];
    char[] tue_sch_ = new char[5];
    char[] wed_sch_ = new char[5];
    char[] thu_sch_ = new char[5];
    char[] fri_sch_ = new char[5];
    char[] sat_sch_ = new char[5];
    char[] sun_sch_ = new char[5];


    private ArrayList<TimeList> mData = new ArrayList<>();
    private String class_no2;

    private com.wdwy.ftp_connect.ui.dashboard.SomoimSetAdapter.OnItemClickListener mListener = null;
    public void setOnItemClickListener(com.wdwy.ftp_connect.ui.dashboard.SomoimSetAdapter.OnItemClickListener onItemClickListener) {
        this.mListener = (com.wdwy.ftp_connect.ui.dashboard.SomoimSetAdapter.OnItemClickListener) onItemClickListener;
    }
    public  static interface  OnItemClickListener{
        void onItemClick(View v, int pos) throws JSONException;
    }


    // 아이템 뷰를 저장하는 뷰홀더 클래스.
    public class ViewHolder extends RecyclerView.ViewHolder {
        ToggleButton mon1, mon2, mon3, mon4, mon5;
        ToggleButton tue1, tue2, tue3, tue4, tue5;
        ToggleButton wed1, wed2, wed3, wed4, wed5;
        ToggleButton thu1, thu2, thu3, thu4, thu5;
        ToggleButton fri1, fri2, fri3, fri4, fri5;
        ToggleButton sat1, sat2, sat3, sat4, sat5;
        ToggleButton sun1, sun2, sun3, sun4, sun5;

        ImageView profile_img;
        TextView time_view, name;


        ViewHolder(View itemView) {
            super(itemView) ;
            time_view = itemView.findViewById(R.id.time_view);

            //php 연결하여 이미지와 이름 가져오기

            profile_img = itemView.findViewById(R.id.profile_img);
            name = itemView.findViewById(R.id.name);

            // 뷰 객체에 대한 참조. (hold strong reference)
            if (mon_sch_[0] == '1') {
                mon1 = itemView.findViewById(R.id.mon1);
                mon1.setBackgroundResource(R.color.colorPrimary);
                mon1.setText("가능");
            } else mon1 = itemView.findViewById(R.id.mon1);
            if (mon_sch_[1] == '1') {
                mon2 = itemView.findViewById(R.id.mon2);
                mon2.setBackgroundResource(R.color.colorPrimary);
                mon2.setText("가능");
            } else mon2 = itemView.findViewById(R.id.mon2);
            if (mon_sch_[2] == '1') {
                mon3 = itemView.findViewById(R.id.mon3);
                mon3.setBackgroundResource(R.color.colorPrimary);
                mon3.setText("가능");
            } else mon3 = itemView.findViewById(R.id.mon3);
            if (mon_sch_[3] == '1') {
                mon4 = itemView.findViewById(R.id.mon4);
                mon4.setBackgroundResource(R.color.colorPrimary);
                mon4.setText("가능");
            } else mon4 = itemView.findViewById(R.id.mon4);
            if (mon_sch_[4] == '1') {
                mon5 = itemView.findViewById(R.id.mon5);
                mon5.setBackgroundResource(R.color.colorPrimary);
                mon5.setText("가능");
            } else mon5 = itemView.findViewById(R.id.mon5);

            if (tue_sch_[0] == '1') {
                tue1 = itemView.findViewById(R.id.tue1);
                tue1.setBackgroundResource(R.color.colorPrimary);
                tue1.setText("가능");
            } else tue1 = itemView.findViewById(R.id.tue1);
            if (tue_sch_[1] == '1') {
                tue2 = itemView.findViewById(R.id.tue2);
                tue2.setBackgroundResource(R.color.colorPrimary);
                tue2.setText("가능");
            } else tue2 = itemView.findViewById(R.id.tue2);
            if (tue_sch_[2] == '1') {
                tue3 = itemView.findViewById(R.id.mon3);
                tue3.setBackgroundResource(R.color.colorPrimary);
                tue3.setText("가능");
            } else mon3 = itemView.findViewById(R.id.tue3);
            if (tue_sch_[3] == '1') {
                tue4 = itemView.findViewById(R.id.tue4);
                tue4.setBackgroundResource(R.color.colorPrimary);
                tue4.setText("가능");
            } else mon4 = itemView.findViewById(R.id.tue4);
            if (tue_sch_[4] == '1') {
                tue5 = itemView.findViewById(R.id.tue5);
                tue5.setBackgroundResource(R.color.colorPrimary);
                tue5.setText("가능");
            } else tue5 = itemView.findViewById(R.id.tue5);

            if (wed_sch_[0] == '1') {
                wed1 = itemView.findViewById(R.id.wed1);
                wed1.setBackgroundResource(R.color.colorPrimary);
                wed1.setText("가능");
            } else wed1 = itemView.findViewById(R.id.wed1);
            if (wed_sch_[1] == '1') {
                wed2 = itemView.findViewById(R.id.wed2);
                wed2.setBackgroundResource(R.color.colorPrimary);
                wed2.setText("가능");
            } else wed2 = itemView.findViewById(R.id.wed2);
            if (wed_sch_[2] == '1') {
                wed3 = itemView.findViewById(R.id.wed3);
                wed3.setBackgroundResource(R.color.colorPrimary);
                wed3.setText("가능");
            } else wed3 = itemView.findViewById(R.id.wed3);
            if (wed_sch_[3] == '1') {
                wed4 = itemView.findViewById(R.id.wed4);
                wed4.setBackgroundResource(R.color.colorPrimary);
                wed4.setText("가능");
            } else wed4 = itemView.findViewById(R.id.wed4);
            if (wed_sch_[4] == '1') {
                wed5 = itemView.findViewById(R.id.wed5);
                wed5.setBackgroundResource(R.color.colorPrimary);
                wed5.setText("가능");
            } else wed5 = itemView.findViewById(R.id.wed5);

            if (thu_sch_[0] == '1') {
                thu1 = itemView.findViewById(R.id.thu1);
                thu1.setBackgroundResource(R.color.colorPrimary);
                thu1.setText("가능");
            } else thu1 = itemView.findViewById(R.id.thu1);
            if (thu_sch_[1] == '1') {
                thu2 = itemView.findViewById(R.id.thu2);
                thu2.setBackgroundResource(R.color.colorPrimary);
                thu2.setText("가능");
            } else thu2 = itemView.findViewById(R.id.thu2);
            if (thu_sch_[2] == '1') {
                thu3 = itemView.findViewById(R.id.thu3);
                thu3.setBackgroundResource(R.color.colorPrimary);
                thu3.setText("가능");
            } else thu3 = itemView.findViewById(R.id.thu3);
            if (thu_sch_[3] == '1') {
                thu4 = itemView.findViewById(R.id.wed4);
                thu4.setBackgroundResource(R.color.colorPrimary);
                thu4.setText("가능");
            } else thu4 = itemView.findViewById(R.id.thu4);
            if (thu_sch_[4] == '1') {
                thu5 = itemView.findViewById(R.id.thu5);
                thu5.setBackgroundResource(R.color.colorPrimary);
                thu5.setText("가능");
            } else thu5 = itemView.findViewById(R.id.thu5);


        }
    }

    // 생성자에서 데이터 리스트 객체를 전달받음.
    SomoimSetAdapter(Context context){
        this.context =  context;
    }

    // onCreateViewHolder() - 아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴.
    @Override
    public com.wdwy.ftp_connect.ui.dashboard.SomoimSetAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext() ;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;

        View view = inflater.inflate(R.layout.recyclerview_somoim, parent, false);
        com.wdwy.ftp_connect.ui.dashboard.SomoimSetAdapter.ViewHolder vh = new com.wdwy.ftp_connect.ui.dashboard.SomoimSetAdapter.ViewHolder(view) ;

        return vh ;
    }

    // onBindViewHolder() - position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시.
    @Override
    public void onBindViewHolder(@NonNull com.wdwy.ftp_connect.ui.dashboard.SomoimSetAdapter.ViewHolder holder, int position) {
        //class_no2 = titles.getClass_no2();
    }


    // getItemCount() - 전체 데이터 갯수 리턴.
    @Override
    public int getItemCount() {
        return mData.size() ;
    }

    public  void addItem(TimeList list){ mData.add(list);}

}
