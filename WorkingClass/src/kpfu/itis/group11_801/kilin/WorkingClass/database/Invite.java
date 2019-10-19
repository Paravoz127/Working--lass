package kpfu.itis.group11_801.kilin.workingClass.database;

public class Invite {
    private int id;
    private User sender;
    private User receiver;
    private FactorAndUser factorAndUser;

    public Invite(int id, User sender, User receiver, FactorAndUser factorAndUser) {
        this.factorAndUser = factorAndUser;
        this.receiver = receiver;
        this.sender = sender;
        this.id = id;
    }

    public User getReceiver() {
        return receiver;
    }

    public User getSender() {
        return sender;
    }

    public FactorAndUser getFactorAndUser() {
        return factorAndUser;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Invite) {
            Invite invite = (Invite) obj;
            return invite.getFactorAndUser().equals(factorAndUser) && invite.receiver.equals(receiver);
        }
        else {
            return false;
        }
    }
}
