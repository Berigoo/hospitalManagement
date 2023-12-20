package AClass;

import PageClass.PasienEdit;
import PageClass.PasienJanjiTemu;
import PageClass.PasienMenu;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Pasien extends Base{
    private Base parent;
    private PasienMenu pasienMenu;
    private PasienEdit pasienEdit;
    private PasienJanjiTemu pasienJanjiTemu;

    public Pasien(){
        pasienMenu = new PasienMenu();
        pasienEdit = new PasienEdit();
        pasienJanjiTemu = new PasienJanjiTemu();

        //main menu
        pasienMenu.getDataDiri().addActionListener(e->{
            dumpMyOwnData();
            goToPasienEdit();
        });
        pasienMenu.getJanjiTemu().addActionListener(e->{
            populateDokterComboBox();
            refreshJanjiTemuTable();
            goToJanjiTemu();
        });
        pasienMenu.getLogout().addActionListener(e->{
            unviewAll();
            parent.goToLoginPage();
        });

        //data diri
        pasienEdit.getSave().addActionListener(e->{
            updateMyOwnData(pasienEdit.getUsername().getText(), pasienEdit.getPasswd().getText(), pasienEdit.getNama().getText(),
                    pasienEdit.getAlamat().getText(), pasienEdit.getEmail().getText(), pasienEdit.getTelp().getText());
        });
        pasienEdit.getBack().addActionListener(e->{
            goToMainMenu();
        });

        //janji temu
        pasienJanjiTemu.getSubmit().addActionListener(e->{
            insertJanjiTemu(pasienJanjiTemu.getTanggal().getText(), pasienJanjiTemu.getWaktu().getText(), pasienJanjiTemu.getDesc().getText(),
                    Character.toString(((String) pasienJanjiTemu.getDokter().getSelectedItem()).charAt(0)));
            refreshJanjiTemuTable();
        });
        pasienJanjiTemu.getBack().addActionListener(e->{
            goToMainMenu();
        });
    }

    public void updateMyOwnData(String username, String passwd, String nama, String alamat, String email, String telp){
        if(id != -1) {
            if(conn.execPreparedQuery("UPDATE pasien SET pasien_nama=?, pasien_alamat=?, pasien_email=?, pasien_telp=? WHERE pasien_id=?",
                    new String[]{nama, alamat, email, telp, Integer.toString(id)})){
                conn.execPreparedQuery("UPDATE credentials SET username=?, password=? WHERE credentials_id=?",
                        new String[]{username, passwd, Integer.toString(id)});
                }
        }
    }
    public void dumpMyOwnData(){
        if(id != -1){
            try{
                ResultSet res = conn.execQPreparedQuery("SELECT * FROM pasien INNER JOIN credentials ON pasien.pasien_id = credentials.credentials_id",
                        new String[]{});
                if(res.next()){
                    pasienEdit.getUsername().setText(res.getString("username"));
                    pasienEdit.getPasswd().setText(res.getString("password"));
                    pasienEdit.getNama().setText(res.getString("pasien_nama"));
                    pasienEdit.getAlamat().setText(res.getString("pasien_alamat"));
                    pasienEdit.getEmail().setText(res.getString("pasien_email"));
                    pasienEdit.getTelp().setText(res.getString("pasien_telp"));
                }
            }catch (SQLException e){
                System.out.println(e);
            }
        }
    }
    public void goToPasienEdit(){
        unviewAll();
        pasienEdit.setVisible(true);
    }
    public void goToMainMenu(){
        unviewAll();
        pasienMenu.setVisible(true);
    }
    public void goToJanjiTemu(){
        unviewAll();
        pasienJanjiTemu.setVisible(true);
    }
    @Override
    public void unviewAll () {
        super.unviewAll();
        parent.unviewAll();
        pasienMenu.setVisible(false);
        pasienEdit.setVisible(false);
        pasienJanjiTemu.setVisible(false);
    }
    public void setParent(Base parent){
        if(parent != null){
            this.parent = parent;
        }
    }

    private void populateDokterComboBox(){
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        try{
            ResultSet res = conn.execQPreparedQuery("SELECT * FROM dokter", new String[]{});
            while (res.next()){
                model.addElement(res.getString("dokter_id") + " " + res.getString("dokter_nama"));
            }
            pasienJanjiTemu.getDokter().setModel(model);
        }catch (SQLException e){
            System.out.println(e);
        }
    }
    public void refreshJanjiTemuTable(){
        DefaultTableModel model = new DefaultTableModel();
        model.setDataVector(populateJanjiTemuTable(), new String[]{"id", "nama dokter", "tanggal", "waktu", "deskripsi", "status"});
        pasienJanjiTemu.getTable().setModel(model);
    }
    private String[][] populateJanjiTemuTable(){
        String[][] result = new String[][]{};
        try {
            List<List<String>> tmp = new ArrayList<>();
            ResultSet res = conn.execQPreparedQuery("SELECT * FROM janji_temu INNER JOIN dokter ON janji_temu.dokter_id = dokter.dokter_id WHERE janji_temu.pasien_id=?", new String[]{Integer.toString(id)});
            while (res.next()){
                List<String> tmp2 = new ArrayList<>();
                tmp2.add(res.getString("janji_temu_id"));
                tmp2.add(res.getString("dokter_nama"));
                tmp2.add(res.getString("tanggal"));
                tmp2.add(res.getString("waktu"));
                tmp2.add(res.getString("desc"));
                tmp2.add(res.getString("status"));
                tmp.add(tmp2);
            }
            if(tmp.size() != 0) {
                result = new String[tmp.size()][tmp.get(0).size()];
                for (int i = 0; i < tmp.size(); i++) {
                    for (int j = 0; j < tmp.get(0).size(); j++) {
                        result[i][j] = tmp.get(i).get(j);
                    }
                }
            }
            return result;
        }catch (SQLException e){
            System.out.println(e);
        }
        return null;
    }
    private boolean insertJanjiTemu(String tanggal, String waktu, String desc, String dokter_id){
        return conn.execPreparedQuery("INSERT INTO janji_temu VALUES(NULL, ?, ?, ?, ?, ?, DEFAULT)", new String[]{tanggal, waktu, desc, dokter_id, Integer.toString(id)});
    }
}
