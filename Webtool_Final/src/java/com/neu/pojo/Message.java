
package com.neu.pojo;


public class Message {
    private long id;
    private String userName;
    private String fromUser;
    private String message;
    private String messageDate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessageDate() {
        return messageDate;
    }

    public void setMessageDate(String messageDate) {
        this.messageDate = messageDate;
    }

    public String getFromUser() {
        return fromUser;
    }

    public void setFromUser(String fromUser) {
        this.fromUser = fromUser;
    }

    @Override
    public String toString() {
        return "Message{" + "messageId=" + id + ", userName=" + userName + ", fromUser=" + fromUser + ", message=" + message + ", messageDate=" + messageDate + '}';
    }
}
