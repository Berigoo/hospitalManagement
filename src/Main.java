import AClass.Admin;
import AClass.Base;

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
                Base individual = new Base();
                individual.getLoginPage().getSubmit().addActionListener(e -> {
                    String username = individual.getLoginPage().getUsername().getText();
                    String password = new String(individual.getLoginPage().getPassword().getPassword());
                    System.out.println(username + " " + password);
                    individual.setCreden(username, password);
                    System.out.println(individual.getCredenType());
                    switch(individual.getCredenType()){
                        case PASIEN:
                            break;
                        case DOKTER:
                            break;
                        case ADMIN:
                            System.out.println("Admin In");
                            admin.setParent(individual);
                            admin.setCreden(username, password);
                            admin.goToMainMenu();
                            individual.unviewAll();
                            break;
                    }
                });
                individual.goToLoginPage();
            }
        });
    }
}