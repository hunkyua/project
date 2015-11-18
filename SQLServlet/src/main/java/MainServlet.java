import sqlcmd.JDBCConnector;
import sqlcmd.ClearAllData;
import sqlcmd.command.crud.DeleteRecord;
import sqlcmd.command.crud.InsertRecord;
import sqlcmd.command.crud.SelectRecord;
import sqlcmd.command.crud.UpdateRecord;
import sqlcmd.command.db.CreateDB;
import sqlcmd.command.db.CreateUser;
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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = getAction(request);

        if (action.startsWith("/connect")) {
            String db_name = request.getParameter("db_name");
            String db_user = request.getParameter("db_user");
            String db_password = request.getParameter("db_password");
            ClearAllData.clear();

            try {
                new JDBCConnector(db_name, db_user, db_password);
                boolean SQLExc = false;
                try {
                    JDBCConnector.getConnection();
                    response.sendRedirect(response.encodeRedirectURL("menu.jsp"));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    SQLExc = true;
                    JDBCConnector.er_connect = "If you not have account: Register please!";
                    JDBCConnector.error = "Incorrect data. Try again!";
                }
                if (SQLExc) {
                    response.sendRedirect("connect.jsp");
                }
            } catch (Exception e) {
                request.setAttribute("message", e.getMessage());
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
        }


        if (action.startsWith("/createuser")) {
            String db_name = request.getParameter("db_name");
            String db_user = request.getParameter("db_user");
            String db_password = request.getParameter("db_password");
            ClearAllData.clear();

            try {
                new JDBCConnector("postgres", "postgres", "1336");
                boolean SQLExc = false;
                try {
                    CreateUser.UserCreate(db_user, db_password);
                    CreateDB.DBCreate(db_name, db_user);
                    response.sendRedirect(response.encodeRedirectURL("connect.jsp"));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    SQLExc = true;
                    JDBCConnector.error = "Sorry your data wrong!!!";
                }
                if (SQLExc) {
                    response.sendRedirect("registration.jsp");
                }
            } catch (Exception e) {
                request.setAttribute("message", e.getMessage());
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
        }

        if (action.startsWith("/updaterecord")) {
            String table_name = request.getParameter("table_name");
            Integer id = Integer.valueOf(request.getParameter("id"));
            String username = request.getParameter("username");
            String surname = request.getParameter("surname");
            try {
                UpdateRecord.UpdateRecordInTable(table_name, id, username, surname);
                response.sendRedirect(response.encodeRedirectURL("updaterecord.jsp"));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                request.setAttribute("error", e.getMessage());
            }
        }

        if (action.startsWith("/insertrecord")) {
            String table_name = request.getParameter("table_name");
            String username = request.getParameter("username");
            String surname = request.getParameter("surname");
            try {
                InsertRecord.InsertRecordInTable(table_name, username, surname);
                response.sendRedirect(response.encodeRedirectURL("insertrecord.jsp"));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                request.setAttribute("error", e.getMessage());
            }
        }

        if (action.startsWith("/deleterecord")) {
            String table_name = request.getParameter("table_name");
            Integer id = Integer.valueOf(request.getParameter("id"));
            try {
                DeleteRecord.DeleteRecordInTable(table_name, id);
                response.sendRedirect(response.encodeRedirectURL("deleterecord.jsp"));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                request.setAttribute("error", e.getMessage());
            }
        }

        if (action.startsWith("/selectrecord")) {
            String table_name = request.getParameter("table_name");
            String select = request.getParameter("select");
            try {
                SelectRecord.SelectRecordInTable(table_name, select);
                response.sendRedirect(response.encodeRedirectURL("selectrecord.jsp"));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                request.setAttribute("error", e.getMessage());
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