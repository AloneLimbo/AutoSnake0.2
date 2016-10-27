package com.limbo.test;

import com.limbo.entity.Point;
import com.limbo.util.Astar;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by limbo on 16-10-25.
 */
public class AstarTest {


    public static void main(String []args){

        long startTime=System.currentTimeMillis();   //获取开始时间
        List<Point> snake  = new ArrayList<>();
        snake.add(new Point(0,0));
        Astar ast =new Astar(snake,20,18,20,20,48,48);
        List<Point> list = ast.getLoad();
        long endTime=System.currentTimeMillis();   //获取结束时间



        for (Point point:list){
            System.out.println(point);
        }
        System.out.println("程序运行时间： "+(endTime-startTime)+"ms");
    }

}
