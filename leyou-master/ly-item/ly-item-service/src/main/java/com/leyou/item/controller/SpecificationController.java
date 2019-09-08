package com.leyou.item.controller;

import com.leyou.item.po.Specification;
import com.leyou.item.service.SpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 规格参数
 *
 * @author lujianrong
 */
@RestController
@RequestMapping("spec")
public class SpecificationController {

    @Autowired
    private SpecificationService specificationService;


    /**
     * 根据分类id查询规格参数
     *
     * @param id 分类id
     */
    @GetMapping("{id}")
    public ResponseEntity<String> querySpecificationByCategoryId(@PathVariable("id") Long id) {

        Specification spec = this.specificationService.querySpecificationByCategoryId(id);

        if (spec == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(spec.getSpecifications());
    }


    /**
     * 编辑规格参数
     *
     * @param spec 规格参数
     */
    @PutMapping
    public ResponseEntity<Void> editSpecification(Specification spec) {

        specificationService.editSpecification(spec);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 新增规格参数
     *
     * @param spec 规格参数
     */
    @PostMapping
    public ResponseEntity<Void> saveSpecification(Specification spec) {

        specificationService.saveSpecification(spec);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
