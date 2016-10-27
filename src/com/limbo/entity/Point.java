package com.limbo.entity;

/**
 * Created by limbo on 16-10-25.
 * 记录此点坐标
 * G，H  F值
 *
 */
public class Point {




    private int x;
    private int y;

    private int G;
    private int H;

    private int F;


    public Point(int x,int y){
        this.x=x;
        this.y=y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getG() {
        return G;
    }

    public int getH() {
        return H;
    }

    public int getF() {
        return F;
    }

    public void setF(int g,int h){
        this.F = g+h;
        this.G = g;
        this.H = h;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                ", G=" + G +
                ", H=" + H +
                ", F=" + F +
                '}';
    }
}
