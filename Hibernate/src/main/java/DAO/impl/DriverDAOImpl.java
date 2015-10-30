package DAO.impl;

import DAO.DriverDAO;
import logic.Bus;
import logic.Driver;
import logic.Route;
import org.hibernate.Query;
import org.hibernate.Session;
import util.HibernateUtil;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by oktopus on 22.10.15.
 */
public class DriverDAOImpl implements DriverDAO {
    public void addDriver(Driver driver) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(driver);
            session.getTransaction().commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка при вставке", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {

                session.close();
            }
        }
    }

    public void updateDriver(Long driver_id, Driver driver) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(driver);
            session.getTransaction().commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка при вставке", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public Driver getDriverById(Long driver_id) throws SQLException {
        Session session = null;
        Driver driver = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            driver = (Driver) session.load(Driver.class, driver_id);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка 'findById'", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return driver;
    }

    public Collection getAllBusses() throws SQLException {
        Session session = null;
        List busses = new ArrayList<Bus>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            busses = session.createCriteria(Bus.class).list();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка 'getAll'", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return busses;
    }

    public void deleteBus(Bus bus) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(bus);
            session.getTransaction().commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка при удалении", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public Collection getBussesByDriver(Driver driver) throws SQLException {
        Session session = null;
        List busses = new ArrayList<Bus>();
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            Long driver_id = driver.getId();
            Query query = session.createQuery(
                    " select b "
                            + " from Bus b INNER JOIN b.drivers driver"
                            + " where driver.id = :driverId "
            )
                    .setLong("driverId", driver_id);
            busses = (List<Bus>) query.list();
            session.getTransaction().commit();

        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return busses;
    }

    public Collection getBussesByRoute(Route route) throws SQLException {
        Session session = null;
        List busses = new ArrayList<Bus>();
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            Long route_id = route.getId();
            Query query = session.createQuery("from Bus where route_id = :routeId ").setLong("routeId", route_id);
            busses = (List<Bus>) query.list();
            session.getTransaction().commit();

        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return busses;
    }


    public Collection getDriversByBus(Bus bus) throws SQLException {
        return null;
    }

}
