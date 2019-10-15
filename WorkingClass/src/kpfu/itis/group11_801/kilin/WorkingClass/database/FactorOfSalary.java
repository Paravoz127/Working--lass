package kpfu.itis.group11_801.kilin.WorkingClass.database;

public class FactorOfSalary {
    private String name;
    private String info;

    public FactorOfSalary(String name, String info) {
        this.info = info;
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof FactorOfSalary) {
            return ((FactorOfSalary) obj).getName().equals(getName());
        }
        else {
            return false;
        }
    }
}