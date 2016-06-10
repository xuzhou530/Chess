package com.example.crxc.chess.model;

import com.example.crxc.chess.bean.ChessPoint;

import java.util.ArrayList;

/**
 * Created by crxc on 2016/6/9.
 */
public class BlackArray {
    public ArrayList<ChessPoint> getmBlackArray() {
        return mBlackArray;
    }

    public void setmBlackArray(ArrayList<ChessPoint> mBlackArray) {
        this.mBlackArray = mBlackArray;
    }

    private ArrayList<ChessPoint> mBlackArray=new ArrayList<ChessPoint>();
}
