package kpfu.itis.group11_801.kilin.workingClass.database;

import java.util.List;

public class MessageToCompany extends Message {
    private Company receiver;
    public MessageToCompany(int id, User sender, String text, List<Image> images, Company receiver, DateTime dateTime) {
        super(id, sender, text, images, dateTime);
        this.receiver = receiver;
    }

    public Company getReceiver() {
        return receiver;
    }
}