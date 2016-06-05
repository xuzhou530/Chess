package com.example.crxc.chess;

import java.util.ArrayList;

/**
 * Created by crxc on 2016/6/4.
 */
public class MaRule {
    protected static ArrayList<PanelPoint> getVaildPoint(PanelPoint p, float mLineHight,
                                                         ArrayList<PanelPoint> mRedPoint, ArrayList<PanelPoint> mBlackPoint) {
        ArrayList<PanelPoint> vaildPoint = new ArrayList<PanelPoint>();
        ArrayList<PanelPoint> plist = new ArrayList<PanelPoint>();

        PanelPoint p1 = new PanelPoint(p.getX() , p.getY() - 1, mLineHight);
        PanelPoint p2 = new PanelPoint(p.getX() , p.getY() + 1, mLineHight);
        PanelPoint p3 = new PanelPoint(p.getX() - 1, p.getY() , mLineHight);
        PanelPoint p4 = new PanelPoint(p.getX() + 1, p.getY() , mLineHight);

        PanelPoint p5 = new PanelPoint(p.getX() - 1, p.getY() - 2, mLineHight);
        PanelPoint p6 = new PanelPoint(p.getX() + 1, p.getY() - 2, mLineHight);
        PanelPoint p7 = new PanelPoint(p.getX() + 1, p.getY() + 2, mLineHight);
        PanelPoint p8 = new PanelPoint(p.getX() - 1, p.getY() + 2, mLineHight);
        PanelPoint p9 = new PanelPoint(p.getX() - 2, p.getY() - 1, mLineHight);
        PanelPoint p10 = new PanelPoint(p.getX() - 2, p.getY() + 1, mLineHight);
        PanelPoint p11 = new PanelPoint(p.getX() + 2, p.getY() - 1, mLineHight);
        PanelPoint p12 = new PanelPoint(p.getX() + 2, p.getY() + 1, mLineHight);
        if (!(mRedPoint.contains(p1) || mBlackPoint.contains(p1))) {
            plist.add(p5);
            plist.add(p6);
        }
        if (!(mRedPoint.contains(p2) || mBlackPoint.contains(p2))) {
            plist.add(p7);
            plist.add(p8);
        }
        if (!(mRedPoint.contains(p3) || mBlackPoint.contains(p3))) {
            plist.add(p9);
            plist.add(p10);
        }
        if (!(mRedPoint.contains(p4) || mBlackPoint.contains(p4))) {
            plist.add(p11);
            plist.add(p12);
        }



        for (PanelPoint point : plist) {
                vaildPoint.add(point);
        }
        return vaildPoint;
    }
}
