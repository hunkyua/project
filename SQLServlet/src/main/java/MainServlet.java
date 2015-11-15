import sqlcmd.JDBCConnector;
import sqlcmd.command.table.TableCreate;
import sqlcmd.command.table.TableDelete;
import sqlcmd.command.table.TableNames;
import sqlcmd.command.table.TableSize;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by Hunky on 12.11.2015.
 */
public class MainServlet extends HttpServlet {
    public static String errorData;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = getAction(request);

        if (action.startsWith("/connect")) {
            String db_name = request.getParameter("db_name");
            String db_user = request.getParameter("db_user");
            String db_password = request.getParameter("db_password");
            clearAllData();

            try {
                new JDBCConnector(db_name, db_user, db_password);
                boolean SQLExc = false;
                try {
                    JDBCConnector.getConnection();
                    response.sendRedirect(response.encodeRedirectURL("menu.jsp"));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    errorData = e.getMessage();
                    SQLExc = true;
                }
                if (SQLExc) {
                    response.sendRedirect("connect.jsp");
                }
            } catch (Exception e) {
                request.setAttribute("message", e.getMessage());
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
        }
    }

    private void clearAllData() {
        TableCreate.error = "";
        TableCreate.doesNotExist = "";
        TableDelete.error = "";
        TableDelete.doesNotExist = "";
        TableSize.error = "";
        TableSize.doesNotExist = "";
        TableNames.error = "";
        TableNames.doesNotExist = "";
        TableNames.tables = new String[] {"*Please enter Show tables*"};
    }

    private String getAction(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        return requestURI.substring(request.getContextPath().length(), requestURI.length());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = getAction(request);
        response.setContentType("text/html; charset=utf-8");


        if (action.startsWith("/create")) {
            String create_table = request.getParameter("create_table");
            try {
                TableCreate.CreateTable(create_table);
                response.sendRedirect(response.encodeRedirectURL("createtable.jsp"));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                request.setAttribute("error", e.getMessage());
            }
        }

        if (action.startsWith("/delete")) {
            String delete_table = request.getParameter("delete_table");
            try {
                TableDelete.DeleteTable(delete_table);
                response.sendRedirect(response.encodeRedirectURL("deletetable.jsp"));
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        if (action.startsWith("/tablesize")) {
            String name_table = request.getParameter("name_table");
            try {
                TableSize.GetTableSize(name_table);
                response.sendRedirect(response.encodeRedirectURL("tablesize.jsp"));
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        if (action.startsWith("/showtables")) {
            try {
                TableNames.GetAllTableNames();
                response.sendRedirect(response.encodeRedirectURL("showtables.jsp"));
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}