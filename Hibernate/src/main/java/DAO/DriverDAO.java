package DAO;

import logic.Bus;
import logic.Driver;
import logic.Route;

import java.sql.SQLException;
import java.util.Collection;

/**
 * Created by oktopus on 22.10.15.
 */
public interface DriverDAO {
    public void addDriver(Driver driver) throws SQLException;
    public void updateDriver(Long driver_id, Driver driver) throws SQLException;
    public Driver getDriverById(Long driver_id) throws SQLException;
    public Collection getAllBusses() throws SQLException;
    public void deleteBus(Bus bus) throws SQLException;
    public Collection getBussesByDriver(Driver driver) throws SQLException;
    public Collection getBussesByRoute(Route route) throws SQLException;

    Collection getDriversByBus(Bus bus) throws SQLException;
}
