package com.example.crxc.chess.model;

import android.graphics.Bitmap;

import com.example.crxc.chess.bean.ChessPoint;
import com.example.crxc.chess.bean.PanelPoint;

/**
 * Created by crxc on 2016/6/10.
 */
public class SelectPanelInfo {
    public Bitmap getBmp() {
        return bmp;
    }

    public void setBmp(Bitmap bmp) {
        this.bmp = bmp;
    }

    public ChessPoint getChessPoint() {
        return chessPoint;
    }

    public void setChessPoint(ChessPoint chessPoint) {
        this.chessPoint = chessPoint;
    }

    public PanelPoint getPanelPoint() {
        return panelPoint;
    }

    public void setPanelPoint(PanelPoint panelPoint) {
        this.panelPoint = panelPoint;
    }

    private Bitmap bmp;
    private ChessPoint chessPoint;
    private PanelPoint panelPoint;
}
