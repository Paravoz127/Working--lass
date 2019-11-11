package kpfu.itis.group11_801.kilin.workingClass.database.services;

import kpfu.itis.group11_801.kilin.workingClass.database.DAO.PromotionRequestDAO;
import kpfu.itis.group11_801.kilin.workingClass.database.PromotionRequest;
import kpfu.itis.group11_801.kilin.workingClass.database.User;

import java.util.ArrayList;
import java.util.List;

public class PromotionRequestService {
    public List<PromotionRequest> getAllFromUser(User user) {
        return PromotionRequestDAO.getPromotionRequestDAO().getAllFromUser(user);
    }

    public List<PromotionRequest> getAllToUser(User user) {
        List<PromotionRequest> res = new ArrayList<>();
        for (User u : new UserService().getEmployees(user)) {
            res.addAll(getAllFromUser(u));
        }
        return res;
    }

    public PromotionRequest getById(int id) {
        return PromotionRequestDAO.getPromotionRequestDAO().getById(id);
    }

    public void create(PromotionRequest pr) {
        PromotionRequestDAO.getPromotionRequestDAO().create(pr);
    }

    public void delete(int id) {
        PromotionRequestDAO.getPromotionRequestDAO().delete(id);
    }
}
