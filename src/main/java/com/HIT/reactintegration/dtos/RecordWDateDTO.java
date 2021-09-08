package com.HIT.reactintegration.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data @Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecordWDateDTO {

    private String recordContent;
    @JsonFormat(pattern = "dd/MM/yyyy'@'hh:mm:ss")
    private LocalDateTime createdAt;

}
