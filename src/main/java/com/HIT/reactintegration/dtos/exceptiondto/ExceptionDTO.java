package com.HIT.reactintegration.dtos.exceptiondto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonPropertyOrder( {"exceptionName", "exceptionMessage"} )
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExceptionDTO {

    private String exceptionName;
    private String exceptionMessage;

}
