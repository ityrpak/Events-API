package com.HIT.reactintegration.dtos.eventsdto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.UUID;

@Data @Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventWDateDTO {

    private String eventTitle;
    private String eventImageUrl;
    @JsonFormat(pattern = "dd/MM/yyyy'@'HH:mm:ss")
    private LocalDateTime createdAt;
    private UUID eventId;

}
