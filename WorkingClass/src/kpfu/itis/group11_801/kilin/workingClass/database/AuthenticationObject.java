package kpfu.itis.group11_801.kilin.workingClass.database;

public class AuthenticationObject {
    private User user;
    private int code;

    public AuthenticationObject(User user, int code) {
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
