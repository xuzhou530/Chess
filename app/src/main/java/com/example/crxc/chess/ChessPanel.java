package com.example.crxc.chess;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by crxc on 2016/5/31.
 */
public class ChessPanel extends View {
    private int mPanelWidth;
    private int line = 10;
    private int lie = 9;
    private float mLineHight;
    private Paint mPaint;
    private int mPanelHight;
    private Bitmap mBingPiece;
    private Bitmap mZuPiece;
    private Bitmap mShiPiece;
    private Bitmap mShiBPiece;
    private Bitmap mJiangPiece;
    private Bitmap mShuaiPiece;
    private Bitmap mPaoPiece;
    private Bitmap mPaoBPiece;
    private Bitmap mXiangPiece;
    private Bitmap mXiangBPiece;
    private Bitmap mChePiece;
    private Bitmap mCheBPiece;
    private Bitmap mMaPiece;
    private Bitmap mMaBPiece;
    private float Bili = 0.75f;
    private boolean mIsRed = true;
    private ArrayList<ChessPoint> mRedArray = new ArrayList<ChessPoint>();
    private ArrayList<ChessPoint> mBlackArray = new ArrayList<ChessPoint>();
    private ArrayList<PanelPoint> mRedPoint = new ArrayList<PanelPoint>();
    private ArrayList<PanelPoint> mBlackPoint = new ArrayList<PanelPoint>();
    private ChessPoint cp1;
    private ChessPoint cp2;
    private ChessPoint cp3;
    private ChessPoint cp4;
    private ChessPoint cp5;
    private ChessPoint cp6;
    private ChessPoint cp7;
    private ChessPoint cp8;
    private ChessPoint cp9;
    private ChessPoint cp10;
    private ChessPoint cp11;
    private ChessPoint cp12;
    private ChessPoint cp13;
    private ChessPoint cp14;
    private ChessPoint cpb1;
    private ChessPoint cpb2;
    private ChessPoint cpb3;
    private ChessPoint cpb4;
    private ChessPoint cpb5;
    private ChessPoint cpb6;
    private ChessPoint cpb7;
    private ChessPoint cpb8;
    private ChessPoint cpb9;
    private ChessPoint cpb10;
    private ChessPoint cpb11;
    private ChessPoint cpb12;
    private ChessPoint cpb13;
    private ChessPoint cpb14;
    private ArrayList<PanelPoint> vaildPoint;
    private ArrayList<PanelPoint> mJggPoint;
    private ArrayList<PanelPoint> mHongPoint=new ArrayList<PanelPoint>();
    private ArrayList<PanelPoint> mHeiPoint=new ArrayList<PanelPoint>();
    private boolean mDianjiguo = false;
    private Bitmap bmp = null;
    private static final String TAG = "ChessPanel";
    private PanelPoint mSelectPiece;
    private ChessPoint chessPoint;
    private ChessPoint chessPoint1;

    public ChessPanel(Context context, AttributeSet attrs) {
        super(context, attrs);
        setBackgroundColor(0x44ff0000);
        init();
    }


    private void init() {
        mPaint = new Paint();
        mPaint.setColor(0x88000000);
        cshPieceBmp();


    }

    private void cshJgg() {
        PanelPoint p1 = new PanelPoint(3, 0, mLineHight);
        PanelPoint p2 = new PanelPoint(3, 1, mLineHight);
        PanelPoint p3 = new PanelPoint(3, 2, mLineHight);
        PanelPoint p4 = new PanelPoint(4, 0, mLineHight);
        PanelPoint p5 = new PanelPoint(4, 1, mLineHight);
        PanelPoint p6 = new PanelPoint(4, 2, mLineHight);
        PanelPoint p7 = new PanelPoint(5, 0, mLineHight);
        PanelPoint p8 = new PanelPoint(5, 1, mLineHight);
        PanelPoint p9 = new PanelPoint(5, 2, mLineHight);
        PanelPoint pb1 = new PanelPoint(3, 7, mLineHight);
        PanelPoint pb2 = new PanelPoint(3, 8, mLineHight);
        PanelPoint pb3 = new PanelPoint(3, 9, mLineHight);
        PanelPoint pb4 = new PanelPoint(4, 7, mLineHight);
        PanelPoint pb5 = new PanelPoint(4, 8, mLineHight);
        PanelPoint pb6 = new PanelPoint(4, 9, mLineHight);
        PanelPoint pb7 = new PanelPoint(5, 7, mLineHight);
        PanelPoint pb8 = new PanelPoint(5, 8, mLineHight);
        PanelPoint pb9 = new PanelPoint(5, 9, mLineHight);

        mJggPoint = new ArrayList<PanelPoint>();
        mJggPoint.add(p1);
        mJggPoint.add(p2);
        mJggPoint.add(p3);
        mJggPoint.add(p4);
        mJggPoint.add(p5);
        mJggPoint.add(p6);
        mJggPoint.add(p7);
        mJggPoint.add(p8);
        mJggPoint.add(p9);
        mJggPoint.add(pb1);
        mJggPoint.add(pb2);
        mJggPoint.add(pb3);
        mJggPoint.add(pb4);
        mJggPoint.add(pb5);
        mJggPoint.add(pb6);
        mJggPoint.add(pb7);
        mJggPoint.add(pb8);
        mJggPoint.add(pb9);

    }

    private void cshPieceBmp() {
        mBingPiece = BitmapFactory.decodeResource(getResources(), R.mipmap.bing);
        mZuPiece = BitmapFactory.decodeResource(getResources(), R.mipmap.zu);
        mShiPiece = BitmapFactory.decodeResource(getResources(), R.mipmap.shi);
        mShiBPiece = BitmapFactory.decodeResource(getResources(), R.mipmap.shib);
        mJiangPiece = BitmapFactory.decodeResource(getResources(), R.mipmap.jiang);
        mShuaiPiece = BitmapFactory.decodeResource(getResources(), R.mipmap.shuai);
        mPaoPiece = BitmapFactory.decodeResource(getResources(), R.mipmap.pao);
        mPaoBPiece = BitmapFactory.decodeResource(getResources(), R.mipmap.paob);
        mXiangPiece = BitmapFactory.decodeResource(getResources(), R.mipmap.xiang);
        mXiangBPiece = BitmapFactory.decodeResource(getResources(), R.mipmap.xiangb);
        mChePiece = BitmapFactory.decodeResource(getResources(), R.mipmap.che);
        mCheBPiece = BitmapFactory.decodeResource(getResources(), R.mipmap.cheb);
        mMaPiece = BitmapFactory.decodeResource(getResources(), R.mipmap.ma);
        mMaBPiece = BitmapFactory.decodeResource(getResources(), R.mipmap.mab);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int width = Math.min(widthSize, heightSize);
        setMeasuredDimension(width, width / 9 * 10);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mLineHight = w / 9;
        mPanelWidth = w;
        mPanelHight = h;
        int pieceWidth = (int) (mLineHight * Bili);
        cshPiece(pieceWidth);
        cshPoint();
        cshChessPoint();
        cshArray();
        cshJgg();
        cshHongPoint();
        cshHeiPoint();
    }

    private void cshHeiPoint() {
        for (int y = 5; y < 10; y++) {
            for (int x = 0; x < 9; x++) {
                mHeiPoint.add(new PanelPoint(x, y, mLineHight));
            }
        }
    }

    private void cshHongPoint() {
        for (int y = 0; y < 5; y++) {
            for (int x = 0; x < 9; x++) {
                mHongPoint.add(new PanelPoint(x, y, mLineHight));
            }
        }
    }

    private void cshArray() {
        mRedArray.add(cp1);
        mRedArray.add(cp2);
        mRedArray.add(cp3);
        mRedArray.add(cp4);
        mRedArray.add(cp5);
        mRedArray.add(cp6);
        mRedArray.add(cp7);
        mRedArray.add(cp8);
        mRedArray.add(cp9);
        mRedArray.add(cp10);
        mRedArray.add(cp11);
        mRedArray.add(cp12);
        mRedArray.add(cp13);
        mRedArray.add(cp14);
        mBlackArray.add(cpb1);
        mBlackArray.add(cpb2);
        mBlackArray.add(cpb3);
        mBlackArray.add(cpb4);
        mBlackArray.add(cpb5);
        mBlackArray.add(cpb6);
        mBlackArray.add(cpb7);
        mBlackArray.add(cpb8);
        mBlackArray.add(cpb9);
        mBlackArray.add(cpb10);
        mBlackArray.add(cpb11);
        mBlackArray.add(cpb12);
        mBlackArray.add(cpb13);
        mBlackArray.add(cpb14);
    }

    private void cshChessPoint() {
        cp1 = new ChessPoint(mRedPoint.get(0), mChePiece);
        cp2 = new ChessPoint(mRedPoint.get(1), mMaPiece);
        cp3 = new ChessPoint(mRedPoint.get(2), mXiangPiece);
        cp4 = new ChessPoint(mRedPoint.get(3), mShiPiece);
        cp5 = new ChessPoint(mRedPoint.get(4), mShuaiPiece);
        cp9 = new ChessPoint(mRedPoint.get(8), mChePiece);
        cp8 = new ChessPoint(mRedPoint.get(7), mMaPiece);
        cp7 = new ChessPoint(mRedPoint.get(6), mXiangPiece);
        cp6 = new ChessPoint(mRedPoint.get(5), mShiPiece);
        cp10 = new ChessPoint(mRedPoint.get(9),mBingPiece);
        cp11 = new ChessPoint(mRedPoint.get(10), mBingPiece);
        cp12 = new ChessPoint(mRedPoint.get(11), mBingPiece);
        cp13 = new ChessPoint(mRedPoint.get(12), mBingPiece);
        cp14 = new ChessPoint(mRedPoint.get(13), mBingPiece);

        cpb1 = new ChessPoint(mBlackPoint.get(0), mCheBPiece);
        cpb2 = new ChessPoint(mBlackPoint.get(1), mMaBPiece);
        cpb3 = new ChessPoint(mBlackPoint.get(2), mXiangBPiece);
        cpb4 = new ChessPoint(mBlackPoint.get(3), mShiBPiece);
        cpb5 = new ChessPoint(mBlackPoint.get(4), mJiangPiece);
        cpb9 = new ChessPoint(mBlackPoint.get(8), mCheBPiece);
        cpb8 = new ChessPoint(mBlackPoint.get(7), mMaBPiece);
        cpb7 = new ChessPoint(mBlackPoint.get(6), mXiangBPiece);
        cpb6 = new ChessPoint(mBlackPoint.get(5), mShiBPiece);
        cpb10 = new ChessPoint(mBlackPoint.get(9), mZuPiece);
        cpb11 = new ChessPoint(mBlackPoint.get(10), mZuPiece);
        cpb12 = new ChessPoint(mBlackPoint.get(11), mZuPiece);
        cpb13 = new ChessPoint(mBlackPoint.get(12), mZuPiece);
        cpb14 = new ChessPoint(mBlackPoint.get(13), mZuPiece);
    }

    private void cshPoint() {
        for (int i = 0; i < 9; i++) {
            mRedPoint.add(new PanelPoint(i, 0, mLineHight));
            mBlackPoint.add(new PanelPoint(i, 9, mLineHight));
        }
        for (int i = 0; i < 5; i++) {
            mRedPoint.add(new PanelPoint(2 * i, 3, mLineHight));
            mBlackPoint.add(new PanelPoint(2 * i, 6, mLineHight));
        }
    }

    private void cshPiece(int pieceWidth) {
        mBingPiece = Bitmap.createScaledBitmap(mBingPiece, pieceWidth, pieceWidth, false);
        mZuPiece = Bitmap.createScaledBitmap(mZuPiece, pieceWidth, pieceWidth, false);
        mShiPiece = Bitmap.createScaledBitmap(mShiPiece, pieceWidth, pieceWidth, false);
        mShiBPiece = Bitmap.createScaledBitmap(mShiBPiece, pieceWidth, pieceWidth, false);
        mJiangPiece = Bitmap.createScaledBitmap(mJiangPiece, pieceWidth, pieceWidth, false);
        mShuaiPiece = Bitmap.createScaledBitmap(mShuaiPiece, pieceWidth, pieceWidth, false);
        mPaoPiece = Bitmap.createScaledBitmap(mPaoPiece, pieceWidth, pieceWidth, false);
        mPaoBPiece = Bitmap.createScaledBitmap(mPaoBPiece, pieceWidth, pieceWidth, false);
        mXiangPiece = Bitmap.createScaledBitmap(mXiangPiece, pieceWidth, pieceWidth, false);
        mXiangBPiece = Bitmap.createScaledBitmap(mXiangBPiece, pieceWidth, pieceWidth, false);
        mChePiece = Bitmap.createScaledBitmap(mChePiece, pieceWidth, pieceWidth, false);
        mCheBPiece = Bitmap.createScaledBitmap(mCheBPiece, pieceWidth, pieceWidth, false);
        mMaPiece = Bitmap.createScaledBitmap(mMaPiece, pieceWidth, pieceWidth, false);
        mMaBPiece = Bitmap.createScaledBitmap(mMaBPiece, pieceWidth, pieceWidth, false);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawBoard(canvas);
        drawPieces(canvas);
    }

    private void drawPieces(Canvas canvas) {
        for (ChessPoint chessPoint : mRedArray) {
            Bitmap bitmap = chessPoint.getmBitmap();
            Point point = chessPoint.getmPoint();
            canvas.drawBitmap(bitmap, point.x, point.y, null);
        }
        for (ChessPoint chessPoint : mBlackArray) {
            Bitmap bitmap = chessPoint.getmBitmap();
            Point point = chessPoint.getmPoint();
            canvas.drawBitmap(bitmap, point.x, point.y, null);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        if (action == MotionEvent.ACTION_DOWN) {
            int x = (int) event.getX();
            int y = (int) event.getY();
             PanelPoint p = getVaildPoint(x, y);
            if (mDianjiguo == false) {
                Log.d(TAG, "onTouchEvent:第一次点击 ");
                getLuoZiPoint(p);
            } else {
                Log.d(TAG, "onTouchEvent: 第二次点击");

//                    if (vaildPoint.contains(p)) {
                        if (mIsRed == true) {
                            //加入新棋子
                            if (!mRedPoint.contains(p)) {
                                Log.d(TAG, "onTouchEvent: 1");
                                if (vaildPoint.contains(p)) {
                                    Log.d(TAG, "onTouchEvent: 2");

                                    mIsRed = false;
                                    mDianjiguo = !mDianjiguo;
                                    //                        吃子
                                    if (mBlackPoint.contains(p)) {
                                        mBlackPoint.remove(p);
                                        for (ChessPoint cp : mBlackArray) {
                                           if(cp.getmPoint().equals(p)){
                                            chessPoint1 = cp;
                                           }
                                        }
                                        mBlackArray.remove(chessPoint1);
                                        Log.d(TAG, "onTouchEvent:红方吃子 ");
                                    }
                                    mRedPoint.add(p);
                                    mRedArray.add(new ChessPoint(p, bmp));
                                    Log.d(TAG, "onTouchEvent: 红方加入新棋子");
                                    mRedPoint.remove(mSelectPiece);
                                    mRedArray.remove(chessPoint);
                                    Log.d(TAG, "onTouchEvent: 从红方棋子队列移除点击棋子");
                                    invalidate();
                                    Log.d(TAG, "onTouchEvent: 红方落子");
                                }
                            }else {
                                mDianjiguo=false;
                                getLuoZiPoint(p);
                                Log.d(TAG, "onTouchEvent: 取消选择棋子");
                            }
                        } else {

    //                        黑棋逻辑
                            if (!mBlackPoint.contains(p)) {
                                if (vaildPoint.contains(p)) {
                                    mIsRed = !mIsRed;

                                    mDianjiguo = !mDianjiguo;
                                    //                        吃子
                                    if (mRedPoint.contains(p)) {
                                        mRedPoint.remove(p);
                                        for (ChessPoint cp : mRedArray) {
                                            if(cp.getmPoint().equals(p)){
                                            chessPoint1 = cp;
                                            }
                                        }
                                        mRedArray.remove(chessPoint1);
                                        Log.d(TAG, "onTouchEvent:黑方吃子 ");
                                    }

                                    Log.d(TAG, "onTouchEvent:黑方落子 ");
                                    mBlackPoint.add(p);
                                    mBlackArray.add(new ChessPoint(p, bmp));
                                    Log.d(TAG, "onTouchEvent: 黑方加入新棋子");
                                    mBlackPoint.remove(mSelectPiece);
                                    mBlackArray.remove(chessPoint);
                                    Log.d(TAG, "onTouchEvent: 从黑方棋子队列移除点击棋子");
                                    invalidate();
                                }
                            }else {
                                mDianjiguo=false;
                                getLuoZiPoint(p);
                                Log.d(TAG, "onTouchEvent: 取消选择棋子");
                            }


                        }
//                    }
                }




            return true;
        }

        return super.onTouchEvent(event);

    }

    private void getLuoZiPoint(PanelPoint p) {
        if (mIsRed) {
            Log.d(TAG, "onTouchEvent: 红方选择棋子");
            if (mRedPoint.contains(p)) {
                Log.d(TAG, "onTouchEvent: 红方有效选择");
                mSelectPiece=p;

                for (ChessPoint cp : mRedArray) {
                    if (cp.getmPoint().equals(p)) {
                        bmp = cp.getmBitmap();
                        Log.d(TAG, "onTouchEvent: 取得点击棋子图像");
                        chessPoint = cp;
                        mDianjiguo = true;
                    }
                }
                if (bmp == mShuaiPiece) {
                    //                        设置图片闪烁
                    Log.d(TAG, "onTouchEvent: 点击了帅");
                    vaildPoint = ShuaiRule.getVaildPoint(p, mLineHight, mJggPoint);
                    Log.d(TAG, "onTouchEvent: 取得帅的有效位置");
                } else if (bmp == mShiPiece) {
                    //闪烁效果
                    Log.d(TAG, "onTouchEvent: 点击了红士");
                    vaildPoint = ShiRule.getVaildPoint(p, mLineHight, mJggPoint);
                    Log.d(TAG, "onTouchEvent: 取得士的有效位置");
                }else if (bmp == mXiangPiece) {
                    //闪烁效果
                    Log.d(TAG, "onTouchEvent: 点击了红象");
                    vaildPoint = XiangRule.getVaildPoint(p, mLineHight, mHongPoint,mRedPoint,mBlackPoint);
                    Log.d(TAG, "onTouchEvent: 取得象的有效位置");
                }else if (bmp == mMaPiece) {
                    //闪烁效果
                    Log.d(TAG, "onTouchEvent: 点击了红马");
                    vaildPoint = MaRule.getVaildPoint(p, mLineHight, mRedPoint,mBlackPoint);
                    Log.d(TAG, "onTouchEvent: 取得马的有效位置");
                }else if (bmp == mChePiece) {
                    //闪烁效果
                    Log.d(TAG, "onTouchEvent: 点击了红车");
                    vaildPoint = CheRule.getVaildPoint(p, mLineHight, mRedPoint,mBlackPoint);
                    Log.d(TAG, "onTouchEvent: 取得车的有效位置");
                }else if (bmp == mBingPiece) {
                    //闪烁效果
                    Log.d(TAG, "onTouchEvent: 点击了兵");
                    vaildPoint = BingRule.getVaildPoint(p, mLineHight,mHongPoint);
                    Log.d(TAG, "onTouchEvent: 取得兵的有效位置");
                }

            }

            //                mRedArray.add(new ChessPoint(p,));
        } else {
            //                黑棋走棋
            Log.d(TAG, "onTouchEvent: 黑方选择棋子");
            if (mBlackPoint.contains(p)) {
                Log.d(TAG, "onTouchEvent: 黑方有效选择");
                mSelectPiece=p;

                for (ChessPoint cp : mBlackArray) {
                    if (cp.getmPoint().equals(p)) {
                        bmp = cp.getmBitmap();
                        Log.d(TAG, "onTouchEvent: 取得点击棋子图像");
                        chessPoint = cp;
                        mDianjiguo = !mDianjiguo;
                    }
                }

                if (bmp == mJiangPiece) {
                    Log.d(TAG, "onTouchEvent: 点击了将");

                    //                        设置图片闪烁
                    vaildPoint = JiangRule.getVaildPoint(p, mLineHight, mJggPoint);
                    Log.d(TAG, "onTouchEvent: 取得将的有效位置");
                } else {
                    if (bmp == mShiBPiece) {
                        Log.d(TAG, "onTouchEvent: 点击了黑士");

                        //                        设置图片闪烁
                        vaildPoint = ShiBRule.getVaildPoint(p, mLineHight, mJggPoint);
                        Log.d(TAG, "onTouchEvent: 取得黑士的有效位置");
                    }else if (bmp == mXiangBPiece) {
                        //闪烁效果
                        Log.d(TAG, "onTouchEvent: 点击了黑象");
                        vaildPoint = XiangRule.getVaildPoint(p, mLineHight, mHeiPoint,mRedPoint,mBlackPoint);
                        Log.d(TAG, "onTouchEvent: 取得黑象的有效位置");
                    }else if (bmp == mMaBPiece) {
                        //闪烁效果
                        Log.d(TAG, "onTouchEvent: 点击了黑马");
                        vaildPoint = MaRule.getVaildPoint(p, mLineHight, mRedPoint,mBlackPoint);
                        Log.d(TAG, "onTouchEvent: 取得黑马的有效位置");
                    }else if (bmp == mCheBPiece) {
                        //闪烁效果
                        Log.d(TAG, "onTouchEvent: 点击了黑车");
                        vaildPoint = CheRule.getVaildPoint(p, mLineHight, mRedPoint,mBlackPoint);
                        Log.d(TAG, "onTouchEvent: 取得黑车的有效位置");
                    }else if (bmp == mZuPiece) {
                        //闪烁效果
                        Log.d(TAG, "onTouchEvent: 点击了卒");
                        vaildPoint = ZuRule.getVaildPoint(p, mLineHight,mHongPoint);
                        Log.d(TAG, "onTouchEvent: 取得卒的有效位置");
                    }

                }
            }
        }

    }

    private PanelPoint getVaildPoint(int x, int y) {
        PanelPoint p = new PanelPoint((int) (x / mLineHight), (int) ((10 * mLineHight - y) / mLineHight), mLineHight);
        return p;
    }


    private void drawBoard(Canvas canvas) {
        ZhPoint p1 = new ZhPoint(3, 0, mLineHight);
        ZhPoint p2 = new ZhPoint(3, 2, mLineHight);
        ZhPoint p3 = new ZhPoint(5, 0, mLineHight);
        ZhPoint p4 = new ZhPoint(5, 2, mLineHight);
        ZhPoint pB1 = new ZhPoint(3, 7, mLineHight);
        ZhPoint pB2 = new ZhPoint(3, 9, mLineHight);
        ZhPoint pB3 = new ZhPoint(5, 7, mLineHight);
        ZhPoint pB4 = new ZhPoint(5, 9, mLineHight);
        canvas.drawLine(p1.x, p1.y, p4.x, p4.y, mPaint);
        canvas.drawLine(p2.x, p2.y, p3.x, p3.y, mPaint);
        canvas.drawLine(pB1.x, pB1.y, pB4.x, pB4.y, mPaint);
        canvas.drawLine(pB2.x, pB2.y, pB3.x, pB3.y, mPaint);

        for (int i = 0; i < 10; i++) {
            int startX = (int) mLineHight / 2;
            int endX = (int) (mPanelWidth - startX);
            int y = (int) ((0.5 + i) * mLineHight);
            canvas.drawLine(startX, y, endX, y, mPaint);
        }
        for (int i = 0; i < 9; i++) {
            int startY1 = (int) mLineHight / 2;
            int endY1 = (int) (startY1 + (mPanelHight - mLineHight) * 4 / 9);
            int startY2 = (int) (startY1 + (mPanelHight - mLineHight) * 5 / 9);
            int endY2 = (int) (mPanelHight - startY1);
            int x = (int) ((0.5 + i) * mLineHight);
            canvas.drawLine(x, startY1, x, endY1, mPaint);
            canvas.drawLine(x, startY2, x, endY2, mPaint);
        }
        drawText(canvas);
    }

    private void drawText(Canvas canvas) {
//        Point point=new Point((int)(7.5*mLineHight),(int)(4.8*mLineHight));
        mPaint.setTextSize(40);
        canvas.drawText("楚河", (int) 7.5 * mLineHight, (int) 5.8 * mLineHight, mPaint);
        canvas.drawText("汉界", (int) 1.8 * mLineHight, (int) 5.8 * mLineHight, mPaint);
    }


}
