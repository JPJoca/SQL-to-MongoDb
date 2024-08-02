package main.bazePodataka.view.swingGui;

import main.bazePodataka.AppCore;
import main.bazePodataka.view.swingGui.controler.Akcija;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;

@Getter
@Setter
public class MainFrame extends JFrame {

    private static MainFrame instance;
    private JPanel desktop;
    private JTextField tekst;
    private JButton dugme;
    private JTable tabela;
    //  private JPanel tabela;

    private Akcija akcija;

    private MainFrame(){


    }

    private void initialise(){

        initialiseGui();
    }

    private void initialiseGui() {

        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        setSize(screenWidth / 2  , screenHeight/ 2  );
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Baze Podataka");
        akcija = new Akcija();
        tekst = new JTextField();
        dugme = new JButton("Run");
      //  dugme.addActionListener(e -> akcija.actionPerformed(e));
        dugme.addActionListener(akcija);

        tabela = new JTable();
        tabela.setPreferredScrollableViewportSize(new Dimension(500, 400));
        tabela.setFillsViewportHeight(true);
        tabela.setModel(AppCore.getInstance().getTableModel());
//        JScrollPane scrollPane = new JScrollPane();
//
//        scrollPane.add(tabela);
        desktop = new JPanel(new BorderLayout());
        desktop.add(tekst,BorderLayout.CENTER);
        desktop.add(dugme, BorderLayout.EAST);

        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, desktop, tabela);
        getContentPane().add(splitPane, BorderLayout.CENTER);
        splitPane.setMinimumSize(new Dimension(150, 250));
        splitPane.setDividerLocation(100);
        splitPane.setOneTouchExpandable(false);


    }


    public static MainFrame getInstance(){
        if(instance == null){
            instance = new MainFrame();
            instance.initialise();
        }
        return instance;
    }

}
