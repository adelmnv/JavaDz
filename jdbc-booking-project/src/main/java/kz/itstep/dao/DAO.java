package kz.itstep.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface DAO<T> {


    Optional<T> findById(Long id) throws SQLException;
    List<T> findAll() throws SQLException;
    Integer save(T t) throws SQLException;
    Integer updateById(Long id, T t) throws SQLException;
    Integer deleteById(Long id) throws SQLException;
}
