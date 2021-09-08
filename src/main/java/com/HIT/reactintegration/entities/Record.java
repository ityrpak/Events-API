package com.HIT.reactintegration.entities;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

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
public class Record {

    @Id
    @Column(name = "id")
    private UUID id = UUID.randomUUID();

    @Column(name = "record_content", nullable = false, unique = true)
    private String recordContent;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public Record(String recordContent) {
        this.recordContent = recordContent;
    }
}
