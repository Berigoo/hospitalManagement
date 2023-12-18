import AClass.Admin;
import AClass.Base;
import AClass.Pasien;

import javax.swing.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main (String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run () {
                //Object
                Admin admin = new Admin();
                Pasien pasien = new Pasien();
                Base individual = new Base();
                individual.getLoginPage().getSubmit().addActionListener(e -> {
                    String username = individual.getLoginPage().getUsername().getText();
                    String password = new String(individual.getLoginPage().getPassword().getPassword());
                    System.out.println(username + " " + password);
                    individual.setCreden(username, password);
                    switch(individual.getCredenType()){
                        case PASIEN:
                            System.out.println("Pasien In");
                            pasien.setParent(individual);
                            pasien.setCreden(username, password);
                            pasien.goToMainMenu();
                            break;
                        case DOKTER:
                            break;
                        case ADMIN:
                            System.out.println("Admin In");
                            admin.setParent(individual);
                            admin.setCreden(username, password);
                            admin.goToMainMenu();
                            break;
                    }
                });
                individual.goToLoginPage();
            }
        });
    }
}