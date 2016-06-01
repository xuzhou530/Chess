package com.example.crxc.chess;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

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
    private ArrayList<Point> mRedPoint = new ArrayList<Point>();
    private ArrayList<Point> mBlackPoint = new ArrayList<Point>();
    private ChessPoint cp1;
    private ChessPoint cp2;
    private ChessPoint cp3;
    private ChessPoint cp4;
    private ChessPoint cp5;
    private ChessPoint cp6;
    private ChessPoint cp7;
    private ChessPoint cp8;
    private ChessPoint cp9;
    private ChessPoint cpb1;
    private ChessPoint cpb2;
    private ChessPoint cpb3;
    private ChessPoint cpb4;
    private ChessPoint cpb5;
    private ChessPoint cpb6;
    private ChessPoint cpb7;
    private ChessPoint cpb8;
    private ChessPoint cpb9;

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
        mBlackArray.add(cpb1);
        mBlackArray.add(cpb2);
        mBlackArray.add(cpb3);
        mBlackArray.add(cpb4);
        mBlackArray.add(cpb5);
        mBlackArray.add(cpb6);
        mBlackArray.add(cpb7);
        mBlackArray.add(cpb8);
        mBlackArray.add(cpb9);
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
        cpb1 = new ChessPoint(mBlackPoint.get(0), mCheBPiece);
        cpb2 = new ChessPoint(mBlackPoint.get(1), mMaBPiece);
        cpb3 = new ChessPoint(mBlackPoint.get(2), mXiangBPiece);
        cpb4 = new ChessPoint(mBlackPoint.get(3), mShiBPiece);
        cpb5 = new ChessPoint(mBlackPoint.get(4), mJiangPiece);
        cpb9 = new ChessPoint(mBlackPoint.get(8), mCheBPiece);
        cpb8 = new ChessPoint(mBlackPoint.get(7), mMaBPiece);
        cpb7 = new ChessPoint(mBlackPoint.get(6), mXiangBPiece);
        cpb6 = new ChessPoint(mBlackPoint.get(5), mShiBPiece);
    }

    private void cshPoint() {
        for (int i = 0; i < 9; i++) {
            mRedPoint.add(new Point((int) ((i + 0.125) * mLineHight), (int) (mLineHight / 2 + 8.625 * mLineHight)));
            mBlackPoint.add(new Point((int) (mLineHight / 8 + i * mLineHight), (int) mLineHight / 8));
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
            Matrix m=new Matrix();
            m.postTranslate(point.x,point.y);
            m.postRotate(180,(int)(point.x+0.375*mLineHight),(int)(point.y+0.375*mLineHight));
            canvas.drawBitmap(bitmap,m, null);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        if (action == MotionEvent.ACTION_DOWN) {
            int x = (int) event.getX();
            int y = (int) event.getY();
            Point p = getVaildPoint(x, y);

            if (mIsRed) {
//                mRedArray.add(new ChessPoint(p,));
            } else {
//                mBlackArray.add(p);
            }
            return true;
        }
        return super.onTouchEvent(event);

    }

    private Point getVaildPoint(int x, int y) {
        return new Point((int) (x / mLineHight), (int) (y / mLineHight));
    }


    private void drawBoard(Canvas canvas) {

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

