package kpfu.itis.group11_801.kilin.workingClass.database;

import java.util.List;

public class MessageToCompany extends Message {
    private Company receiver;
    public MessageToCompany(int id, User sender, String text, List<Image> images, Company receiver) {
        super(id, sender, text, images);
        this.receiver = receiver;
    }

    public Company getReceiver() {
        return receiver;
    }
}