package com.example.crxc.chess.model;

import com.example.crxc.chess.bean.ChessPoint;

import java.util.ArrayList;

/**
 * Created by crxc on 2016/6/9.
 */
public class RedArray {
    public ArrayList<ChessPoint> getmRedArray() {
        return mRedArray;
    }

    public void setmRedArray(ArrayList<ChessPoint> mRedArray) {
        this.mRedArray = mRedArray;
    }

    private ArrayList<ChessPoint> mRedArray=new ArrayList<ChessPoint>();
}
