package com.wdwy.ftp_connect.ui.dashboard;

public class TimeList {
    int count = 5;
    char[] mon_sch_ = new char[5];
    char[] tue_sch_ = new char[5];
    char[] wed_sch_ = new char[5];
    char[] thu_sch_ = new char[5];
    char[] fri_sch_ = new char[5];
    char[] sat_sch_ = new char[5];
    char[] sun_sch_ = new char[5];

    String class_no2;

    public TimeList(char[] mon_sch_,
                    char[] tue_sch_,
                    char[] wed_sch_,
                    char[] thu_sch_,
                    char[] fri_sch_,
                    char[] sat_sch_,
                    char[] sun_sch_){

        this.mon_sch_ = mon_sch_;
        this.tue_sch_ = tue_sch_;
        this.wed_sch_ = wed_sch_;
        this.thu_sch_ = thu_sch_;
        this.fri_sch_ = fri_sch_;
        this.sat_sch_ = sat_sch_;
        this.sun_sch_ = sun_sch_;

        this.class_no2=class_no2;;
    }


    public String getClass_no2 () { return this.class_no2; }


}
