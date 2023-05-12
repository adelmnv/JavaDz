package kz.itstep.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface DAO<T> {


    Optional<T> findById(Long id);
    List<T> findAll() throws SQLException;
    Integer save(T t);
    Integer updateById(Long id, T t);
    Integer deleteById(Long id, T t);
}
