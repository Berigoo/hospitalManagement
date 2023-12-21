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
    private AButton register;

    public BaseLoginPage (){
        main = new APanel(new BorderLayout());
        APanel box = new APanel();
        box.setLayout(new BoxLayout(box, BoxLayout.Y_AXIS));

        title = new ALabel("Login Page", SwingConstants.CENTER);
        title.setFont(title.getFont().deriveFont(24.0f));
        tUsername = new ALabel("Username: ", SwingConstants.LEFT);
        tPassword = new ALabel("Password: ", SwingConstants.LEFT);
        Mid = new APanel(new GridLayout(2, 1, 4, 4));
        APanel midTop = new APanel();
        midTop.setLayout(new BoxLayout(midTop, BoxLayout.X_AXIS));
        APanel midBot = new APanel();
        midBot.setLayout(new BoxLayout(midBot, BoxLayout.X_AXIS));
        username = new ATextField();
        password = new APasswordField();
        submit = new AButton("Login");
        register = new AButton("Register");

        Mid.add(tUsername);
        Mid.add(username);
        Mid.add(tPassword);
        Mid.add(password);

        midTop.add(Box.createHorizontalGlue());
        APanel ins = new APanel(new FlowLayout(FlowLayout.CENTER));
        ins.add(tUsername);
        ins.add(username);
        midTop.add(ins);
        ins.setPreferredSize(new Dimension(400, 65));
        ins.setMaximumSize(new Dimension(500, 65));
        midTop.add(Box.createHorizontalGlue());
        midBot.add(Box.createHorizontalGlue());
        APanel ins2 = new APanel(new FlowLayout(FlowLayout.CENTER));
        ins2.add(tPassword);
        ins2.add(password);
        midBot.add(ins2);
        ins2.setPreferredSize(new Dimension(400, 65));
        ins2.setMaximumSize(new Dimension(500, 65));
        midBot.add(Box.createHorizontalGlue());

        box.add(Box.createVerticalGlue());
        box.add(midTop);
        box.add(new Box.Filler(new Dimension(0, 5), new Dimension(0, 15), new Dimension(0, 55)));
        box.add(midBot);
        box.add(Box.createVerticalGlue());

        APanel midWrap = new APanel();
        midWrap.add(Mid);

        main.add(title, BorderLayout.NORTH);
        main.add(box, BorderLayout.CENTER);

        APanel bot = new APanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        bot.add(register);
        bot.add(submit);
        main.add(bot, BorderLayout.SOUTH);

        add(main);
        pack();
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

    public AButton getRegister () {
        return register;
    }
}
