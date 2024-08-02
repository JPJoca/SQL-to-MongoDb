package main.bazePodataka.messageGenerator;

import main.bazePodataka.view.swingGui.GuiMessage;

import java.sql.Timestamp;

public class Message {

    private String content;
    private Timestamp timestamp = new Timestamp(System.currentTimeMillis());

    public Message(String content){
        this.content = content;
        GuiMessage gm = new GuiMessage();
        gm.generate(content,this.timestamp);
        gm.pokazi();
    }
}
