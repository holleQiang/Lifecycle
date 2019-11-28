package com.zhangqiang.lifecycle.sample;

import android.support.annotation.NonNull;
import android.util.Log;

import com.zhangqiang.mvp.IView;
import com.zhangqiang.mvp.Presenter;

public class TestPresener extends Presenter<TestPresener.View> {


    @Override
    protected void onViewAttached(@NonNull View view) {
        super.onViewAttached(view);
        Log.i("Test","====onViewAttached========");
    }

    @Override
    protected void onViewDetached(@NonNull View view) {
        super.onViewDetached(view);
        Log.i("Test","====onViewDetached========");
    }

    public void test() {

    }

    public interface View extends IView {

    }
}
