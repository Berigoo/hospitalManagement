package PageClass;

import misc.*;

import javax.swing.*;
import java.awt.*;

public class BaseLoginPage extends JFrame {
    private APanel main;
    private ALabel title;
    private ALabel tUsername;
    private ALabel tPassword;
    private APanel Mid;
    private ATextField username;
    private APasswordField password;
    private AButton submit;

    public BaseLoginPage (){
        main = new APanel(new GridBagLayout());
        title = new ALabel("Login Page", SwingConstants.CENTER);
        tUsername = new ALabel("Username: ", SwingConstants.LEFT);
        tPassword = new ALabel("Password: ", SwingConstants.LEFT);
        Mid = new APanel(new GridLayout(2, 1, 4, 4));
        username = new ATextField();
        password = new APasswordField();
        submit = new AButton("Login");

        Mid.add(tUsername);
        Mid.add(username);
        Mid.add(tPassword);
        Mid.add(password);

        title.setOpaque(true);
        title.setBackground(new Color(255, 0, 0));

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 0;
        c.weighty = .8;
        c.weightx = 1;
        c.gridwidth = 2;
        c.insets = new Insets(10, 5, 10 , 5);
        main.add(title, c);

        c.gridwidth = 1;
        c.weighty = 1;
        c.weightx = .1;
        c.gridy = 1;
        main.add(tUsername, c);
        c.gridx = 1;
        c.weightx = 1;
        main.add(username, c);

        c.gridy = 2;
        c.gridx = 0;
        c.weightx = .1;
        main.add(tPassword, c);
        c.gridx = 1;
        c.weightx = 1;
        main.add(password, c);

        c.gridy = 3;
        c.gridx = 0;
        c.weighty = .2;
        c.weightx = .1;
        c.gridwidth = 2;
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