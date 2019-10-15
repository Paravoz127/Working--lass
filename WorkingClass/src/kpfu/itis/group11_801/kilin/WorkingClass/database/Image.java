package kpfu.itis.group11_801.kilin.WorkingClass.database;

public class Image {
    private int number;
    private String imagePath;
    private String info;

    public Image(int number, String imagePath, String info) {
        this.number = number;
        this.imagePath = imagePath;
        this.info = info;
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
}