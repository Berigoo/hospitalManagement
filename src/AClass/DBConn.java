package AClass;

import java.sql.*;

public class DBConn {
    private String url= "jdbc:mysql://localhost:3306/PBO";
    private String username = "root";
    private String pass = "";
    private Connection conn;
    public DBConn(){
        try {
            conn = DriverManager.getConnection(url, username, pass);
        }catch (SQLException e){
            System.out.println(e);
        }
    }

    public Connection getConn () {
        return conn;
    }
    public ResultSet execQPreparedQuery (String query, String[]fillData){
        try {
            PreparedStatement statement = conn.prepareStatement(query);
            for(int i=1; i<=fillData.length; i++){
                statement.setString(i, fillData[i-1]);
            }
            return statement.executeQuery();
        }catch (SQLException e){
            System.out.println(e);
        }
        return null;
    }

    public boolean execPreparedQuery(String query, String[]fillData){
        try {
            PreparedStatement statement = conn.prepareStatement(query);
            for(int i=1; i<=fillData.length; i++){
                statement.setString(i, fillData[i-1]);
            }
            statement.execute();
        }catch (SQLException e){
            System.out.println(e);
            return false;
        }
        return true;
    }
}
