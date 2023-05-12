package kz.itstep.dao;

import kz.itstep.annotations.AnnotationService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public abstract class AbstractDAO<T> implements DAO<T>{

//    private Connection connection;
//    private AnnotationService annotationService;
//
//    private final String SELECT_ALL = "SELECT * FROM ";
//
//    @Override
//    public Optional<T> findById(Long id) {
//        return Optional.empty();
//    }
//
//    @Override
//    public List<T> findAll() throws SQLException {
//        String tableName = annotationService.getTableName(this.getClass());
//        List<T> users = new ArrayList<>();
//
//        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL + tableName);
//
//        ResultSet resultSet = preparedStatement.executeQuery(); //user1 user2
//
//        while (resultSet.next()){
//            this.getClass().getField()
//
//
//            users.add(new User(id, login,password,creation_date));
//        }
//
//        return users;
//
//        return null;
//    }
//
//    @Override
//    public Integer deleteById(Long id, T t) {
//        return null;
//    }
}
