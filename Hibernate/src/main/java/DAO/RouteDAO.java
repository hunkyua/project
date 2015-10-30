package DAO;

import logic.Bus;
import logic.Driver;
import logic.Route;

import java.sql.SQLException;
import java.util.Collection;

/**
 * Created by oktopus on 22.10.15.
 */
public interface RouteDAO {
        public void addRoute(Route route) throws SQLException;
        public void updateRoute(Long route_id, Route route) throws SQLException;
        public Route getRouteById(Long route_id) throws SQLException;
        public Collection getAllBusses() throws SQLException;
        public void deleteBus(Bus bus) throws SQLException;
        public Collection getBussesByDriver(Driver driver) throws SQLException;
        public Collection getBussesByRoute(Route route) throws SQLException;
        Collection getAllRoutes()throws SQLException;
}
