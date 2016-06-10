package com.example.crxc.chess.bean;

import android.graphics.Point;

/**
 * Created by crxc on 2016/6/3.
 */
public class PanelPoint extends Point {
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    private int x;
    private int y;

    public PanelPoint(int x, int y, float mLineHight) {
        super((int) ((x ) * mLineHight), (int) ((9 - y) * mLineHight));
        this.x=x;
        this.y=y;
    }

}
