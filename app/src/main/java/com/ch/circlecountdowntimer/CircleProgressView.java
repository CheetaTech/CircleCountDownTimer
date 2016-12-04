package com.ch.circlecountdowntimer;

/**
 * Created by Ali.Guvenbas on 3.12.2016.
 */

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.IntDef;
import android.util.AttributeSet;
import android.view.View;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class CircleProgressView extends View {
    private Paint circlePaint;
    private Paint textPaint;
    private Paint secondCirclePaint;
    private int circleWidth = 30;
    private int circleColor = Color.RED;
    private int circleRadius = 40;
    private Paint.Style secondCirclePaintStyle = Paint.Style.STROKE;
    private int secondCircleColor = Color.WHITE;
    private int textSize = 20;
    private int textColor = Color.BLACK;
    private Paint.FontMetricsInt fontMetrics;
    private float progress = 0;
    private int milisecond = 1000;
    private int progressMode = DESC;
    public static final int ASC = 0;
    public static final int DESC = 1;
    private FinishListener mFinishListener;
    private ProgressHandler progressHandler;

    public CircleProgressView(final Context context) {
        super(context);
        init(context, null);
    }

    public CircleProgressView(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public interface FinishListener {
        void finish();
    }

    @Retention(RetentionPolicy.RUNTIME)
    @IntDef({ASC, DESC})
    public @interface ProgressMode {
    }

    private void init(final Context context, final AttributeSet attrs) {
        progressHandler = new ProgressHandler(this);

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.CircleProgressView);
        circleWidth = (int) array.getDimension(R.styleable.CircleProgressView_circle_width, circleWidth);
        circleColor = array.getColor(R.styleable.CircleProgressView_circle_color, circleColor);
        circleRadius = (int) array.getDimension(R.styleable.CircleProgressView_circle_radius, circleRadius);
        secondCircleColor = array.getColor(R.styleable.CircleProgressView_second_circle_color, secondCircleColor);
        textColor = array.getColor(R.styleable.CircleProgressView_text_color, textColor);
        textSize = (int) array.getDimension(R.styleable.CircleProgressView_text_size, textSize);

        intCirclePaint();
        intTextPaint();
        initSecondCirclePaint();
        array.recycle();
    }

    private void initSecondCirclePaint() {
        secondCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        secondCirclePaint.setStyle(secondCirclePaintStyle);
        secondCirclePaint.setColor(secondCircleColor);
        secondCirclePaint.setStrokeWidth(circleWidth);
    }

    private void intCirclePaint() {
        circlePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        circlePaint.setStyle(Paint.Style.STROKE);
        circlePaint.setColor(circleColor);
        circlePaint.setStrokeWidth(circleWidth);
    }

    private void intTextPaint() {
        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        textPaint.setColor(textColor);
        textPaint.setTextSize(textSize);
        textPaint.setTextAlign(Paint.Align.CENTER);
        fontMetrics = textPaint.getFontMetricsInt();
    }

    @Override
    protected void onDraw(final Canvas canvas) {
        super.onDraw(canvas);
        final RectF rectF = new RectF(circleWidth, circleWidth, canvas.getWidth() - circleWidth, canvas.getHeight() - circleWidth);
        canvas.drawOval(rectF, circlePaint);
        if (secondCirclePaintStyle == Paint.Style.FILL) {
            canvas.drawArc(rectF, -90, (progress / milisecond) * 360, true, secondCirclePaint);
        } else {
            canvas.drawArc(rectF, -90, (progress / milisecond) * 360, false, secondCirclePaint);
        }

        int baseline = (int) ((rectF.bottom + rectF.top - fontMetrics.bottom - fontMetrics.top) / 2);
        String text = String.format("%02d", (((int) (milisecond - progress) / 1000)) / 60) + ":" + String.format("%02d", (((int) (milisecond - progress) / 1000)) % 60);

        canvas.drawText(text, rectF.centerX(), baseline, textPaint);
    }

    public void setMiliSecond(final int milisecond) {
        this.milisecond = milisecond;
    }

    public void setSecond(final int second) {
        this.milisecond = second * 1000;
    }

    public void setMinute(final int minute) {
        this.milisecond = minute * 1000 * 60;
    }

    public void start() {
        progressHandler.sendEmptyMessage(1);
    }

    public void stop() {
        progressHandler.sendEmptyMessage(2);
    }

    public void reset() {
        progress = 0;
        invalidate();
    }

    public void speedUp() {
       // progress = 0;
        progressHandler.sendEmptyMessage(4);
        invalidate();
        progressHandler.sendEmptyMessageDelayed(1, 100);
    }

    public void speedDown() {
        //progress = 0;
        progressHandler.sendEmptyMessage(3);
        invalidate();
        progressHandler.sendEmptyMessageDelayed(1, -100);
    }

    public void finish() {
        if (mFinishListener != null) {
            mFinishListener.finish();
            progress = 0;
        }
    }

    public void addProgress() {
        progressUp();
        progressHandler.sendEmptyMessageDelayed(1, 100);
    }

    public void progressUp() {
        progress += 100;
        invalidate();
    }

    public void progressDown() {
        progress -= 100;
        invalidate();
    }


    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        progressHandler.removeCallbacksAndMessages(null);
    }

    public int getCircleWidth() {
        return circleWidth;
    }

    public void setCircleWidth(final int circleWidth) {
        this.circleWidth = circleWidth;
    }

    public int getCircleColor() {
        return circleColor;
    }

    public void setCircleColor(final int circleColor) {
        this.circleColor = circleColor;
    }

    public int getCircleRadius() {
        return circleRadius;
    }

    public void setCircleRadius(final int circleRadius) {
        this.circleRadius = circleRadius;
    }

    public int getSecondCircleColor() {
        return secondCircleColor;
    }

    public void setSecondCircleColor(final int secondCircleColor) {
        this.secondCircleColor = secondCircleColor;
    }

    public int getTextSize() {
        return textSize;
    }

    public void setTextSize(final int textSize) {
        this.textSize = textSize;
    }

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(final int textColor) {
        this.textColor = textColor;
    }

    public int getMilisecond() {
        return milisecond;
    }

    public float getProgress() {
        return progress;
    }

    public Paint.Style getPaintStyle() {
        return secondCirclePaintStyle;
    }

    public void setPaintStyle(final Paint.Style secondCirclePaintStyle) {
        this.secondCirclePaintStyle = secondCirclePaintStyle;
        secondCirclePaint.setStyle(secondCirclePaintStyle);
    }

    public int getProgressMode() {
        return progressMode;
    }

    public void setProgress(final float progress) {
        this.progress = progress;
        if (progress > milisecond && mFinishListener != null) {
            mFinishListener.finish();
        }
        invalidate();
    }


    public void setProgressMode(@ProgressMode final int progressMode) {
        this.progressMode = progressMode;
        if (progressMode == ASC) {
            milisecond = 100;
        }
    }


    public void setFinishListener(final FinishListener finishListener) {
        mFinishListener = finishListener;
    }

}