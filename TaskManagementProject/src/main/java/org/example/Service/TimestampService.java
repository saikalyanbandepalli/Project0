package org.example.Service;

import org.example.DAO.TimestampDAO;
import org.example.model.Timestamps;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class TimestampService {
    private final TimestampDAO timestampDAO;

    public TimestampService(Connection connection) {
        this.timestampDAO = new TimestampDAO(connection);
    }

    public List<Timestamps> getAllTimestamps() throws SQLException {
        return timestampDAO.getAllTimestamps();
    }

    public Timestamps getTimestamp(int id) throws SQLException {
        return timestampDAO.getTimestamp(id);
    }

    public void saveTimestamp(Timestamps timestamps) throws SQLException {
        timestampDAO.saveTimestamp(timestamps);
    }

    public void deleteTimestamp(int id) throws SQLException {
        timestampDAO.deleteTimestamp(id);
    }
}

