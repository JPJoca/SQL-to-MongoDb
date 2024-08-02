package main.bazePodataka.view.swingGui;

import javax.swing.*;
import java.awt.*;
import java.sql.Timestamp;

public class GuiMessage extends JDialog {
    private JLabel jLabel;
    private String content;
    private Timestamp ts;
    private JButton b;
    public GuiMessage() {

    }
    public void generate(String content, Timestamp ts){
        this.content = content;
        this.ts = ts;
        initialise();
        System.out.println(ts);
    }

    private void initialise() {
        jLabel = new JLabel(content);

        b = new JButton("Ok");
        b.addActionListener(e -> {
            this.dispose();
        });
        JPanel p = new JPanel(new BorderLayout());
        this.add(p);
        p.add(jLabel, BorderLayout.CENTER);
        p.add(b , BorderLayout.SOUTH);
    }

    public void pokazi(){
        this.setTitle("Validator Error");
        this.setSize(300,200);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
