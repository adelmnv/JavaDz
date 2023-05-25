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
public class BookingDAO implements DAO<Booking>{
    private final String SELECT_ALL = "SELECT * FROM Booking";
    private final String WHERE_ID = "WHERE id = ?";
    private final String SAVE = "INSERT INTO Booking(id, customer, room, start, end) VALUES(?,?,?,?,?)";
    private final String UPDATE_BY_ID = "UPDATE Booking SET customer = ?, room = ?, start = ?, end = ?" +
            "WHERE id = ?";
    private final String DELETE_BY_ID = "DELETE FROM Booking WHERE id = ?";
    private Connection connection;
    private AnnotationService annotationService;

    @Override
    public Optional<Booking> findById(Long id_) throws SQLException {
        PreparedStatement preparedStatement =
                connection.prepareStatement(SELECT_ALL + " " + WHERE_ID);

        preparedStatement.setLong(1, id_);

        ResultSet resultSet = preparedStatement.executeQuery();
        Customer customer = resultSet.getObject("customer", Customer.class);
        Room room = resultSet.getObject("room", Room.class);
        Date start = resultSet.getDate("start");
        Date end = resultSet.getDate("end");

        Booking booking = new Booking(id_, customer, room, start, end);

        return Optional.of(booking);
    }

    @Override
    public List<Booking> findAll() throws SQLException {
        List<Booking> bookings = new ArrayList<>();

        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL);

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()){
            long id = resultSet.getLong("id");
            Customer customer = resultSet.getObject("customer", Customer.class);
            Room room = resultSet.getObject("room", Room.class);
            Date start = resultSet.getDate("start");
            Date end = resultSet.getDate("end");

            bookings.add(new Booking(id, customer, room, start, end));
        }

        return bookings;
    }

    @Override
    public Integer save(Booking booking) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(SAVE);

        preparedStatement.setLong(1, booking.getId());
        preparedStatement.setObject(2, booking.getCustomer());
        preparedStatement.setObject(3, booking.getRoom());
        preparedStatement.setDate(4, booking.getStart());
        preparedStatement.setDate(5, booking.getEnd());

        return preparedStatement.executeUpdate();
    }

    @Override
    public Integer updateById(Long id, Booking booking) throws SQLException{
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BY_ID);

        preparedStatement.setObject(1, booking.getCustomer());
        preparedStatement.setObject (2, booking.getRoom());
        preparedStatement.setDate(3, booking.getStart());
        preparedStatement.setDate(4, booking.getEnd());
        preparedStatement.setLong(5, booking.getId());

        return preparedStatement.executeUpdate();
    }

    @Override
    public Integer deleteById(Long id) throws SQLException {
        Integer res = null;

        String tableName = annotationService.getTableName(this.getClass());
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID);
        preparedStatement.setLong(1, id);

        var rowAffectedCount = preparedStatement.executeUpdate();

        return rowAffectedCount;
    }
}
