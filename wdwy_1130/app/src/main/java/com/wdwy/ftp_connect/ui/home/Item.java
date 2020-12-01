package com.wdwy.ftp_connect.ui.home;

public class Item {
    String name;
   // int image;
    String image;
    String no;
    String no2;
    String time;
    public Item(String name, String image,String no,String no2,String time){
        this.name=name;
        this.image=image;
        this.no=no;
        this.no2=no2;
        this.time=time;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }
    public String getNo2() {
        return no;
    }

    public void setNo2(String no2) {
        this.no2 = no2;
    }

    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }
}