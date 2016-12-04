package com.ch.circlecountdowntimer;

import android.os.Handler;
import android.os.Message;

import java.lang.ref.WeakReference;

/**
 * Created by Ali.Guvenbas on 4.12.2016.
 */

public class ProgressHandler extends Handler {
    WeakReference<CircleProgressView> mCircleReference;

    ProgressHandler(CircleProgressView circleProgressView) {
        mCircleReference = new WeakReference<>(circleProgressView);
    }

    @Override
    public void handleMessage(Message msg) {
        final CircleProgressView circleProgressView = mCircleReference.get();
        if (circleProgressView == null) {
            return;
        }
        switch(msg.what){
            case 1://start,speedUp
                this.removeMessages(0);
                circleProgressView.addProgress();
                break;
            case 2://stop
                this.removeMessages(0);
                removeCallbacksAndMessages(null);
                break;
            case 3://speedDown
                this.removeMessages(0);
                circleProgressView.progressDown();
                break;
            case 4://speedUp
                this.removeMessages(0);
                circleProgressView.progressUp();
                break;
            default:
                super.handleMessage(msg);
                break;
        }
        if (circleProgressView.getProgress() >= circleProgressView.getMilisecond()) {
            circleProgressView.finish();
            removeMessages(1);
        }
    }
}
