package com.limbo.apllication;

import com.limbo.entity.Point;
import com.limbo.util.Astar;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by limbo on 16-10-25.
 */
public class AutoSnake extends Frame {

    //矩形大小
    private int d=10;
    //定义起点
    private int startX=0;
    private int startY=0;
    //定义终点
    private int endX=20;
    private int endY=20;

    //面板大小
    private int width=800;
    private int height=600;

    //计数器
    private int count=0;

    //Sanke
    private List<Point> snakeList=new ArrayList<Point>();



    //双缓冲，缓存
    Image offScreenImage = null;

    //初始化路径
    List<Point> loadList =null;

    int x=0,y=0;

    Point point = null;



    public void lauchFrame(){
        this.setSize(width,height);
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
        new Thread(new PaintThread()).start();
        snakeList.add(new Point(0,0));

        point = new Astar(snakeList,startX,startY,endX,endY,width/10,height/10).returnPoint(snakeList.get(0).getX(),snakeList.get(0).getY());

       // loadList = new Astar(snakeList,startX,startY,endX,endY,width/10,height/10).getLoad();
        this.setVisible(true);

    }

    @Override
    public void paint(Graphics g) {
        g.drawLine(0,39,width,39);
        drawEndPoint(endX*10,endY*10+39,g);

        drawSanek(snakeList,point.getX(),point.getY(),g);

        if(point.getX()==endX&&point.getY()==endY){
                int m=((int)(Math.random()*(80))),n=(int)(Math.random()*(50));
                    endX=m;
                    endY=n;
            snakeList.add(point);
            point = new Astar(snakeList,point.getX(),point.getY(),endX,endY,width/10,height/10).returnPoint(snakeList.get(0).getX(),snakeList.get(0).getY());
            drawSanek(snakeList,point.getX(),point.getY(),g);

            }
        point = new Astar(snakeList,point.getX(),point.getY(),endX,endY,width/10,height/10).returnPoint(snakeList.get(0).getX(),snakeList.get(0).getY());

       // System.out.println("snakeX = "+snakeList.get(0).getX()+" snakeY = "+snakeList.get(0).getY());
    }

    @Override
    public void update(Graphics g) {
        if(offScreenImage == null) {
            offScreenImage = this.createImage(width,height);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.gray);
        gOffScreen.fillRect(0, 0, width, height);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    public void drawEndPoint(int x, int y, Graphics g){
        Color c=g.getColor();
        g.setColor(Color.RED);
        g.fillRect(x,y,d,d);
        g.setColor(c);
    }

    public void drawSanek(List<Point> snakeList,int x,int y,Graphics g){
        Color c=g.getColor();
        g.setColor(Color.BLACK);
        if(snakeList.size()>1) {
            for (int i = snakeList.size() - 1; i > 0; i--) {
                snakeList.get(i).setX(snakeList.get(i - 1).getX());
                snakeList.get(i).setY(snakeList.get(i - 1).getY());
            }
        }
        snakeList.get(0).setX(x);
        snakeList.get(0).setY(y);
        for (Point point:snakeList)
        g.fillRect(point.getX()*10,point.getY()*10+39,d,d);
        g.setColor(c);
    }

    private class PaintThread implements Runnable {

        public void run() {
            while(true) {
                repaint();
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String []args){
       new AutoSnake().lauchFrame();
    }


}
