package com.fourchet.bl.account.activities;

import com.fourchet.persist.AbstractFactory;
import com.fourchet.persist.account.UserDao;
import com.fourchet.persist.account.activities.ActivitiesDao;
import com.fourchet.users.User;
import com.fourchet.users.actitvities.Activity;

import java.util.List;
import java.util.Objects;

// This class below is a singleton class (we create the facade only once)
public class ActivitiesFacade {
    private static ActivitiesFacade instance = null;

    // The UserDaoFactory
    private AbstractFactory abstractFactory;
    // The UserDao
    private ActivitiesDao activitiesDao;

    private Activity currentActivity;


    private ActivitiesFacade() {
        this.abstractFactory = AbstractFactory.getInstance();
        this.activitiesDao = abstractFactory.getActivitiesDao();
    }

    public static ActivitiesFacade getInstance() {
        if (instance == null) {
            instance = new ActivitiesFacade();
        }
        return instance;
    }

    public Activity addActivity(Activity activity) {
        try {
            activitiesDao.save(activity);
            currentActivity = activity;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return currentActivity;
    }

    public Activity update(Activity activity, String[] params, Object picture) throws Exception {
        try {
            Activity updatedActivity = activitiesDao.update(activity, params, picture);
            currentActivity = updatedActivity;
        } catch (Exception e){
            System.out.println(e.getMessage());
            throw new Exception("Error during the connection to the database");
        }
        return currentActivity;
    }

    public List<Activity> getAllByUser(User user) {
        return activitiesDao.getAllByUser(user);
    }


    public Activity getCurrentActivity() {
        return currentActivity;
    }

    public void setCurrentActivity(Activity currentActivity) {
        this.currentActivity = currentActivity;
    }
}
