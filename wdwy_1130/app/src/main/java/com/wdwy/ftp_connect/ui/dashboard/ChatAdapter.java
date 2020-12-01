package com.wdwy.ftp_connect.ui.dashboard;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wdwy.ftp_connect.R;
import java.util.ArrayList;

public class ChatAdapter extends BaseAdapter {

    ArrayList<MessageItem> messageItems;
    LayoutInflater layoutInflater;



    public ChatAdapter(ArrayList<MessageItem> messageItems, LayoutInflater layoutInflater) {
        this.messageItems = messageItems;
        this.layoutInflater = layoutInflater;
    }

    @Override
    public int getCount() {
        return messageItems.size();
    }

    @Override
    public Object getItem(int position) {
        return messageItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        //현재 보여줄 번째의(position)의 데이터로 뷰를 생성
        MessageItem item=messageItems.get(position);

        //재활용할 뷰는 사용하지 않음!!
        View itemView=null;

        // 시간
        if (position == 0) {
            if (item.getId().equals(G.id)) {
                itemView = layoutInflater.inflate(R.layout.my_msgbox_d, viewGroup, false);
            } else {
                itemView = layoutInflater.inflate(R.layout.other_msgbox_d, viewGroup, false);
            }
            TextView tvDate = itemView.findViewById(R.id.date);
            tvDate.setText(item.getDate());
        }
        else {
            MessageItem beforeItem = messageItems.get(position - 1);

            if (!beforeItem.getDate().equals(item.getDate())) {
                //메세지가 내 메세지인지??
                if (item.getId().equals(G.id)) {
                    itemView = layoutInflater.inflate(R.layout.my_msgbox_d, viewGroup, false);
                } else {
                    itemView = layoutInflater.inflate(R.layout.other_msgbox_d, viewGroup, false);
                }
                TextView tvDate = itemView.findViewById(R.id.date);
                tvDate.setText(item.getDate());
            } else {
                //메세지가 내 메세지인지??
                if (item.getId().equals(G.id)) {
                    itemView = layoutInflater.inflate(R.layout.my_msgbox, viewGroup, false);
                } else {
                    itemView = layoutInflater.inflate(R.layout.other_msgbox, viewGroup, false);
                }
            }
        }

        //만들어진 itemView에 값들 설정
        ImageView iv= itemView.findViewById(R.id.iv);
        TextView tvName= itemView.findViewById(R.id.tv_name);
        TextView tvMsg= itemView.findViewById(R.id.tv_msg);
        TextView tvTime= itemView.findViewById(R.id.tv_time);

        tvName.setText(item.getName());
        tvMsg.setText(item.getMessage());
        tvTime.setText(item.getTime());
        if (!item.getPofileUrl().equals("no")) {
            Glide.with(itemView).load(item.getPofileUrl()).into(iv);
        }


        return itemView;
    }
}