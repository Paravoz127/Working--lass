package kpfu.itis.group11_801.kilin.workingClass.database.services;

import kpfu.itis.group11_801.kilin.workingClass.database.DAO.MessageToUserDAO;
import kpfu.itis.group11_801.kilin.workingClass.database.MessageToUser;
import kpfu.itis.group11_801.kilin.workingClass.database.User;

import java.util.*;
import java.util.stream.Collectors;

public class MessageToUserService {
    public MessageToUser getById(int id) {
        return MessageToUserDAO.getMessageToUserDAO().getById(id);
    }

    public List<MessageToUser> getMessagesFrom1to2(User user1, User user2) {
        List<MessageToUser> res = new ArrayList<>();
        res = MessageToUserDAO.getMessageToUserDAO().getMessagesFrom1To2(user1, user2);
        return res;
    }

    public List<MessageToUser> getMessages(User user1, User user2) {
        List<MessageToUser> res = getMessagesFrom1to2(user1, user2);
        res.addAll(getMessagesFrom1to2(user2, user1));
        Collections.sort(res);
        return res;
    }

    public void create(MessageToUser messageToUser) {
        MessageToUserDAO.getMessageToUserDAO().create(messageToUser);
    }

    public List<MessageToUser> getBySender(User u) {
        return MessageToUserDAO.getMessageToUserDAO().getBySender(u);
    }

    public List<MessageToUser> getByTarget(User u) {
        return MessageToUserDAO.getMessageToUserDAO().getByTarget(u);
    }

    public Set<User> getUsersWhoHaveDialogsWithUser(User u) {
        List<User> users = getBySender(u).stream()
                .map(x -> x.getReceiver())
                .collect(Collectors.toList());
        users.addAll(getByTarget(u).stream()
                .map(x -> x.getSender())
                .collect(Collectors.toList())
        );

        Set<User> res = new TreeSet<>();
        res.addAll(users);
        return res;
    }
}
