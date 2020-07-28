package com.lz.crs.data;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class RentOrder {

    private Long id;
    private Long userId;
    private Long carInfoId;
    private String currency;
    private BigDecimal price;
    private Integer quantity;
    private BigDecimal amount;
    private OrderStatusEnum orderStatus;
    private Date startTime;
    private Date endTime;
    private String comments;
    private Date createdTime;
    private Date modifiedTime;

}
