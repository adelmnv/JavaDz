package kz.itstep.dao;
import kz.itstep.annotations.AnnotationService;
import kz.itstep.model.Customer;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor

public class CustomerDAO implements DAO<Customer> {
    private Connection connection;
    private AnnotationService annotationService;

    private final String SELECT_ALL = "SELECT * FROM ";
    private final String UPDATE = "UPDATE ";

    private final String DELETE = "DELETE FROM ";

    private final String INSERT_INTO = "INSERT INTO CUSTOMER (id,lName, fName, bDate, email) VALUES(?,?,?,?,?)";


    @Override
    public Optional<Customer> findById(Long id) throws SQLException{
        Customer customer = null;
        Optional<Customer> opt = Optional.empty();
        String tableName = annotationService.getTableName(this.getClass());
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL + tableName + " WHERE id = ?");
        preparedStatement.setLong(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()){
            String lName = resultSet.getString("lName");
            String fName = resultSet.getString("fName");
            String email = resultSet.getString("email");
            Date date = resultSet.getDate("bDate");

            customer = new Customer(id,lName,fName,date,email);
            opt = Optional.of(customer);
        }
        return opt;
    }

    @Override
    public List<Customer> findAll() throws SQLException {
        String tableName = annotationService.getTableName(this.getClass());
        List<Customer> customers = new ArrayList<Customer>();

        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL + tableName);

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()){
            long id = resultSet.getLong("id");
            String lName = resultSet.getString("lName");
            String fName = resultSet.getString("fName");
            String email = resultSet.getString("email");
            Date date = resultSet.getDate("bDate");

            customers.add(new Customer(id,lName,fName,date,email));
        }
        return customers;
    }

    @Override
    public Integer save(Customer customer) throws SQLException{
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO);
        preparedStatement.setLong(1,customer.getId());
        preparedStatement.setString(2,customer.getLName());
        preparedStatement.setString(3,customer.getFName());
        preparedStatement.setDate(4,customer.getBDate());
        preparedStatement.setString(5,customer.getEmail());

        var rowAffectedCount = preparedStatement.executeUpdate();

        return rowAffectedCount;
    }

    @Override
    public Integer updateById(Long id, Customer customer) throws SQLException{
        Integer res = null;

        String tableName = annotationService.getTableName(this.getClass());

        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE + tableName + "SET lName = ?, fName = ?, bDate = ?, email = ?" + " WHERE id = ?");

        preparedStatement.setString(1,customer.getLName());
        preparedStatement.setString(2,customer.getFName());
        preparedStatement.setDate(3,customer.getBDate());
        preparedStatement.setString(4,customer.getEmail());
        preparedStatement.setLong(5, id);

        var rowAffectedCount = preparedStatement.executeUpdate();

        return rowAffectedCount;
    }

    @Override
    public Integer deleteById(Long id) throws SQLException {
        Integer res = null;

        String tableName = annotationService.getTableName(this.getClass());

        PreparedStatement preparedStatement = connection.prepareStatement(DELETE + tableName + "WHERE id = ?");
        preparedStatement.setLong(1,id);

        var rowAffectedCount = preparedStatement.executeUpdate();

        return rowAffectedCount;
    }

}
