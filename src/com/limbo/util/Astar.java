package com.limbo.util;

import com.limbo.entity.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * 寻路算法
 * Created by limbo on 16-10-25.
 */
public class Astar {

    //定义搜寻地图大小 宽，高
    private int width;
    private int height;

    //定义起点
    private int startX;
    private int startY;
    //定义终点
    private int endX;
    private int endY;

    int[][] map=new int[width][height];

    //定义路径表
    private List<Point> loadList = new ArrayList<Point>();

    //Snake身体
    private  List<Point> snakePage  = null;

    //定义Astar构造函数
    public  Astar(List<Point> snakePage,int startX,int startY,int endX,int endY,int width,int height){
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        this.width = width;
        this.height = height;
        this.snakePage = snakePage;
    }

    //定义Astar构造函数
    public  Astar(int startX,int startY,int endX,int endY,int width,int height){
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        this.width = width;
        this.height = height;
    }

    public Astar(List<Point> snakePage){
        this.snakePage=snakePage;
    }

    /**
     * 列出点x,y上下左右能取得的点
     * 并计算G，H，F
     * 返回openList
     * @param x
     * @param y
     * @return
     */
    public List<Point> Obtain(int x,int y){
        List<Point> openList = new ArrayList<Point>();
        if(x-1>=0){
            Point point = new Point(x-1,y);
            if(endX<startX) {

                    point.setF(Math.abs(x - 1 - startX) + Math.abs(y - startY)-1,Math.abs(x - 1 - endX) + Math.abs(y - endY)-1);

            }else {
                point.setF(Math.abs(x - 1 - startX) + Math.abs(y - startY)+2,Math.abs(x - 1 - endX) + Math.abs(y - endY)+width*2);
            }
            openList.add(point);
        }

        if(x+1<=width-1){
            Point point = new Point(x+1,y);

            if(endX>startX) {


                    point.setF(Math.abs(x + 1 - startX) + Math.abs(y - startY)-1,Math.abs(x + 1 - endX) + Math.abs(y - endY)-1);

            }else {
                point.setF(Math.abs(x + 1 - startX) + Math.abs(y - startY)+2,Math.abs(x + 1 - endX) + Math.abs(y - endY)+width*2);
            }
            openList.add(point);
        }

        if(y-1>=0){
            Point point = new Point(x,y-1);
            if(endY<startY) {

                    point.setF(Math.abs(x - startX) + Math.abs(y -1- startY)-1,Math.abs(x - endX) + Math.abs(y -1- endY)-1);
            }else {
                point.setF(Math.abs(x - startX) + Math.abs(y -1- startY)+2,Math.abs(x  - endX) + Math.abs(y -1- endY)+width*2);
            }
            openList.add(point);
        }

        if(y+1<=height-1){
            Point point = new Point(x,y+1);

            if(endY>startY) {

                    point.setF(Math.abs(x  - startX) + Math.abs(y +1- startY)-1,Math.abs(x  - endX) + Math.abs(y +1- endY)-1);
            }else {

                point.setF(Math.abs(x  - startX) + Math.abs(y +1- startY)+2,Math.abs(x  - endX) + Math.abs(y +1- endY)+width*2);
            }
            openList.add(point);
        }

        return openList;
    }



    public boolean isOnSnake( int x,int y){
        x=x*10;
        y=y*10+39;
        for (int i=1;i<snakePage.size();i++){
           if( snakePage.get(i).getX()==x&&snakePage.get(i).getY()==y){

               return true;
           }
        }
        return false;
    }
    /**
     * 获得点x,y周围F值最小的点
     * 返回Point（entity）
     * @param openList
     * @return
     */
    public Point queryPointByMinF(List<Point> openList){

        for(Point snake : snakePage){

            for(int i=0;i<openList.size();i++){
                if (snake.getX() == openList.get(i).getX()&& snake.getY() == openList.get(i).getY()) {
                            openList.get(i).setF(1000000,1000000);
                        }
            }
        }

        Point point=openList.get(0);
        for(int i=1;i<=openList.size()-1;i++){
            if(openList.get(i).getF()<point.getF()){
                point=openList.get(i);
            }
        }
        openList.clear();
        return point;
    }

    public List<Point> getLoad(){

        loadList.add(new Point(startX,startY));
        int x=startX,y=startY;
        Point point=null;

       for(;;){
           point =  queryPointByMinF(Obtain(x,y));
           if(point!=null&&point.getX()==endX&&point.getY()==endY){
               loadList.add(point);
               break;
           }else {
               loadList.add(point);
               x=point.getX();
               y=point.getY();
           }
       }
        return loadList;
    }




    public Point returnPoint(int x,int y){
        return queryPointByMinF(Obtain(x,y));
    }

}
