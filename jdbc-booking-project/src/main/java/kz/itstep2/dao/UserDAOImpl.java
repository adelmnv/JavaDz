package kz.itstep2.dao;

import kz.itstep2.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO{
    private final Connection connection;
    private final String SELECT_ALL = "SELECT * FROM users_test";
    private final String SELECT_BY_ID = "SELECT * FROM users_test where id = ?";
    private final String CREATE_USER = "INSERT INTO users_test(id, login, password, creation_date) VALUES(?,?,?,?)";
    private final String UPDATE_BY_ID = "UPDATE User SET login = ?, password = ?, creation_date = ?"+"WHERE id = ?";
    public UserDAOImpl(Connection connection) {
        this.connection = connection;
    }
    @Override
    public List<User> findAll() throws SQLException {
        List<User> users = new ArrayList<>();

        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL);

        ResultSet resultSet = preparedStatement.executeQuery(); //user1 user2

        while (resultSet.next()){
            long id = resultSet.getLong("id");
            String login = resultSet.getString("login");
            String password = resultSet.getString("password");
            Date creation_date = resultSet.getDate("creation_date");

            users.add(new User(id, login,password,creation_date));
        }

        return users;
    }
    @Override
    public User findById(Long id) throws SQLException { //SELECT * FROM users_test where id =
        User user = null;

        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID);
        preparedStatement.setLong(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();

        if(resultSet.next()){
            String login = resultSet.getString("login");
            String password = resultSet.getString("password");
            Date date = resultSet.getDate("creation_date");

            user = new User(id, login, password, date);
        }

        return user;
    }

    @Override
    public Integer create(User user) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(CREATE_USER);
        preparedStatement.setLong(1, user.getId());
        preparedStatement.setString(2, user.getLogin());
        preparedStatement.setString(3, user.getPassword());
        preparedStatement.setDate(4, user.getCreation_date());

        Integer rowAffectedCount = preparedStatement.executeUpdate();

        return rowAffectedCount;
    }

    @Override
    public Integer updateById(Long id, User user) throws SQLException{
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BY_ID);
        preparedStatement.setString(1, user.getLogin());
        preparedStatement.setString(2,user.getPassword());
        preparedStatement.setDate(3,user.getCreation_date());
        preparedStatement.setLong(4,id);

        var rowAffectedCount = preparedStatement.executeUpdate();

        return rowAffectedCount;
    }
}
