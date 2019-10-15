package kpfu.itis.group11_801.kilin.WorkingClass.database;

public class PromotionRequest {
    private int id;
    private User sender;
    private User receiver;
    private String message;
    private boolean decided;

    public PromotionRequest(int id, User sender, User receiver, String message, boolean decided) {
        this.sender = sender;
        this.receiver = receiver;
        this.message = message;
        this.decided = decided;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public User getSender() {
        return sender;
    }

    public User getReceiver() {
        return receiver;
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