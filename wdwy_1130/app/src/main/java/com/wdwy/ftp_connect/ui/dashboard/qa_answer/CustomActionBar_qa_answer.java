package com.wdwy.ftp_connect.ui.dashboard.qa_answer;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;

import com.wdwy.ftp_connect.R;

//style에서 actionbar 사이즈 조절해주기!!
public class CustomActionBar_qa_answer {
    private Activity activity;
    private ActionBar actionBar;

    public CustomActionBar_qa_answer(Activity _activity, ActionBar _actionBar){
        this.activity = _activity;
        this.actionBar=_actionBar;
    }

    public void setActionBar(){
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowHomeEnabled(false);

        View mCustomView=LayoutInflater.from(activity).inflate(R.layout.actionbar_action_qa_answer,null);
        actionBar.setCustomView(mCustomView);
        Toolbar parent =(Toolbar) mCustomView.getParent();
        parent.setContentInsetsAbsolute(0,0);
    }
}
