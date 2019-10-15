package kpfu.itis.group11_801.kilin.WorkingClass.database;

public class FactorOfSalary {
    private int id;
    private String name;
    private String info;
    private Company company;

    public FactorOfSalary(int id, String name, String info, Company company) {
        this.info = info;
        this.name = name;
        this.id = id;
        this.company = company;
    }

    public String getInfo() {
        return info;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public Company getCompany() {
        return company;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof FactorOfSalary) {
            return ((FactorOfSalary) obj).getId() == getId();
        }
        else {
            return false;
        }
    }
}