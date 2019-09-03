package com.leyou.item.service.impl;

import com.leyou.item.mapper.SpecificationMapper;
import com.leyou.item.service.SpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 规格参数
 * @author lujianrong
 */
@Service
public class SpecificationServiceImpl implements SpecificationService {

    @Autowired
    SpecificationMapper specificationMapper;
}
