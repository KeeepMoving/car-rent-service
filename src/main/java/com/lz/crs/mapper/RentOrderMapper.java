package com.lz.crs.mapper;


import com.lz.crs.data.OrderStatusEnum;
import com.lz.crs.data.RentOrder;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Mapper
public interface RentOrderMapper {

    @Update("update t_rent_order set order_status = #{orderStatus} where id = #{rentOrderId}")
    void updateOrderStatus(Long rentOrderId, OrderStatusEnum orderStatus);

    @Insert("INSERT INTO t_rent_order(user_id, car_info_id, currency, price, quantity, amount, order_status, start_time, end_time, comments, created_time, modified_time)" +
            " VALUES(#{userId}, #{carInfoId}, #{currency}, #{price}, #{quantity}, #{amount}, #{orderStatus}, #{startTime}, #{endTime}, #{comments}, #{createdTime}, #{modifiedTime})")
    void addRentOrder(Long userId, Long carInfoId, String currency, BigDecimal price, Integer quantity, BigDecimal amount, OrderStatusEnum orderStatus, Date startTime, Date endTime, String comments, Date createdTime, Date modifiedTime);

    @Select("select * from t_rent_order")
    List<RentOrder> getRentOrders();

    @Select("select * from t_rent_order where id = #{id}")
    RentOrder getRentOrder(Long id);
}
