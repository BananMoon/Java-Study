package com.moonz.javapractice.CrackingCodingInterview.ch7.prob7;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * 사용자 클래스 - 필드: id, name, 개인채팅, 그룹채팅, 연락처목록, 상메, 활동상태, 친구요청 받은목록, 친구요청 목록
 * 메서드:
 * 메서드 전송 sendMessageToUser  (이때 chat이 null이면 새로 생성함)
 * 연락처 추가 addContact
 * 수신 친구요청 추가 putReceivedRequests
 * 발신 친구요청 추가 putSentRequests
 * 그룹/개인 채팅 추가
 */
@AllArgsConstructor
public class User {
    @Getter private Long id;
    @Getter private String name;
    private Map<Long, Chat> privateChats = new HashMap<>();   // id를 toUser로 해놓아도 되나?
	private Map<Long, GroupChat> groupChats = new HashMap<>();
    private Map<Long, User> contacts = new HashMap<>();    //주소록 (userId로 find)

    private String profileMessage;
    @Setter private UserStatusType userStatusType;
    private Map<Long, AddRequest> receivedRequests = new HashMap<>();
    private Map<Long, AddRequest> sentRequests = new HashMap<>();

    /*
    메시지 전송
    1대1 채팅
     */
    public void sendMessageToUser (User to, String content) {
        Chat chat = privateChats.get(to.getId());
        if (chat == null) {         // 새로운 채팅이라면
            chat = PrivateChat.create(this, to);
            privateChats.put(to.getId(), chat);
        }

        Message message = new Message(content, LocalDateTime.now());
        chat.addMessage(message);
    }

    /**
     * 그룹채팅에 메시지 전송
     */
    public boolean sendMessageToGroupChat(Long groupId, String content) {
        Chat chat = groupChats.get(groupId);
        if (chat == null) {
            chat = GroupChat.create(this);
        }

        Message m = new Message(content, LocalDateTime.now());
        return chat.addMessage(m);
    }

    /*
    목록부 추가
     */
    public boolean addContact (User user) {
        if (!contacts.containsKey(user.id)) {
            contacts.put(user.id, user);
        }
        return true;
    }
    /*
    받은 친구요청을 list에 put
     */
    public void putReceivedRequests (AddRequest request) {
        Long fromUserId = request.getFromUser().id;
        if (!receivedRequests.containsKey(fromUserId)) {
            receivedRequests.put(fromUserId, request);
        }
    }

    /*
    보낸 친구요청을 list에 put
     */
    public void putSentRequests (AddRequest request) {
        Long toUserId = request.getToUser().id;
        if (!sentRequests.containsKey(toUserId)) {
            sentRequests.put(toUserId, request);
        }
    }

    public void removeAddRequest(AddRequest req) {
        User toUser = req.getToUser();
        if (req.getFromUser() == this) {
            this.receivedRequests.remove(req.getToUser().getId());
        } else if (req.getToUser() == this) {
            this.sentRequests.remove(req.getFromUser().getId());
        }
    }

    public void addPrivateChat(PrivateChat privateChat) {
        User otherParticipant = privateChat.getOtherParticipant(this);
        privateChats.put(otherParticipant.getId(), privateChat);
    }

    public void addGroupChat(GroupChat groupChat) {
        groupChats.put(groupChat.id, groupChat);
    }

    @Getter
    public enum UserStatusType {
        Offline,
        Available,
        Busy,
        ;
    }
}
