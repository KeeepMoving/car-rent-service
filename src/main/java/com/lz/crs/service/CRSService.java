package com.lz.crs.service;

import com.lz.crs.data.*;
import com.lz.crs.mapper.CarInfoMapper;
import com.lz.crs.mapper.RentOrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;

/**
 * Service for car-info and rent-order
 */
@Service
@Slf4j
public class CRSService {

    @Autowired
    private CarInfoMapper carInfoMapper;
    @Autowired
    private RentOrderMapper rentOrderMapper;

    public List<CarInfo> getCarInfos(Date startTime, Date endTime) {
        return this.carInfoMapper.getCarInfos(startTime, endTime);
    }

    /**
     * 1: Lock target car record by 'for update',
     * 2: check if target record is still available at this time
     * 3: insert rent order or throw exception provisionally
     */
    public void rentCar(Long carInfoId, RentReqVO rentReqVO) throws CRSException {
        CarInfo carInfo = this.carInfoMapper.getCarInfoForLock(carInfoId);
        List<CarInfo> carInfos = this.carInfoMapper.getCarInfos(rentReqVO.getStartTime(), rentReqVO.getEndTime());
        if (CollectionUtils.isEmpty(carInfos) || !carInfos.stream().anyMatch(item -> item.getId() == carInfoId)) {
            throw new CRSException(ResponseCodeEnum.INSUFFICIENT_STOCK, ResponseCodeEnum.INSUFFICIENT_STOCK.getMsg());
        } else {
            this.rentOrderMapper.addRentOrder(rentReqVO.getUserId(), carInfoId, carInfo.getCurrency(), carInfo.getDailyRent(),
                    1, carInfo.getDailyRent(), OrderStatusEnum.ON_GOING, rentReqVO.getStartTime(), rentReqVO.getEndTime(), "", new Date(), new Date());
        }
    }

    public List<RentOrder> getRentOrders() {
        return this.rentOrderMapper.getRentOrders();
    }

    public RentOrder cancelRentOrder(Long rentOrderId) {
        this.rentOrderMapper.updateOrderStatus(rentOrderId, OrderStatusEnum.CANCELED);
        return this.rentOrderMapper.getRentOrder(rentOrderId);
    }
}
