package com.wdwy.ftp_connect.ui.home;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;

import com.wdwy.ftp_connect.R;

public class CustomActionBar_login {
    private Activity activity;
    private ActionBar actionBar;

    public CustomActionBar_login(Activity _activity, ActionBar _actionBar){
        this.activity = _activity;
        this.actionBar=_actionBar;
    }

    public void setActionBar(){
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowHomeEnabled(false);

        View mCustomView= LayoutInflater.from(activity).inflate(R.layout.actionbar_action_login,null);
        actionBar.setCustomView(mCustomView);
        Toolbar parent =(Toolbar) mCustomView.getParent();
        parent.setContentInsetsAbsolute(0,0);
    }
}

