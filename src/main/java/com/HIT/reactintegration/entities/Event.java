package com.HIT.reactintegration.entities;

import lombok.*;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "events")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Event {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @GeneratedValue (generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @ColumnDefault("random_uuid()")
    @Type(type = "uuid-char")
    private UUID id = UUID.randomUUID();

    @Column(name = "title", nullable = false)
    private String eventTitle;

    @Column(name = "description", nullable = false)
    private String eventDescription;

    @Column(name = "image_URL")
    private String eventImageURL;

    @ManyToOne(optional = false)
    @JoinColumn(name = "author_id", nullable = false)
    private User eventAuthor;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "last_updated")
    private LocalDateTime lastUpdated;


    public Event(String eventTitle, String eventDescription, User eventAuthor) {
        this.eventTitle = eventTitle;
        this.eventDescription = eventDescription;
        this.eventAuthor = eventAuthor;
    }
}
