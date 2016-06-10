package com.example.crxc.chess.model;

/**
 * Created by crxc on 2016/6/9.
 */
public class Specification {
    private float mLineHight;
    private int mPanelWidth;
    private int mPanelHight;
    public static final int Bili=1;
    private Boolean mDianJiGuo=false;

    public Boolean getmDianJiGuo() {
        return mDianJiGuo;
    }

    public void setmDianJiGuo(Boolean mDianJiGuo) {
        this.mDianJiGuo = mDianJiGuo;
    }

    public Boolean getmIsRead() {
        return mIsRead;
    }

    public void setmIsRead(Boolean mIsRead) {
        this.mIsRead = mIsRead;
    }

    private Boolean mIsRead=true;

    public int getPieceWidth() {
        return pieceWidth;
    }

    public void setPieceWidth(int pieceWidth) {
        this.pieceWidth = pieceWidth;
    }

    private int pieceWidth;

    public float getmLineHight() {
        return mLineHight;
    }

    public void setmLineHight(float lineHight) {
        mLineHight = lineHight;
    }

    public int getmPanelWidth() {
        return mPanelWidth;
    }

    public void setmPanelWidth(int mPanelWidth) {
        this.mPanelWidth = mPanelWidth;
    }

    public int getmPanelHight() {
        return mPanelHight;
    }

    public void setmPanelHight(int mPanelHight) {
        this.mPanelHight = mPanelHight;
    }
}
