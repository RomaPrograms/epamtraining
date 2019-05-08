package by.training.lakes_paradise.db.mysql;

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
     * Method deletes object in database by id and name of table.
     *
     * @param id          - id of object for deletion
     * @param nameOfTable - name of table
     */
    protected PreparedStatement delete(final String nameOfTable,
                                       final Integer id)
            throws SQLException {
        String sql = "delete from " + nameOfTable + " where id = (?)";
        PreparedStatement statement;
        statement = getConnection().prepareStatement(sql);
        statement.setInt(1, id);

        statement.executeUpdate();
        return statement;
    }
}
