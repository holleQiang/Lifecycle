package com.zhangqiang.lifecycle;

import java.util.ArrayList;
import java.util.List;

public final class MLifecycle {

    private State mState = State.IDLE;
    private List<Observer> observers;

    public void registerObserver(Observer observer) {
        if (observers == null) {
            observers = new ArrayList<>();
        }
        if (observers.contains(observer)) {
            return;
        }
        observers.add(observer);
    }

    public void unregisterObserver(Observer observer) {
        if (observers == null) {
            return;
        }
        observers.remove(observer);
    }

    public interface Observer {

        void onCreate();

        void onStart();

        void onResume();

        void onPause();

        void onStop();

        void onDestroy();
    }


    void notifyCreate() {
        mState = State.CREATED;
        if (observers == null) {
            return;
        }
        for (int i = observers.size() - 1; i >= 0; i--) {
            observers.get(i).onCreate();
        }
    }

    void notifyStart() {
        mState = State.START;
        if (observers == null) {
            return;
        }
        for (int i = observers.size() - 1; i >= 0; i--) {
            observers.get(i).onStart();
        }
    }

    void notifyResume() {
        mState = State.RESUME;
        if (observers == null) {
            return;
        }
        for (int i = observers.size() - 1; i >= 0; i--) {
            observers.get(i).onResume();
        }
    }

    void notifyPause() {
        mState = State.PAUSE;
        if (observers == null) {
            return;
        }
        for (int i = observers.size() - 1; i >= 0; i--) {
            observers.get(i).onPause();
        }
    }

    void notifyStop() {
        mState = State.STOP;
        if (observers == null) {
            return;
        }
        for (int i = observers.size() - 1; i >= 0; i--) {
            observers.get(i).onStop();
        }
    }

    void notifyDestroy() {
        mState = State.DESTROY;
        if (observers == null) {
            return;
        }
        for (int i = observers.size() - 1; i >= 0; i--) {
            observers.get(i).onDestroy();
        }
    }

    public State getCurrentState() {
        return mState;
    }

    public enum State {

        IDLE(-1),

        CREATED(0),

        START(1),

        RESUME(2),

        PAUSE(3),

        STOP(4),

        DESTROY(5);

        private int value;

        State(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    private void notifyByState(State state) {

        switch (state) {
            case CREATED:
                notifyCreate();
                break;
            case START:
                notifyCreate();
                notifyStart();
                break;
            case RESUME:
                notifyCreate();
                notifyStart();
                notifyResume();
                break;
            case PAUSE:
                notifyCreate();
                notifyStart();
                notifyResume();
                notifyPause();
                break;
            case STOP:
                notifyCreate();
                notifyStart();
                notifyResume();
                notifyPause();
                notifyStop();
                break;
            case DESTROY:
                notifyCreate();
                notifyStart();
                notifyResume();
                notifyPause();
                notifyStop();
                notifyDestroy();
                break;
        }
    }
}
