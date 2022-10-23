package com.moonz.javapractice.CrackingCodingInterview.ch7.prob7;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * 사용자 관리 클래스가 관리하는 기능
 *  - 친구 추가 요청
 *  - 친구 추가 승낙
 *  - 친구 추가 거절
 *  - 사용자 로그인
 * 추가 가능할 메서드 : 회원가입
 */
public class UserManager {
    private static UserManager instance;
    private Map<Long, User> usersById = new HashMap<>();
    private Map<String, User> usersByName = new HashMap<>();

    public static UserManager getInstance() {
        if (instance == null) {
            instance = new UserManager();
        }
        return instance;
    }

    /**
     * 친구 추가 요청 시 UserManager가 관리함.
     */
    public void putAddFriendRequest (User fromUser, String toUserName) {
        User to = usersByName.get(toUserName);
        AddRequest request = new AddRequest(fromUser, to, LocalDateTime.now(), RequestStatus.UNREAD);
        to.putSentRequests(request);
        fromUser.putReceivedRequests(request);
    }

    /**
     * 친구추가 승낙
     */
    public void approveAddFriendRequest (AddRequest req) {
        req.setRequestStatus(RequestStatus.RECEIVED);
        User fromUser = req.getFromUser();
        User toUser = req.getToUser();
        fromUser.addContact(toUser);
        toUser.addContact(fromUser);
    }
    /**
     * 친구추가 거절
     */
    public void rejectAddFriendRequest (AddRequest req) {
        req.setRequestStatus(RequestStatus.REJECTED);
        User fromUser = req.getFromUser();
        User toUser = req.getToUser();
        fromUser.removeAddRequest(req);
    }

    /**
     * 사용자 로그인
     */
    public void userLogin (String username) {
        User user = usersByName.get(username);
        if (user != null) {
            user.setUserStatusType(User.UserStatusType.Available);
        }
    }
}
