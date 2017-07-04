package com.sugar.grapecollege.test.presenter;

import android.os.SystemClock;

import com.qsmaxmin.qsbase.common.aspect.ThreadPoint;
import com.qsmaxmin.qsbase.common.utils.QsHelper;
import com.sugar.grapecollege.common.http.HomeHttp;
import com.sugar.grapecollege.common.model.BaseModelReq;
import com.sugar.grapecollege.common.presenter.GrapeCollegePresenter;
import com.sugar.grapecollege.test.fragment.TestPullListFragment;
import com.sugar.grapecollege.test.model.TestModel;

import java.util.ArrayList;

/**
 * @CreateBy qsmaxmin
 * @Date 2017/7/4 16:52
 * @Description
 */

public class TestPullListPresenter extends GrapeCollegePresenter<TestPullListFragment> {
    int page;

    @ThreadPoint public void requestListData(boolean isLoadingMore) {
        SystemClock.sleep(1000);
        HomeHttp http = QsHelper.getInstance().getHttpHelper().create(HomeHttp.class);
        BaseModelReq req = new BaseModelReq();
        if (isLoadingMore) {
            if (page < 1) return;
            req.pageNumber = page;
            TestModel testData = getTestListData();
//            ModelProductList fontList = http.requestRecommendFontListData(req);
            if (isSuccess(testData, true)) {
                page++;
                getView().addData(testData.list);
                paging(testData);
            }
        } else {
            page = 0;
            req.pageNumber = page;
            TestModel testData = getTestListData();
//            ModelProductList fontList = http.requestRecommendFontListData(req);
            if (isSuccess(testData, true)) {
                page = 1;
                getView().setData(testData.list);
                paging(testData);
            }
        }
    }

    private TestModel getTestListData() {
        TestModel model = new TestModel();
        model.code = 0;
        model.isLastPage = page >= 10;
        model.list = new ArrayList<>(20);
        for (int i = 0; i < 20; i++) {
            TestModel.TestModelInfo detail = new TestModel.TestModelInfo();
            detail.testName = "哈哈" + i;
            model.list.add(detail);
        }
        return model;
    }
}
