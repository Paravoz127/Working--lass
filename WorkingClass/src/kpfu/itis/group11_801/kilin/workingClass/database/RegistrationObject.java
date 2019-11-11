package kpfu.itis.group11_801.kilin.workingClass.database;

public class RegistrationObject {
    private User user;
    private int code;

    public RegistrationObject(User user, int code) {
        this.user = user;
        this.code = code;
    }

    public User getUser() {
        return user;
    }

    public int getCode() {
        return code;
    }
}
