package com.HIT.reactintegration.dtos.eventsdto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

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

}
