package AClass;

import PageClass.DokterEdit;
import PageClass.DokterMenu;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Dokter extends Base{
    private Base parent;
    private DokterMenu dokterMenu;
    private DokterEdit dokterEdit;

    public Dokter(){
        dokterMenu = new DokterMenu();
        dokterEdit = new DokterEdit();

        //main menu
        dokterMenu.getDataDiri().addActionListener(e->{
            populateData();
            goToEdit();
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

    @Override
    public void unviewAll () {
        super.unviewAll();
        parent.unviewAll();
        dokterMenu.setVisible(false);
        dokterEdit.setVisible(false);
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
}
