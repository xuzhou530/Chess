package com.example.crxc.chess.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.crxc.chess.R;
import com.example.crxc.chess.bean.PanelPoint;
import com.example.crxc.chess.bean.ChessPoint;
import com.example.crxc.chess.bean.ZhPoint;
import com.example.crxc.chess.model.ChessModel;
import com.example.crxc.chess.presenter.CshPresenter;
import com.example.crxc.chess.presenter.MoveChessPresenter;


import java.util.ArrayList;

/**
 * Created by crxc on 2016/6/9.
 */
public class ChessPanelView extends View implements IChessPanelView{
    private Paint mPaint;
    private CshPresenter mPresent = null;
    private MoveChessPresenter mMovePresent = null;
    private String text;
    ChessModel mChessModel = new ChessModel(getContext());
    private static final String TAG = "ChessPanelView";


    public ChessPanelView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPresent = new CshPresenter(this, mChessModel);
        mMovePresent = new MoveChessPresenter(this, mChessModel);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(5f);
        cshPieceBmp();
//        LayoutInflater inflater=LayoutInflater.from(getContext().getApplicationContext());
//        View layout=inflater.inflate(R.layout.activity_main,null);

    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int width = Math.min(widthSize, heightSize);
        setMeasuredDimension(width, width / 9 * 10);
    }

    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        csh(w, h);
//        Button bt1 = (Button) getRootView().findViewById(R.id.player1huiqi);
//        bt1.setOnClickListener(this);
//        Button bt4 = (Button) getRootView().findViewById(R.id.player2huiqi);
//        bt4.setOnClickListener(this);
//        Button bt5 = (Button)  getRootView().findViewById(R.id.player2qiuhe);
//        bt5.setOnClickListener(this);
//        Button bt6 = (Button)  getRootView().findViewById(R.id.player2surrender);
//        bt6.setOnClickListener(this);
    }

    public void csh(int w, int h) {
        mPresent.csh(w, h);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawBoard(canvas);
        drawPieces(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        if (action == MotionEvent.ACTION_DOWN) {
            Log.d(TAG, "onTouchEvent:down ");
            int x = (int) event.getX();
            int y = (int) event.getY();
            PanelPoint p = getVaildPoint(x, y);
            mMovePresent.MovePiece(p);
            return true;
        }

        return super.onTouchEvent(event);
    }

    private void drawPieces(Canvas canvas) {
        ArrayList<ChessPoint> mRedArray = getRedArray();
        ArrayList<ChessPoint> mBlackArray = getBlackArray();
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

    private void drawBoard(Canvas canvas) {
        float mLineHight = mPresent.getLineHight();
        int mPanelHight = mPresent.getPanelHight();
        int mPanelWidth = mPresent.getPanelWidth();
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

    }


    @Override
    public ArrayList<ChessPoint> getRedArray() {
        return mPresent.getRedArray();
    }

    @Override
    public ArrayList<ChessPoint> getBlackArray() {
        return mPresent.getBlackArray();
    }

    @Override
    public float getLineHight() {
        return mPresent.getLineHight();
    }

    @Override
    public int getPanelWidth() {
        return mPresent.getPanelWidth();
    }

    @Override
    public int getPanelHight() {
        return mPresent.getPanelHight();
    }

    @Override
    public PanelPoint getVaildPoint(int x, int y) {
        float mLineHight = mPresent.getLineHight();
        PanelPoint p = new PanelPoint((int) (x / mLineHight), (int) ((10 * mLineHight - y) / mLineHight), mLineHight);
        return p;
    }

    @Override
    public void PlaySelectMusic() {
        MediaPlayer player = new MediaPlayer().create(getContext(), R.raw.select_panel);
        player.start();
        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.release();
            }
        });
    }



    @Override
    public void setText(int redWin) {
        text = getResources().getString(redWin);
    }

    @Override
    public void ShowDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(text);
        builder.setMessage(getResources().getString(R.string.OneMoreGame));
        builder.setPositiveButton(getResources().getString(R.string.OneMoreGameYes), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                refresh();
            }
        });
        builder.create().show();

    }

    private void refresh() {
        mPresent.refresh();
    }

    @Override
    public void PlayChiZiMusic() {
        MediaPlayer player = new MediaPlayer().create(getContext(), R.raw.chize);
        player.start();
        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.release();
            }
        });

    }

    @Override
    public void ShowJiangJunToast() {
        Toast toast = new Toast(getContext());
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View toast_view = inflater.inflate(R.layout.layout_jiangjun_toast, null);
        toast.setView(toast_view);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    @Override
    public void cshPieceBmp() {
        mPresent.setBingPiece(BitmapFactory.decodeResource(getResources(), R.mipmap.bing));
        mPresent.setShuaiPiece(BitmapFactory.decodeResource(getResources(), R.mipmap.shuai));
        mPresent.setShiPiece(BitmapFactory.decodeResource(getResources(), R.mipmap.shi));
        mPresent.setXiangPiece(BitmapFactory.decodeResource(getResources(), R.mipmap.xiang));
        mPresent.setMaPiece(BitmapFactory.decodeResource(getResources(), R.mipmap.ma));
        mPresent.setChePiece(BitmapFactory.decodeResource(getResources(), R.mipmap.che));
        mPresent.setPaoPiece(BitmapFactory.decodeResource(getResources(), R.mipmap.pao));
        mPresent.setZuPiece(BitmapFactory.decodeResource(getResources(), R.mipmap.zu));
        mPresent.setJiangPiece(BitmapFactory.decodeResource(getResources(), R.mipmap.jiang));
        mPresent.setShiBPiece(BitmapFactory.decodeResource(getResources(), R.mipmap.shib));
        mPresent.setXiangBPiece(BitmapFactory.decodeResource(getResources(), R.mipmap.xiangb));
        mPresent.setMaBPiece(BitmapFactory.decodeResource(getResources(), R.mipmap.mab));
        mPresent.setCheBPiece(BitmapFactory.decodeResource(getResources(), R.mipmap.cheb));
        mPresent.setPaoBPiece(BitmapFactory.decodeResource(getResources(), R.mipmap.paob));
    }
//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.player1huiqi:
//                mMovePresent.huiqi();
//                break;
//            case R.id.player2huiqi:
//                mMovePresent.huiqi();
//                break;
//
//            case R.id.player2qiuhe:
//                mMovePresent.qiuhe();
//                break;
//
//            case R.id.player2surrender:
//                mMovePresent.surrender();
//                break;
//        }
//    }


}

