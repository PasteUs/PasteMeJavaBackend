package cn.pasteme.backend.controller;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import cn.pasteme.backend.model.*;
import cn.pasteme.backend.mapper.*;

@RestController
public class Controller {
    private permanentMapper mapper;
    public Controller(permanentMapper mapper){
        this.mapper=mapper;
    }


}
