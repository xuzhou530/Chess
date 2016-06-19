package com.example.crxc.chess.presenter;

import android.graphics.Bitmap;
import android.util.Log;

import com.example.crxc.chess.bean.ChessPoint;
import com.example.crxc.chess.bean.PanelPoint;
import com.example.crxc.chess.model.ChessModel;
import com.example.crxc.chess.model.IChessModel;
import com.example.crxc.chess.view.ChessPanelView;
import com.example.crxc.chess.view.IChessPanelView;

import java.util.ArrayList;

/**
 * Created by crxc on 2016/6/9.
 */
public class CshPresenter {
    private IChessModel mChessModel = null;
    private IChessPanelView mChessPanelView;

    public CshPresenter(ChessPanelView chessPanelView,ChessModel chessModel) {
        mChessPanelView = chessPanelView;
        mChessModel = chessModel;
    }

    public void csh(int w, int h) {
        mChessModel.cshSpecification(w, h);
        mChessModel.cshPiece();
        mChessModel.cshPoint();
        mChessModel.cshArray();
        mChessModel.cshJgg();
        mChessModel.cshHongPoint();
        mChessModel.cshHeiPoint();
    }

    public ArrayList<ChessPoint> getRedArray() {
        return mChessModel.getRedArray();
    }

    public ArrayList<ChessPoint> getBlackArray() {
        return mChessModel.getBlackArray();
    }

    public float getLineHight() {
        return mChessModel.getLineHight();
    }

    public int getPanelWidth() {
        return mChessModel.getPanelWidth();
    }

    public int getPanelHight() {
        return mChessModel.getPanelHight();
    }


    public void refresh() {
        mChessModel.setDianJiGuo(false);
        mChessModel.setIsRed(true);
        mChessModel.cshPoint();
        mChessModel.cshArray();
        mChessPanelView.invalidate();
    }

    public void setBingPiece(Bitmap bitmap) {
        mChessModel.setBingPiece(bitmap);
    }

    public void setShuaiPiece(Bitmap bitmap) {
        mChessModel.setShuaiPiece(bitmap);
    }

    public void setShiPiece(Bitmap bitmap) {
        mChessModel.setShiPiece(bitmap);

    }

    public void setXiangPiece(Bitmap bitmap) {
        mChessModel.setXiangPiece(bitmap);

    }

    public void setMaPiece(Bitmap bitmap) {
        mChessModel.setMaPiece(bitmap);

    }

    public void setChePiece(Bitmap bitmap) {
        mChessModel.setChePiece(bitmap);

    }

    public void setPaoPiece(Bitmap bitmap) {
        mChessModel.setPaoPiece(bitmap);

    }

    public void setZuPiece(Bitmap bitmap) {
        mChessModel.setZuPiece(bitmap);

    }

    public void setJiangPiece(Bitmap bitmap) {
        mChessModel.setJiangPiece(bitmap);

    }

    public void setShiBPiece(Bitmap bitmap) {
        mChessModel.setShiBPiece(bitmap);

    }

    public void setXiangBPiece(Bitmap bitmap) {
        mChessModel.setXiangBPiece(bitmap);

    }

    public void setMaBPiece(Bitmap bitmap) {
        mChessModel.setMaBPiece(bitmap);

    }

    public void setCheBPiece(Bitmap bitmap) {
        mChessModel.setCheBPiece(bitmap);

    }

    public void setPaoBPiece(Bitmap bitmap) {
        mChessModel.setPaoBPiece(bitmap);

    }



}

