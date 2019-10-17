package kpfu.itis.group11_801.kilin.workingClass.database;

public class Company {
    private int id;
    private String name;
    private String info;
    private Image image;

    public Company(int id, String name, String info, Image image) {
        this.id= id;
        this.name = name;
        this.info = info;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getInfo() {
        return info;
    }

    public String getName() {
        return name;
    }

    public Image getImage() {
        return image;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Company) {
            return ((Company) obj).getName().equals(getName());
        }
        else {
            return false;
        }
    }
}