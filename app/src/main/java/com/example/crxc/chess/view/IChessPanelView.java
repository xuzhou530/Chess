package com.example.crxc.chess.view;

import com.example.crxc.chess.bean.PanelPoint;
import com.example.crxc.chess.bean.ChessPoint;

import java.util.ArrayList;

/**
 * Created by crxc on 2016/6/9.
 */
public interface IChessPanelView {
    void csh(int w,int h);
    ArrayList<ChessPoint> getRedArray();
    ArrayList<ChessPoint> getBlackArray();
    float getLineHight();
    int getPanelWidth();
    int getPanelHight();
     PanelPoint getVaildPoint(int x, int y);

    void PlaySelectMusic();

    void setText(int redWin);

    void ShowDialog();

    void PlayChiZiMusic();

    void invalidate();

    void ShowJiangJunToast();

    void cshPieceBmp();

}
