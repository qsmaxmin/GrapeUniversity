package com.sugar.grapecollege.common.base.fragment;

import com.qsmaxmin.qsbase.mvvm.fragment.MvFragment;
import com.sugar.grapecollege.R;

/**
 * @CreateBy qsmaxmin
 * @Date 2019/9/6 15:56
 * @Description
 */
public abstract class BaseFragment extends MvFragment {
    @Override public int viewStateInAnimationId() {
        return R.anim.view_state_in;
    }
}
