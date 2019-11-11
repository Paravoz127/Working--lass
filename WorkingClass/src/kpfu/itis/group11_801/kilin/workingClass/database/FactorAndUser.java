package kpfu.itis.group11_801.kilin.workingClass.database;

public class FactorAndUser {
    private int id;
    private User user;
    private FactorOfSalary factorOfSalary;
    private int value;

    public FactorAndUser(int id, User user, FactorOfSalary factorOfSalary, int value) {
        this.id = id;
        this.user = user;
        this.factorOfSalary = factorOfSalary;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public int getValue() {
        return value;
    }

    public User getUser() {
        return user;
    }

    public FactorOfSalary getFactorOfSalary() {
        return factorOfSalary;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof FactorAndUser) {
            FactorAndUser factorAndUser = (FactorAndUser) obj;
            return factorAndUser.factorOfSalary.equals(factorOfSalary) && factorAndUser.user.equals(user);
        }
        else {
            return false;
        }
    }
}
