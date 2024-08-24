package com.kedu.firmware.DTO;

import java.sql.Timestamp;

public class PrivateMessageDTO {
    private Long message_seq;  // 메시지의 고유 식별자
    private String sender_username;  // 보낸 사람의 사용자 이름
    private String receiver_username;  // 받는 사람의 사용자 이름
    private String content;  // 메시지 내용
    private Timestamp send_date;  // 보낸 날짜
    private Boolean read;  // 메시지 읽음 상태 (Boolean으로 변경)

    // 기본 생성자
    public PrivateMessageDTO() {
    }

    // 매개변수 생성자
    public PrivateMessageDTO(String sender_username, String receiver_username, String content, Timestamp send_date, Boolean read) {
        this.sender_username = sender_username;
        this.receiver_username = receiver_username;
        this.content = content;
        this.send_date = send_date;
        this.read = read;
    }

    // Getter 및 Setter 메서드

    public Long getMessage_seq() {
        return message_seq;
    }

    public void setMessage_seq(Long message_seq) {
        this.message_seq = message_seq;
    }

    public String getSender_username() {
        return sender_username;
    }

    public void setSender_username(String sender_username) {
        this.sender_username = sender_username;
    }

    public String getReceiver_username() {
        return receiver_username;
    }

    public void setReceiver_username(String receiver_username) {
        this.receiver_username = receiver_username;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getSend_date() {
        return send_date;
    }

    public void setSend_date(Timestamp send_date) {
        this.send_date = send_date;
    }

    public Boolean getRead() {
        return read;
    }

    public void setRead(Boolean read) {
        this.read = read;
    }
}