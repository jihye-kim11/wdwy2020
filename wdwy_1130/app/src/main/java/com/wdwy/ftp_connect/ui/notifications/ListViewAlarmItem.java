package com.wdwy.ftp_connect.ui.notifications;

public class ListViewAlarmItem {
    private String contentStr;
    private String titleStr;
    private String class_no2;

    public void setTitle(String title) {
        titleStr = title;
    }


    public void setContent(String content) {
        contentStr = content;
    }

    public void setClass_no2(String no2) { class_no2 = no2; }

    public String getContent() {
        return this.contentStr;
    }

    public String getTitle() {
        return this.titleStr;
    }

    public String getClass_no2 () { return this.class_no2; }
}
