package kpfu.itis.group11_801.kilin.workingClass.database.services;

import kpfu.itis.group11_801.kilin.workingClass.database.DAO.MessageToUserDAO;
import kpfu.itis.group11_801.kilin.workingClass.database.MessageToUser;
import kpfu.itis.group11_801.kilin.workingClass.database.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
}
