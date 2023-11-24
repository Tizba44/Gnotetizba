package com.restbnote.rest.configs.exceptions;

import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
public class MyException extends RuntimeException{
    private MyExceptionPayLoad payLoad;
}
