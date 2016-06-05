package com.example.crxc.chess;

import android.graphics.Bitmap;
import android.graphics.Point;

/**
 * Created by crxc on 2016/6/1.
 */
public class ChessPoint  {

    private final Point mPoint;

    public Bitmap getmBitmap() {
        return mBitmap;
    }

    public Point getmPoint() {
        return mPoint;
    }

    private final Bitmap mBitmap;


    public ChessPoint(Point p, Bitmap b) {
        mPoint=p;
        mBitmap=b;
    }
}
