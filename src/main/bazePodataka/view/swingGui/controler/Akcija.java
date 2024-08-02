package main.bazePodataka.view.swingGui.controler;

import main.bazePodataka.AppCore;
import main.bazePodataka.view.swingGui.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class Akcija extends AbstractAction {
    @Override
    public void actionPerformed(ActionEvent e) {
        AppCore.getInstance().getSqlParser().parseQuery(MainFrame.getInstance().getTekst().getText());
    }
}
