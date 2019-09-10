package com.sugar.grapecollege.home.presenter;

import com.qsmaxmin.qsbase.common.aspect.ThreadPoint;
import com.qsmaxmin.qsbase.common.aspect.ThreadType;
import com.qsmaxmin.qsbase.common.log.L;
import com.qsmaxmin.qsbase.common.utils.QsHelper;
import com.sugar.grapecollege.common.http.BaseModelReq;
import com.sugar.grapecollege.common.http.HomeHttp;
import com.sugar.grapecollege.common.http.resp.ModelHomeHeader;
import com.sugar.grapecollege.common.http.resp.ModelProductInfo;
import com.sugar.grapecollege.common.http.resp.ModelProductList;
import com.sugar.grapecollege.common.base.presenter.GrapeCollegePresenter;
import com.sugar.grapecollege.home.fragment.MainFragment;
import com.sugar.grapecollege.home.model.HomeConstants;

import java.util.ArrayList;


/**
 * @CreateBy qsmaxmin
 * @Date 2017/5/3 13:29
 * @Description
 */

public class MainPresenter extends GrapeCollegePresenter<MainFragment> {
    private int page;

    /**
     * 注解ThreadPoint(ThreadType.HTTP)的作用是将该方法放入Http线程中执行
     */
    @ThreadPoint(ThreadType.HTTP) public void requestBannerData() {
//        setHeaderCacheData();
        L.i(initTag(), "requestBannerData 当前线程:" + Thread.currentThread().getName());
//        HomeHttp homeHttp = createHttpRequest(HomeHttp.class);
//        ModelHomeHeader header = homeHttp.requestHomeHeaderData(new BaseModelReq());
        ModelHomeHeader header = getTestHeaderData();
        getView().updateHeader(header);
    }

    @ThreadPoint(ThreadType.HTTP) public void requestListData(boolean isLoadingMore, boolean needCache) {
        if (needCache) setListCacheData();
        HomeHttp http = createHttpRequest(HomeHttp.class);
        BaseModelReq req = new BaseModelReq();
        if (isLoadingMore) {
            if (page < 1) return;
            req.pageNumber = page;
            ModelProductList testData = getTestListData();
//            ModelProductList fontList = http.requestRecommendFontListData(req);
            if (isSuccess(testData, true)) {
                page++;
                getView().addData(testData.list);
                paging(testData);
            }
        } else {
            page = 0;
            req.pageNumber = page;
            ModelProductList testData = getTestListData();
//            ModelProductList fontList = http.requestRecommendFontListData(req);
            if (isSuccess(testData, true)) {
                page = 1;
                getView().setData(testData.list);
                paging(testData);
                saveListDataToCache(testData);
            }
        }
    }

    private ModelHomeHeader getTestHeaderData() {
        ModelHomeHeader header = new ModelHomeHeader();
        header.code = 0;
        header.responseData = new ArrayList<>(5);

        ArrayList<String> urls = new ArrayList<>();
        urls.add("http://image.tianjimedia.com/uploadImages/2015/204/22/YMG9CAUWUM15.jpg");
        urls.add("http://image.tianjimedia.com/uploadImages/2015/204/24/Q3NSQD2094FO.jpg");
        urls.add("http://image.tianjimedia.com/uploadImages/2015/204/28/73NL5CN350LB.jpg");
        urls.add("http://image.tianjimedia.com/uploadImages/2015/204/25/WC9015475YJ9.jpg");
        urls.add("http://img01.taopic.com/150605/267832-1506050R10761.jpg");

        for (String url : urls) {
            ModelHomeHeader.ResponseDataModel dataModel = new ModelHomeHeader.ResponseDataModel();
            dataModel.picUrl = url;
            header.responseData.add(dataModel);
        }
        return header;
    }

    private ModelProductList getTestListData() {
        ModelProductList productList = new ModelProductList();
        productList.code = 0;
        productList.isLastPage = page >= 10;
        productList.list = new ArrayList<>(20);
        for (int i = 0; i < 20; i++) {
            ModelProductInfo.ProductInfo detail = new ModelProductInfo.ProductInfo();
            detail.productName = "哈哈" + i;
            productList.list.add(detail);
        }
        return productList;
    }

    private void saveHeaderDataToCache(ModelHomeHeader header) {
        QsHelper.getCacheHelper().saveObject2File(header, HomeConstants.CACHE_MAIN_HEADER);
    }

    private void saveListDataToCache(ModelProductList list) {
        QsHelper.getCacheHelper().saveObject2File(list, HomeConstants.CACHE_MAIN_FONT_LIST);
    }

    private void setListCacheData() {
        ModelProductList dataFromCache = QsHelper.getCacheHelper().getObjectFromFile(HomeConstants.CACHE_MAIN_FONT_LIST, ModelProductList.class);
        if (isSuccess(dataFromCache, false) && dataFromCache.list != null) {
            getView().setData(dataFromCache.list);
        }
    }
}
