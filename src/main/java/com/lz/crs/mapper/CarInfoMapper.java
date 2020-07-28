package com.lz.crs.mapper;


import com.lz.crs.data.CarInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

@Mapper
public interface CarInfoMapper {

    @Select("SELECT\n" +
            "\ttci.* \n" +
            "FROM\n" +
            "\tt_car_info AS tci\n" +
            "where tci.id not in (\n" +
            "SELECT\n" +
            "\ttro.car_info_id \n" +
            "FROM\n" +
            "\tt_rent_order AS tro \n" +
            "WHERE\n" +
            "\ttro.order_status = 'ON_GOING' \n" +
            "\tAND !( tro.start_time >= #{endTime} OR tro.end_time <= #{startTime} ) \n" +
            " );")
    List<CarInfo> getCarInfos(Date startTime, Date endTime);

    @Select("select * from t_car_info where id = #{id} for update")
    CarInfo getCarInfoForLock(Long id);

}
