package com.wdwy.ftp_connect.ui.dashboard.qa_answer;

public class Item_qa_answer {
    String name;
    String image;
    String no;
    String u_name;
    String qa;
    public Item_qa_answer(String name, String image, String no, String u_name, String qa){
        this.name=name;
        this.image=image;
        this.no=no;
        this.u_name=u_name;
        this.qa=qa;
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

    public String getU_Name() {
        return u_name;
    }
    public void setU_Name(String u_name) {
        this.u_name = u_name;
    }

    public String getqa() {
        return qa;
    }
    public void setqa(String qa) {
        this.qa = qa;
    }
}