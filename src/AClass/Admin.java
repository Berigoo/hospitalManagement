package AClass;

import PageClass.AdminAboutPage;
import PageClass.AdminMenu;
import PageClass.AdminDokterAdd;
import PageClass.AdminDumpPage;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Admin extends Base{
    private Base parent;
    protected AdminMenu mainMenu;
    protected AdminDokterAdd dokterAdd;
    protected AdminDumpPage listPasien;
    protected AdminAboutPage aboutPage;
    private int currDataId = -1;
    public Admin(){
        super();
        mainMenu = new AdminMenu();
        dokterAdd = new AdminDokterAdd();
        listPasien = new AdminDumpPage();
        aboutPage = new AdminAboutPage();
        dumpDokterInfo();
        refreshDokterTable();
        refreshListPasienTable();


        //main menu
        mainMenu.getDoctorAdd().addActionListener(e->{
            goToDokterAdd();
        });
        mainMenu.getListPatients().addActionListener(e->{
            goToListPatient();
        });
        mainMenu.getAbout().addActionListener(e->{
            goToAboutPage();
        });
        mainMenu.getLogout().addActionListener(e->{
            unviewAll();
            parent.goToLoginPage();
        });

        //CRUD dokter
        dokterAdd.getTambah().addActionListener(e->{
            if(inserDokterData(dokterAdd.getUsername().getText(), dokterAdd.getPasswd().getText(), dokterAdd.getNama().getText(), dokterAdd.getAlamat().getText(),
                    dokterAdd.getEmail().getText(), dokterAdd.getTelp().getText(), (String)dokterAdd.getDept().getSelectedItem(), (String)dokterAdd.getSpecialist().getSelectedItem())) {
                refreshDokterTable();
            }
        });
        dokterAdd.getHapus().addActionListener(e->{
            int row = dokterAdd.getDataTable().getSelectedRow();
            String id = (String)dokterAdd.getDataTable().getValueAt(row, 0);
            if (conn.execPreparedQuery("DELETE FROM dokter WHERE dokter_id=?", new String[]{id})){
                conn.execPreparedQuery("DELETE FROM credentials WHERE credentials_id=?", new String[]{id});
                refreshDokterTable();
            }
        });
        dokterAdd.getEdit().addActionListener(e->{
            int row = dokterAdd.getDataTable().getSelectedRow();
            String username = (String)dokterAdd.getDataTable().getValueAt(row, 1);
            String passwd = (String)dokterAdd.getDataTable().getValueAt(row, 2);
            String name = (String)dokterAdd.getDataTable().getValueAt(row, 3);
            String alamat = (String)dokterAdd.getDataTable().getValueAt(row, 4);
            String email = (String)dokterAdd.getDataTable().getValueAt(row, 5);
            String telp = (String)dokterAdd.getDataTable().getValueAt(row, 6);
            String dept = (String)dokterAdd.getDataTable().getValueAt(row, 7);
            String specialist = (String)dokterAdd.getDataTable().getValueAt(row, 8);

            dokterAdd.getUsername().setText(username);
            dokterAdd.getPasswd().setText(passwd);
            dokterAdd.getNama().setText(name);
            dokterAdd.getAlamat().setText(alamat);
            dokterAdd.getEmail().setText(email);
            dokterAdd.getTelp().setText(telp);
            dokterAdd.getDept().setSelectedItem(dept);
            dokterAdd.getSpecialist().setSelectedItem(specialist);

            currDataId = Integer.parseInt((String)dokterAdd.getDataTable().getValueAt(row, 0));
        });
        dokterAdd.getSimpan().addActionListener(e->{
            if(updateDokterData(currDataId, dokterAdd.getUsername().getText(), dokterAdd.getPasswd().getText(), dokterAdd.getNama().getText(), dokterAdd.getAlamat().getText(),
                    dokterAdd.getEmail().getText(), dokterAdd.getTelp().getText(), (String)dokterAdd.getDept().getSelectedItem(), (String)dokterAdd.getSpecialist().getSelectedItem())) {
                currDataId = -1;
            }
            refreshDokterTable();
        });
        dokterAdd.getBack().addActionListener(e->{
            goToMainMenu();
        });

        //list pasien
        listPasien.getBack().addActionListener(e->{
            goToMainMenu();
        });
        listPasien.getDelete().addActionListener(e->{
            int row = listPasien.getTable().getSelectedRow();
            int id = (int)listPasien.getTable().getValueAt(row, 0);
            if(conn.execPreparedQuery("DELETE FROM pasien WHERE pasien_id=?", new String[]{Integer.toString(id)})) {
                conn.execPreparedQuery("DELETE FROM credentials WHERE credentials_id=?", new String[]{Integer.toString(id)});
                refreshListPasienTable();
            }
        });

        //about page
        aboutPage.getBack().addActionListener(e->{
            unviewAll();
            goToMainMenu();
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
    public void goToListPatient(){
        unviewAll();
        listPasien.setVisible(true);
    }
    public void goToAboutPage(){
        unviewAll();
        aboutPage.setVisible(true);
    }

    @Override
    public void unviewAll () {
        super.unviewAll();
        parent.unviewAll();
        mainMenu.setVisible(false);
        dokterAdd.setVisible(false);
        listPasien.setVisible(false);
        aboutPage.setVisible(false);
    }

    protected String[][] dumpDokterData(){
        String[][] result;
        try {
            List<List<String>> tmp = new ArrayList<>();
            ResultSet res = conn.execQPreparedQuery("SELECT * FROM dokter INNER JOIN dokter_info ON dokter.info = dokter_info.dokter_info_id INNER JOIN credentials ON dokter.dokter_id = credentials.credentials_id", new String[]{});
            while (res.next()){
                List<String> tmp2 = new ArrayList<>();
                tmp2.add(res.getString("dokter_id"));
                tmp2.add(res.getString("username"));
                tmp2.add(res.getString("password"));
                tmp2.add(res.getString("dokter_nama"));
                tmp2.add(res.getString("dokter_alamat"));
                tmp2.add(res.getString("dokter_email"));
                tmp2.add(res.getString("dokter_telp"));
                tmp2.add(res.getString("department"));
                tmp2.add(res.getString("specialist"));
                tmp.add(tmp2);
            }
            result = new String[tmp.size()][tmp.get(0).size()];
            for (int i=0; i<tmp.size(); i++){
                for (int j=0; j<tmp.get(0).size(); j++){
                    result[i][j] = tmp.get(i).get(j);
                }
            }
            return result;
        }catch (SQLException e){
            System.out.println(e);
        }
        return null;
    }
    protected String[][] dumpPasienData(){
        String[][] result;
        try {
            List<List<String>> tmp = new ArrayList<>();
            ResultSet res = conn.execQPreparedQuery("SELECT * FROM pasien INNER JOIN credentials ON pasien.pasien_id = credentials.credentials_id", new String[]{});
            while (res.next()){
                List<String> tmp2 = new ArrayList<>();
                tmp2.add(res.getString("pasien_id"));
                tmp2.add(res.getString("username"));
                tmp2.add(res.getString("password"));
                tmp2.add(res.getString("pasien_nama"));
                tmp2.add(res.getString("pasien_alamat"));
                tmp2.add(res.getString("pasien_email"));
                tmp2.add(res.getString("pasien_telp"));
                tmp.add(tmp2);
            }
            result = new String[tmp.size()][tmp.get(0).size()];
            for (int i=0; i<tmp.size(); i++){
                for (int j=0; j<tmp.get(0).size(); j++){
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
    private void refreshDokterTable (){
        DefaultTableModel model = new DefaultTableModel();
        model.setDataVector(dumpDokterData(), new String[]{"id", "username", "password", "nama", "alamat", "e-mail", "telp", "department", "specialist"});
        dokterAdd.getDataTable().setModel(model);
    }
    private void refreshListPasienTable(){
        DefaultTableModel model = new DefaultTableModel();
        model.setDataVector(dumpPasienData(), new String[]{"id", "username", "password", "nama", "alamat", "e-mail", "telp"});
        listPasien.getTable().setModel(model);
    }
    private void dumpDokterInfo(){              //need improvements
        DefaultComboBoxModel<String> dept = new DefaultComboBoxModel<>();
        DefaultComboBoxModel<String> specialist = new DefaultComboBoxModel<>();
        try{
            ResultSet res = conn.execQPreparedQuery("SELECT * FROM dokter_info", new String[]{});
            while (res.next()){
                dept.addElement(res.getString("department"));
                specialist.addElement(res.getString("specialist"));
            }
            dokterAdd.getDept().setModel(dept);
            dokterAdd.getSpecialist().setModel(specialist);
        }catch (SQLException e){
            System.out.println(e);
        }
    }
    private boolean updateDokterData(int id, String username, String password, String nama, String alamat, String email, String telp, String dept, String specialist){
        if(id != -1){
            try {
                ResultSet res = conn.execQPreparedQuery("SELECT dokter_info_id FROM dokter_info WHERE department=? && specialist=?", new String[]{dept, specialist});
                if(res.next()) {
                    String infoId = res.getString("dokter_info_id");
                    conn.execPreparedQuery("UPDATE dokter SET dokter_nama=?, dokter_alamat=?, dokter_email=?, dokter_telp=?, info=? WHERE dokter_id=?",
                            new String[]{nama, alamat, email, telp, infoId, Integer.toString(id)});
                    conn.execPreparedQuery("UPDATE credentials SET username=?, password=? WHERE credentials_id=?", new String[]{username, password, Integer.toString(id)});
                    return true;
                }
            }catch (SQLException e){
                System.out.println(e);
                return false;
            }
        }
        return false;
    }
    public void setParent(Base parent){
        if(parent != null){
            this.parent = parent;
        }
    }
}
