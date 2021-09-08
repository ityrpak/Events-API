package com.HIT.reactintegration.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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

    public Record(String recordContent) {
        this.recordContent = recordContent;
    }
}
