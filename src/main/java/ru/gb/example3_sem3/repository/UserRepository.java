package ru.gb.example3_sem3.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.gb.example3_sem3.domain.User;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class UserRepository {

    private final JdbcTemplate jdbc;

    public UserRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<User> findAll() {
        String sql = "SELECT * FROM userTable";
        return jdbc.query(sql,userRowMapper());
    }

    public void addUser(User user){
        String sql = "INSERT INTO userTable (name,age,email) VALUES (?,?,?)";
        jdbc.update(sql,user.getName(),user.getAge(),user.getEmail());
    }

    public List<User> filterUserByAge(int age){
        String sql = "SELECT * FROM userTable WHERE age>?";
        return jdbc.query(sql, userRowMapper(), age);
    }

    public List<User> sortUserByAge(){
        String sql = "SELECT * FROM userTable ORDER BY age";
        return jdbc.query(sql,userRowMapper());
    }

    public double averageAgeUser(){
        String sql = "SELECT AVG(age) from userTable";
        if(jdbc.queryForObject(sql,Double.class) == null){
            return 0.0;
        } else {
            return jdbc.queryForObject(sql,Double.class);
        }
    }





    private RowMapper<User> userRowMapper(){
        return (r, i) -> {
            User rowObject = new User();
            rowObject.setId(r.getInt("id"));
            rowObject.setName(r.getString("name"));
            rowObject.setAge(r.getInt("age"));
            rowObject.setEmail(r.getString("email"));
            return rowObject;
        };
    }

}
