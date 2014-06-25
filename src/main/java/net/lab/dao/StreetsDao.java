package net.lab.dao;

import net.lab.entity.Streets;

import java.sql.SQLException;
import java.util.List;

public interface StreetsDao {
    public void addStreet(Streets s) throws SQLException;
    public void updateStreet(Streets s) throws SQLException;
    public void delStreet(Streets s) throws SQLException;
    public Streets getStreetById(Integer in) throws SQLException;
    public List getAllStreets() throws SQLException;
}
