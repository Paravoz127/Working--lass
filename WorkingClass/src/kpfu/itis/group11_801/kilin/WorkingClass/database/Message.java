package kpfu.itis.group11_801.kilin.WorkingClass.database;

import java.util.*;

public class Message {
    private int id;
    private User sender;
    private String text;
    private List<Image> images;

    public Message(int id, User sender, String text, List<Image> images) {
        this.id = id;
        this.sender = sender;
        this.text = text;
        this.images = images;
    }

    public int getId() {
        return id;
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
