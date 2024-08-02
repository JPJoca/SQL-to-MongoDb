package main.bazePodataka.view;

import main.bazePodataka.core.Gui;
import main.bazePodataka.view.swingGui.MainFrame;

public class SwingGui implements Gui {


    public SwingGui(){

    }
    @Override
    public void start() {
        MainFrame.getInstance().setVisible(true);
        MainFrame.getInstance().setLocationRelativeTo(null);

    }



}