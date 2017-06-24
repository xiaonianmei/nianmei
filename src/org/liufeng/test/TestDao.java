package org.liufeng.test;

import net.sf.json.JSONArray;
import org.apache.ibatis.session.SqlSession;
import org.liufeng.course.bean.Address;
import org.liufeng.course.dao.AddressDao;
import org.liufeng.course.dao.AppUserDao;
import org.liufeng.course.util.MybatisSqlSession;
import org.liufeng.weixin.pojo.AccessToken;
import org.liufeng.weixin.util.WeixinUtil;

import java.io.*;
import java.nio.file.Paths;
import java.util.*;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;
import static org.liufeng.course.util.MybatisSqlSession.sqlSessionFactory;

/**
 * Created by Administrator on 2017/5/25.
 */
class TestDao implements Runnable {
    private int ticket =10;
    private String name;
    public void run(){
        for(int i =0;i<500;i++){
            if(this.ticket>0){
                System.out.println(Thread.currentThread().getName()+"卖票---->"+(this.ticket--));
            }
        }
    }
}
class RunnableDemo {


    public static void main(String[] args) {
        // TODO Auto-generated method stub
        //设计三个线程
        TestDao mt = new TestDao();
        Thread t1 = new Thread(mt,"一号窗口");
        Thread t2 = new Thread(mt,"二号窗口");
        Thread t3 = new Thread(mt,"三号窗口");
//         MyThread1 mt2 = new MyThread1();
//         MyThread1 mt3 = new MyThread1();
        t1.start();
        t2.start();
        t3.start();
    }
}
