package com.example.WebAppCinephiles.Models;

import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.lang.NonNull;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name="Messages")
public class Messages implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long messageId;
    @Column(name = "userIdSender")
    private long userIdSender;
    @Column(name = "userIdReceiver")
    private long userIdReceiver;
    @NonNull
    @Column(name="content")
    private String content;
    @Column(name="publish_date")
    private LocalDateTime publishDate;

    public Messages(long userIdSender, long userIdReceiver, @NonNull String content) {
        this.userIdSender = userIdSender;
        this.userIdReceiver = userIdReceiver;
        this.content = content;
        this.publishDate = publishDate;
    }

    public Messages() {}

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public Long getMessageId() {
        return messageId;
    }

    public long getUserIdSender() {
        return userIdSender;
    }

    public void setUserIdSender(long userIdSender) {
        this.userIdSender = userIdSender;
    }

    public long getUserIdReceiver() {
        return userIdReceiver;
    }

    public void setUserIdReceiver(long userIdReceiver) {
        this.userIdReceiver = userIdReceiver;
    }

    @NonNull
    public String getContent() {
        return content;
    }

    public void setContent(@NonNull String content) {
        this.content = content;
    }

    public LocalDateTime getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(LocalDateTime publishDate) {
        this.publishDate = publishDate;
    }

//    @Override
//    public String toString() {
//        return "Messages{" +
//                "messageId=" + messageId +
//                ", userIdSender=" + userIdSender +
//                ", userIdReceiver=" + userIdReceiver +
//                ", content='" + content + '\'' +
//                ", publishDate=" + publishDate +
//                '}';
//    }

        @Override
    public String toString() {
        return "Messages{" +
                "messageId=" + messageId +
                ", userIdSender=" + userIdSender +
                ", userIdReceiver=" + userIdReceiver +
                ", content='" + content + '\'' +
                '}';
    }
}