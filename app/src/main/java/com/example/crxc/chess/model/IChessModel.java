package com.example.crxc.chess.model;

import android.graphics.Bitmap;

import com.example.crxc.chess.bean.ChessPoint;
import com.example.crxc.chess.bean.PanelPoint;

import java.util.ArrayList;

/**
 * Created by crxc on 2016/6/9.
 */
public interface IChessModel {


    void cshSpecification(int w, int h);

    void cshPiece();

    void cshPoint();

    void cshArray();

    void cshJgg();

    void cshHongPoint();

    void cshHeiPoint();

    ArrayList<ChessPoint> getRedArray();

    ArrayList<ChessPoint> getBlackArray();

    float getLineHight();

    int getPanelWidth();

    int getPanelHight();

    Boolean isDianJiGuo();

    Boolean isRed();

    ArrayList<PanelPoint> getRedPoint();

    ArrayList<PanelPoint> getBlackPoint();

    PanelPoint getJiangPoint();

    PanelPoint getShuaiPoint();

    ArrayList<PanelPoint> getVaildPoint();

    void setSelectPanelPoint(PanelPoint p);

    void setSelectPanelBitmap(Bitmap bmp);

    void setSelectChessPoint(ChessPoint cp);

    void setDianJiGuo(boolean b);

    Bitmap getSelectPanelBitmap();

    Bitmap getShuaiPiece();

    ArrayList<PanelPoint> getJggPoint();

    void setVaildPoint(ArrayList<PanelPoint> vaildPoint);

    Bitmap getShiPiece();

    Bitmap getXiangPiece();

    Bitmap getMaPiece();

    Bitmap getChePiece();

    Bitmap getPaoPiece();

    Bitmap getBingPiece();

    Bitmap getJiangPiece();

    Bitmap getShiBPiece();

    Bitmap getXiangBPiece();

    Bitmap getMaBPiece();

    Bitmap getCheBPiece();

    Bitmap getPaoBPiece();

    Bitmap getZuPiece();

    ArrayList<PanelPoint> getHongPoint();

    ArrayList<PanelPoint> getHeiPoint();

    void setIsRed(boolean b);

    void setShuaiPoint(PanelPoint p);

    void setBlackPoint(ArrayList<PanelPoint> mBlackPoint);

    void setBlackArray(ArrayList<ChessPoint> mBlackArray);

    void setRedPoint(ArrayList<PanelPoint> mRedPoint);

    void setRedArray(ArrayList<ChessPoint> mRedArray);

    PanelPoint getSelectPoint();

    ChessPoint getSelectChessPoint();

    void setRedVaildPoint(ArrayList<PanelPoint> vaildPoint);

    ArrayList<PanelPoint> getRedVaildPoint();

    void setJiangPoint(PanelPoint p);

    void setBlackVaildPoint(ArrayList<PanelPoint> vaildPoint);

    ArrayList<PanelPoint> getBlackVaildPoint();

    void setPaoBPiece(Bitmap bitmap);

    void setCheBPiece(Bitmap bitmap);

    void setMaBPiece(Bitmap bitmap);

    void setXiangBPiece(Bitmap bitmap);

    void setShiBPiece(Bitmap bitmap);

    void setJiangPiece(Bitmap bitmap);

    void setShiPiece(Bitmap bitmap);

    void setShuaiPiece(Bitmap bitmap);

    void setXiangPiece(Bitmap bitmap);

    void setMaPiece(Bitmap bitmap);

    void setChePiece(Bitmap bitmap);

    void setPaoPiece(Bitmap bitmap);

    void setZuPiece(Bitmap bitmap);

    void setBingPiece(Bitmap bitmap);

    void SavePoint(PanelPoint p, int id, ChessPoint chessPoint1);

    ArrayList<Object> loadLastPoint(int id);


}
