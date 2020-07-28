package com.lz.crs.data;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import java.util.Date;

@Data
@AllArgsConstructor
public class RentReqVO {

    @NonNull
    @ApiModelProperty(value = "userId", example = "10001")
    private Long userId;
    @NonNull
    @ApiModelProperty(value = "startTime", example = "2020-08-01 15:34:12")
    private Date startTime;
    @NonNull
    @ApiModelProperty(value = "endTime", example = "2020-08-10 15:34:12")
    private Date endTime;
}