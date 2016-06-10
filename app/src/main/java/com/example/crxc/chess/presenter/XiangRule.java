package com.example.crxc.chess.presenter;

import com.example.crxc.chess.bean.PanelPoint;

import java.util.ArrayList;

/**
 * Created by crxc on 2016/6/4.
 */
public class XiangRule {
    protected static ArrayList<PanelPoint> getVaildPoint(PanelPoint p, float mLineHight, ArrayList<PanelPoint> HongPoint,
                                                         ArrayList<PanelPoint> mRedPoint, ArrayList<PanelPoint> mBlackPoint) {
        ArrayList<PanelPoint> vaildPoint = new ArrayList<PanelPoint>();
        ArrayList<PanelPoint> plist = new ArrayList<PanelPoint>();

        PanelPoint p1 = new PanelPoint(p.getX() - 1, p.getY() - 1, mLineHight);
        PanelPoint p2 = new PanelPoint(p.getX() + 1, p.getY() + 1, mLineHight);
        PanelPoint p3 = new PanelPoint(p.getX() - 1, p.getY() + 1, mLineHight);
        PanelPoint p4 = new PanelPoint(p.getX() + 1, p.getY() - 1, mLineHight);
        PanelPoint p5 = new PanelPoint(p.getX() - 2, p.getY() - 2, mLineHight);
        PanelPoint p6 = new PanelPoint(p.getX() + 2, p.getY() + 2, mLineHight);
        PanelPoint p7 = new PanelPoint(p.getX() - 2, p.getY() + 2, mLineHight);
        PanelPoint p8 = new PanelPoint(p.getX() + 2, p.getY() - 2, mLineHight);
        if (!(mRedPoint.contains(p1) || mBlackPoint.contains(p1))) {
            plist.add(p5);
        }
        if (!(mRedPoint.contains(p2) || mBlackPoint.contains(p2))) {
            plist.add(p6);
        }
        if (!(mRedPoint.contains(p3) || mBlackPoint.contains(p3))) {
            plist.add(p7);
        }
        if (!(mRedPoint.contains(p4) || mBlackPoint.contains(p4))) {
            plist.add(p8);
        }



        for (PanelPoint point : plist) {
            if (HongPoint.contains(point)) {
                vaildPoint.add(point);
            }
        }
        return vaildPoint;
    }
}
