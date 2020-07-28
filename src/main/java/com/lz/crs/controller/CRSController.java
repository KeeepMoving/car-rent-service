package com.lz.crs.controller;

import com.lz.crs.data.*;
import com.lz.crs.service.CRSService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


/**
 * Controller for car-info and rent-order
 */
@Slf4j
@RestController
@RequestMapping("/api")
public class CRSController {

    @Autowired
    private CRSService crsService;

    /**
     * Get car information
     */
    @GetMapping("/carInfos")
    public ResponseEntity<List<CarInfo>> getCarInfos(@RequestParam(value = "startTime", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date startTime,
                                                     @RequestParam(value = "endTime", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date endTime) {
        try {
            if (log.isInfoEnabled()) {
                log.info("Received getting car infos request, startTime: {}, endTime: {}", startTime, endTime);
            }
            List<CarInfo> carInfos = this.crsService.getCarInfos(startTime, endTime);
            return new ResponseEntity<>(carInfos);
        } catch (Exception e) {
            log.error("Fail to process getting car infos request. error: {}", e.getMessage(), e);
            return new ResponseEntity<>(ResponseCodeEnum.INTERNAL_SERVER_ERROR.getStatus(), ResponseCodeEnum.INTERNAL_SERVER_ERROR.getMsg());
        }
    }


    /**
     * Place order for a car
     */
    @PostMapping("/carInfos/{id}/rent")
    public ResponseEntity rent(@PathVariable("id") Long carInfoId, @RequestBody RentReqVO rentReqVO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("Received getting rent car request, carInfoId: {}, rentReqVO: {}", carInfoId, rentReqVO);
            }
            this.crsService.rentCar(carInfoId, rentReqVO);
            return new ResponseEntity<>(null);
        } catch (CRSException lke) {
            log.error("Fail to process getting rent car request. code: {}, msg: {}, errorMessage: {}", lke.getErrorCode().getStatus(), lke.getErrorCode().getMsg(), lke.getErrorMessage());
            return new ResponseEntity<>(lke.getErrorCode().getStatus(), lke.getErrorCode().getMsg());
        } catch (Exception e) {
            log.error("Fail to process getting rent car  request. error: {}", e.getMessage(), e);
            return new ResponseEntity<>(ResponseCodeEnum.INTERNAL_SERVER_ERROR.getStatus(), ResponseCodeEnum.INTERNAL_SERVER_ERROR.getMsg());
        }
    }

    /**
     * Get user's rent orders information
     * TODO move userId to http header
     */
    @GetMapping("/rentOrders")
    public ResponseEntity<List<RentOrder>> getRentOrders() {
        try {
            if (log.isInfoEnabled()) {
                log.info("Received getting rent orders request");
            }
            List<RentOrder> rentOrders = this.crsService.getRentOrders();
            return new ResponseEntity<>(rentOrders);
        } catch (Exception e) {
            log.error("Fail to process getting rent orders request. error: {}", e.getMessage(), e);
            return new ResponseEntity<>(ResponseCodeEnum.INTERNAL_SERVER_ERROR.getStatus(), ResponseCodeEnum.INTERNAL_SERVER_ERROR.getMsg());
        }
    }

    /**
     * Cancel rent order
     */
    @PutMapping("/rentOrders/{id}/cancel")
    public ResponseEntity<RentOrder> cancelRentOrder(@PathVariable("id") Long renOrderId) {
        try {
            if (log.isInfoEnabled()) {
                log.info("Received getting cancel rent order request, renOrderId: {}", renOrderId);
            }
            RentOrder rentOrder = this.crsService.cancelRentOrder(renOrderId);
            return new ResponseEntity<>(rentOrder);
        } catch (Exception e) {
            log.error("Fail to process getting cancel rent order request. error: {}", e.getMessage(), e);
            return new ResponseEntity<>(ResponseCodeEnum.INTERNAL_SERVER_ERROR.getStatus(), ResponseCodeEnum.INTERNAL_SERVER_ERROR.getMsg());
        }
    }

}
