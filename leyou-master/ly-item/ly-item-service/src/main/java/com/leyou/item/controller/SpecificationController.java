package com.leyou.item.controller;

import com.leyou.item.service.SpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 规格参数
 *
 * @author lujianrong
 */
@Controller
@RequestMapping("spec")
public class SpecificationController {

    @Autowired
    SpecificationService specificationService;

    @GetMapping
    public ResponseEntity<String> querySpecificationByCategoryId(){



        return ResponseEntity.ok("");
    }



}
