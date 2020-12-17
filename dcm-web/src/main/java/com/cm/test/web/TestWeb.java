package com.cm.test.web;

import com.cm.test.service.ITestService;
import com.cm.vs.entity.VehicleLocation;
import com.cm.vs.handle.VehicleShadowHandler;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Api(tags = {"test"})
@Controller
@RequestMapping("/test")
@Slf4j
public class TestWeb{

    @Resource
    private ITestService testService;

    @Resource
    private VehicleShadowHandler vehicleShadowHandler;


    @GetMapping(value = "/abc")
    @ResponseBody
    public String abd(HttpServletRequest request) throws Exception {
        return testService.testDubbo();
    }

    @GetMapping(value = "/vehicle")
    @ResponseBody
    public List<VehicleLocation> vehicle(String vin, HttpServletRequest request) throws Exception {
        return vehicleShadowHandler.getVehicleInfo(Collections.singletonList(vin));
    }
}
