package by.bsuir.stephanovich.server.dao.user;

import by.bsuir.stephanovich.model.User;
import by.bsuir.stephanovich.serializer.Serializer;

import java.util.List;

public class UserDao implements IUser {

    private List<User> users;
    private Serializer serializer;
    private final static String FILE_NAME = "users.xml";

    public UserDao(){
        serializer = new Serializer(FILE_NAME, User.class);
    }

    @Override
    public User authirize(String login, String password) {
        users = (List<User>) serializer.deserialize();

        for (User user : users){
            if (login.equals(user.getLogin()) && (password.equals(user.getPassword()))){
                return user;
            }
        }

        return null;
    }

    @Override
    public User registrate(String login, String password, boolean isAdmin) {
        if (!isLoginAlreadyExists(login)){
            User user = new User(login, password, false);
            users = (List<User>) serializer.deserialize();
            users.add(user);
            serializer.serialize(users);
            return user;
        }
        return null;
    }

    private boolean isLoginAlreadyExists(String login){
        users = (List<User>) serializer.deserialize();
        for (User user : users){
            if (login.equals(user.getLogin())){
                return true;
            }
        }
        return false;
    }
}
