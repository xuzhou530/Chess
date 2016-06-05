package com.example.crxc.chess;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by crxc on 2016/6/4.
 */
public class CheRule {
    private static int min ;
    private static int max ;
    private static int Xmax ;
    private static int Xmin ;
    private static final String TAG = "CheRule";

    protected static ArrayList<PanelPoint> getVaildPoint(PanelPoint p, float mLineHight,
                                                         ArrayList<PanelPoint> mRedPoint, ArrayList<PanelPoint> mBlackPoint) {
        ArrayList<PanelPoint> vaildPoint = new ArrayList<PanelPoint>();
        ArrayList<PanelPoint> plist = new ArrayList<PanelPoint>();

        min=-p.getY();
        max=9-p.getY();
        Xmin=-p.getX();
        Xmax=8-p.getX();
        for (PanelPoint panelPoint : mRedPoint) {
            if (panelPoint.getX() == p.getX()) {
                int y1 = panelPoint.getY();
                int m = y1 - p.getY();
                if (m > 0) {
                    if (m < max) {
                        max = m;
                    }
                } else if (m < 0) {
                    if (m > min) {
                        min = m;
                    }
                }
            }
        }
        for (PanelPoint panelPoint : mRedPoint) {
            if (panelPoint.getY() == p.getY()) {
                int x1 = panelPoint.getX();
                int m = x1 - p.getX();
                if (m > 0) {
                    if (m < Xmax) {
                        Xmax = m;
                    }
                } else if (m < 0) {
                    if (m > Xmin) {
                        Xmin = m;
                    }
                }
            }
        }
        for (PanelPoint panelPoint : mBlackPoint) {
            if (panelPoint.getX() == p.getX()) {
                int y1 = panelPoint.getY();
                int m = y1 - p.getY();
                if (m > 0) {
                    if (m < max) {
                        max = m;
                    }
                } else if (m < 0) {
                    if (m > min) {
                        min = m;
                    }
                }
            }
        }
        for (PanelPoint panelPoint : mBlackPoint) {
            if (panelPoint.getY() == p.getY()) {
                int x1 = panelPoint.getX();
                int m = x1 - p.getX();
                if (m > 0) {
                    if (m < Xmax) {
                        Xmax = m;
                    }
                } else if (m<0) {
                    if (m > Xmin) {
                        Xmin = m;
                    }
                }
            }
        }

        for (int x = p.getX() + Xmin; x <= p.getX() + Xmax; x++) {
            plist.add(new PanelPoint(x, p.getY(), mLineHight));
        }
        for (int y = p.getY() + min; y <= p.getY() + max; y++) {
            plist.add(new PanelPoint(p.getX(), y, mLineHight));
        }


        for (PanelPoint point : plist) {
            vaildPoint.add(point);
        }
        Log.d(TAG, "min:"+min);
        Log.d(TAG, "max"+max);
        Log.d(TAG, "Xmin"+Xmin);
        Log.d(TAG, "Xmax"+Xmax);
        return vaildPoint;
    }
}
