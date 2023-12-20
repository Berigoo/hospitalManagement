package AClass;

import PageClass.DokterEdit;
import PageClass.DokterJanjiTemu;
import PageClass.DokterMenu;

import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Dokter extends Base{
    public enum EJanjiTemuStatus{
        PENDING,
        APPROVED,
        REJECTED;
    }
    private Base parent;
    private DokterMenu dokterMenu;
    private DokterEdit dokterEdit;
    private DokterJanjiTemu dokterJanjiTemu;

    public Dokter(){
        dokterMenu = new DokterMenu();
        dokterEdit = new DokterEdit();
        dokterJanjiTemu = new DokterJanjiTemu();

        //main menu
        dokterMenu.getDataDiri().addActionListener(e->{
            populateData();
            goToEdit();
        });
        dokterMenu.getJanjiTemu().addActionListener(e->{
            refreshTable();
            goToJanjiTemu();
        });
        dokterMenu.getLogout().addActionListener(e->{
            unviewAll();
            parent.goToLoginPage();
        });

        //Edit menu
        dokterEdit.getSave().addActionListener(e->{
            updateData(dokterEdit.getUsername().getText(), dokterEdit.getPasswd().getText(), dokterEdit.getTelp().getText());
        });
        dokterEdit.getBack().addActionListener(e->{
            goToMainMenu();
        });

        //janji temu menu
        dokterJanjiTemu.getReject().addActionListener(e->{
            int row = dokterJanjiTemu.getTable().getSelectedRow();
            String id = (String)dokterJanjiTemu.getTable().getValueAt(row, 0);
            updateJanjiTemu(id, EJanjiTemuStatus.REJECTED);
            refreshTable();
        });
        dokterJanjiTemu.getApprove().addActionListener(e->{
            int row = dokterJanjiTemu.getTable().getSelectedRow();
            String id = (String)dokterJanjiTemu.getTable().getValueAt(row, 0);
            updateJanjiTemu(id, EJanjiTemuStatus.APPROVED);
            refreshTable();
        });
        dokterJanjiTemu.getBack().addActionListener(e->{
            goToMainMenu();
        });
    }

    public void setParent(Base parent){
        if(parent != null){
            this.parent = parent;
        }
    }
    public void goToMainMenu(){
        unviewAll();
        dokterMenu.setVisible(true);
    }
    public void goToEdit(){
        unviewAll();
        dokterEdit.setVisible(true);
    }
    public void goToJanjiTemu(){
        unviewAll();
        dokterJanjiTemu.setVisible(true);
    }

    @Override
    public void unviewAll () {
        super.unviewAll();
        parent.unviewAll();
        dokterMenu.setVisible(false);
        dokterEdit.setVisible(false);
        dokterJanjiTemu.setVisible(false);
    }
    private void updateData(String username, String password, String telp){
        conn.execPreparedQuery("UPDATE dokter SET dokter_telp=? WHERE dokter_id=?", new String[]{telp, Integer.toString(id)});
        conn.execPreparedQuery("UPDATE credentials SET username=?, password=? WHERE credentials_id=?", new String[]{username, password, Integer.toString(id)});
    }
    public void populateData(){
        try{
            ResultSet res = conn.execQPreparedQuery("SELECT * FROM dokter INNER JOIN dokter_info ON dokter.info = dokter_info.dokter_info_id INNER JOIN credentials ON dokter.dokter_id = credentials.credentials_id WHERE dokter_id=?",
                    new String[]{Integer.toString(id)});
            if(res.next()){
                dokterEdit.getUsername().setText(res.getString("username"));
                dokterEdit.getPasswd().setText(res.getString("password"));
                dokterEdit.getNama().setText(res.getString("dokter_nama"));
                dokterEdit.getAlamat().setText(res.getString("dokter_alamat"));
                dokterEdit.getEmail().setText(res.getString("dokter_email"));
                dokterEdit.getTelp().setText(res.getString("dokter_telp"));
                dokterEdit.getDept().setText(res.getString("department"));
                dokterEdit.getSpecialist().setText(res.getString("specialist"));
            }
        }catch (SQLException e){
            System.out.println(e);
        }
    }
    public void refreshTable(){
        DefaultTableModel model = new DefaultTableModel();
        model.setDataVector(populateTable(), new String[]{"id", "nama pasien", "telp", "tanggal", "waktu", "deskripsi", "status"});
        dokterJanjiTemu.getTable().setModel(model);
    }
    public String[][] populateTable(){
        String[][] result = new String[][]{};
        try {
            List<List<String>> tmp = new ArrayList<>();
            ResultSet res = conn.execQPreparedQuery("SELECT * FROM janji_temu INNER JOIN pasien ON janji_temu.pasien_id = pasien.pasien_id WHERE janji_temu.dokter_id=?", new String[]{Integer.toString(id)});
            while (res.next()){
                List<String> tmp2 = new ArrayList<>();
                tmp2.add(res.getString("janji_temu_id"));
                tmp2.add(res.getString("pasien_nama"));
                tmp2.add(res.getString("pasien_telp"));
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
    public void updateJanjiTemu(String id, EJanjiTemuStatus status){
        conn.execPreparedQuery("UPDATE janji_temu SET status=? WHERE janji_temu_id=?", new String[]{status.name(), id});
    }
}
