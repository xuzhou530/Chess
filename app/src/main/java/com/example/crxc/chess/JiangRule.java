package com.example.crxc.chess;

import java.util.ArrayList;

/**
 * Created by crxc on 2016/6/4.
 */
public class JiangRule {

    protected static ArrayList<PanelPoint>  getVaildPoint(PanelPoint p,float mLineHight,ArrayList<PanelPoint> mJggPoint) {
        ArrayList<PanelPoint> vaildPoint = new ArrayList<PanelPoint>();
        PanelPoint p1 = new PanelPoint(p.getX()-1,p.getY(),mLineHight);
        PanelPoint p2 = new PanelPoint(p.getX()+1,p.getY(),mLineHight);
        PanelPoint p3 = new PanelPoint(p.getX(),p.getY()-1,mLineHight);
        PanelPoint p4 = new PanelPoint(p.getX(),p.getY()+1,mLineHight);


        PanelPoint[] plist = new PanelPoint[]{p1, p2, p3, p4};
        for (PanelPoint point: plist) {
            if (mJggPoint.contains(point)) {
                vaildPoint.add(point);
            }
        }
        return vaildPoint;
    }
}
