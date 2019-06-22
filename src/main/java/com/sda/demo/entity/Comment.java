package com.sda.demo.entity;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;
    private DateTimeFormat createDate;
    private DateTimeFormat modifyDate;
    private DateTimeFormat deleteDate;
    private Long userId;
    private Long postId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DateTimeFormat getCreateDate() {
        return createDate;
    }

    public void setCreateDate(DateTimeFormat createDate) {
        this.createDate = createDate;
    }

    public DateTimeFormat getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(DateTimeFormat modifyDate) {
        this.modifyDate = modifyDate;
    }

    public DateTimeFormat getDeleteDate() {
        return deleteDate;
    }

    public void setDeleteDate(DateTimeFormat deleteDate) {
        this.deleteDate = deleteDate;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }
}
