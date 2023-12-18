package AClass;

import PageClass.AdminMenu;
import PageClass.DokterAdd;

import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Admin extends Base{
    protected AdminMenu mainMenu;
    protected DokterAdd dokterAdd;
    public Admin(){
        super();
        mainMenu = new AdminMenu();
        dokterAdd = new DokterAdd();
        dumpDokterInfo();
        refreshTable();



        mainMenu.getDoctorAdd().addActionListener(e->{
            goToDokterAdd();
        });
        dokterAdd.getSimpan().addActionListener(e->{
            System.out.println(dokterAdd.getSpecialist().getSelectedItem());
            if(inserDokterData(dokterAdd.getUsername().getText(), dokterAdd.getPasswd().getText(), dokterAdd.getNama().getText(), dokterAdd.getAlamat().getText(),
                    dokterAdd.getEmail().getText(), dokterAdd.getTelp().getText(), (String)dokterAdd.getDept().getSelectedItem(), (String)dokterAdd.getSpecialist().getSelectedItem())) {
                refreshTable();
            }
        });
    }

    public void goToMainMenu(){
        unviewAll();
        mainMenu.setVisible(true);
    }
    public void goToDokterAdd(){
        unviewAll();
        dokterAdd.setVisible(true);
    }

    @Override
    public void unviewAll () {
        super.unviewAll();
        mainMenu.setVisible(false);
        dokterAdd.setVisible(false);
    }

    protected String[][] dumpDokterData(){
        String[][] result;
        try {
            List<List<String>> tmp = new ArrayList<>();
            ResultSet res = conn.execQPreparedQuery("SELECT * FROM dokter INNER JOIN dokter_info ON dokter.info = dokter_info.dokter_info_id INNER JOIN credentials ON dokter.dokter_id = credentials.credentials_id", new String[]{});
            while (res.next()){
                System.out.println(res.getString("specialist"));
                List<String> tmp2 = new ArrayList<>();
                tmp2.add(res.getString("username"));
                tmp2.add(res.getString("password"));
                tmp2.add(res.getString("dokter_nama"));
                tmp2.add(res.getString("dokter_alamat"));
                tmp2.add(res.getString("dokter_email"));
                tmp2.add(res.getString("dokter_telp"));
                tmp2.add(res.getString("department"));
                tmp2.add(res.getString("specialist"));
                tmp.add(tmp2);
                dokterAdd.getDeptModel().addElement(res.getString("department"));
                dokterAdd.getSpecialistModel().addElement(res.getString("specialist"));
            }
            result = new String[tmp.size()][8];
            for (int i=0; i<tmp.size(); i++){
                for (int j=0; j<8; j++){
                    result[i][j] = tmp.get(i).get(j);
                }
            }
            return result;
        }catch (SQLException e){
            System.out.println(e);
        }
        return null;
    }
    public boolean inserDokterData(String username, String passwd, String nama, String alamat, String email, String telp, String dept, String specialist){
        int infoId = -1;
        int credenId = -1;
        try{
            ResultSet res = conn.execQPreparedQuery("SELECT dokter_info_id FROM dokter_info WHERE department=? && specialist=?", new String[]{dept, specialist});
            if(res.next()) {
                conn.execPreparedQuery("INSERT INTO credentials VALUES(NULL, ?, ?)", new String[]{username, passwd});
                ResultSet res2 = conn.execQPreparedQuery("SELECT credentials_id FROM credentials WHERE username=? && password=?", new String[]{username, passwd});
                infoId = res.getInt("dokter_info_id");
                if(res2.next()) {
                    credenId = res2.getInt("credentials_id");
                    conn.execPreparedQuery("INSERT INTO dokter VALUES(?, ?, ?, ?, ?, ?)", new String[]{Integer.toString(credenId), nama, alamat, email, telp, Integer.toString(infoId)});
                    return true;
                }
            }
            return false;
        }catch (SQLException e){
            return false;
        }
    }
    private void refreshTable(){
        DefaultTableModel model = new DefaultTableModel();
        model.setDataVector(dumpDokterData(), new String[]{"username", "Password", "nama", "alamat", "e-mail", "telp", "department", "specialist"});
        dokterAdd.getDataTable().setModel(model);
    }
    private void dumpDokterInfo(){
        try{
            ResultSet res = conn.execQPreparedQuery("SELECT * FROM dokter_info", new String[]{});
            while (res.next()){
                dokterAdd.getDeptModel().addElement(res.getString("department"));
                dokterAdd.getSpecialistModel().addElement(res.getString("specialist"));
            }
        }catch (SQLException e){
            System.out.println(e);
        }
    }
}
