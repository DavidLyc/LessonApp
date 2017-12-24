package com.david.aclass.lesson;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.david.aclass.base.BasePresenter;
import com.david.aclass.lesson.model.DayItem;
import com.david.aclass.lesson.model.LessonItem;
import com.david.aclass.util.NetworkUtils;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class LessonPresenter extends BasePresenter<LessonContract.View> implements LessonContract.Presenter {

    private List<String> day = Arrays.asList("星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期日");

    LessonPresenter(@NonNull LessonContract.View view, @NonNull Context context) {
        super(view, context);
        mView.setPresenter(this);
    }

    @Override
    public void subscribe() {
        super.subscribe();
        if (mIsFirstLoad) {
            mIsFirstLoad = false;
            Activity activity = ((LessonFragment) mView).getActivity();
            assert activity != null;
            String major = activity.getIntent().getStringExtra("major");
            getLessonData(major);
        }
    }

    @Override
    public void getLessonData(final String major) {
        mView.showProgressDialog();
        addDisposable(Observable.create(new ObservableOnSubscribe<String>() {
                    @Override
                    public void subscribe(ObservableEmitter<String> e) throws Exception {
                        e.onNext(NetworkUtils.getLessons(major));
                        e.onComplete();
                    }
                }).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<String>() {
                            @Override
                            public void accept(String s) throws Exception {
                                mView.cancelProgressDialog();
                                if (s.equals("timeout")) {
                                    Toast.makeText(mContext, "网络连接出错！", Toast.LENGTH_SHORT).show();
                                } else {
                                    generateLessonData(new JsonParser().parse(s).getAsJsonArray());
                                }
                            }
                        })
        );
    }

    @Override
    public void generateLessonData(JsonArray jsonArray) {
        List<MultiItemEntity> entities = new ArrayList<>();
        List<DayItem> dayItems = new ArrayList<>();
        for (String d : day) {
            dayItems.add(new DayItem(d));
        }
        for (JsonElement jsonElement : jsonArray) {
            LessonItem lessonItem = new Gson().fromJson(jsonElement, LessonItem.class);
            dayItems.get(day.indexOf("星期" + lessonItem.getDay())).addSubItem(lessonItem);
        }
        entities.addAll(dayItems);
        mView.setRecyclerviewEntity(entities);
    }

}
