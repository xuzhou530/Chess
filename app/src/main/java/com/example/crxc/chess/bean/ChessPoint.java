package com.example.crxc.chess.bean;

import android.graphics.Bitmap;
import android.graphics.Point;

/**
 * Created by crxc on 2016/6/1.
 */
public class ChessPoint  {

    private final Point mPoint;

    private final Bitmap mBitmap;

    public Bitmap getmBitmap() {
        return mBitmap;
    }

    public Point getmPoint() {
        return mPoint;
    }


    public ChessPoint(PanelPoint p, Bitmap b) {
        mPoint=p;
        mBitmap=b;
    }
}
