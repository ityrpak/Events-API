package com.HIT.reactintegration.dtos.eventsdto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data @Builder
@NoArgsConstructor @AllArgsConstructor
@Schema(name = "Event", description = "Event creation object")
public class EventDTO {

    @JsonProperty(value = "eventTitle")
    @NotBlank(message = "must not be blank")
    private String eventTitle;

    @JsonProperty(value = "eventDescription")
    @NotBlank(message = "must not be blank")
    private String eventDescription;

    @JsonProperty(value = "authorNickname")
    @NotBlank(message = "must not be blank")
    private String eventUserNickname;

}
