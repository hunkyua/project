package DAO;

import DAO.impl.BusDAOImpl;
import DAO.impl.DriverDAOImpl;
import DAO.impl.RouteDAOImpl;

/**
 * Created by oktopus on 22.10.15.
 */
public class Factory {

    private static BusDAO busDAO = null;
    private static DriverDAO driverDAO = null;
    private static RouteDAO routeDAO = null;
    private static Factory instance = null;

    public static synchronized Factory getInstance(){
        if (instance == null){
            instance = new Factory();
        }
        return instance;
    }

    public BusDAO getBusDAO(){
        if (busDAO == null){
            busDAO = new BusDAOImpl();
        }
        return busDAO;
    }

    public DriverDAO getDriverDAO(){
        if (driverDAO == null){
            driverDAO = new DriverDAOImpl();
        }
        return driverDAO;
    }

    public RouteDAO getRouteDAO(){
        if (routeDAO == null){
            routeDAO = new RouteDAOImpl();
        }
        return routeDAO;
    }
}