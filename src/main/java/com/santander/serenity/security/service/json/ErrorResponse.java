package com.santander.serenity.security.service.json;

import com.santander.serenity.security.service.error.ErrorService;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * This bean represent a response with errors in JSON format.  
 */
@ApiModel
public class ErrorResponse {
    private int code;
    private String message;
    private String detail;

    public ErrorResponse(ErrorService error){
        this.code = error.getCode();
        this.message = error.getError();
        this.detail = error.getDetail();
    }

    @ApiModelProperty(value = "HTTP status code")
    public int getCode() {
        return code;
    }

    @ApiModelProperty(value = "HTTP status message")
    public String getMessage() {
        return message;
    }

    @ApiModelProperty(value = "Detail error message")
    public String getDetail() {
        return detail;
    }
    
    public void appendDetail(String detailExtended){
        this.detail = detail.concat(detailExtended);
    }
    
    @Override
    public String toString() {
        return "{" +
                "\"code\":\"" + code + "\"" +
                ", \"message\":" + message + "\"" +
                ", \"detail\":" + detail + "\"" +
                "}";
    }
}
