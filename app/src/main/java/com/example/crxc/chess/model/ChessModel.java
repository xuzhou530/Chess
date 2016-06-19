package com.example.crxc.chess.model;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.util.Log;

import com.example.crxc.chess.bean.PanelPoint;
import com.example.crxc.chess.bean.ChessPoint;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by crxc on 2016/6/9.
 */
public class ChessModel implements IChessModel {
    private static final String TAG = "ChessModel";
    private final Context mContext;
    ChessBitmap mChessBitmap = new ChessBitmap();
    Specification mSpecification = new Specification();
    RedPoint mRedPoint = new RedPoint();

    BlackPoint mBlackPoint = new BlackPoint();

    RedArray mRedArray = new RedArray();

    BlackArray mBlackArray = new BlackArray();
    JggPoint mJggPoint = new JggPoint();
    HongPoint mHongPoint = new HongPoint();
    HeiPoint mHeiPoint = new HeiPoint();
    VaildPoint mVaildPoint = new VaildPoint();
    JSPoint mJSPoint = new JSPoint();
    SelectPanelInfo mSelectPanelInfo = new SelectPanelInfo();
    RedVaildPoint mRedVaildPoint = new RedVaildPoint();
    BlackVaildPoint mBlackVaildPoint = new BlackVaildPoint();
    private MySqliteHelper myHelper;

    public ChessModel(Context context) {
        mContext=context;
        myHelper=new MySqliteHelper(mContext,new Date(System.currentTimeMillis()).toString()+".db",null,1);
    }


    @Override
    public void cshSpecification(int w, int h) {
        mSpecification.setmLineHight(w / 9);
        mSpecification.setmPanelHight(h);
        mSpecification.setmPanelWidth(w);
        mSpecification.setPieceWidth((int) mSpecification.getmLineHight() * Specification.Bili);
    }

    @Override
    public void cshPiece() {
        mChessBitmap.setmBingPiece(Bitmap.createScaledBitmap(mChessBitmap.getmBingPiece(), mSpecification.getPieceWidth(), mSpecification.getPieceWidth(), false));
        mChessBitmap.setmBingPiece(Bitmap.createScaledBitmap(mChessBitmap.getmBingPiece(), mSpecification.getPieceWidth(), mSpecification.getPieceWidth(), false));
        mChessBitmap.setmZuPiece(Bitmap.createScaledBitmap(mChessBitmap.getmZuPiece(), mSpecification.getPieceWidth(), mSpecification.getPieceWidth(), false));
        mChessBitmap.setmJiangPiece(Bitmap.createScaledBitmap(mChessBitmap.getmJiangPiece(), mSpecification.getPieceWidth(), mSpecification.getPieceWidth(), false));
        mChessBitmap.setmShuaiPiece(Bitmap.createScaledBitmap(mChessBitmap.getmShuaiPiece(), mSpecification.getPieceWidth(), mSpecification.getPieceWidth(), false));
        mChessBitmap.setmShiPiece(Bitmap.createScaledBitmap(mChessBitmap.getmShiPiece(), mSpecification.getPieceWidth(), mSpecification.getPieceWidth(), false));
        mChessBitmap.setmXiangPiece(Bitmap.createScaledBitmap(mChessBitmap.getmXiangPiece(), mSpecification.getPieceWidth(), mSpecification.getPieceWidth(), false));
        mChessBitmap.setmMaPiece(Bitmap.createScaledBitmap(mChessBitmap.getmMaPiece(), mSpecification.getPieceWidth(), mSpecification.getPieceWidth(), false));
        mChessBitmap.setmChePiece(Bitmap.createScaledBitmap(mChessBitmap.getmChePiece(), mSpecification.getPieceWidth(), mSpecification.getPieceWidth(), false));
        mChessBitmap.setmShiBPiece(Bitmap.createScaledBitmap(mChessBitmap.getmShiBPiece(), mSpecification.getPieceWidth(), mSpecification.getPieceWidth(), false));
        mChessBitmap.setmPaoPiece(Bitmap.createScaledBitmap(mChessBitmap.getmPaoPiece(), mSpecification.getPieceWidth(), mSpecification.getPieceWidth(), false));
        mChessBitmap.setmPaoBPiece(Bitmap.createScaledBitmap(mChessBitmap.getmPaoBPiece(), mSpecification.getPieceWidth(), mSpecification.getPieceWidth(), false));
        mChessBitmap.setmXiangBPiece(Bitmap.createScaledBitmap(mChessBitmap.getmXiangBPiece(), mSpecification.getPieceWidth(), mSpecification.getPieceWidth(), false));
        mChessBitmap.setmMaBPiece(Bitmap.createScaledBitmap(mChessBitmap.getmMaBPiece(), mSpecification.getPieceWidth(), mSpecification.getPieceWidth(), false));
        mChessBitmap.setmCheBPiece(Bitmap.createScaledBitmap(mChessBitmap.getmCheBPiece(), mSpecification.getPieceWidth(), mSpecification.getPieceWidth(), false));
    }

    @Override
    public void cshPoint() {
        mJSPoint.setmJiangPoint(new PanelPoint(4,9,mSpecification.getmLineHight()));
        mJSPoint.setmShuaiPoint(new PanelPoint(4,0,mSpecification.getmLineHight()));
        float mLineHight = mSpecification.getmLineHight();
        ArrayList<PanelPoint> redPoint = new ArrayList<PanelPoint>();
        ArrayList<PanelPoint> blackPoint = new ArrayList<PanelPoint>();
        for (int i = 0; i < 9; i++) {
            redPoint.add(new PanelPoint(i, 0, mLineHight));
            blackPoint.add(new PanelPoint(i, 9, mLineHight));
        }
        for (int i = 0; i < 5; i++) {
            redPoint.add(new PanelPoint(2 * i, 3, mLineHight));
            blackPoint.add(new PanelPoint(2 * i, 6, mLineHight));
        }
        redPoint.add(new PanelPoint(1, 2, mLineHight));
        redPoint.add(new PanelPoint(7, 2, mLineHight));
        blackPoint.add(new PanelPoint(1, 7, mLineHight));
        blackPoint.add(new PanelPoint(7, 7, mLineHight));
        setRedPoint(redPoint);
        setBlackPoint(blackPoint);
        Log.d(TAG, "cshPoint: "+getRedPoint().toString());
    }

    @Override
    public void cshArray() {
        ArrayList<PanelPoint> redPoint = mRedPoint.getmRedPoint();
        ArrayList<PanelPoint> blackPoint = mBlackPoint.getmBlackPoint();
        ArrayList<ChessPoint> redArray = new ArrayList<ChessPoint>();
        ArrayList<ChessPoint> blackArray = new ArrayList<ChessPoint>();
        ChessPoint cp1 = new ChessPoint(redPoint.get(0), mChessBitmap.getmChePiece());
        ChessPoint cp2 = new ChessPoint(redPoint.get(1), mChessBitmap.getmMaPiece());
        ChessPoint cp3 = new ChessPoint(redPoint.get(2), mChessBitmap.getmXiangPiece());
        ChessPoint cp4 = new ChessPoint(redPoint.get(3), mChessBitmap.getmShiPiece());
        ChessPoint cp5 = new ChessPoint(redPoint.get(4), mChessBitmap.getmShuaiPiece());
        ChessPoint cp9 = new ChessPoint(redPoint.get(8), mChessBitmap.getmChePiece());
        ChessPoint cp8 = new ChessPoint(redPoint.get(7), mChessBitmap.getmMaPiece());
        ChessPoint cp7 = new ChessPoint(redPoint.get(6), mChessBitmap.getmXiangPiece());
        ChessPoint cp6 = new ChessPoint(redPoint.get(5), mChessBitmap.getmShiPiece());
        ChessPoint cp10 = new ChessPoint(redPoint.get(9), mChessBitmap.getmBingPiece());
        ChessPoint cp11 = new ChessPoint(redPoint.get(10), mChessBitmap.getmBingPiece());
        ChessPoint cp12 = new ChessPoint(redPoint.get(11), mChessBitmap.getmBingPiece());
        ChessPoint cp13 = new ChessPoint(redPoint.get(12), mChessBitmap.getmBingPiece());
        ChessPoint cp14 = new ChessPoint(redPoint.get(13), mChessBitmap.getmBingPiece());
        ChessPoint cp15 = new ChessPoint(redPoint.get(14), mChessBitmap.getmPaoPiece());
        ChessPoint cp16 = new ChessPoint(redPoint.get(15), mChessBitmap.getmPaoPiece());
        redArray.add(cp1);
        redArray.add(cp2);
        redArray.add(cp3);
        redArray.add(cp4);
        redArray.add(cp5);
        redArray.add(cp6);
        redArray.add(cp7);
        redArray.add(cp8);
        redArray.add(cp9);
        redArray.add(cp10);
        redArray.add(cp11);
        redArray.add(cp12);
        redArray.add(cp13);
        redArray.add(cp14);
        redArray.add(cp15);
        redArray.add(cp16);


        ChessPoint cpb1 = new ChessPoint(blackPoint.get(0), mChessBitmap.getmCheBPiece());
        ChessPoint cpb2 = new ChessPoint(blackPoint.get(1), mChessBitmap.getmMaBPiece());
        ChessPoint cpb3 = new ChessPoint(blackPoint.get(2), mChessBitmap.getmXiangBPiece());
        ChessPoint cpb4 = new ChessPoint(blackPoint.get(3), mChessBitmap.getmShiBPiece());
        ChessPoint cpb5 = new ChessPoint(blackPoint.get(4), mChessBitmap.getmJiangPiece());
        ChessPoint cpb9 = new ChessPoint(blackPoint.get(8), mChessBitmap.getmCheBPiece());
        ChessPoint cpb8 = new ChessPoint(blackPoint.get(7), mChessBitmap.getmMaBPiece());
        ChessPoint cpb7 = new ChessPoint(blackPoint.get(6), mChessBitmap.getmXiangBPiece());
        ChessPoint cpb6 = new ChessPoint(blackPoint.get(5), mChessBitmap.getmShiBPiece());
        ChessPoint cpb10 = new ChessPoint(blackPoint.get(9), mChessBitmap.getmZuPiece());
        ChessPoint cpb11 = new ChessPoint(blackPoint.get(10), mChessBitmap.getmZuPiece());
        ChessPoint cpb12 = new ChessPoint(blackPoint.get(11), mChessBitmap.getmZuPiece());
        ChessPoint cpb13 = new ChessPoint(blackPoint.get(12), mChessBitmap.getmZuPiece());
        ChessPoint cpb14 = new ChessPoint(blackPoint.get(13), mChessBitmap.getmZuPiece());
        ChessPoint cpb15 = new ChessPoint(blackPoint.get(14), mChessBitmap.getmPaoBPiece());
        ChessPoint cpb16 = new ChessPoint(blackPoint.get(15), mChessBitmap.getmPaoBPiece());
        blackArray.add(cpb1);
        blackArray.add(cpb2);
        blackArray.add(cpb3);
        blackArray.add(cpb4);
        blackArray.add(cpb5);
        blackArray.add(cpb6);
        blackArray.add(cpb7);
        blackArray.add(cpb8);
        blackArray.add(cpb9);
        blackArray.add(cpb10);
        blackArray.add(cpb11);
        blackArray.add(cpb12);
        blackArray.add(cpb13);
        blackArray.add(cpb14);
        blackArray.add(cpb15);
        blackArray.add(cpb16);
        mRedArray.setmRedArray(redArray);
        mBlackArray.setmBlackArray(blackArray);
    }

    @Override
    public void cshJgg() {
        float mLineHight = mSpecification.getmLineHight();
        PanelPoint p1 = new PanelPoint(3, 0, mLineHight);
        PanelPoint p2 = new PanelPoint(3, 1, mLineHight);
        PanelPoint p3 = new PanelPoint(3, 2, mLineHight);
        PanelPoint p4 = new PanelPoint(4, 0, mLineHight);
        PanelPoint p5 = new PanelPoint(4, 1, mLineHight);
        PanelPoint p6 = new PanelPoint(4, 2, mLineHight);
        PanelPoint p7 = new PanelPoint(5, 0, mLineHight);
        PanelPoint p8 = new PanelPoint(5, 1, mLineHight);
        PanelPoint p9 = new PanelPoint(5, 2, mLineHight);
        PanelPoint pb1 = new PanelPoint(3, 7, mLineHight);
        PanelPoint pb2 = new PanelPoint(3, 8, mLineHight);
        PanelPoint pb3 = new PanelPoint(3, 9, mLineHight);
        PanelPoint pb4 = new PanelPoint(4, 7, mLineHight);
        PanelPoint pb5 = new PanelPoint(4, 8, mLineHight);
        PanelPoint pb6 = new PanelPoint(4, 9, mLineHight);
        PanelPoint pb7 = new PanelPoint(5, 7, mLineHight);
        PanelPoint pb8 = new PanelPoint(5, 8, mLineHight);
        PanelPoint pb9 = new PanelPoint(5, 9, mLineHight);

        ArrayList<PanelPoint> JggPoint = new ArrayList<PanelPoint>();
        JggPoint.add(p1);
        JggPoint.add(p2);
        JggPoint.add(p3);
        JggPoint.add(p4);
        JggPoint.add(p5);
        JggPoint.add(p6);
        JggPoint.add(p7);
        JggPoint.add(p8);
        JggPoint.add(p9);
        JggPoint.add(pb1);
        JggPoint.add(pb2);
        JggPoint.add(pb3);
        JggPoint.add(pb4);
        JggPoint.add(pb5);
        JggPoint.add(pb6);
        JggPoint.add(pb7);
        JggPoint.add(pb8);
        JggPoint.add(pb9);
        mJggPoint.setmJggPoint(JggPoint);
    }

    @Override
    public void cshHongPoint() {
        float mLineHight = mSpecification.getmLineHight();
        ArrayList<PanelPoint> HongPoint = new ArrayList<PanelPoint>();
        for (int y = 0; y < 5; y++) {
            for (int x = 0; x < 9; x++) {
                HongPoint.add(new PanelPoint(x, y, mLineHight));
            }
        }
        mHongPoint.setmHongPoint(HongPoint);
    }

    @Override
    public void cshHeiPoint() {
        float mLineHight = mSpecification.getmLineHight();
        ArrayList<PanelPoint> HeiPoint = new ArrayList<PanelPoint>();
        for (int y = 5; y < 10; y++) {
            for (int x = 0; x < 9; x++) {
                HeiPoint.add(new PanelPoint(x, y, mLineHight));
            }
        }
        mHeiPoint.setmHeiPoint(HeiPoint);
    }

    @Override
    public ArrayList<ChessPoint> getRedArray() {
        return mRedArray.getmRedArray();
    }

    @Override
    public ArrayList<ChessPoint> getBlackArray() {
        return mBlackArray.getmBlackArray();
    }

    @Override
    public float getLineHight() {
        return mSpecification.getmLineHight();
    }

    @Override
    public int getPanelWidth() {
        return mSpecification.getmPanelWidth();
    }

    @Override
    public int getPanelHight() {
        return mSpecification.getmPanelHight();
    }

    @Override
    public Boolean isDianJiGuo() {
        return mSpecification.getmDianJiGuo();
    }

    @Override
    public Boolean isRed() {
        return mSpecification.getmIsRead();
    }

    @Override
    public ArrayList<PanelPoint> getRedPoint() {
        return mRedPoint.getmRedPoint();
    }

    @Override
    public ArrayList<PanelPoint> getBlackPoint() {
        return mBlackPoint.getmBlackPoint();
    }

    @Override
    public PanelPoint getJiangPoint() {
        return mJSPoint.getmJiangPoint();
    }

    @Override
    public PanelPoint getShuaiPoint() {
        return mJSPoint.getmShuaiPoint();
    }

    @Override
    public ArrayList<PanelPoint> getVaildPoint() {
        return mVaildPoint.getmVaildPoint();
    }


    @Override
    public void setSelectPanelPoint(PanelPoint p) {
        mSelectPanelInfo.setPanelPoint(p);
    }

    @Override
    public void setSelectPanelBitmap(Bitmap bmp) {
        mSelectPanelInfo.setBmp(bmp);
    }

    @Override
    public void setSelectChessPoint(ChessPoint cp) {
        mSelectPanelInfo.setChessPoint(cp);
    }

    @Override
    public void setDianJiGuo(boolean b) {
        mSpecification.setmDianJiGuo(b);
    }

    @Override
    public Bitmap getSelectPanelBitmap() {
        return mSelectPanelInfo.getBmp();
    }

    @Override
    public Bitmap getShuaiPiece() {
        return mChessBitmap.getmShuaiPiece();
    }

    @Override
    public ArrayList<PanelPoint> getJggPoint() {
        return mJggPoint.getmJggPoint();
    }

    @Override
    public void setVaildPoint(ArrayList<PanelPoint> vaildPoint) {
        mVaildPoint.setmVaildPoint(vaildPoint);
    }

    @Override
    public Bitmap getShiPiece() {
        return mChessBitmap.getmShiPiece();
    }

    @Override
    public Bitmap getXiangPiece() {
        return mChessBitmap.getmXiangPiece();
    }

    @Override
    public Bitmap getMaPiece() {
        return mChessBitmap.getmMaPiece();
    }

    @Override
    public Bitmap getChePiece() {
        return mChessBitmap.getmChePiece();
    }

    @Override
    public Bitmap getPaoPiece() {
        return mChessBitmap.getmPaoPiece();
    }

    @Override
    public Bitmap getBingPiece() {
        return mChessBitmap.getmBingPiece();
    }

    @Override
    public Bitmap getJiangPiece() {
        return mChessBitmap.getmJiangPiece();
    }

    @Override
    public Bitmap getShiBPiece() {
        return mChessBitmap.getmShiBPiece();
    }

    @Override
    public Bitmap getXiangBPiece() {
        return mChessBitmap.getmXiangBPiece();
    }

    @Override
    public Bitmap getMaBPiece() {
        return mChessBitmap.getmMaBPiece();
    }

    @Override
    public Bitmap getCheBPiece() {
        return mChessBitmap.getmCheBPiece();
    }

    @Override
    public Bitmap getPaoBPiece() {
        return mChessBitmap.getmPaoBPiece();
    }

    @Override
    public Bitmap getZuPiece() {
        return mChessBitmap.getmZuPiece();
    }

    @Override
    public ArrayList<PanelPoint> getHongPoint() {
        return mHongPoint.getmHongPoint();
    }

    @Override
    public ArrayList<PanelPoint> getHeiPoint() {
        return mHeiPoint.getmHeiPoint();
    }

    @Override
    public void setIsRed(boolean b) {
        mSpecification.setmIsRead(b);

    }

    @Override
    public void setShuaiPoint(PanelPoint p) {
        mJSPoint.setmShuaiPoint(p);
    }

    @Override
    public void setBlackPoint(ArrayList<PanelPoint> mBlackPoint) {
        this.mBlackPoint.setmBlackPoint(mBlackPoint);
    }

    @Override
    public void setBlackArray(ArrayList<ChessPoint> mBlackArray) {
        this.mBlackArray.setmBlackArray(mBlackArray);
    }

    @Override
    public void setRedPoint(ArrayList<PanelPoint> mRedPoint) {
        this.mRedPoint.setmRedPoint(mRedPoint);
    }

    @Override
    public void setRedArray(ArrayList<ChessPoint> mRedArray) {
        this.mRedArray.setmRedArray(mRedArray);
    }

    @Override
    public PanelPoint getSelectPoint() {
        return mSelectPanelInfo.getPanelPoint();
    }

    @Override
    public ChessPoint getSelectChessPoint() {
        return mSelectPanelInfo.getChessPoint();
    }

    @Override
    public void setRedVaildPoint(ArrayList<PanelPoint> vaildPoint) {
        mRedVaildPoint.setmRedVaildPoint(vaildPoint);
    }

    @Override
    public ArrayList<PanelPoint> getRedVaildPoint() {
        return mRedVaildPoint.getmRedVaildPoint();
    }

    @Override
    public void setJiangPoint(PanelPoint p) {
        mJSPoint.setmJiangPoint(p);
    }

    @Override
    public void setBlackVaildPoint(ArrayList<PanelPoint> vaildPoint) {
        mBlackVaildPoint.setmBlackVaildPoint(vaildPoint);
    }

    @Override
    public ArrayList<PanelPoint> getBlackVaildPoint() {
        return mBlackVaildPoint.getmBlackVaildPoint();
    }

    @Override
    public void setPaoBPiece(Bitmap bitmap) {
        mChessBitmap.setmPaoBPiece(bitmap);
    }

    @Override
    public void setCheBPiece(Bitmap bitmap) {
        mChessBitmap.setmCheBPiece(bitmap);
    }

    @Override
    public void setMaBPiece(Bitmap bitmap) {
        mChessBitmap.setmMaBPiece(bitmap);
    }

    @Override
    public void setXiangBPiece(Bitmap bitmap) {
        mChessBitmap.setmXiangBPiece(bitmap);
    }

    @Override
    public void setShiBPiece(Bitmap bitmap) {
        mChessBitmap.setmShiBPiece(bitmap);
    }

    @Override
    public void setJiangPiece(Bitmap bitmap) {
        mChessBitmap.setmJiangPiece(bitmap);
    }

    @Override
    public void setShiPiece(Bitmap bitmap) {
        mChessBitmap.setmShiPiece(bitmap);
    }

    @Override
    public void setShuaiPiece(Bitmap bitmap) {
        mChessBitmap.setmShuaiPiece(bitmap);
    }

    @Override
    public void setXiangPiece(Bitmap bitmap) {
        mChessBitmap.setmXiangPiece(bitmap);
    }

    @Override
    public void setMaPiece(Bitmap bitmap) {
        mChessBitmap.setmMaPiece(bitmap);
    }

    @Override
    public void setChePiece(Bitmap bitmap) {
        mChessBitmap.setmChePiece(bitmap);
    }

    @Override
    public void setPaoPiece(Bitmap bitmap) {
        mChessBitmap.setmPaoPiece(bitmap);
    }

    @Override
    public void setZuPiece(Bitmap bitmap) {
        mChessBitmap.setmZuPiece(bitmap);
    }

    @Override
    public void setBingPiece(Bitmap bitmap) {
        mChessBitmap.setmBingPiece(bitmap);
    }

    @Override
    public void SavePoint(PanelPoint p, int id, ChessPoint chessPoint1) {
        PanelPoint p2=getSelectPoint();
        SQLiteDatabase db=myHelper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("_id",id);
        values.put("X1",p2.getX());
        values.put("Y1",p2.getY());
        values.put("X2",p.getX());
        values.put("Y2",p.getY());
        if (chessPoint1!=null) {
            ByteArrayOutputStream arrayOutputStream=new ByteArrayOutputStream();
            try {
                ObjectOutputStream objectOutputStream=new ObjectOutputStream(arrayOutputStream);
                objectOutputStream.writeObject(chessPoint1);
                objectOutputStream.flush();
                byte data[]=arrayOutputStream.toByteArray();
                objectOutputStream.close();
                arrayOutputStream.close();
                values.put("ChessPoint",data);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        db.insert("Chess",null,values);
        Log.d(TAG, "SavePoint: 插入数据");
        db.close();
    }

    @Override
    public ArrayList<Object> loadLastPoint(int id) {
        PanelPoint panelPoint1;
        PanelPoint panelPoint2;
        ArrayList<Object> list=new ArrayList<Object>();
        SQLiteDatabase db=myHelper.getReadableDatabase();
        Cursor cursor=db.query("Chess", null,"_id="+id,null,null,null,"_id");
        cursor.moveToFirst();
        if (cursor!=null&&cursor.moveToFirst()) {
            int X1Column=cursor.getColumnIndex("X1");
            int Y1Column=cursor.getColumnIndex("Y1");
            int X2Column=cursor.getColumnIndex("X2");
            int Y2Column=cursor.getColumnIndex("Y2");
            int X1=cursor.getInt(X1Column);
            int Y1=cursor.getInt(Y1Column);
            int X2=cursor.getInt(X2Column);
            int Y2=cursor.getInt(Y2Column);
            String text=cursor.getString(cursor.getColumnIndex("ChessPoint"));
            if (text!=null) {
                byte data[] =cursor.getBlob(cursor.getColumnIndex("ChessPoint"));
                ByteArrayInputStream arrayInputStream=new ByteArrayInputStream(data);
                try {
                    ObjectInputStream objectInputStream=new ObjectInputStream(arrayInputStream);
                    try {
                        ChessPoint chessPoint=(ChessPoint)objectInputStream.readObject();
                        list.add(chessPoint);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    objectInputStream.close();
                    arrayInputStream.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            panelPoint1=new PanelPoint(X1,Y1,getLineHight());
            panelPoint2=new PanelPoint(X2,Y2,getLineHight());
            list.add(panelPoint1);
            list.add(panelPoint2);
        }

        return  list;
    }


}
