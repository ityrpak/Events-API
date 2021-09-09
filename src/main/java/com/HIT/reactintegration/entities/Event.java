package com.HIT.reactintegration.entities;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "RECORD")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Event {

    @Id
    @Column(name = "id")
    private UUID id = UUID.randomUUID();

    @Column(name = "title", nullable = false)
    private String eventTitle;

    @Column(name = "description", nullable = false)
    private String eventDescription;

    @Column(name = "image", nullable = false)
    private String eventImage;

    @Column(name = "author", nullable = false)
    private String eventAuthor;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "last_updated")
    private LocalDateTime lastUpdated;

    @UpdateTimestamp
    @Column(name = "expiration")
    private LocalDateTime recordExpiration;

    public Event(String eventTitle) {
        this.eventTitle = eventTitle;
    }
}
