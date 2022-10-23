package com.moonz.javapractice.CrackingCodingInterview.ch7.prob5;

import java.util.Map;

public class UserManager {
    private Map<Long, User> usersById;


    public User addUser(long id, String details, int accountType) {
        if (usersById.containsKey(id)) {
            return null;
        }
        User user = new User(id, details, accountType);
        usersById.put(id, user);
        return user;
    }
    public boolean removeById(long id) {
        if (!usersById.containsKey(id)) {
            return false;
        }
        usersById.remove(id);
        return true;
    }
    public boolean remove (User user) {
        return removeById(user.getUserId());
    }
    public User findById (long id) {
        return usersById.get(id);
    }
}
