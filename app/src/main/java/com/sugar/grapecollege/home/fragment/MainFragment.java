package com.sugar.grapecollege.home.fragment;

import android.os.Bundle;
import android.view.View;

import com.qsmaxmin.annotation.bind.Bind;
import com.qsmaxmin.annotation.bind.OnClick;
import com.qsmaxmin.annotation.event.Subscribe;
import com.qsmaxmin.annotation.presenter.Presenter;
import com.qsmaxmin.annotation.thread.ThreadPoint;
import com.qsmaxmin.annotation.thread.ThreadType;
import com.qsmaxmin.qsbase.common.log.L;
import com.qsmaxmin.qsbase.common.widget.toast.QsToast;
import com.qsmaxmin.qsbase.common.widget.viewpager.PagerSlidingTabStrip;
import com.qsmaxmin.qsbase.common.widget.viewpager.autoscroll.AutoScrollViewPager;
import com.qsmaxmin.qsbase.common.widget.viewpager.autoscroll.CirclePageIndicator;
import com.qsmaxmin.qsbase.common.widget.viewpager.autoscroll.InfinitePagerAdapter;
import com.qsmaxmin.qsbase.mvp.adapter.QsListAdapterItem;
import com.sugar.grapecollege.R;
import com.sugar.grapecollege.common.base.fragment.BasePullListFragment;
import com.sugar.grapecollege.common.event.ApplicationEvent;
import com.sugar.grapecollege.common.http.resp.ModelHomeHeader;
import com.sugar.grapecollege.common.http.resp.ModelProductInfo;
import com.sugar.grapecollege.home.adapter.MainListAdapterItem;
import com.sugar.grapecollege.home.presenter.MainPresenter;
import com.sugar.grapecollege.test.TestABActivity;
import com.sugar.grapecollege.test.TestActivity;


/**
 * @CreateBy qsmaxmin
 * @Date 2017/4/27 15:58
 * @Description
 */
@Presenter(MainPresenter.class)
public class MainFragment extends BasePullListFragment<MainPresenter, ModelProductInfo.ProductInfo> {
    @Bind(R.id.banner)       AutoScrollViewPager banner;
    @Bind(R.id.page_indicator) CirclePageIndicator page_indicator;

    @Override public int getHeaderLayout() {
        return R.layout.header_main_fragment;
    }

    @Override public QsListAdapterItem<ModelProductInfo.ProductInfo> getListAdapterItem(int i) {
        return new MainListAdapterItem();
    }

    @Override public void onRefresh() {
        getPresenter().requestListData(false, false);
    }

    @Override public void onLoad() {
        getPresenter().requestListData(true, false);
    }

    @Override public void initData(Bundle bundle) {
        getPresenter().requestBannerData();
        getPresenter().requestListData(false, true);
    }

    @ThreadPoint(ThreadType.MAIN)
    public void updateHeader(ModelHomeHeader header) {
        L.i(initTag(), "updateHeader 当前线程:" + Thread.currentThread().getName());

        InfinitePagerAdapter adapter = new InfinitePagerAdapter();
        adapter.setOnPageClickListener(new InfinitePagerAdapter.OnPageClickListener() {
            @Override public void onPageClick(int position) {
                QsToast.show("click:"+position);
            }
        });
        adapter.setData(header.data);
        banner.setAdapter(adapter);
        page_indicator.setViewPager(banner);
        showContentView();
    }

    @Override public void onResume() {
        super.onResume();
        banner.startAutoScroll();
    }

    @Override public void onPause() {
        super.onPause();
        banner.stopAutoScroll();
    }

    @OnClick({R.id.tv_left, R.id.tv_right})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.tv_left:
                intent2Activity(TestActivity.class);
                break;
            case R.id.tv_right:
                intent2Activity(TestABActivity.class);
                break;
        }
    }

    /**
     * eventBus事件接收
     */
    @Subscribe public void onEvent(ApplicationEvent.TestClickEvent event) {
        intent2Activity(TestActivity.class);
    }

}
