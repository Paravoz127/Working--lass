package kpfu.itis.group11_801.kilin.WorkingClass.database;

public class Company {
    private int id;
    private String info;

    public Company(int id, String info) {
        this.id= id;
        this.info = info;
    }

    public int getId() {
        return id;
    }

    public String getInfo() {
        return info;
    }
}