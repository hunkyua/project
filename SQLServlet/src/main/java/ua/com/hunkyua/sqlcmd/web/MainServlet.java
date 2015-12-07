package ua.com.hunkyua.sqlcmd.web;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.com.hunkyua.sqlcmd.dao.JDBCDatabaseManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Hunky on 12.11.2015.
 */
public class MainServlet extends HttpServlet {

    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"config.xml"});
    JDBCDatabaseManager jdbcDatabaseManager = (JDBCDatabaseManager) context.getBean("JDBCConnector");


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = getAction(request);
        if (action.startsWith("/connect")) {
            String name = request.getParameter("name");
            String user = request.getParameter("user");
            String password = request.getParameter("password");
            try {
                jdbcDatabaseManager.connect(name, user, password);
                response.sendRedirect(response.encodeRedirectURL("menu.jsp"));
            } catch (Exception e) {
                request.setAttribute("message", e.getMessage());
                request.setAttribute("registerError", "If you not have account: Register please!");
                request.getRequestDispatcher("connect.jsp").forward(request, response);
            }

        }

        if (action.startsWith("/registration")) {
            String dbName = request.getParameter("dbName");
            String user = request.getParameter("user");
            String password = request.getParameter("password");
            try {
                jdbcDatabaseManager.connect("postgres", "postgres", "1336");
                jdbcDatabaseManager.userCreate(user, password);
                jdbcDatabaseManager.dbCreate(dbName, user);
                request.setAttribute("created", "Your account created");
                response.sendRedirect(response.encodeRedirectURL("connect.jsp"));
            } catch (Exception e) {
                request.setAttribute("message", e.getMessage());
                request.getRequestDispatcher("registration.jsp").forward(request, response);
            }
        }

        if (action.startsWith("/updaterecord")) {
            String tableName = request.getParameter("table_name");
            Integer id = Integer.valueOf(request.getParameter("id"));
            String username = request.getParameter("username");
            String surname = request.getParameter("surname");
            try {
                jdbcDatabaseManager.updateRecordInTable(tableName, id, username, surname);
                request.setAttribute("exist", String.format("Record id:\"%s\" in table:\"%s\" updated", id, tableName));
                request.getRequestDispatcher("updaterecord.jsp").forward(request, response);
            } catch (Exception e) {
                request.setAttribute("doesNotExist", String.format("Sorry, but record id: \"%s\" in table:\"%s\" can't be updated. Try again", id, tableName));
                request.setAttribute("error", e.getMessage());
                request.getRequestDispatcher("updaterecord.jsp").forward(request, response);
            }
        }

        if (action.startsWith("/insertrecord")) {
            String tableName = request.getParameter("tableName");
            String username = request.getParameter("username");
            String surname = request.getParameter("surname");
            try {
                jdbcDatabaseManager.insertRecordInTable(tableName, username, surname);
                request.setAttribute("exist", String.format("Record username:\"%s\" surname:\"%s\" in table:\"%s\" inserted", username, surname, tableName));
                request.getRequestDispatcher("insertrecord.jsp").forward(request, response);
            } catch (Exception e) {
                request.setAttribute("doesNotExist", String.format("Sorry, but record username:\"%s\" surname:\"%s\" in table:\"%s\" can't be inserted." +
                        " Try again", username, surname, tableName));
                request.setAttribute("error", e.getMessage());
                request.getRequestDispatcher("insertrecord.jsp").forward(request, response);
            }
        }

        if (action.startsWith("/deleterecord")) {
            String tableName = request.getParameter("tableName");
            Integer id = Integer.valueOf(request.getParameter("id"));
            try {
                jdbcDatabaseManager.deleteRecordInTable(tableName, id);
                request.setAttribute("exist", String.format("Record id:\"%s\" in table:\"%s\" deleted", id, tableName));
                request.getRequestDispatcher("deleterecord.jsp").forward(request, response);
            } catch (Exception e) {
                request.setAttribute("doesNotExist", String.format("Sorry, but record id:\"%s\" in table:\"%s\" can't be deleted. Try again", id, tableName));
                request.setAttribute("error", e.getMessage());
                request.getRequestDispatcher("deleterecord.jsp").forward(request, response);            }
        }

        if (action.startsWith("/selectrecord")) {
            String tableName = request.getParameter("tableName");
            String select = request.getParameter("select");
            try {
                jdbcDatabaseManager.selectRecordInTable(tableName, select);
                request.setAttribute("exist", String.format("Record in table:\"%s\"", tableName));
                request.setAttribute("list", jdbcDatabaseManager.selectRecordInTable(tableName, select));
                request.getRequestDispatcher("selectrecord.jsp").forward(request, response);
            } catch (Exception e) {
                request.setAttribute("doesNotExist", String.format("Sorry, but record in table:\"%s\" can't be selected. Try again", tableName));
                request.setAttribute("error", e.getMessage());
                request.getRequestDispatcher("selectrecord.jsp").forward(request, response);
            }
        }
    }

    private String getAction(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        return requestURI.substring(request.getContextPath().length(), requestURI.length());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = getAction(request);
        response.setContentType("text/html; charset=utf-8");

        if (action.startsWith("/createTable")) {
            String tableName = request.getParameter("tableName");
            try {
//                TableService tableService = new TableService();
//                tableService.tableCreate("lox");
                jdbcDatabaseManager.createTable(tableName);
                request.setAttribute("exist", String.format("Table:\"%s\" created", tableName));
                request.getRequestDispatcher("createtable.jsp").forward(request, response);
            } catch (Exception e) {
                request.setAttribute("doesNotExist", String.format("Sorry, but table:\"%s\" can't be created. Try again", tableName));
                request.setAttribute("error", e.getMessage());
                request.getRequestDispatcher("createtable.jsp").forward(request, response);
            }
        }

        if (action.startsWith("/deleteTable")) {
            String tableName = request.getParameter("tableName");
            try {
                jdbcDatabaseManager.deleteTable(tableName);
                request.setAttribute("exist", String.format("Table:\"%s\" deleted", tableName));
                request.getRequestDispatcher("deletetable.jsp").forward(request, response);
            } catch (Exception e) {
                request.setAttribute("doesNotExist", String.format("Sorry, but table:\"%s\" can't be deleted. Try again", tableName));
                request.setAttribute("error", e.getMessage());
                request.getRequestDispatcher("deletetable.jsp").forward(request, response);
            }
        }

        if (action.startsWith("/tablesize")) {
            String tableName = request.getParameter("tableName");
            try {
                jdbcDatabaseManager.getTableSize(tableName);
                request.setAttribute("size", jdbcDatabaseManager.getTableSize(tableName));
                request.getRequestDispatcher("tablesize.jsp").forward(request, response);
            } catch (Exception e) {
                request.setAttribute("doesNotExist", String.format("Sorry, but table:\"%s\" doesn't exist. Try again", tableName));
                request.setAttribute("error", e.getMessage());
                request.getRequestDispatcher("tablesize.jsp").forward(request, response);
            }
        }

        if (action.startsWith("/showtables")) {
            try {
                jdbcDatabaseManager.getAllTableNames();
                request.setAttribute("tableNames", jdbcDatabaseManager.toString(jdbcDatabaseManager.getAllTableNames()));
                request.getRequestDispatcher("showtables.jsp").forward(request, response);
            } catch (Exception e) {
                request.setAttribute("tableNames", String.format("Register please!!!"));
                request.setAttribute("doesNotExist", String.format("Sorry, but tables doesn't exist. Try again"));
                request.setAttribute("error", e.getMessage());
                request.getRequestDispatcher("showtables.jsp").forward(request, response);
            }
        }
    }
}