package org.example.Controller;

import org.example.Service.TimestampService;
import org.example.model.Timestamps;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class TimestampController {
    private final TimestampService timestampService;

    public TimestampController(Connection connection) {
        this.timestampService = new TimestampService(connection);
    }

    public List<Timestamps> getAllTimestamps() throws SQLException {
        return timestampService.getAllTimestamps();
    }

    public Timestamps getTimestamp(int id) throws SQLException {
        return timestampService.getTimestamp(id);
    }

    public void saveTimestamp(Timestamps timestamps) throws SQLException {
        timestampService.saveTimestamp(timestamps);
    }

    public void deleteTimestamp(int id) throws SQLException {
        timestampService.deleteTimestamp(id);
    }
}

