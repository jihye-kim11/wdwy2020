package com.wdwy.ftp_connect.ui.dashboard;

public class NoticeTitle {
    String title;
    String writer;
    String class_no2;

    public NoticeTitle(String title, String writer){
        this.title=title;
        this.writer=writer;
        this.class_no2=class_no2;
    }

    public String get_title() {
        return title;
    }

    public String get_writer() { return writer; }

    public String getClass_no2 () { return this.class_no2; }


}
