package com.david.aclass.lesson;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.david.aclass.R;
import com.david.aclass.base.BaseActivity;
import com.david.aclass.util.ActivityUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LessonActivity extends BaseActivity {

    @BindView(R.id.toolbar_title)
    TextView mToolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.normal_activity);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        mToolbarTitle.setText("课表");
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        LessonFragment lessonFragment = (LessonFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);
        if (lessonFragment == null) {
            lessonFragment = LessonFragment.newInstance();
            ActivityUtil.addFragmentToActivity(getSupportFragmentManager(), lessonFragment, R.id.fragment);
        }

        new LessonPresenter(lessonFragment, this);
    }

}
