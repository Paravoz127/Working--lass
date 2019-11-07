package kpfu.itis.group11_801.kilin.workingClass.database;
public class User implements Comparable<User> {
    private int id;
    private String firstName;
    private String secondName;
    private String login;
    private String password;
    private Date date;
    private Company company;
    private User boss;
    private Image image;

    public User(int id, String firstName, String secondName, String login, String password,
                Date date, Company company, User boss, Image image) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.login = login;
        this.password = password;
        this.date = date;
        this.company = company;
        this.boss = boss;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLogin() {
        return login;
    }

    public String getSecondName() {
        return secondName;
    }

    public Company getCompany() {
        return company;
    }

    public Date getDate() {
        return date;
    }

    public User getBoss() {
        return boss;
    }

    public Image getImage() {
        return image;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public void setBoss(User boss) {
        this.boss = boss;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof User) {
            return ((User) obj).getId() == getId();
        }
        else {
            return false;
        }
    }

    @Override
    public String toString() {
        return firstName + " " + secondName;
    }

    @Override
    public int compareTo(User o) {
        return id - o.getId();
    }
}