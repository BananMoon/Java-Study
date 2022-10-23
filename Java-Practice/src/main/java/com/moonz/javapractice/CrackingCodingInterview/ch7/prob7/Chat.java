package com.moonz.javapractice.CrackingCodingInterview.ch7.prob7;

import java.util.ArrayList;
import java.util.List;

/**
 * - 참가자 추가
 * - 참가자 제거
 */
public abstract class Chat {
    protected Long id;
    protected List<User> participants = new ArrayList<>();
    protected List<Message> messages = new ArrayList<>();

    public boolean addMessage(Message message) {
        messages.add(message);
        // 송신 성공에 따라
        return true;
    }
}

class GroupChat extends Chat {
    private GroupChat (User user) {
        participants.add(user);
    }
    public void addParticipant(User user) {
        participants.add(user);
    }
    public void removeParticipant(User user) {
        participants.remove(user);
    }

    public static GroupChat create(User user) {
        return new GroupChat(user);
    }
}

class PrivateChat extends Chat {
    private PrivateChat (User user1, User user2) {
        participants.add(user1);
        participants.add(user2);
    }

    public static PrivateChat create(User sender, User receiever) {
        return new PrivateChat(sender, receiever);
    }

    public User getOtherParticipant(User primaryUser) {
        //개인채팅은 1대1
        if (participants.get(0) == primaryUser) {
            return participants.get(1);
        } else if (participants.get(1) == primaryUser){
            return participants.get(0);
        }
        return null;
    }
}
