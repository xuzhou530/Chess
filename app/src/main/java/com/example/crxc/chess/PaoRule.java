package com.example.crxc.chess;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by crxc on 2016/6/4.
 */
public class PaoRule {
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

        for (int x = p.getX()+1 + Xmin; x < p.getX() + Xmax; x++) {
            plist.add(new PanelPoint(x, p.getY(), mLineHight));
        }
        for (int y = p.getY()+1 + min; y < p.getY() + max; y++) {
            plist.add(new PanelPoint(p.getX(), y, mLineHight));
        }
        PanelPoint p1=getMinPanel(p,mRedPoint,mBlackPoint);
        PanelPoint p2=getMinPanel(p1,mRedPoint,mBlackPoint);
        plist.add(p2);
        PanelPoint p3=getMaxPanel(p,mRedPoint,mBlackPoint);
        PanelPoint p4=getMaxPanel(p3,mRedPoint,mBlackPoint);
        plist.add(p4);
        PanelPoint p5=getXMinPanel(p,mRedPoint,mBlackPoint);
        PanelPoint p6=getXMinPanel(p5,mRedPoint,mBlackPoint);
        plist.add(p6);
        PanelPoint p7=getXMaxPanel(p,mRedPoint,mBlackPoint);
        PanelPoint p8=getXMaxPanel(p7,mRedPoint,mBlackPoint);
        plist.add(p8);

        for (PanelPoint point : plist) {
            vaildPoint.add(point);
        }
        Log.d(TAG, "min:"+min);
        Log.d(TAG, "max"+max);
        Log.d(TAG, "Xmin"+Xmin);
        Log.d(TAG, "Xmax"+Xmax);
        return vaildPoint;
    }

    private static PanelPoint getXMinPanel(PanelPoint p, ArrayList<PanelPoint> mRedPoint, ArrayList<PanelPoint> mBlackPoint) {
        PanelPoint p1=p;
        Xmin=-p.getX();
        for (PanelPoint panelPoint : mRedPoint) {
            if (panelPoint.getY() == p.getY()) {
                int x1 = panelPoint.getX();
                int m = x1 - p.getX();
                if (m > 0) {
                    if (m < Xmax) {
                        Xmax = m;
                    }
                } else if (m < 0) {
                    if (m >= Xmin) {
                        Xmin = m;
                        p1=panelPoint;
                        Log.d(TAG, "getXMinPanel: X,y"+p1.getX()+p1.getY());
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
                } else if (m < 0) {
                    if (m >= Xmin) {
                        Xmin = m;
                        p1 = panelPoint;
                        Log.d(TAG, "getXMinPanel: X,y"+p1.getX()+p1.getY());

                    }
                }
            }
        }
        return p1;

    }
    private static PanelPoint getXMaxPanel(PanelPoint p, ArrayList<PanelPoint> mRedPoint, ArrayList<PanelPoint> mBlackPoint) {
        PanelPoint p1=p;
        Xmax=8-p.getX();
        Log.d(TAG, "getXMaxPanel: Xmax="+Xmax);
        for (PanelPoint panelPoint : mRedPoint) {
            if (panelPoint.getY() == p.getY()) {
                int x1 = panelPoint.getX();
                int m = x1 - p.getX();
                if (m > 0) {
                    if (m <= Xmax) {
                        Xmax = m;
                        p1=panelPoint;
                        Log.d(TAG, "getXMaxPanel: X,y"+p1.getX()+p1.getY());

                    }
                } else if (m < 0) {
                    if (m >= Xmin) {
                        Xmin = m;

                    }
                }
            }
        }
        for (PanelPoint panelPoint : mBlackPoint) {
            if (panelPoint.getY() == p.getY()) {
                int x1 = panelPoint.getX();
                int m = x1 - p.getX();
                if (m > 0) {
                    if (m <= Xmax) {
                        Xmax = m;
                        p1 = panelPoint;
                        Log.d(TAG, "getXMaxPanel: X,y"+p1.getX()+p1.getY());

                    }
                } else if (m < 0) {
                    if (m >= min) {
                        Xmin = m;

                    }
                }
            }
        }
        return p1;

    }

    private static PanelPoint getMaxPanel(PanelPoint p, ArrayList<PanelPoint> mRedPoint, ArrayList<PanelPoint> mBlackPoint) {
        {
            max=9-p.getY();
            Log.d(TAG, "getMaxPanel: max="+max);
            PanelPoint p1=p;
            for (PanelPoint panelPoint : mRedPoint) {
                if (panelPoint.getX() == p.getX()) {
                    int y1 = panelPoint.getY();
                    int m = y1 - p.getY();
                    if (m > 0) {
                        if (m <= max) {
                            max = m;
                            p1=panelPoint;
                            Log.d(TAG, "getMaxPanel: x="+p1.getX()+"y="+p1.getY());
                        }
                    } else if (m < 0) {
                        if (m > min) {
                            min = m;
                        }
                    }
                }
            }
            for (PanelPoint panelPoint : mBlackPoint) {
                if (panelPoint.getX() == p.getX()) {
                    int y1 = panelPoint.getY();
                    int m = y1 - p.getY();
                    if (m > 0) {
                        if (m <= max) {
                            max = m;
                            p1=panelPoint;
                            Log.d(TAG, "getMaxPanel: x="+p1.getX()+"y="+p1.getY());

                        }
                    } else if (m < 0) {
                        if (m > min) {
                            min = m;
                        }
                    }
                }
            }
            Log.d(TAG, "getMaxPanel: max="+max);
            return p1;
        }
    }

    private static PanelPoint getMinPanel(PanelPoint p,ArrayList<PanelPoint> mRedPoint,ArrayList<PanelPoint>mBlackPoint) {
        PanelPoint p1=p;
        min=-p.getY();
        for (PanelPoint panelPoint : mRedPoint) {
            if (panelPoint.getX() == p.getX()) {
                int y1 = panelPoint.getY();
                int m = y1 - p.getY();
                if (m > 0) {
                    if (m < max) {
                        max = m;
                    }
                } else if (m < 0) {
                    if (m >= min) {
                        min = m;
                        p1=panelPoint;
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
                    if (m >= min) {
                        min = m;
                        p1 = panelPoint;
                    }
                }
            }
        }
        return p1;
    }

}
