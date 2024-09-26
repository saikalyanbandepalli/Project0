package org.example.Controller;

import org.example.Service.MilestoneService;
import org.example.model.Milestone;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class MilestoneController {
    private final MilestoneService milestoneService;

    public MilestoneController(Connection connection) {
        this.milestoneService = new MilestoneService(connection);
    }

    public List<Milestone> getAllMilestones() throws SQLException {
        return milestoneService.getAllMilestones();
    }

    public Milestone getMilestone(int id) throws SQLException {
        return milestoneService.getMilestone(id);
    }

    public void saveMilestone(Milestone milestone) throws SQLException {
        milestoneService.saveMilestone(milestone);
    }

    public void deleteMilestone(int id) throws SQLException {
        milestoneService.deleteMilestone(id);
    }
}

