package com.wdwy.ftp_connect.details.fragment2;

public class Item_fragment2 {
    String name;
    String b;
    String c;
    String image;
    public Item_fragment2(String name, String b, String c,String image){
        this.name=name;
        this.b=b;
        this.c=c;
        this.image=image;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getb() { return b; }
    public void setb(String b) {
        this.b = b;
    }

    public String getc() {
        return c;
    }
    public void setc(String c) {
        this.c = c;
    }

    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
}