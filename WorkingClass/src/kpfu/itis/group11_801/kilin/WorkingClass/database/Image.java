package kpfu.itis.group11_801.kilin.WorkingClass.database;

public class Image {
    private int id;
    private int number;
    private String imagePath;
    private String info;
    private int messageId;

    public Image(int id, int number, String imagePath, String info, int messageId) {
        this.number = number;
        this.imagePath = imagePath;
        this.info = info;
        this.messageId = messageId;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getInfo() {
        return info;
    }

    public int getNumber() {
        return number;
    }

    public String getImagePath() {
        return imagePath;
    }

    public int getMessageId() {
        return messageId;
    }
}