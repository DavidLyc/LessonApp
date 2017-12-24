package com.david.aclass.lesson;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.david.aclass.R;
import com.david.aclass.base.BaseFragment;
import com.david.aclass.lesson.adapter.LessonAdapter;
import com.david.aclass.lesson.ui.DividerDecoration;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class LessonFragment extends BaseFragment<LessonContract.Presenter> implements LessonContract.View {

    @BindView(R.id.lesson_recyclerview)
    RecyclerView mRecyclerview;
    Unbinder unbinder;
    LessonAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container
            , @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.lesson_fragment, container, false);
        unbinder = ButterKnife.bind(this, root);
        initRecyclerview();
        return root;
    }

    public static LessonFragment newInstance() {
        return new LessonFragment();
    }

    private void initRecyclerview() {
        mAdapter = new LessonAdapter(null);
        mRecyclerview.setAdapter(mAdapter);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerview.addItemDecoration(new DividerDecoration(getContext()));
        mRecyclerview.setNestedScrollingEnabled(false);
        mRecyclerview.setFocusable(false);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void setRecyclerviewEntity(List<MultiItemEntity> entities) {
        mAdapter.addData(entities);
        mAdapter.notifyDataSetChanged();
        mAdapter.expandAll();
    }

}
