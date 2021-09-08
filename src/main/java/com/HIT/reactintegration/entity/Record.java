package com.HIT.reactintegration.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class Record {

    @Id
    private UUID id = UUID.randomUUID();

}
