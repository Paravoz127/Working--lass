package kpfu.itis.group11_801.kilin.workingClass.database.services;

import kpfu.itis.group11_801.kilin.workingClass.database.Company;
import kpfu.itis.group11_801.kilin.workingClass.database.DAO.MessageToCompanyDAO;
import kpfu.itis.group11_801.kilin.workingClass.database.MessageToCompany;
import kpfu.itis.group11_801.kilin.workingClass.database.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MessageToCompanyService {
    public MessageToCompany getById(int id) {
        return MessageToCompanyDAO.getMessageToCompanyDAO().getById(id);
    }

    public List<MessageToCompany> getMessagesFrom1to2(User user1,  Company company) {
        List<MessageToCompany> res = new ArrayList<>();
        res = MessageToCompanyDAO.getMessageToCompanyDAO().getMessagesFrom1To2(user1, company);
        return res;
    }

    public List<MessageToCompany> getMessages(Company company) {
        List<MessageToCompany> res = MessageToCompanyDAO.getMessageToCompanyDAO().getMessagesByCompany(company);
        Collections.sort(res);
        return res;
    }

    public void create(MessageToCompany messageToUser) {
        MessageToCompanyDAO.getMessageToCompanyDAO().create(messageToUser);
    }
}
