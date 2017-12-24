package com.david.aclass.lesson.model;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.google.gson.annotations.SerializedName;

public class LessonItem implements MultiItemEntity {

    @SerializedName("name")
    private String lessonName;
    private int start;
    private int end;
    private String day;
    private String time;
    private String place;
    private String teacher;

    @Override
    public int getItemType() {
        return 1;
    }

    public String getLessonName() {
        return lessonName;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public String getTime() {
        return time;
    }

    public String getPlace() {
        return place;
    }

    public String getTeacher() {
        return teacher;
    }

    public String getDay() {
        return day;
    }

}
