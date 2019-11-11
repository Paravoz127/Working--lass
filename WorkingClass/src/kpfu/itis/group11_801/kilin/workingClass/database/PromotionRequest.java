package kpfu.itis.group11_801.kilin.workingClass.database;

public class PromotionRequest {
    private int id;
    private User user;
    private String message;
    private boolean decided;

    public PromotionRequest(int id, User user, String message, boolean decided) {
        this.user = user;
        this.message = message;
        this.decided = decided;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public User getSender() {
        return user;
    }

    public boolean isDecided() {
        return decided;
    }

    public String getMessage() {
        return message;
    }

    public void setDecided(boolean decided) {
        this.decided = decided;
    }
}