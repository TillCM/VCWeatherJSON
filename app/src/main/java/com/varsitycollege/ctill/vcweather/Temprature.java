package com.varsitycollege.ctill.vcweather;

public class Temprature
{
    // this is the model class that we will use to populate our list view



    String Date;

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getMinTemp() {
        return MinTemp;
    }

    public void setMinTemp(String minTemp) {
        MinTemp = minTemp;
    }

    public String getMaxTemp() {
        return MaxTemp;
    }

    public void setMaxTemp(String maxTemp) {
        MaxTemp = maxTemp;
    }

    public String getLink() {
        return Link;
    }

    public void setLink(String link) {
        Link = link;
    }

    String MinTemp;
    String MaxTemp;
    String Link;
}
