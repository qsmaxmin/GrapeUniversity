package com.sugar.grapecollege.home;

import android.Manifest;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.qsmaxmin.qsbase.common.aspect.Permission;
import com.qsmaxmin.qsbase.common.log.L;
import com.qsmaxmin.qsbase.common.viewbind.annotation.Bind;
import com.qsmaxmin.qsbase.mvp.QsViewPagerABActivity;
import com.qsmaxmin.qsbase.mvp.model.QsModelPager;
import com.sugar.grapecollege.R;
import com.sugar.grapecollege.common.model.AppConfig;
import com.sugar.grapecollege.home.fragment.MainFragment;
import com.sugar.grapecollege.home.fragment.UserFragment;
import com.sugar.grapecollege.searcher.model.ModelSearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class HomeActivity extends QsViewPagerABActivity {
    @Bind(R.id.tv_title) TextView tv_title;

    @Override public int actionbarLayoutId() {
        return R.layout.actionbar_title;
    }

    @Override public void initData(Bundle bundle) {
        tv_title.setText("custom bind view");
        requestPermission();
        testAppConfig();
    }

    private void testAppConfig() {
        String testString = AppConfig.getInstance().testString;
        int testInt = AppConfig.getInstance().testInt;
        short testShort = AppConfig.getInstance().testShort;
        byte testByte = AppConfig.getInstance().testByte;
        char testChar = AppConfig.getInstance().testChar;
        long testLong = AppConfig.getInstance().testLong;
        float testFloat = AppConfig.getInstance().testFloat;
        double testDouble = AppConfig.getInstance().testDouble;
        boolean testBoolean = AppConfig.getInstance().testBoolean;
        ModelSearch testObject = AppConfig.getInstance().testObject;
        List<ModelSearch> testObjectList = AppConfig.getInstance().testObjectList;
        Map<String, ModelSearch> testObjectMap = AppConfig.getInstance().testObjectMap;

        L.i(initTag(), "testString = " + testString);
        L.i(initTag(), "testInt = " + testInt);
        L.i(initTag(), "testShort = " + testShort);
        L.i(initTag(), "testByte = " + testByte);
        L.i(initTag(), "testChar = " + testChar);
        L.i(initTag(), "testLong = " + testLong);
        L.i(initTag(), "testFloat = " + testFloat);
        L.i(initTag(), "testDouble = " + testDouble);
        L.i(initTag(), "testBoolean = " + testBoolean);
        L.i(initTag(), "testObject = " + testObject);
        L.i(initTag(), "testObjectList = " + testObjectList);
        L.i(initTag(), "testObjectMap = " + testObjectMap);

        AppConfig.getInstance().testString = "hello world~";
        AppConfig.getInstance().testInt = 1;
        AppConfig.getInstance().testShort = 2;
        AppConfig.getInstance().testByte = 3;
        AppConfig.getInstance().testChar = 4;
        AppConfig.getInstance().testLong = 5;
        AppConfig.getInstance().testFloat = 6.6f;
        AppConfig.getInstance().testDouble = 7.7d;
        AppConfig.getInstance().testBoolean = true;

        ModelSearch modelSearch = new ModelSearch();
        modelSearch.responseData = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            ModelSearch.ModelProduct product = new ModelSearch.ModelProduct();
            product.name = "product " + i;
            modelSearch.responseData.add(product);
        }
        AppConfig.getInstance().testObject = modelSearch;


        ArrayList<ModelSearch> list = new ArrayList<>();
        list.add(modelSearch);
        AppConfig.getInstance().testObjectList = list;

        HashMap<String, ModelSearch> hashMap = new HashMap<>();
        hashMap.put("my key", modelSearch);
        AppConfig.getInstance().testObjectMap = hashMap;

        AppConfig.getInstance().commit();
    }

    @Override public boolean isTransparentStatusBar() {
        return true;
    }

    /**
     * 因为要申请完权限通过后再初始化ViewPager
     * 所以返回null
     */
    @Override public QsModelPager[] getModelPagers() {
//        QsModelPager modelPager1 = new QsModelPager();
//        modelPager1.fragment = MainFragment.getInstance();
//        modelPager1.title = "title1";
//        modelPager1.position = 0;
//
//        QsModelPager modelPager3 = new QsModelPager();
//        modelPager3.fragment = UserFragment.getInstance();
//        modelPager3.title = "title1";
//        modelPager3.position = 1;
//        return new QsModelPager[]{modelPager1, modelPager3};
        return null;
    }

    @Permission({Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_PHONE_STATE})
    public void requestPermission() {
        QsModelPager modelPager1 = new QsModelPager();
        modelPager1.fragment = MainFragment.getInstance();
        modelPager1.title = "title1";
        modelPager1.position = 0;

        QsModelPager modelPager3 = new QsModelPager();
        modelPager3.fragment = UserFragment.getInstance();
        modelPager3.title = "title1";
        modelPager3.position = 1;

        initViewPager(new QsModelPager[]{modelPager1, modelPager3}, 3);
    }

    @Override public int getTabItemLayout() {
        return R.layout.item_home_tab;
    }

    @Override public void initTab(View view, QsModelPager qsModelPager) {
        TextView tv_tab = (TextView) view.findViewById(R.id.tv_tab);
        tv_tab.setText(qsModelPager.title);
    }

    @Override protected int getTabsSelectedTitleColor() {
        return getResources().getColor(R.color.colorAccent);
    }

    @Override protected int getTabsTitleColor() {
        return getResources().getColor(R.color.color_black);
    }
}
