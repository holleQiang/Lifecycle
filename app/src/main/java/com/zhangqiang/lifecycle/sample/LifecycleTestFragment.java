package com.zhangqiang.lifecycle.sample;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhangqiang.lifecycle.MLifecycle;
import com.zhangqiang.lifecycle.MLifecycleProvider;

public class LifecycleTestFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getLifecycle().addObserver(new GenericLifecycleObserver() {
//            @Override
//            public void onStateChanged(MLifecycleOwner source, android.arch.lifecycle.MLifecycle.Event event) {
//                Log.i("Test","=======MLifecycle.Event====="+event);
//            }
//        });
        final MLifecycle lifecycle1 = MLifecycleProvider.get(this);
        lifecycle1.registerObserver(new MLifecycle.Observer() {
            @Override
            public void onCreate() {
                Log.i("Test", "=====onCreate=======" + LifecycleTestFragment.this);
            }

            @Override
            public void onStart() {
                Log.i("Test", "=====onStart======="+ LifecycleTestFragment.this);

            }

            @Override
            public void onResume() {
                Log.i("Test", "=====onResume======="+ LifecycleTestFragment.this);

            }

            @Override
            public void onPause() {
                Log.i("Test", "=====onPause======="+ LifecycleTestFragment.this);

            }

            @Override
            public void onStop() {
                Log.i("Test", "=====onStop======="+ LifecycleTestFragment.this);

            }

            @Override
            public void onDestroy() {
                Log.i("Test", "=====onDestroy======="+ LifecycleTestFragment.this);
                lifecycle1.unregisterObserver(this);
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_lifecycle_test,container,false);
    }
}
