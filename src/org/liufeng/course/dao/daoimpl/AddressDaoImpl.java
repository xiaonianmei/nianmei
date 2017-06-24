package org.liufeng.course.dao.daoimpl;


import org.apache.ibatis.session.SqlSession;
import org.liufeng.course.bean.Address;
import org.liufeng.course.dao.AddressDao;
import org.liufeng.course.util.MybatisSqlSession;

import java.util.ArrayList;
import java.util.List;

import static org.liufeng.course.util.MybatisSqlSession.sqlSessionFactory;


public class AddressDaoImpl implements AddressDao {
	
      @Override
      public List<Address> getUserCount(){

          List<Address> list=new ArrayList<>();
          SqlSession session = sqlSessionFactory.openSession(false);
          try{
              AddressDao addressDao= MybatisSqlSession.getSqlSession().getMapper(AddressDao.class);
              list=addressDao.getUserCount();
          }finally{
              MybatisSqlSession.getSqlSession().close();
          }
          return list;
      }
}
