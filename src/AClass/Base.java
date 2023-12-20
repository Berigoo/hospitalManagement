package AClass;

import PageClass.BaseLoginPage;
import PageClass.BaseRegister;

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
    private final BaseLoginPage loginPage;
    private final BaseRegister registerPage;
    protected DBConn conn;
    public Base(){
        loginPage = new BaseLoginPage();
        registerPage = new BaseRegister();
        conn = new DBConn();

        //login page
        loginPage.getRegister().addActionListener(e->{
            goToRegisterPage();
        });

        //register page
        registerPage.getBack().addActionListener(e->{
            goToLoginPage();
        });
        registerPage.getRegister().addActionListener(e->{
            if(insertPasien(registerPage.getUsername().getText(), registerPage.getPasswd().getText(), registerPage.getNama().getText(),
                    registerPage.getAlamat().getText(), registerPage.getEmail().getText(), registerPage.getTelp().getText())){
                goToLoginPage();
            }
        });
    };
    public void setCreden(String username, String password){
        this.username = username;
        this.password = password;

        verifikasi();
    }
    public void goToLoginPage (){
        unviewAll();
        loginPage.setVisible(true);
    }
    public void goToRegisterPage(){
        unviewAll();
        registerPage.setVisible(true);
    }
    public void verifikasi(){
        try {
            ResultSet res = conn.execQPreparedQuery("SELECT credentials_id AS id FROM credentials WHERE username=? && password=?",
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
                res = conn.execQPreparedQuery("SELECT * FROM pasien WHERE pasien_id=?", new String[]{Integer.toString(id)});
                if(res.next()){
                    return EPrivilege.PASIEN;
                }
                res = conn.execQPreparedQuery("SELECT * FROM dokter WHERE dokter_id=?", new String[]{Integer.toString(id)});
                if(res.next()){
                    return EPrivilege.DOKTER;
                }
                res = conn.execQPreparedQuery("SELECT * FROM admins WHERE admin_id=?", new String[]{Integer.toString(id)});
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
        registerPage.setVisible(false);
    }

    public BaseLoginPage getLoginPage () {
        return loginPage;
    }
    private boolean insertPasien(String username, String password, String nama, String alamat, String email, String telp){
        try {
            if(conn.execPreparedQuery("INSERT INTO credentials VALUES(NULL, ?, ?)", new String[]{username, password})) {
                ResultSet res2 = conn.execQPreparedQuery("SELECT credentials_id FROM credentials WHERE username=? && password=?", new String[]{username, password});
                if (res2.next()) {
                    int credenId = res2.getInt("credentials_id");
                    return conn.execPreparedQuery("INSERT INTO pasien VALUES(?, ?, ?, ?, ?)", new String[]{Integer.toString(credenId), nama, alamat, email, telp});
                }
            }
        }catch (SQLException e){
            System.out.println(e);
        }
        return false;
    }
}
