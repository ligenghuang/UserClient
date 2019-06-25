package com.yizhitong.userclient.utils.cusview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.SweepGradient;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

import com.yizhitong.userclient.R;
import com.yizhitong.userclient.utils.data.DisplayUtil;

import java.text.DecimalFormat;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.widget.ListPopupWindow.WRAP_CONTENT;
/**
* description ： 渐变圆环
* author : lgh
* email : 1045105946@qq.com
* date : 2019/6/24
*/
public class ScoreCircle extends View {
    private static final String TAG = "ScoreCircleLog";
    private int circleRadius;//圆半径
    private int circleStroke;//线宽
    private int totalSize;//View总大小
    private int padding;

    private int firstCircleColor;
    private int secondStartColor;
    private int secondEndColor;
    private SweepGradient mGradient;
    private Matrix gradientMatrix;

    private int frontColor;
    private int frontSize;

    private int totalAngle = 349;//最多只能转350度，不然会重合

    private float totalScore = 100f; //总分100
    private float currentScore = 60f;//
    private DecimalFormat decimalFormat=new DecimalFormat(".0");

    private Paint mPaint;

    public ScoreCircle(Context context) {
        super(context);
    }

    public ScoreCircle(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ScoreCircle(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(attrs,context);
        initPaint();
    }

    private void initAttrs(AttributeSet attrs,Context context) {

        TypedArray typedArray = this.getContext().getTheme().obtainStyledAttributes(attrs, R.styleable.ScoreCircle, 0, 0);

        try {
            int width = typedArray.getLayoutDimension(R.styleable.ScoreCircle_ScoreCircle_android_layout_width, (int) this.getResources().getDimension(R.dimen.scoreCircle_size));
            int height = typedArray.getLayoutDimension(R.styleable.ScoreCircle_ScoreCircle_android_layout_height, (int)this.getResources().getDimension(R.dimen.scoreCircle_size));
            setTotalSize(width,height);
            padding = (int) typedArray.getDimension(R.styleable.ScoreCircle_ScoreCircle_padding, this.getResources().getDimension(R.dimen.dp_10));
            circleStroke = (int) typedArray.getDimension(R.styleable.ScoreCircle_ScoreCircle_stroke, this.getResources().getDimension(R.dimen.dp_10));
            firstCircleColor = typedArray.getColor(R.styleable.ScoreCircle_ScoreCircle_firstCircleColor, ContextCompat.getColor(context,R.color.color_f4));
            secondStartColor = typedArray.getColor(R.styleable.ScoreCircle_ScoreCircle_sendColorStart,ContextCompat.getColor(context,R.color.color_f4));
            secondEndColor = typedArray.getColor(R.styleable.ScoreCircle_ScoreCircle_sendColorEnd,ContextCompat.getColor(context,R.color.color_f4));
            frontColor = typedArray.getColor(R.styleable.ScoreCircle_ScoreCircle_front_color,ContextCompat.getColor(context,R.color.color_f4));
            frontSize = (int) typedArray.getDimension(R.styleable.ScoreCircle_ScoreCircle_front_size, DisplayUtil.dpToPx(22));

        } finally {
            typedArray.recycle();
        }

    }

    public void setScore(float score) {
        if (score > 100)
            score = 100;
        if (score < 0)
            score = 0;

        this.currentScore = score;
        invalidate();
    }

    private void setTotalSize(int width,int height){
        if (width == WRAP_CONTENT || width == MATCH_PARENT){
            if (width == WRAP_CONTENT){
                totalSize = (int)this.getResources().getDimension(R.dimen.scoreCircle_size);
            }
        }else {
            if (width > height){
                totalSize = width;
            }else
                totalSize = height;
        }
    }

    private void initPaint(){
        mPaint = new Paint();
        mGradient = new SweepGradient(0,0,secondStartColor,secondEndColor);
        gradientMatrix = new Matrix();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        totalSize = resolveSize(totalSize,widthMeasureSpec);
        circleRadius = totalSize/2 - padding - circleStroke/2;
        setMeasuredDimension(totalSize,totalSize);
    }

    //  invalidate();  ondraw
    //  requestLayout(); onMeasure onlayout

    @Override
    protected void onDraw(Canvas canvas) {
        drawFirstCircle(canvas);
        drawSecondCircle(canvas);
//        drawText(canvas);
    }
    //底部圆
    private void drawFirstCircle(Canvas canvas) {
        mPaint.reset();
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(circleStroke);
        mPaint.setColor(firstCircleColor);
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(totalSize/2,totalSize/2,circleRadius,mPaint);
    }
    //表层圆
    @SuppressLint("NewApi")
    private void drawSecondCircle(Canvas canvas) {
        mPaint.reset();
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(circleStroke+2);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setShader(mGradient);

        float angle = (currentScore / totalScore)*totalAngle;
        canvas.save();

        gradientMatrix.setTranslate(totalSize/2,totalSize/2);
        mGradient.setLocalMatrix(gradientMatrix);
        // gradientMatrix.setRotate(0.9f,totalSize/2,totalSize/2);
        canvas.rotate(-90,totalSize/2,totalSize/2);
        //  mGradient.setLocalMatrix(gradientMatrix);

        canvas.drawArc(padding+circleStroke/2, padding+circleStroke/2 ,
                totalSize - padding-circleStroke/2, totalSize - padding-circleStroke/2,
                6,angle,false,mPaint
        );

        canvas.restore();
    }
//    //中间文字
//    private void drawText(Canvas canvas) {
//        mPaint.reset();
//        mPaint.setAntiAlias(true);
//        mPaint.setFakeBoldText(true);
//        Rect textBounds = new Rect();
//        String score = decimalFormat.format(currentScore);
//        mPaint.setTextSize(frontSize);
//        frontColor = ContextCompat.getColor(getContext(),R.color.f4);
//        mPaint.setColor(frontColor);
//        mPaint.getTextBounds(score,0,score.length(),textBounds);
//        canvas.drawText(score,totalSize/2 - textBounds.width()/2 - textBounds.left,
//                totalSize/2 + textBounds.height()/2 - textBounds.bottom  ,mPaint);
//    }
}
