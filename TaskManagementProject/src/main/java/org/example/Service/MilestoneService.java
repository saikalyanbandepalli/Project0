package org.example.Service;

import org.example.DAO.MilestoneDAO;
import org.example.model.Milestone;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class MilestoneService {
    private final MilestoneDAO milestoneDAO;

    public MilestoneService(Connection connection) {
        this.milestoneDAO = new MilestoneDAO(connection);
    }

    public List<Milestone> getAllMilestones() throws SQLException {
        return milestoneDAO.getAllMilestones();
    }

    public Milestone getMilestone(int id) throws SQLException {
        return milestoneDAO.getMilestone(id);
    }

    public void saveMilestone(Milestone milestone) throws SQLException {
        milestoneDAO.saveMilestone(milestone);
    }

    public void deleteMilestone(int id) throws SQLException {
        milestoneDAO.deleteMilestone(id);
    }
}

