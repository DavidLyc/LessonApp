package com.david.aclass.lesson;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.david.aclass.base.BaseContract;
import com.google.gson.JsonArray;

import java.util.List;

public interface LessonContract {

    interface View extends BaseContract.BaseView<Presenter> {

        void setRecyclerviewEntity(List<MultiItemEntity> entities);

    }

    interface Presenter extends BaseContract.BasePresenter {

        void getLessonData(String major);

        void generateLessonData(JsonArray jsonArray);

    }

}
