package com.limbo.test;

import com.limbo.entity.Point;
import com.limbo.util.Astar;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.util.List;

/**
 * Created by limbo on 16-10-25.
 */
public class AutoSankeTest extends Frame {


    //矩形大小
    private int d=10;
    //定义起点
    private int startX=0;
    private int startY=0;
    //定义终点
    private int endX=480;
    private int endY=480;

    //面板大小
    private int width=480;
    private int height=519;

    //计数器
    private int count=0;

    //Sanke
    private Point point=null;

    //初始化路径
//    List<Point> loadList=null;
      List<Point> loadList;

    public void lauchFrame(){
        Astar astar =  new Astar(startX,startY,endX,endY,width,height);
        loadList=astar.getLoad();
        System.out.println("sfafasfasfasdas!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        this.setSize(480,480);
        this.setLocation(500,200);
        this.addWindowListener(new WindowAdapter() {
            /**
             * Invoked when a window is in the process of being closed.
             * The close operation can be overridden at this point.
             *
             * @param e
             */
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(1);
            }
        });
        //  new Thread(new PaintThread()).start();
        this.setVisible(true);


        for (Point point:loadList){
            System.out.println(point);
        }
    }

    public static void main(String []args){
        new AutoSankeTest().lauchFrame();
    }
}
