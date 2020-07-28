package com.lz.crs.service;

import com.lz.crs.CRSApplication;
import com.lz.crs.data.CRSException;
import com.lz.crs.data.RentOrder;
import com.lz.crs.data.RentReqVO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Calendar;
import java.util.Date;
import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = CRSApplication.class)
public class CRSServiceTest {

    @Autowired
    private CRSService crsService;

    @Test
    public void testRentCar() throws CRSException {
        Calendar instance = Calendar.getInstance();
        instance.setTime(new Date());
        Date startTime = instance.getTime();
        instance.add(Calendar.DAY_OF_MONTH, 10);
        Date endTime = instance.getTime();
        RentReqVO rentReqVO = new RentReqVO(1001L, startTime, endTime);
        this.crsService.rentCar(1L, rentReqVO);
        List<RentOrder> rentOrders = this.crsService.getRentOrders();
        Assert.assertEquals(1, rentOrders.size());
    }

    @Test(expected = CRSException.class)
    public void testRentCarWithInsufficientStock() throws CRSException {
        Calendar instance = Calendar.getInstance();
        instance.setTime(new Date());
        Date startTime = instance.getTime();
        instance.add(Calendar.DAY_OF_MONTH, 10);
        Date endTime = instance.getTime();
        RentReqVO rentReqVO1 = new RentReqVO(1002L, startTime, endTime);
        this.crsService.rentCar(2L, rentReqVO1);
        List<RentOrder> rentOrders1 = this.crsService.getRentOrders();
        Assert.assertEquals(1, rentOrders1.size());

        instance.add(Calendar.DAY_OF_MONTH, -1);
        RentReqVO rentReqVO2 = new RentReqVO(1002L, startTime, endTime);
        this.crsService.rentCar(2L, rentReqVO2);
    }
}