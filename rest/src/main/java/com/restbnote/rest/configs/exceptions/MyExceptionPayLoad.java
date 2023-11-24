package com.restbnote.rest.configs.exceptions;

import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
public class MyExceptionPayLoad {
    private Integer httpCode;
    private String message;
}
