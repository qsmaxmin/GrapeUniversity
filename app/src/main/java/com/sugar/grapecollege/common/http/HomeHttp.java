package com.sugar.grapecollege.common.http;

import com.qsmaxmin.qsbase.common.aspect.FormParam;
import com.qsmaxmin.qsbase.common.aspect.POST;
import com.qsmaxmin.qsbase.common.aspect.RequestStyle;
import com.sugar.grapecollege.common.http.resp.ModelHomeHeader;
import com.sugar.grapecollege.common.http.resp.ModelProductList;

/**
 * @CreateBy qsmaxmin
 * @Date 2017/5/18 17:37
 * @Description
 */

public interface HomeHttp {
    /**
     * 推荐位轮播图
     */
    @POST("/hehehe/dsfsdf") @RequestStyle(HttpConstants.STYLE_DECRYPT_RESP | HttpConstants.STYLE_ENCRYPT_REQ)
    ModelHomeHeader requestHomeHeaderData(@FormParam("targetId") String targetId);

    /**
     * 获取推荐字体列表
     */
    @POST("/hehehe/dsfsdf") @RequestStyle(HttpConstants.STYLE_DECRYPT_RESP)
    ModelProductList requestRecommendFontListData(@FormParam("id") String id);
}
