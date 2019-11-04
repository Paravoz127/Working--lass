package kpfu.itis.group11_801.kilin.workingClass.database;

import java.util.*;

public class Message implements Comparable<Message> {
    private int id;
    private User sender;
    private String text;
    private List<Image> images;
    private DateTime dateTime;

    public Message(int id, User sender, String text, List<Image> images, DateTime dateTime) {
        this.id = id;
        this.sender = sender;
        this.text = text;
        this.images = images;
        this.dateTime = dateTime;
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

    public DateTime getDateTime() {
        return dateTime;
    }

    @Override
    public int compareTo(Message o) {
        return getDateTime().getSeconds() - o.getDateTime().getSeconds();
    }
}
