package com.david.aclass.lesson.model;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;

public class DayItem extends AbstractExpandableItem<LessonItem> implements MultiItemEntity {

    private String day;

    public DayItem(String day) {
        this.day = day;
    }

    @Override
    public int getLevel() {
        return 0;
    }

    @Override
    public int getItemType() {
        return 0;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

}
