package com.HIT.reactintegration.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data @Builder
@NoArgsConstructor @AllArgsConstructor
@Schema(name = "Record", description = "Record creation object")
public class RecordDTO {

    @JsonProperty(value = "Record content")
    @NotBlank(message = "must not be blank")
    private String recordContent;

}
