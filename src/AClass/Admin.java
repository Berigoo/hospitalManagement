package AClass;

import PageClass.AdminMenu;

public class Admin extends Base{
    protected AdminMenu mainMenu;
    public Admin(){
        super();
        mainMenu = new AdminMenu();

    }

    public void goToMainMenu(){
        unviewAll();
        mainMenu.setVisible(true);
    }

    @Override
    public void unviewAll () {
        super.unviewAll();
        mainMenu.setVisible(false);
    }
}
