package com.zhangqiang.lifecycle.sample;

import android.support.v4.app.FragmentActivity;

import com.zhangqiang.lifecycle.MLifecycle;
import com.zhangqiang.lifecycle.MLifecycleProvider;
import com.zhangqiang.mvp.IView;
import com.zhangqiang.mvp.Presenter;
import com.zhangqiang.mvp.PresenterProviders;

public class PresenterFactory {

    public static <T extends FragmentActivity & IView,P extends Presenter> P get(final T t, Class<P> pClass) {
        final P p = PresenterProviders.of(t).get(pClass);
        final MLifecycle mLifecycle = MLifecycleProvider.get(t);
        mLifecycle.registerObserver(new MLifecycle.Observer() {
            @Override
            public void onCreate() {
                p.attachView(t);
            }

            @Override
            public void onStart() {

            }

            @Override
            public void onResume() {

            }

            @Override
            public void onPause() {

            }

            @Override
            public void onStop() {

            }

            @Override
            public void onDestroy() {
                p.detachView();
                mLifecycle.unregisterObserver(this);
            }
        });
        return p;
    }
}
