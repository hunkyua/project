import org.springframework.context.support.ClassPathXmlApplicationContext;
import sqlcmd.ClearAllData;
import sqlcmd.JDBCConnector;
import sqlcmd.command.crud.RecordDelete;
import sqlcmd.command.crud.RecordInsert;
import sqlcmd.command.crud.RecordSelect;
import sqlcmd.command.crud.RecordUpdate;
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

    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"config.xml"});
    ClearAllData clearAllDataBean = (ClearAllData) context.getBean("clear");
    TableNames tableNamesBean = (TableNames) context.getBean("tableNames");
    TableSize tableSizeBean = (TableSize) context.getBean("tableSize");
    TableCreate tableCreateBean = (TableCreate) context.getBean("tableCreate");
    TableDelete tableDeleteBean = (TableDelete) context.getBean("tableDelete");
    RecordDelete recordDeleteBean = (RecordDelete) context.getBean("recordDelete");
    RecordInsert recordInsertBean = (RecordInsert) context.getBean("recordInsert");
    RecordSelect recordSelectBean = (RecordSelect) context.getBean("recordSelect");
    RecordUpdate recordUpdateBean = (RecordUpdate) context.getBean("recordUpdate");

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = getAction(request);

        if (action.startsWith("/connect")) {
            String db_name = request.getParameter("db_name");
            String db_user = request.getParameter("db_user");
            String db_password = request.getParameter("db_password");
            clearAllDataBean.clear();

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
                    JDBCConnector.error = String.format("Can't get connection to database:\"%s\" user:\"%s\"", db_name, db_user);
                }
                if (SQLExc) {
                    request.setAttribute("error", JDBCConnector.error);
                    request.setAttribute("er_connect", JDBCConnector.er_connect);
                    request.getRequestDispatcher("connect.jsp").forward(request, response);
                    response.sendRedirect("connect.jsp");
                }
            } catch (Exception e) {
                request.setAttribute("message", e.getMessage());
                request.getRequestDispatcher("connect.jsp").forward(request, response);
            }
        }


        if (action.startsWith("/createuser")) {
            String db_name = request.getParameter("db_name");
            String db_user = request.getParameter("db_user");
            String db_password = request.getParameter("db_password");
            clearAllDataBean.clear();

            try {
                new JDBCConnector("postgres", "postgres", "1336");
                boolean SQLExc = false;
                try {
                    CreateUser.userCreate(db_user, db_password);
                    CreateDB.dbCreate(db_name, db_user);
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
                recordUpdateBean.updateRecordInTable(table_name, id, username, surname);
                request.setAttribute("doesNotExist", RecordUpdate.doesNotExist);
                request.setAttribute("error", RecordUpdate.error);
                request.getRequestDispatcher("updaterecord.jsp").forward(request, response);
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
                recordInsertBean.insertRecordInTable(table_name, username, surname);
                request.setAttribute("doesNotExist", RecordInsert.doesNotExist);
                request.setAttribute("error", RecordInsert.error);
                request.getRequestDispatcher("insertrecord.jsp").forward(request, response);
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
                recordDeleteBean.deleteRecordInTable(table_name, id);
                request.setAttribute("doesNotExist", RecordDelete.doesNotExist);
                request.setAttribute("error", RecordDelete.error);
                request.getRequestDispatcher("deleterecord.jsp").forward(request, response);
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
            clearAllDataBean.clear();
            try {
                recordSelectBean.selectRecordInTable(table_name, select);
                request.setAttribute("doesNotExist", RecordSelect.doesNotExist);
                request.setAttribute("error", RecordSelect.error);
                request.setAttribute("list", RecordSelect.list);
                request.getRequestDispatcher("selectrecord.jsp").forward(request, response);
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

        if (action.startsWith("/createTable")) {
            String create_table = request.getParameter("create_table");
            try {
                tableCreateBean.createTable(create_table);
                request.setAttribute("doesNotExist", TableCreate.doesNotExist);
                request.setAttribute("error", TableCreate.error);
                request.getRequestDispatcher("createtable.jsp").forward(request, response);
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
                tableDeleteBean.deleteTable(delete_table);
                request.setAttribute("doesNotExist", TableDelete.doesNotExist);
                request.setAttribute("error", TableDelete.error);
                request.getRequestDispatcher("deletetable.jsp").forward(request, response);
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
                tableSizeBean.getTableSize(name_table);
                request.setAttribute("doesNotExist", TableSize.doesNotExist);
                request.setAttribute("error", TableSize.error);
                request.setAttribute("size", TableSize.size);
                request.getRequestDispatcher("tablesize.jsp").forward(request, response);
                response.sendRedirect(response.encodeRedirectURL("tablesize.jsp"));
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        if (action.startsWith("/showtables")) {
            try {
                tableNamesBean.getAllTableNames();
                request.setAttribute("doesNotExist", TableNames.doesNotExist);
                request.setAttribute("error", TableNames.error);
                request.setAttribute("tableNames", TableNames.toString(TableNames.tables));
                request.getRequestDispatcher("showtables.jsp").forward(request, response);
                response.sendRedirect(response.encodeRedirectURL("showtables.jsp"));
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}