package AClass;

import PageClass.LoginPage;

import java.sql.ResultSet;
import java.sql.SQLException;


public class Base {
    public enum EPrivilege {
        NONE,
        ADMIN,
        DOKTER,
        PASIEN;
    }
    protected int id = -1;
    protected String username;
    protected String password;
    protected LoginPage loginPage;
    protected DBConn conn;
    public Base(){
        loginPage = new LoginPage();
        conn = new DBConn();

//        loginPage.getSubmit().addActionListener(e->{
//            setCreden(loginPage.getUsername().getText(), loginPage.getPassword().getText());
//        });
    };
    public void setCreden(String username, String password){
        this.username = username;
        this.password = password;

        verifikasi();
    }
    public void goToLoginPage (){
        loginPage.setVisible(true);
    }
    public void verifikasi(){
        try {
            ResultSet res = conn.execPreparedQuery("SELECT credentials_id AS id FROM credentials WHERE username=? && password=?",
                    new String[]{username, password});
            while(res.next()) {
                id = res.getInt("id");
                return;
            }
        }catch (SQLException e){
            System.out.println(e);
        }
        id = -1;
    }
    public EPrivilege getCredenType(){
        ResultSet res;
        if(id != -1){
            try {
                res = conn.execPreparedQuery("SELECT * FROM pasien WHERE pasien_id=?", new String[]{Integer.toString(id)});
                if(res.next()){
                    return EPrivilege.PASIEN;
                }
                res = conn.execPreparedQuery("SELECT * FROM dokter WHERE dokter_id=?", new String[]{Integer.toString(id)});
                if(res.next()){
                    return EPrivilege.DOKTER;
                }
                res = conn.execPreparedQuery("SELECT * FROM admins WHERE admin_id=?", new String[]{Integer.toString(id)});
                if(res.next()){
                    return EPrivilege.ADMIN;
                }
            }catch (SQLException e){
                System.out.println(e);
            }
        }
        return EPrivilege.NONE;
    }
    public void unviewAll(){
        loginPage.setVisible(false);
    }

    public LoginPage getLoginPage () {
        return loginPage;
    }
}
