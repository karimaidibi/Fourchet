package com.fourchet.persist;

<<<<<<< HEAD
=======
import com.fourchet.persist.account.UserDao;

>>>>>>> 1e2495129ac64cfa790518da8a9ad2cbba42027a
// THis class below is a singleton class
public abstract class AbstractFactory {
    private static DaoFactory instance = null;


    protected AbstractFactory() {}

    public static DaoFactory getInstance() {
        if (instance == null) {
            instance = new DaoFactory();
        }
        return instance;
    }

<<<<<<< HEAD
    public abstract MongoDB getMongoDB();

=======
>>>>>>> 1e2495129ac64cfa790518da8a9ad2cbba42027a
    public abstract UserDao getUserDao();

}
