import AClass.DBConn;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Individual implements Individu {
    private String username;
    private String pass;
    public String name = "Guest";
    DBConn conn;

    public Individual(){
        conn = new DBConn();
    }
    @Override
    public void setCredentials (String username, String pass){
        if(!username.isEmpty() && !pass.isEmpty()){
            this.username = username;
            this.pass = pass;
        }
    }

    @Override
    public int verifikasi (){
        try {
            ResultSet res = conn.execQPreparedQuery("SELECT credentials_id AS count FROM credentials WHERE username=? && password=?",
                    new String[]{username, pass});
            while(res.next()) {
                return res.getInt("id");
            }
        }catch (SQLException e){
            System.out.println(e);
        }
        return 0;
    }

    public int createChild(int id){
        ResultSet res;
        try {
            res = conn.execQPreparedQuery("SELECT * FROM pasien WHERE pasien_id=?", new String[]{Integer.toString(id)});
            if (res.next()){
                return 1;
            }
            res = conn.execQPreparedQuery("SELECT * FROM dokter WHERE dokter_id=?", new String[]{Integer.toString(id)});
            if (res.next()){
                return 2;
            }
            res = conn.execQPreparedQuery("SELECT * FROM admins WHERE admin_id=?", new String[]{Integer.toString(id)});
            if (res.next()){
                return 3;
            }
        }catch (SQLException e){
            System.out.println(e);
        }
        return -1;
    }
}
