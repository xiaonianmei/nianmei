package org.liufeng.course.dao.daoimpl;


import org.apache.ibatis.session.SqlSession;
import org.liufeng.course.bean.Address;
import org.liufeng.course.dao.AddressDao;
import org.liufeng.course.dao.AppUserDao;
import org.liufeng.course.util.MybatisSqlSession;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.liufeng.course.util.MybatisSqlSession.sqlSessionFactory;


public class AppUserDaoImpl implements AppUserDao {


      @Override
      public int addAppUser(HashMap<String, String> map){
          int count=0;
          SqlSession session = sqlSessionFactory.openSession(false);
          try{
              AppUserDao appUserDao= MybatisSqlSession.getSqlSession().getMapper(AppUserDao.class);
              count=appUserDao.addAppUser(map);
          }finally{
              MybatisSqlSession.getSqlSession().close();
          }
          return count;
      }
    @Override
    public int deleteAppUser(HashMap<String,Integer> map){
        int count=0;
        SqlSession session = sqlSessionFactory.openSession(false);
        try{
            AppUserDao appUserDao= MybatisSqlSession.getSqlSession().getMapper(AppUserDao.class);
            count=appUserDao.deleteAppUser(map);
        }finally{
            MybatisSqlSession.getSqlSession().close();
        }
        return count;
    }
}
