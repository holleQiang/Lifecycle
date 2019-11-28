package com.zhangqiang.lifecycle.sample;

import android.arch.lifecycle.GenericLifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.zhangqiang.lifecycle.MLifecycle;
import com.zhangqiang.lifecycle.MLifecycleOwner;
import com.zhangqiang.lifecycle.MLifecycleProvider;

public class MainActivity extends AppCompatActivity implements MLifecycleOwner,TestPresener.View {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        testLifeCycle();

//        if (savedInstanceState == null) {
//            getSupportFragmentManager().beginTransaction()
//                    .replace(R.id.fl_fragment_container,new LifecycleTestFragment())
//                    .commit();
//        }

//        getSupportFragmentManager().beginTransaction()
//                .replace(R.id.fl_fragment_container,new LifecycleTestFragment())
//                .commit();
        PresenterFactory.get(this,TestPresener.class).test();
    }

    private void testLifeCycle() {

//        getLifecycle().addObserver(new GenericLifecycleObserver() {
//            @Override
//            public void onStateChanged(LifecycleOwner source, android.arch.lifecycle.Lifecycle.Event event) {
//                Log.i("Test","=======MLifecycle.Event====="+event);
//            }
//        });
        Log.i("Test","==========MainActivity====onCreate======");
        getMLifecycle().registerObserver(observer);
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();
    }


    MLifecycle.Observer observer = new MLifecycle.Observer() {
        @Override
        public void onCreate() {
            Log.i("Test", "=====onCreate=======");
        }

        @Override
        public void onStart() {
            Log.i("Test", "=====onStart=======");

        }

        @Override
        public void onResume() {
            Log.i("Test", "=====onResume=======");

        }

        @Override
        public void onPause() {
            Log.i("Test", "=====onPause=======");

        }

        @Override
        public void onStop() {
            Log.i("Test", "=====onStop=======");

        }

        @Override
        public void onDestroy() {
            Log.i("Test", "=====onDestroy=======");
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        testLifeCycle();

        MLifecycleProvider.get(MainActivity.this).unregisterObserver(observer);
    }

    @Override
    public MLifecycle getMLifecycle() {
        return MLifecycleProvider.get(this);
    }
}
