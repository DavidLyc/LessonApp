package com.david.aclass.lesson.adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.david.aclass.R;
import com.david.aclass.lesson.model.DayItem;
import com.david.aclass.lesson.model.LessonItem;

import java.util.List;

public class LessonAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {

    private static final int TYPE_DAY = 0;
    private static final int TYPE_LESSON = 1;

    public LessonAdapter(List<MultiItemEntity> data) {
        super(data);
        addItemType(TYPE_DAY, R.layout.day_item);
        addItemType(TYPE_LESSON, R.layout.lesson_item);
    }

    @Override
    protected void convert(final BaseViewHolder helper, MultiItemEntity item) {
        switch (helper.getItemViewType()) {
            case TYPE_DAY:
                final DayItem dayItem = (DayItem) item;
                helper.setText(R.id.lesson_item_text, dayItem.getDay())
                        .setImageResource(R.id.lesson_item_arrow, dayItem.isExpanded() ?
                                R.drawable.arrow_down : R.drawable.arrow_right);
                helper.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pos = helper.getAdapterPosition();
                        if (dayItem.isExpanded()) {
                            collapse(pos);
                        } else {
                            expand(pos);
                        }
                    }
                });
                break;
            case TYPE_LESSON:
                final LessonItem lessonItem = (LessonItem) item;
                helper.setText(R.id.lesson_item_name, lessonItem.getLessonName())
                        .setText(R.id.lesson_item_time, lessonItem.getTime().equals("晚上") ?
                                "晚上" : "第" + lessonItem.getTime() + "节")
                        .setText(R.id.lesson_item_teacher, lessonItem.getTeacher())
                        .setText(R.id.lesson_item_place, lessonItem.getPlace())
                        .setText(R.id.lesson_item_start2end, "第" + lessonItem.getStart() + "—"
                                + lessonItem.getEnd() + "周");
                break;
        }
    }

}
