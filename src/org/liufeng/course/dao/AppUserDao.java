package org.liufeng.course.dao;

 
import org.liufeng.course.bean.Address;

import java.util.HashMap;
import java.util.List;


public interface AppUserDao {

    //查询总数
    int addAppUser(HashMap<String, String> map);

    int deleteAppUser(HashMap<String,Integer> map);
}
