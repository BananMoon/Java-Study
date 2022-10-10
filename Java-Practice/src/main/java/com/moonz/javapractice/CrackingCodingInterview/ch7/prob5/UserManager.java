package com.moonz.javapractice.CrackingCodingInterview.ch7.prob5;

import java.util.Map;

public class UserManager {
    private Map<Long, User> users;

    public User addUser(long id, String details, int accountType) {
        if (users.containsKey(id)) {
            return null;
        }
        User user = new User(id, details, accountType);
        users.put(id, user);
        return user;
    }
    public boolean removeById(long id) {
        if (!users.containsKey(id)) {
            return false;
        }
        users.remove(id);
        return true;
    }
    public boolean remove (User user) {
        return removeById(user.getUserId());
    }
    public User findById (long id) {
        return users.get(id);
    }
}
