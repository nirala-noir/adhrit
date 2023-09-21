package com.meta.verse.debugWhale.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OptinResponseDto {

    private Boolean optinStatus;
    private String access_token;
    private String refresh_token;
    private String token_type;
    private Integer expires_in;

}
//{"code":400,"error":"Request method 'GET' not supported","error_description":"Something went wrong!"}