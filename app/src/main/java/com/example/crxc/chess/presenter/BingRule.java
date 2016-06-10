package com.example.crxc.chess.presenter;

import com.example.crxc.chess.bean.PanelPoint;

import java.util.ArrayList;

/**
 * Created by crxc on 2016/6/4.
 */
public class BingRule {
    protected static ArrayList<PanelPoint> getVaildPoint(PanelPoint p, float mLineHight, ArrayList<PanelPoint> HongPoint) {
        ArrayList<PanelPoint> vaildPoint = new ArrayList<PanelPoint>();
        ArrayList<PanelPoint> plist = new ArrayList<PanelPoint>();
        if (HongPoint.contains(p)) {
            PanelPoint p1 = new PanelPoint(p.getX(), p.getY() + 1, mLineHight);
            plist.add(p1);
        }else {
            PanelPoint p2 = new PanelPoint(p.getX(), p.getY() + 1, mLineHight);
            PanelPoint p3 = new PanelPoint(p.getX()+1, p.getY(), mLineHight);
            PanelPoint p4 = new PanelPoint(p.getX()-1, p.getY(), mLineHight);
            plist.add(p2);
            plist.add(p3);
            plist.add(p4);
        }




        for (PanelPoint point : plist) {
                vaildPoint.add(point);
        }
        return vaildPoint;
    }
}
