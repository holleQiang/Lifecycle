package com.zhangqiang.lifecycle;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import com.zhangqiang.holderfragment.HolderFragment;
import com.zhangqiang.holderfragment.SimpleLifecycleCallback;

public class MLifecycleProvider {

    private static final String TAG_LIFECYCLE = "com.zhangqiang.lifecycle.LifecycleProvider2_tag";

    public static MLifecycle get(FragmentActivity activity){
        return getFromHolderFragment(HolderFragment.forActivity(activity));
    }

    public static MLifecycle get(Fragment fragment){
        return getFromHolderFragment(HolderFragment.forFragment(fragment));
    }

    private static MLifecycle getFromHolderFragment(final HolderFragment holderFragment) {
        MLifecycle lifecycle;
        Object tag = holderFragment.getTag(TAG_LIFECYCLE);
        if (tag instanceof MLifecycle) {
            lifecycle = (MLifecycle) tag;
        } else {
            lifecycle = new MLifecycle();
            holderFragment.setTag(TAG_LIFECYCLE, lifecycle);
            holderFragment.registerLifecycleCallback(new SimpleLifecycleCallback(){

                @Override
                public void onActivityCreated(Bundle savedInstanceState) {
                    super.onActivityCreated(savedInstanceState);
                    Object lifecycleTag = holderFragment.getTag(TAG_LIFECYCLE);
                    if (lifecycleTag instanceof MLifecycle) {
                        ((MLifecycle) lifecycleTag).notifyCreate();
                    }
                }

                @Override
                public void onStart() {
                    super.onStart();
                    Object lifecycleTag = holderFragment.getTag(TAG_LIFECYCLE);
                    if (lifecycleTag instanceof MLifecycle) {
                        ((MLifecycle) lifecycleTag).notifyStart();
                    }
                }

                @Override
                public void onResume() {
                    super.onResume();
                    Object lifecycleTag = holderFragment.getTag(TAG_LIFECYCLE);
                    if (lifecycleTag instanceof MLifecycle) {
                        ((MLifecycle) lifecycleTag).notifyResume();
                    }
                }

                @Override
                public void onPause() {
                    super.onPause();
                    Object lifecycleTag = holderFragment.getTag(TAG_LIFECYCLE);
                    if (lifecycleTag instanceof MLifecycle) {
                        ((MLifecycle) lifecycleTag).notifyPause();
                    }
                }

                @Override
                public void onStop() {
                    super.onStop();
                    Object lifecycleTag = holderFragment.getTag(TAG_LIFECYCLE);
                    if (lifecycleTag instanceof MLifecycle) {
                        ((MLifecycle) lifecycleTag).notifyStop();
                    }
                }

                @Override
                public void onDetach() {
                    super.onDetach();
                    Object lifecycleTag = holderFragment.getTag(TAG_LIFECYCLE);
                    if (lifecycleTag instanceof MLifecycle) {
                        ((MLifecycle) lifecycleTag).notifyDestroy();
                    }
                }

                @Override
                public void onDestroy() {
                    super.onDestroy();
                    Object lifecycleTag = holderFragment.getTag(TAG_LIFECYCLE);
                    if (lifecycleTag instanceof MLifecycle) {
                        ((MLifecycle) lifecycleTag).notifyDestroy();
                    }
                    holderFragment.unregisterLifecycleCallback(this);
                }
            });
        }
        return lifecycle;
    }
}
