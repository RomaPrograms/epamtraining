package by.training.lakes_paradise.db.mysql;

import by.training.lakes_paradise.db.ConnectionDB;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class BaseDaoRealization {
    private Connection connection;

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public Connection getConnection() {
        return connection;
    }

    /**
     * Method deletes object in database by id.
     *
     * @param id - id of object for deletion
     */
    protected PreparedStatement delete(final String nameOfTable, final Integer id)
            throws SQLException {
        String sql = "delete from " + nameOfTable + " where id = (?)";
        Connection connection = null;
        PreparedStatement statement = null;
        connection = ConnectionDB.getConnection();
        statement = connection.prepareStatement(sql);
        statement.setInt(1, id);

        statement.executeUpdate();
        return statement;
    }
}
