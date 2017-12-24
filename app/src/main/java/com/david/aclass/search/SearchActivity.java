package com.david.aclass.search;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.david.aclass.R;
import com.david.aclass.base.BaseActivity;
import com.david.aclass.lesson.LessonActivity;
import com.jaredrummler.materialspinner.MaterialSpinner;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchActivity extends BaseActivity {

    @BindView(R.id.toolbar_title)
    TextView mToolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.search_major_spinner)
    MaterialSpinner mMajorSpinner;
    @BindView(R.id.search_class_spinner)
    MaterialSpinner mClassSpinner;
    private static final String[] mMajorItems = {"计算机", "物联网"};
    private static final String[] mClassItems = {"1401", "1402", "1403", "1404"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_activity);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(null);
        mToolbarTitle.setText("课表查询");
        initSpinner();
    }

    private void initSpinner() {
        mMajorSpinner.setItems(mMajorItems);
        mClassSpinner.setItems(mClassItems);
    }

    @OnClick(R.id.search_button)
    public void onViewClicked() {
        Intent intent = new Intent(SearchActivity.this, LessonActivity.class);
        String major = String.valueOf(mMajorSpinner.getText()).equals(mMajorItems[0]) ? "cs" : "iot";
        intent.putExtra("major", major);
        startActivity(intent);
    }

}
