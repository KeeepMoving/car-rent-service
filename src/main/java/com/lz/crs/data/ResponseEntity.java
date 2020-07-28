package com.lz.crs.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseEntity<T> implements Serializable {

    private static final long serialVersionUID = 1L;
    private int status;
    private String msg;
    private T result;

    public ResponseEntity(int status, String msg) {
        this.status = status;
        this.msg = msg;
	}

    public ResponseEntity(T result) {
        this.status = ResponseCodeEnum.SUCCESS.getStatus();
        this.msg = ResponseCodeEnum.SUCCESS.getMsg();
        this.result = result;
    }
}
