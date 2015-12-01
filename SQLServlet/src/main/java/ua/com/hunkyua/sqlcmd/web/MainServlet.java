package ua.com.hunkyua.sqlcmd.web;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.com.hunkyua.sqlcmd.dao.ClearAllData;
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
    ClearAllData clearAllDataBean = (ClearAllData) context.getBean("clear");
    JDBCDatabaseManager jdbcDatabaseManager = (JDBCDatabaseManager) context.getBean("JDBCConnector");


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = getAction(request);

        if (action.startsWith("/connect")) {
            String name = request.getParameter("name");
            String user = request.getParameter("user");
            String password = request.getParameter("password");
            clearAllDataBean.clear();
            try {
                jdbcDatabaseManager.connect(name, user, password);
                response.sendRedirect(response.encodeRedirectURL("menu.jsp"));
            } catch (Exception e) {
                request.setAttribute("message", e.getMessage());
                request.getRequestDispatcher("connect.jsp").forward(request, response);
                request.setAttribute("error", jdbcDatabaseManager.error);
                request.setAttribute("error_connect", jdbcDatabaseManager.error_connect);
                request.getRequestDispatcher("connect.jsp").forward(request, response);
                response.sendRedirect("connect.jsp");
            }

        }

        if (action.startsWith("/createuser")) {
            String dbName = request.getParameter("dbName");
            String user = request.getParameter("user");
            String password = request.getParameter("password");
            clearAllDataBean.clear();
            try {
                jdbcDatabaseManager.connect("postgres", "postgres", "1336");
                jdbcDatabaseManager.userCreate(user, password);
                jdbcDatabaseManager.dbCreate(dbName, user);
                response.sendRedirect(response.encodeRedirectURL("connect.jsp"));
            } catch (Exception e) {
                jdbcDatabaseManager.error = "Sorry your data wrong!!!";
                request.getRequestDispatcher("createuser.jsp");
                request.setAttribute("message", e.getMessage());
//                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
        }

        if (action.startsWith("/updaterecord")) {
            String tableName = request.getParameter("table_name");
            Integer id = Integer.valueOf(request.getParameter("id"));
            String username = request.getParameter("username");
            String surname = request.getParameter("surname");
            try {
                jdbcDatabaseManager.updateRecordInTable(tableName, id, username, surname);
                request.setAttribute("doesNotExist", jdbcDatabaseManager.doesNotExist);
                request.setAttribute("error", jdbcDatabaseManager.error);
                request.getRequestDispatcher("updaterecord.jsp").forward(request, response);
            } catch (Exception e) {
                request.setAttribute("error", e.getMessage());
                response.sendRedirect(response.encodeRedirectURL("updaterecord.jsp"));
            }
        }

        if (action.startsWith("/insertrecord")) {
            String table_name = request.getParameter("table_name");
            String username = request.getParameter("username");
            String surname = request.getParameter("surname");
            try {
                jdbcDatabaseManager.insertRecordInTable(table_name, username, surname);
                request.setAttribute("doesNotExist", jdbcDatabaseManager.doesNotExist);
                request.setAttribute("error", jdbcDatabaseManager.error);
                request.getRequestDispatcher("insertrecord.jsp").forward(request, response);

            } catch (Exception e) {
                request.setAttribute("error", e.getMessage());
                response.sendRedirect(response.encodeRedirectURL("insertrecord.jsp"));
            }
        }

        if (action.startsWith("/deleterecord")) {
            String table_name = request.getParameter("table_name");
            Integer id = Integer.valueOf(request.getParameter("id"));
            try {
                jdbcDatabaseManager.deleteRecordInTable(table_name, id);
                request.setAttribute("doesNotExist", jdbcDatabaseManager.doesNotExist);
                request.setAttribute("error", jdbcDatabaseManager.error);
                request.getRequestDispatcher("deleterecord.jsp").forward(request, response);

            } catch (Exception e) {
                request.setAttribute("error", e.getMessage());
                response.sendRedirect(response.encodeRedirectURL("deleterecord.jsp"));
            }
        }

        if (action.startsWith("/selectrecord")) {
            try {
                String table_name = request.getParameter("table_name");
                String select = request.getParameter("select");
                clearAllDataBean.clear();
                jdbcDatabaseManager.selectRecordInTable(table_name, select);
                request.setAttribute("doesNotExist", jdbcDatabaseManager.doesNotExist);
                request.setAttribute("error", jdbcDatabaseManager.error);
                request.setAttribute("list", jdbcDatabaseManager.list);
                request.getRequestDispatcher("selectrecord.jsp").forward(request, response);
            } catch (Exception e) {
                response.sendRedirect(response.encodeRedirectURL("selectrecord.jsp"));
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
            String create_table = request.getParameter("create_table");
            try {
                jdbcDatabaseManager.createTable(create_table);
                request.setAttribute("doesNotExist", jdbcDatabaseManager.doesNotExist);

                request.getRequestDispatcher("createtable.jsp").forward(request, response);
            } catch (Exception e) {
                request.setAttribute("error", jdbcDatabaseManager.error);
//                request.setAttribute("error", e.getMessage());
                response.sendRedirect(response.encodeRedirectURL("createtable.jsp"));
            }
        }

        if (action.startsWith("/deleteTable")) {
            String delete_table = request.getParameter("delete_table");
            try {
                jdbcDatabaseManager.deleteTable(delete_table);
                request.setAttribute("doesNotExist", jdbcDatabaseManager.doesNotExist);
                request.setAttribute("error", jdbcDatabaseManager.error);
                request.getRequestDispatcher("deletetable.jsp").forward(request, response);
            } catch (Exception e) {
                request.setAttribute("err", e.getMessage());
                response.sendRedirect(response.encodeRedirectURL("deletetable.jsp"));
            }
        }

        if (action.startsWith("/tablesize")) {
            String name_table = request.getParameter("name_table");
            try {
                jdbcDatabaseManager.getTableSize(name_table);
                request.setAttribute("doesNotExist", jdbcDatabaseManager.doesNotExist);
                request.setAttribute("error", jdbcDatabaseManager.error);
                request.setAttribute("size", jdbcDatabaseManager.size);
                request.getRequestDispatcher("tablesize.jsp").forward(request, response);
            } catch (Exception e) {
                response.sendRedirect(response.encodeRedirectURL("tablesize.jsp"));
            }
        }

        if (action.startsWith("/showtables")) {
            try {
                jdbcDatabaseManager.getAllTableNames();
                request.setAttribute("doesNotExist", jdbcDatabaseManager.doesNotExist);
                request.setAttribute("error", jdbcDatabaseManager.error);
                request.setAttribute("tableNames", jdbcDatabaseManager.toString(jdbcDatabaseManager.tables));
                request.getRequestDispatcher("showtables.jsp").forward(request, response);
            } catch (Exception e) {
                response.sendRedirect(response.encodeRedirectURL("showtables.jsp"));
            }
        }
    }
}