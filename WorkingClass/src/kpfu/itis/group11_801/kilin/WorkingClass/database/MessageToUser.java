package kpfu.itis.group11_801.kilin.workingClass.database;

import java.util.List;

public class MessageToUser extends Message {
    private User receiver;
    public MessageToUser(int id, User sender, String text, List<Image> images, User receiver) {
        super(id, sender, text, images);
        this.receiver = receiver;
    }

    public User getReceiver() {
        return receiver;
    }
}