package com.HIT.reactintegration.dtos;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data @Builder
public class RecordDTO {

    private String recordContent;
    private LocalDateTime createdAt;

}
