package kpfu.itis.group11_801.kilin.WorkingClass.database;

import java.util.Date;
import java.util.Map;

public class User {
    private int id;
    private String firstName;
    private String secondName;
    private String login;
    private String password;
    private Date date;
    private Company company;
    private User boss;
    private Map<FactorOfSalary, Integer> factors;

    public User(int id, String firstName, String secondName, String login, String password,
                Date date, Company company, User boss, Map<FactorOfSalary, Integer> factors) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.login = login;
        this.password = password;
        this.date = date;
        this.company = company;
        this.boss = boss;
        this.factors = factors;
    }

    public int getId() {
        return id;
    }

    public String getPassword() {
        return password;
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

    public Map<FactorOfSalary, Integer> getFactors() {
        return factors;
    }
}