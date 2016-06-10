package com.example.crxc.chess.bean;

import android.graphics.Point;

/**
 * Created by crxc on 2016/6/3.
 */
public class ZhPoint extends Point{
    public ZhPoint(int x, int y, float mLineHight) {
        super((int) ((x + 0.5) * mLineHight), (int) ((9.5-y) * mLineHight));
    }
}
