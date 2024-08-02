package main.bazePodataka.messageGenerator;

import main.bazePodataka.core.MessageGenerator;

public class MessageGeneratorImpl implements MessageGenerator {
    @Override
    public Message generate(String generate) {
        if(!generate.isEmpty()) {
            return new Message(generate);
        }

        return null;
    }
}
