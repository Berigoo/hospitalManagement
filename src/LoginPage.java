import misc.*;

import javax.swing.*;
import java.awt.*;

public class LoginPage extends JFrame {
    private APanel main;
    private ALabel title;
    private ATextField username;
    private APasswordField password;
    private AButton submit;

    public LoginPage(){
        main = new APanel(new GridBagLayout());
        title = new ALabel("Login Page", SwingConstants.CENTER);
        username = new ATextField();
        password = new APasswordField();
        submit = new AButton("Login");


        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 0;
        c.weighty = 1;
        c.weightx = 1;
        c.insets = new Insets(10, 10, 10 , 10);
        main.add(title, c);

        c.gridy = 1;
        main.add(username, c);

        c.gridy = 2;
        main.add(password, c);

        c.gridy = 3;
        main.add(submit, c);

        add(main);
    }

    public APanel getMain () {
        return main;
    }

    public ATextField getUsername () {
        return username;
    }

    public APasswordField getPassword () {
        return password;
    }

    public AButton getSubmit () {
        return submit;
    }
}
