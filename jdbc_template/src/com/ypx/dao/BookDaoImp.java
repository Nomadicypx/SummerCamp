package com.ypx.dao;

import com.ypx.entity.T_User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BookDaoImp implements BookDao{
    @Autowired
    public JdbcTemplate jdbcTemplate;

    @Override
    public void add(T_User u) {
        System.out.println("执行方法");
        String sql = "insert into t_user values(?,?,?)";
        //"update t_user set username="",userstatus="" where user_id="""
        //"delete from t_user where user_id="""
        //"select * from t_user where user_id="""
        Object[] args = {u.getUserid(),u.getUsername(),u.getUserstatus()};
        int update = jdbcTemplate.update(sql,args);
        System.out.println(update);
    }
}
