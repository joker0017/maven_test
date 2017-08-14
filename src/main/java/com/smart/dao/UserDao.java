package com.smart.dao;

import com.smart.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.Map;

@Repository
public class UserDao {
    private JdbcTemplate jdbcTemplate;
    private final static String MATCH_COUNT_SQL="select count(*) as usercount from t_user where user_name=? and password=?";
    private final static String QUERY_USER_BY_USERNAME_SQL="select * from t_user where user_name=?";
    private final static String UPDATE_LOGIN_INFO_SQL = "update t_user set last_visit=?,last_ip=?,credits=? where user_id=?";
    private final static String INSERT_USER_SQL = "insert t_user(user_name,password,last_ip,last_visit,credits) " +
            "values(?,?,?,?,?  )";

    public User findUserByName(final String userName){
        final User user = new User();
        jdbcTemplate.query(QUERY_USER_BY_USERNAME_SQL, new Object[]{userName},
                new RowCallbackHandler() {
                    public void processRow(ResultSet rs) throws SQLException {
                        user.setUserId(rs.getInt("user_id"));
                        user.setUserName(userName);
                        user.setCredits(rs.getInt("credits"));
                    }
                });
        return user;
    }

    public int getMatchCount(final String userName, final String password){
        Map map = jdbcTemplate.queryForMap(MATCH_COUNT_SQL,new Object[]{userName,password});
        return Integer.parseInt(map.get("usercount").toString());
    }

    public void updateLoginInfo(User user){
        jdbcTemplate.update(UPDATE_LOGIN_INFO_SQL,new Object[]{user.getLastVisit(),user.getLastIp(),user.getCredits(),user.getUserId()});
    }

    public Number insertUser(final User user){
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection conn)
                    throws SQLException {
                PreparedStatement ps = conn.prepareStatement(INSERT_USER_SQL,Statement.RETURN_GENERATED_KEYS);
                //(user_name,password,last_ip,last_visit,credits)
                ps.setString(1,user.getUserName());
                ps.setString(2,user.getPassword());
                ps.setString(3,user.getLastIp());
                ps.setDate(4,new Date(user.getLastVisit().getTime()));
                ps.setInt(5,user.getCredits());
                return ps;
            }
        }, keyHolder);
        return keyHolder.getKey();
    }


    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
