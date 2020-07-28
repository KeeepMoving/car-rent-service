package com.lz.crs.data;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class CarInfo {

    private Long id;
    private String licenseNumber;
    private CarModelEnum carModel;
    private BigDecimal dailyRent;
    private String currency;
    private String imageUrl;
    private String comments;
    private Date createdTime;
    private Date modifiedTime;
}