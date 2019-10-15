package kpfu.itis.group11_801.kilin.WorkingClass.database;

import java.util.*;

public class Message {
    private int id;
    private User sender;
    private User receiver;
    private String text;
    private List<Image> images;

    public Message(int id, User sender, User receiver, String text, List<Image> images) {
        this.id = id;
        this.sender = sender;
        this.receiver = receiver;
        this.text = text;
        this.images = images;
    }

    public int getId() {
        return id;
    }

    public User getReceiver() {
        return receiver;
    }

    public User getSender() {
        return sender;
    }

    public List<Image> getImages() {
        return images;
    }

    public String getText() {
        return text;
    }
}
