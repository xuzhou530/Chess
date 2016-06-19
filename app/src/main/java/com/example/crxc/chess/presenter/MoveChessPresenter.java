package com.example.crxc.chess.presenter;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.example.crxc.chess.R;
import com.example.crxc.chess.bean.ChessPoint;
import com.example.crxc.chess.bean.PanelPoint;
import com.example.crxc.chess.model.ChessModel;
import com.example.crxc.chess.model.IChessModel;
import com.example.crxc.chess.view.ChessPanelView;
import com.example.crxc.chess.view.IChessPanelView;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by crxc on 2016/6/9.
 */
public class MoveChessPresenter {
    private IChessModel mChessModel;
    private IChessPanelView mChessPanelView;
    private String TAG;
    private ArrayList<PanelPoint> mRedPoint;
    private ArrayList<PanelPoint> mBlackPoint;
    private boolean mIsRed;
    private Timer timer;
    private Timer timer2;
    private ArrayList<ChessPoint> mRedArray;
    private ArrayList<ChessPoint> mBlackArray;
    private Bitmap mShuaiPiece;
    private Bitmap mShiPiece;
    private Bitmap mXiangPiece;
    private Bitmap mMaPiece;
    private Bitmap mChePiece;
    private Bitmap mPaoPiece;
    private Bitmap mBingPiece;
    private Bitmap mJiangPiece;
    private Bitmap mShiBPiece;
    private Bitmap mXiangBPiece;
    private Bitmap mMaBPiece;
    private Bitmap mCheBPiece;
    private Bitmap mPaoBPiece;
    private Bitmap mZuPiece;
    private int id=0;


    public MoveChessPresenter(ChessPanelView chessPanelView,ChessModel chessModel) {
        mChessPanelView=chessPanelView;
        mChessModel=chessModel;
    }



    public void MovePiece(PanelPoint p) {
        ArrayList<ChessPoint> alist=mChessModel.getRedArray();
        Log.d(TAG, "MovePiece: "+alist);
        cshPiece();
        Boolean mDianjiguo=mChessModel.isDianJiGuo();
        mIsRed=mChessModel.isRed();
        mRedPoint=mChessModel.getRedPoint();
        Log.d(TAG, "MovePiece: "+mRedPoint.toString());
        mBlackPoint=mChessModel.getBlackPoint();
        Log.d(TAG, "MovePiece: "+mBlackPoint.toString());
        ChessPoint chessPoint1=null;
        boolean isChiZi=false;
        if (mDianjiguo == false) {
            Log.d(TAG, "onTouchEvent:第一次点击 ");
            getLuoZiPoint(p);
        } else {
            id=id+1;
            Log.d(TAG, "onTouchEvent: 第二次点击");
            if (mIsRed == true) {
                //加入新棋子
                if (!mRedPoint.contains(p)) {
                    Log.d(TAG, "onTouchEvent: 1");
                    if (mChessModel.getVaildPoint().contains(p)) {
                        Log.d(TAG, "onTouchEvent: 2");
                        mChessModel.setIsRed(false);
                        mChessModel.setDianJiGuo(false);
                        StopRedflicker();

                        if (mChessModel.getSelectPanelBitmap() == mShuaiPiece) {
                            mChessModel.setShuaiPoint(p);
                            Log.d(TAG, "onTouchEvent: 更新帅的位置");

                        }
                        //                        吃子
                        if (mBlackPoint.contains(p)) {
                            mBlackPoint.remove(p);
                            mChessModel.setBlackPoint(mBlackPoint);
                            for (ChessPoint cp : mBlackArray) {
                                if (cp.getmPoint().equals(p)) {
                                    chessPoint1 = cp;
                                }
                            }
                            if (chessPoint1.getmBitmap() == mJiangPiece) {
                                mChessPanelView.setText(R.string.RedWin);
                                mChessPanelView.ShowDialog();
                            }
                            mBlackArray.remove(chessPoint1);
                            mChessModel.setBlackArray(mBlackArray);

                            mChessPanelView.PlayChiZiMusic();
                            Log.d(TAG, "onTouchEvent:红方吃子 ");
                            isChiZi=true;
                        }
                        mRedPoint.add(p);
                        Log.d(TAG, "MovePiece: "+mChessModel.getSelectPoint().toString());
                        mRedPoint.remove(mChessModel.getSelectPoint());
                        mChessModel.setRedPoint(mRedPoint);
                        mRedArray.remove(mChessModel.getSelectChessPoint());
                        mRedArray.add(new ChessPoint(p, mChessModel.getSelectPanelBitmap()));
                        mChessModel.setRedArray(mRedArray);
                        Log.d(TAG, "onTouchEvent: 红方加入新棋子");
                        Log.d(TAG, "onTouchEvent: 从红方棋子队列移除点击棋子");
                        if (!isChiZi) {
                            mChessPanelView.PlaySelectMusic();
                        }
                        mChessPanelView.invalidate();
                        Log.d(TAG, "onTouchEvent: 红方落子");
                        mChessModel.SavePoint(p,id,chessPoint1);
                        getRedNextLuoZiPoint(p);
                        Log.d(TAG, "onTouchEvent: 判断是否将军");
                        if(mChessModel.getRedVaildPoint().contains(mChessModel.getJiangPoint())){
                            mChessPanelView.ShowJiangJunToast();
                        }
                    }
                } else {
                    mChessModel.setDianJiGuo(false);
                    StopRedflicker();
                    mRedArray.remove(mChessModel.getSelectChessPoint());
                    mRedArray.add(mChessModel.getSelectChessPoint());
                    getLuoZiPoint(p);
                    Log.d(TAG, "onTouchEvent: 取消选择棋子");
                }
            } else {

                //                        黑棋逻辑
                if (!mBlackPoint.contains(p)) {
                    if (mChessModel.getVaildPoint().contains(p)) {
                        mChessModel.setIsRed(true);
                        mChessModel.setDianJiGuo(false);
                        StopRedflicker();
                        if (mChessModel.getSelectPanelBitmap() == mJiangPiece) {
                            mChessModel.setJiangPoint(p);
                            Log.d(TAG, "onTouchEvent: 更新将的位置");
                        }
                        //                        吃子
                        if (mRedPoint.contains(p)) {
                            mRedPoint.remove(p);
                            mChessModel.setRedPoint(mRedPoint);
                            for (ChessPoint cp : mRedArray) {
                                if (cp.getmPoint().equals(p)) {
                                    chessPoint1 = cp;
                                }
                            }
                            if (chessPoint1.getmBitmap() == mShuaiPiece) {
                                mChessPanelView.setText(R.string.BlackWin);
                                mChessPanelView.ShowDialog();
                            }
                            mRedArray.remove(chessPoint1);
                            mChessModel.setRedArray(mRedArray);
                            mChessPanelView.PlayChiZiMusic();
                            Log.d(TAG, "onTouchEvent:黑方吃子 ");
                            isChiZi=true;
                        }


                        mBlackPoint.add(p);
                        mBlackPoint.remove(mChessModel.getSelectPoint());
                        mChessModel.setBlackPoint(mBlackPoint);
                        mBlackArray.add(new ChessPoint(p, mChessModel.getSelectPanelBitmap()));
                        mBlackArray.remove(mChessModel.getSelectChessPoint());
                        mChessModel.setBlackArray(mBlackArray);
                        Log.d(TAG, "onTouchEvent: 黑方加入新棋子");
                        Log.d(TAG, "onTouchEvent: 从黑方棋子队列移除点击棋子");

                        if (!isChiZi) {
                            mChessPanelView.PlaySelectMusic();
                        }
                        mChessPanelView.invalidate();
                        Log.d(TAG, "onTouchEvent: 黑方落子");
                        mChessModel.SavePoint(p, id, chessPoint1);

                        getBlackNextLuoZiPoint(p);
                        Log.d(TAG, "onTouchEvent: 判断是否将军");
                        if(mChessModel.getBlackVaildPoint().contains(mChessModel.getShuaiPoint())){
                            mChessPanelView.ShowJiangJunToast();
                            Log.d(TAG, "MovePiece: "+mChessModel.getShuaiPoint()+mChessModel.getJiangPoint());
                            Log.d(TAG, "onTouchEvent: 将军");
                        }
                    }
                } else {
                    mChessModel.setDianJiGuo(false);
                    mBlackArray.remove(mChessModel.getSelectChessPoint());
                    mBlackArray.add(mChessModel.getSelectChessPoint());
                    StopRedflicker();
                    getLuoZiPoint(p);
                    Log.d(TAG, "onTouchEvent: 取消选择棋子");
                }


            }

        }

    }

    private void cshPiece() {
        mShuaiPiece=mChessModel.getShuaiPiece();
        mShiPiece=mChessModel.getShiPiece();
        mXiangPiece = mChessModel.getXiangPiece();
        mMaPiece=mChessModel.getMaPiece();
        mChePiece=mChessModel.getChePiece();
        mPaoPiece=mChessModel.getPaoPiece();
        mBingPiece=mChessModel.getBingPiece();
        mJiangPiece=mChessModel.getJiangPiece();
        mShiBPiece=mChessModel.getShiBPiece();
        mXiangBPiece=mChessModel.getXiangBPiece();
        mMaBPiece = mChessModel.getMaBPiece();
        mCheBPiece=mChessModel.getCheBPiece();
        mPaoBPiece=mChessModel.getPaoBPiece();
        mZuPiece=mChessModel.getZuPiece();
    }

    private void getBlackNextLuoZiPoint(PanelPoint p) {
        Boolean isJSOnlyOne = isJSOnlyOne();
        Bitmap bmp=mChessModel.getSelectPanelBitmap();
        PanelPoint mShuaiPoint=mChessModel.getShuaiPoint();
        PanelPoint mJiangPoint=mChessModel.getJiangPoint();
        float mLineHight=mChessModel.getLineHight();
        ArrayList<PanelPoint> mJggPoint=mChessModel.getJggPoint();
        PanelPoint mSelectPiece=mChessModel.getSelectPoint();
        ArrayList<PanelPoint> mHeiPoint=mChessModel.getHeiPoint();
        if (bmp == mJiangPiece) {
            Log.d(TAG, "onTouchEvent: 点击了将");

            //                        设置图片闪烁
            mChessModel.setBlackVaildPoint(JiangRule.getVaildPoint(p, mShuaiPoint, mJiangPoint, mLineHight, mJggPoint, mBlackPoint, mRedPoint));
            Log.d(TAG, "onTouchEvent: 取得将的有效位置");
            Log.d(TAG, "getLuoZiPoint: " + (mJiangPoint.getX() == mShuaiPoint.getX()) + (mSelectPiece.getX() == mJiangPoint.getX()));
        } else {
            if (bmp == mShiBPiece) {
                Log.d(TAG, "onTouchEvent: 点击了黑士");
                mChessModel.setBlackVaildPoint(ShiBRule.getVaildPoint(p, mLineHight, mJggPoint));
                Log.d(TAG, "onTouchEvent: 取得黑士的有效位置");
                if (mSelectPiece.getX() == mJiangPoint.getX() && mJiangPoint.getX() == mShuaiPoint.getX() && isJSOnlyOne) {
                    getRealVaildPoint(mChessModel.getBlackVaildPoint());
                }
            } else if (bmp == mXiangBPiece) {
                //闪烁效果
                Log.d(TAG, "onTouchEvent: 点击了黑象");
                mChessModel.setBlackVaildPoint(XiangRule.getVaildPoint(p, mLineHight, mHeiPoint, mRedPoint, mBlackPoint));
                Log.d(TAG, "onTouchEvent: 取得黑象的有效位置");
                if (mSelectPiece.getX() == mJiangPoint.getX() && mJiangPoint.getX() == mShuaiPoint.getX() && isJSOnlyOne) {
                    getRealVaildPoint(mChessModel.getBlackVaildPoint());
                }
            } else if (bmp == mMaBPiece) {
                //闪烁效果
                Log.d(TAG, "onTouchEvent: 点击了黑马");
                mChessModel.setBlackVaildPoint(MaRule.getVaildPoint(p, mLineHight, mRedPoint, mBlackPoint));
                Log.d(TAG, "onTouchEvent: 取得黑马的有效位置");
                if (mSelectPiece.getX() == mJiangPoint.getX() && mJiangPoint.getX() == mShuaiPoint.getX() && isJSOnlyOne) {
                    getRealVaildPoint(mChessModel.getBlackVaildPoint());
                }
            } else if (bmp == mCheBPiece) {
                //闪烁效果
                Log.d(TAG, "onTouchEvent: 点击了黑车");
                mChessModel.setBlackVaildPoint(CheRule.getVaildPoint(p, mLineHight, mRedPoint, mBlackPoint));
                Log.d(TAG, "onTouchEvent: 取得黑车的有效位置");
                if (mSelectPiece.getX() == mJiangPoint.getX() && mJiangPoint.getX() == mShuaiPoint.getX() && isJSOnlyOne) {
                    getRealVaildPoint(mChessModel.getBlackVaildPoint());
                }
            } else if (bmp == mZuPiece) {
                //闪烁效果
                Log.d(TAG, "onTouchEvent: 点击了卒");
                mChessModel.setBlackVaildPoint(ZuRule.getVaildPoint(p, mLineHight, mHeiPoint));
                Log.d(TAG, "onTouchEvent: 取得卒的有效位置");
                if (mSelectPiece.getX() == mJiangPoint.getX() && mJiangPoint.getX() == mShuaiPoint.getX() && isJSOnlyOne) {
                    getRealVaildPoint(mChessModel.getBlackVaildPoint());

                }
            } else if (bmp == mPaoBPiece) {
                //闪烁效果
                Log.d(TAG, "onTouchEvent: 点击了黑炮");
                mChessModel.setBlackVaildPoint(PaoRule.getVaildPoint(p, mLineHight, mRedPoint, mBlackPoint));
                Log.d(TAG, "onTouchEvent: 取得黑炮的有效位置");
                if (mSelectPiece.getX() == mJiangPoint.getX() && mJiangPoint.getX() == mShuaiPoint.getX() && isJSOnlyOne) {
                    getRealVaildPoint(mChessModel.getBlackVaildPoint());
                }
            }

        }
    }

    private void getRedNextLuoZiPoint(PanelPoint p) {
        Boolean isJSOnlyOne = isJSOnlyOne();
        Bitmap bmp=mChessModel.getSelectPanelBitmap();
        PanelPoint mShuaiPoint=mChessModel.getShuaiPoint();
        PanelPoint mJiangPoint=mChessModel.getJiangPoint();
        float mLineHight=mChessModel.getLineHight();
        ArrayList<PanelPoint> mJggPoint=mChessModel.getJggPoint();
        PanelPoint mSelectPiece=mChessModel.getSelectPoint();
        ArrayList<PanelPoint> mHongPoint=mChessModel.getHongPoint();
        if (bmp == mShuaiPiece) {
            //                        设置图片闪烁
            Log.d(TAG, "onTouchEvent: 点击了帅");
            mChessModel.setRedVaildPoint(ShuaiRule.getVaildPoint(p, mShuaiPoint, mJiangPoint, mLineHight, mJggPoint, mBlackPoint, mRedPoint));
            Log.d(TAG, "onTouchEvent: 取得帅的有效位置");
        } else if (bmp == mShiPiece) {
            //闪烁效果
            Log.d(TAG, "onTouchEvent: 点击了红士");
            mChessModel.setRedVaildPoint(ShiRule.getVaildPoint(p, mLineHight, mJggPoint));
            Log.d(TAG, "onTouchEvent: 取得士的有效位置");
            if (mSelectPiece.getX() == mJiangPoint.getX() && mJiangPoint.getX() == mShuaiPoint.getX() && isJSOnlyOne) {
                getRealVaildPoint(mChessModel.getRedVaildPoint());
            }
        } else if (bmp == mXiangPiece) {
            //闪烁效果
            Log.d(TAG, "onTouchEvent: 点击了红象");
            mChessModel.setRedVaildPoint(XiangRule.getVaildPoint(p, mLineHight, mHongPoint, mRedPoint, mBlackPoint));
            Log.d(TAG, "onTouchEvent: 取得象的有效位置");
            if (mSelectPiece.getX() == mJiangPoint.getX() && mJiangPoint.getX() == mShuaiPoint.getX() && isJSOnlyOne) {
                getRealVaildPoint(mChessModel.getRedVaildPoint());
            }
        } else if (bmp == mMaPiece) {
            //闪烁效果
            Log.d(TAG, "onTouchEvent: 点击了红马");
            mChessModel.setRedVaildPoint(MaRule.getVaildPoint(p, mLineHight, mRedPoint, mBlackPoint));
            Log.d(TAG, "onTouchEvent: 取得马的有效位置");
            if (mSelectPiece.getX() == mJiangPoint.getX() && mJiangPoint.getX() == mShuaiPoint.getX() && isJSOnlyOne) {
                getRealVaildPoint(mChessModel.getRedVaildPoint());
            }
        } else if (bmp == mChePiece) {
            //闪烁效果
            Log.d(TAG, "onTouchEvent: 点击了红车");
            mChessModel.setRedVaildPoint(CheRule.getVaildPoint(p, mLineHight, mRedPoint, mBlackPoint));
            Log.d(TAG, "onTouchEvent: 取得车的有效位置");
            if (mSelectPiece.getX() == mJiangPoint.getX() && mJiangPoint.getX() == mShuaiPoint.getX() && isJSOnlyOne) {
                getRealVaildPoint(mChessModel.getRedVaildPoint());
            }
        } else if (bmp == mBingPiece) {
            //闪烁效果
            Log.d(TAG, "onTouchEvent: 点击了兵");
            mChessModel.setRedVaildPoint(BingRule.getVaildPoint(p, mLineHight, mHongPoint));
            Log.d(TAG, "onTouchEvent: 取得兵的有效位置");
            if (mSelectPiece.getX() == mJiangPoint.getX() && mJiangPoint.getX() == mShuaiPoint.getX() && isJSOnlyOne) {
                getRealVaildPoint(mChessModel.getRedVaildPoint());
            }
        } else if (bmp == mPaoPiece) {
            //闪烁效果
            Log.d(TAG, "onTouchEvent: 点击了炮");
            mChessModel.setRedVaildPoint(PaoRule.getVaildPoint(p, mLineHight, mRedPoint, mBlackPoint));
            Log.d(TAG, "onTouchEvent: 取得炮的有效位置");
            if (mSelectPiece.getX() == mJiangPoint.getX() && (mJiangPoint.getX() == mShuaiPoint.getX()) && isJSOnlyOne) {
                getRealVaildPoint(mChessModel.getRedVaildPoint());
            }
        }

    }

    private void StopRedflicker() {
        Log.d(TAG, "StopRedflicker: 停止计时器");
        timer.cancel();
        timer2.cancel();
    }

    private void getLuoZiPoint(PanelPoint p){
        Boolean isJSOnlyOne = isJSOnlyOne();
        ArrayList<PanelPoint> mHongPoint=mChessModel.getHongPoint();
        ArrayList<PanelPoint> mHeiPoint=mChessModel.getHeiPoint();
        mRedArray=mChessModel.getRedArray();
        mBlackArray=mChessModel.getBlackArray();
        PanelPoint mShuaiPoint=mChessModel.getShuaiPoint();
        PanelPoint mJiangPoint=mChessModel.getJiangPoint();
        float mLineHight=mChessModel.getLineHight();
        ArrayList<PanelPoint> mJggPoint=mChessModel.getJggPoint();
        Bitmap bmp=null;
        PanelPoint mSelectPiece=null;
        if (mIsRed) {
            Log.d(TAG, "onTouchEvent: 红方选择棋子");
            if (mRedPoint.contains(p)) {
                Log.d(TAG, "onTouchEvent: 红方有效选择");
                mChessModel.setSelectPanelPoint(p);
                Redflicker();
                mChessPanelView.PlaySelectMusic();
                for (ChessPoint cp : mRedArray) {
                    if (cp.getmPoint().equals(p)) {
                        mChessModel.setSelectPanelBitmap(cp.getmBitmap());
                        mSelectPiece=p;
                        bmp=cp.getmBitmap();
                        Log.d(TAG, "onTouchEvent: 取得点击棋子图像");
                        mChessModel.setSelectChessPoint(cp);
                        mChessModel.setDianJiGuo(true);
                    }
                }

                if (bmp == mShuaiPiece) {
                    //                        设置图片闪烁
                    Log.d(TAG, "onTouchEvent: 点击了帅");
                    mChessModel.setVaildPoint(ShuaiRule.getVaildPoint(p, mShuaiPoint, mJiangPoint, mLineHight, mJggPoint, mBlackPoint, mRedPoint));
                    Log.d(TAG, "onTouchEvent: 取得帅的有效位置");
                } else if (bmp == mShiPiece) {
                    //闪烁效果
                    Log.d(TAG, "onTouchEvent: 点击了红士");
                    mChessModel.setVaildPoint(ShiRule.getVaildPoint(p, mLineHight, mJggPoint));
                    Log.d(TAG, "onTouchEvent: 取得士的有效位置");
                    if (mSelectPiece.getX() == mJiangPoint.getX() && mJiangPoint.getX() == mShuaiPoint.getX() && isJSOnlyOne) {
                        getRealVaildPoint(mChessModel.getVaildPoint());
                    }
                } else if (bmp == mXiangPiece) {
                    //闪烁效果
                    Log.d(TAG, "onTouchEvent: 点击了红象");
                    mChessModel.setVaildPoint(XiangRule.getVaildPoint(p, mLineHight, mHongPoint, mRedPoint, mBlackPoint));
                    Log.d(TAG, "onTouchEvent: 取得象的有效位置");
                    if (mSelectPiece.getX() == mJiangPoint.getX() && mJiangPoint.getX() == mShuaiPoint.getX() && isJSOnlyOne) {
                        getRealVaildPoint(mChessModel.getVaildPoint());
                    }
                } else if (bmp == mMaPiece) {
                    //闪烁效果
                    Log.d(TAG, "onTouchEvent: 点击了红马");
                    mChessModel.setVaildPoint(MaRule.getVaildPoint(p, mLineHight, mRedPoint, mBlackPoint));
                    Log.d(TAG, "onTouchEvent: 取得马的有效位置");
                    if (mSelectPiece.getX() == mJiangPoint.getX() && mJiangPoint.getX() == mShuaiPoint.getX() && isJSOnlyOne) {
                        getRealVaildPoint(mChessModel.getVaildPoint());
                    }
                } else if (bmp == mChePiece) {
                    //闪烁效果
                    Log.d(TAG, "onTouchEvent: 点击了红车");
                    mChessModel.setVaildPoint(CheRule.getVaildPoint(p, mLineHight, mRedPoint, mBlackPoint));
                    Log.d(TAG, "onTouchEvent: 取得车的有效位置");
                    if (mSelectPiece.getX() == mJiangPoint.getX() && mJiangPoint.getX() == mShuaiPoint.getX() && isJSOnlyOne) {
                        getRealVaildPoint(mChessModel.getVaildPoint());
                    }
                } else if (bmp == mBingPiece) {
                    //闪烁效果
                    Log.d(TAG, "onTouchEvent: 点击了兵");
                    mChessModel.setVaildPoint(BingRule.getVaildPoint(p, mLineHight, mHongPoint));
                    Log.d(TAG, "onTouchEvent: 取得兵的有效位置");
                    if (mSelectPiece.getX() == mJiangPoint.getX() && mJiangPoint.getX() == mShuaiPoint.getX() && isJSOnlyOne) {
                        getRealVaildPoint(mChessModel.getVaildPoint());
                    }
                } else if (bmp == mPaoPiece) {
                    //闪烁效果
                    Log.d(TAG, "onTouchEvent: 点击了炮");
                    mChessModel.setVaildPoint(PaoRule.getVaildPoint(p, mLineHight, mRedPoint, mBlackPoint));
                    Log.d(TAG, "onTouchEvent: 取得炮的有效位置");
                    if (mSelectPiece.getX() == mJiangPoint.getX() && (mJiangPoint.getX() == mShuaiPoint.getX()) && isJSOnlyOne) {
                        getRealVaildPoint(mChessModel.getVaildPoint());
                    }
                }

            }

            //                mRedArray.add(new ChessPoint(p,));
        } else {
            //                黑棋走棋
            Log.d(TAG, "onTouchEvent: 黑方选择棋子");
            if (mBlackPoint.contains(p)) {
                Log.d(TAG, "onTouchEvent: 黑方有效选择");
                mSelectPiece = p;
                mChessModel.setSelectPanelPoint(p);
                Blackflicker();
                mChessPanelView.PlaySelectMusic();
                for (ChessPoint cp : mBlackArray) {
                    if (cp.getmPoint().equals(p)) {
                        bmp = cp.getmBitmap();
                        mChessModel.setSelectPanelBitmap(cp.getmBitmap());
                        Log.d(TAG, "onTouchEvent: 取得点击棋子图像");
                        mChessModel.setSelectChessPoint(cp);
                        mChessModel.setDianJiGuo(true);
                    }
                }

                if (bmp == mJiangPiece) {
                    Log.d(TAG, "onTouchEvent: 点击了将");
                    mChessModel.setVaildPoint(JiangRule.getVaildPoint(p, mShuaiPoint, mJiangPoint, mLineHight, mJggPoint, mBlackPoint, mRedPoint));
                    Log.d(TAG, "onTouchEvent: 取得将的有效位置");
                    Log.d(TAG, "getLuoZiPoint: " + (mJiangPoint.getX() == mShuaiPoint.getX()) + (mSelectPiece.getX() == mJiangPoint.getX()));
                } else {
                    if (bmp == mShiBPiece) {
                        Log.d(TAG, "onTouchEvent: 点击了黑士");
                        mChessModel.setVaildPoint(ShiBRule.getVaildPoint(p, mLineHight, mJggPoint));
                        Log.d(TAG, "onTouchEvent: 取得黑士的有效位置");
                        if (mSelectPiece.getX() == mJiangPoint.getX() && mJiangPoint.getX() == mShuaiPoint.getX() && isJSOnlyOne) {
                            getRealVaildPoint(mChessModel.getVaildPoint());
                        }
                    } else if (bmp == mXiangBPiece) {
                        //闪烁效果
                        Log.d(TAG, "onTouchEvent: 点击了黑象");
                        mChessModel.setVaildPoint(XiangRule.getVaildPoint(p, mLineHight, mHeiPoint, mRedPoint, mBlackPoint));
                        Log.d(TAG, "onTouchEvent: 取得黑象的有效位置");
                        if (mSelectPiece.getX() == mJiangPoint.getX() && mJiangPoint.getX() == mShuaiPoint.getX() && isJSOnlyOne) {
                            getRealVaildPoint(mChessModel.getVaildPoint());
                        }
                    } else if (bmp == mMaBPiece) {
                        //闪烁效果
                        Log.d(TAG, "onTouchEvent: 点击了黑马");
                        mChessModel.setVaildPoint(MaRule.getVaildPoint(p, mLineHight, mRedPoint, mBlackPoint));
                        Log.d(TAG, "onTouchEvent: 取得黑马的有效位置");
                        if (mSelectPiece.getX() == mJiangPoint.getX() && mJiangPoint.getX() == mShuaiPoint.getX() && isJSOnlyOne) {
                            getRealVaildPoint(mChessModel.getVaildPoint());
                        }
                    } else if (bmp == mCheBPiece) {
                        //闪烁效果
                        Log.d(TAG, "onTouchEvent: 点击了黑车");
                        mChessModel.setVaildPoint(CheRule.getVaildPoint(p, mLineHight, mRedPoint, mBlackPoint));
                        Log.d(TAG, "onTouchEvent: 取得黑车的有效位置");
                        if (mSelectPiece.getX() == mJiangPoint.getX() && mJiangPoint.getX() == mShuaiPoint.getX() && isJSOnlyOne) {
                            getRealVaildPoint(mChessModel.getVaildPoint());
                        }
                    } else if (bmp == mZuPiece) {
                        //闪烁效果
                        Log.d(TAG, "onTouchEvent: 点击了卒");
                        mChessModel.setVaildPoint(ZuRule.getVaildPoint(p, mLineHight, mHeiPoint));
                        Log.d(TAG, "onTouchEvent: 取得卒的有效位置");
                        if (mSelectPiece.getX() == mJiangPoint.getX() && mJiangPoint.getX() == mShuaiPoint.getX() && isJSOnlyOne) {
                            getRealVaildPoint(mChessModel.getVaildPoint());

                        }
                    } else if (bmp == mPaoBPiece) {
                        //闪烁效果
                        Log.d(TAG, "onTouchEvent: 点击了黑炮");
                        mChessModel.setVaildPoint(PaoRule.getVaildPoint(p, mLineHight, mRedPoint, mBlackPoint));
                        Log.d(TAG, "onTouchEvent: 取得黑炮的有效位置");
                        if (mSelectPiece.getX() == mJiangPoint.getX() && mJiangPoint.getX() == mShuaiPoint.getX() && isJSOnlyOne) {
                            getRealVaildPoint(mChessModel.getVaildPoint());
                        }
                    }

                }
            }
        }

    }

    private void Blackflicker() {
        timer = new Timer();
        timer2 = new Timer();
        TimerTask task;
        final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                // TODO Auto-generated method stub
                // 要做的事情
//                mBlackPoint.remove(mSelectPiece);
                mBlackArray.remove(mChessModel.getSelectChessPoint());
                mChessPanelView.invalidate();
                Log.d(TAG, "handleMessage: 棋子消失");
                super.handleMessage(msg);
            }
        };
        task=new TimerTask(){

            @Override
            public void run() {
                Message message=new Message();
                handler.sendMessage(message);
            }
        };
        TimerTask task2;
        final Handler handler2 = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                // TODO Auto-generated method stub
                // 要做的事情
//                mBlackPoint.add(mSelectPiece);
                mBlackArray.add(mChessModel.getSelectChessPoint());
                mChessPanelView.invalidate();
                Log.d(TAG, "handleMessage: 棋子出现");
                super.handleMessage(msg);
            }
        };
        task2=new TimerTask(){

            @Override
            public void run() {
                Message message=new Message();
                handler2.sendMessage(message);
            }
        };
        timer.schedule(task,500,1000);
        timer.schedule(task2,1000,1000);
    }

    private void getRealVaildPoint(ArrayList<PanelPoint> VaildPoint) {

        ArrayList<PanelPoint> plist = new ArrayList<PanelPoint>();
        for (PanelPoint point : VaildPoint) {
            PanelPoint mJiangPoint=mChessModel.getJiangPoint();
            if (point.getX() == mJiangPoint.getX()) {
                plist.add(point);
            }
        }
        Log.d(TAG, "getRealVaildPoint: " + plist.toString());
        mChessModel.setVaildPoint(plist);


    }

    private boolean isJSOnlyOne() {
        PanelPoint mJiangPoint=mChessModel.getJiangPoint();
        PanelPoint mShuaiPoint=mChessModel.getShuaiPoint();
        ArrayList<PanelPoint> mRedPoint=mChessModel.getRedPoint();
        ArrayList<PanelPoint> mBlackPoint=mChessModel.getBlackPoint();

        Log.d(TAG, "isJSOnlyOne: " + mJiangPoint.getX());
        Log.d(TAG, "isJSOnlyOne: " + mShuaiPoint.getX());
        ArrayList<PanelPoint> plist = new ArrayList<PanelPoint>();
        for (PanelPoint point : mRedPoint) {
            if (point.getX() == mJiangPoint.getX()) {
                plist.add(point);
            }
        }
        for (PanelPoint point : mBlackPoint) {
            if (point.getX() == mJiangPoint.getX()) {
                plist.add(point);
            }
        }
        Log.d(TAG, "isJSOnlyOne: " + (plist.size() == 3));
        return plist.size() == 3;
    }
    private void Redflicker() {
        timer = new Timer();
        timer2 = new Timer();
        TimerTask task;
        final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                // TODO Auto-generated method stub
                // 要做的事情
//                mRedPoint.remove(mSelectPiece);
                mRedArray.remove(mChessModel.getSelectChessPoint());
               mChessPanelView.invalidate();
                Log.d(TAG, "handleMessage: 棋子消失");
                super.handleMessage(msg);
            }
        };
        task=new TimerTask(){

            @Override
            public void run() {
                Message message=new Message();
                handler.sendMessage(message);
            }
        };
        TimerTask task2;
        final Handler handler2 = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                // TODO Auto-generated method stub
                // 要做的事情
//                mRedPoint.add(mSelectPiece);
                mRedArray.add(mChessModel.getSelectChessPoint());
                mChessPanelView.invalidate();
                Log.d(TAG, "handleMessage: 棋子出现");
                super.handleMessage(msg);
            }
        };
        task2=new TimerTask(){

            @Override
            public void run() {
                Message message=new Message();
                handler2.sendMessage(message);
            }
        };
        timer.schedule(task,500,1000);
        timer.schedule(task2,1000,1000);
    }
    public void huiqi() {
        ArrayList<Object> list =mChessModel.loadLastPoint(id);
        try {
            ChessPoint chessPoint=(ChessPoint) list.get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        PanelPoint point1=(PanelPoint) list.get(0);
        PanelPoint point2=(PanelPoint) list.get(1);
        MovePiece(point1);
        MovePiece(point2);
    }

    public void qiuhe() {
    }

    public void surrender() {
    }
}

