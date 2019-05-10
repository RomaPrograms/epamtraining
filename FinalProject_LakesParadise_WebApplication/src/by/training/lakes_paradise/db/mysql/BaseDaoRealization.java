package by.training.lakes_paradise.db.mysql;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Class with common methods and variables for DAO classes.
 */
public abstract class BaseDaoRealization {
    /**
     * Connection to database.
     */
    private Connection connection;

    /**
     * Sets the value of connection property.
     *
     * @param curConnection - value of connection property
     */
    public void setConnection(final Connection curConnection) {
        this.connection = curConnection;
    }

    /**
     * Gets the value of connection property.
     *
     * @return value of connection property
     */
    public Connection getConnection() {
        return connection;
    }

    /**
     * Method deletes object in database by id and name of table.
     *
     * @param id          - id of object for deletion
     * @param nameOfTable - name of table
     */

    /**
     * Method deletes object in database by id and name of table.
     *
     * @param nameOfTable - name of table
     * @param id - id of object for deletion
     * @return instance of executed statement
     * @throws SQLException - exception connected with database
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
