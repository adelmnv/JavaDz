package kz.itstep.dao;

import kz.itstep.annotations.AnnotationService;
import kz.itstep.model.Booking;
import kz.itstep.model.Customer;
import kz.itstep.model.Room;
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
public class RoomDAO implements DAO<Room>{
    private Connection connection;
    private AnnotationService annotationService;

    private final String SELECT_ALL = "SELECT * FROM Room";
    private final String UPDATE = "UPDATE Room SET roomsCount = ?, priceForOneRoom = ?"+"WHERE id = ?";

    private final String DELETE = "DELETE FROM Room WHERE id = ?";

    private final String INSERT_INTO = "INSERT INTO Room (id,roomsCount, priceForOneRoom) VALUES(?,?,?)";

    private final String WHERE_ID = "WHERE id = ?";


    @Override
    public Optional<Room> findById(Long id) throws SQLException {
        Room room = null;
        Optional<Room> opt = Optional.empty();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL + WHERE_ID);
        preparedStatement.setLong(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()){
            Integer roomsCount = resultSet.getInt("roomsCount");
            Integer priceForOneRoom = resultSet.getInt("priceForOneRoom");

            room = new Room(id, roomsCount,priceForOneRoom);
            opt = Optional.of(room);
        }
        return opt;
    }

    @Override
    public List<Room> findAll() throws SQLException {
        List<Room> rooms = new ArrayList<Room>();

        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL );

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()){
            long id = resultSet.getLong("id");
            Integer roomsCount = resultSet.getInt("roomsCount");
            Integer priceForOneRoom = resultSet.getInt("priceForOneRoom");

            rooms.add(new Room(id, roomsCount,priceForOneRoom));
        }
        return rooms;
    }

    @Override
    public Integer save(Room room) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO);
        preparedStatement.setLong(1,room.getId());
        preparedStatement.setInt(2,room.getRoomsCount());
        preparedStatement.setInt(3,room.getPriceForOneRoom());

        var rowAffectedCount = preparedStatement.executeUpdate();

        return rowAffectedCount;
    }

    @Override
    public Integer updateById(Long id, Room room) throws SQLException {
        Integer res = null;

        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);

        preparedStatement.setInt(1,room.getRoomsCount());
        preparedStatement.setInt(2,room.getPriceForOneRoom());
        preparedStatement.setLong(3,room.getId());

        var rowAffectedCount = preparedStatement.executeUpdate();

        return rowAffectedCount;
    }

    @Override
    public Integer deleteById(Long id) throws SQLException {
        Integer res = null;

        String tableName = annotationService.getTableName(this.getClass());

        PreparedStatement preparedStatement = connection.prepareStatement(DELETE);
        preparedStatement.setLong(1,id);

        var rowAffectedCount = preparedStatement.executeUpdate();

        return rowAffectedCount;
    }
}
