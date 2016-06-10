package com.example.crxc.chess.model;

import android.util.Log;

import com.example.crxc.chess.bean.PanelPoint;

/**
 * Created by crxc on 2016/6/9.
 */
public class JSPoint {
    PanelPoint mJiangPoint;

    PanelPoint mShuaiPoint;

    private static final String TAG = "JSPoint";

    public PanelPoint getmJiangPoint() {
        return mJiangPoint;
    }

    public void setmJiangPoint(PanelPoint mJiangPoint) {
        this.mJiangPoint = mJiangPoint;
    }

    public PanelPoint getmShuaiPoint() {
        Log.d(TAG, "getmShuaiPoint: "+mShuaiPoint.toString()+mJiangPoint.toString());
        return mShuaiPoint;
    }

    public void setmShuaiPoint(PanelPoint mShuaiPoint) {
        Log.d(TAG, "setmShuaiPoint: 更新帅的位置");
        this.mShuaiPoint = mShuaiPoint;
    }
}
