package com.wdwy.ftp_connect.ui.home.search;

public class Item_search {
    String name;
    String image;
    String no;
    String no2;
    String u_name;
    String time;
    String price;
    String start;

    public Item_search(String name, String image,String no,
                       String no2, String u_name,String time,
                       String price,String start){
        this.name=name;
        this.image=image;
        this.no=no;
        this.no2=no2;
        this.u_name=u_name;
        this.time=time;
        this.price=price;
        this.start=start;
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

    public String getU_Name() {
        return u_name;
    }
    public void setU_Name(String u_name) {
        this.u_name = u_name;
    }

    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }

    public String getPrice() {
        return price;
    }
    public void setPrice(String price) {
        this.price = price;
    }

    public String getStart() {
        return start;
    }
    public void setStart(String start) {
        this.start = start;
    }
}
