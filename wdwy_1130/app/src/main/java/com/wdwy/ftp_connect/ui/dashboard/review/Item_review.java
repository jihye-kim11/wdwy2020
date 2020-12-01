package com.wdwy.ftp_connect.ui.dashboard.review;

public class Item_review {
    String name;
    String image;
    String no;
    public Item_review(String name, String image, String no){
        this.name=name;
        this.image=image;
        this.no=no;
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

}