package com.example.transactionality_demo.service;

import com.example.transactionality_demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional
    public void insert(List<User> users) {
        for (User user:users) {
            System.out.println("Inserting data for User name: " + user.getName());
            jdbcTemplate.update("insert into USER(NAME, DEPT, SALARY) values(?,?,?)", preparedStatement -> {
                preparedStatement.setString(1,user.getName());
                preparedStatement.setString(2, user.getDepartment());
                preparedStatement.setLong(3,user.getSalary());
            });
        }
    }

    public List<User> getUsers() {
        System.out.println("Retrieve all Users list ...");
        List <User> userList = jdbcTemplate.query("select NAME, DEPT, SALARY from USER",
                (resultSet, i) -> new User (resultSet.getString("Name"),
                resultSet.getString("Dept"),
                resultSet.getLong("Salary")));
        return userList;
    }

}
