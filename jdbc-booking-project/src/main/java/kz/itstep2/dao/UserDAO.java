package kz.itstep2.dao;

import kz.itstep2.model.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface UserDAO {
    List<User> findAll() throws SQLException;
    User findById(Long id) throws SQLException;
    Integer create(User user) throws SQLException;
    Integer updateById(Long id, User user) throws SQLException;
}