package com.example.crxc.chess.bean;

import android.graphics.Bitmap;
import android.graphics.Point;

import java.io.Serializable;

/**
 * Created by crxc on 2016/6/1.
 */
public class ChessPoint implements Serializable {

    private final PanelPoint mPoint;

    private final Bitmap mBitmap;

    public Bitmap getmBitmap() {
        return mBitmap;
    }

    public PanelPoint getmPoint() {
        return mPoint;
    }


    public ChessPoint(PanelPoint p, Bitmap b) {
        mPoint=p;
        mBitmap=b;
    }
}
