package com.social.dailylink.model;

import com.social.dailylink.generic.AbstractEntity;
import com.social.dailylink.global.GlobalStrings;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@Table(name = "post", schema = GlobalStrings.SCHEMA_NAME)
public class Post extends AbstractEntity {

    @Column(columnDefinition = "TEXT")
    private String content;

    @CreatedBy
    @ManyToOne
    @JoinColumn(name = "author_id", updatable = false, nullable = false)
    private User author;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Lob
    @Column(name = "media")
    private byte[] media;
}
