package com.HIT.reactintegration.dtos.responsesdto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonPropertyOrder( {"code", "status", "data"} )
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SuccessResponseDTO {

    private Integer code;
    private String status;
    private Object data;

}
