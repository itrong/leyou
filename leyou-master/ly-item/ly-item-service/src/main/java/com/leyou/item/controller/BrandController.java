package com.leyou.item.controller;

import com.leyou.common.pojo.PageResult;
import com.leyou.item.po.Brand;
import com.leyou.item.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("brand")
public class BrandController {

    @Autowired
    private BrandService brandService;


    /**
     * 品牌列表分页查询
     *
     * @param page   页码
     * @param rows   每页显示行数
     * @param sortBy 排序字段
     * @param desc   是否倒序
     * @param key    搜索关键字
     */
    @GetMapping("page")
    public ResponseEntity<PageResult<Brand>> queryBrandByPage(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "10") Integer rows,
            @RequestParam(value = "sortBy", required = false) String sortBy,
            @RequestParam(value = "desc", defaultValue = "false") Boolean desc,
            @RequestParam(value = "key", required = false) String key) {
        PageResult<Brand> result = this.brandService.queryBrandByPageAndSort(page, rows, sortBy, desc, key);
        return ResponseEntity.ok(result);
    }


    /**
     * 新增品牌
     *
     * @param brand 品牌信息
     */
    @PostMapping
    public ResponseEntity<Void> saveBrand(Brand brand, @RequestParam(value = "categories") List<Long> categories) {

        try {
            //保存品牌信息
            this.brandService.saveBrand(brand, categories);
            // 响应201
            return ResponseEntity.status(HttpStatus.CREATED).build();

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * 根据id查询品牌信息
     *
     * @param id 品牌id
     */
    @GetMapping("bid")
    public ResponseEntity<Brand> queryByBid(@RequestParam(value = "id") Long id) {

        try {
            //保存品牌信息
            Brand brand = this.brandService.queryByBid(id);
            // 返回品牌
            return ResponseEntity.ok(brand);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    /**
     * 编辑品牌
     *
     * @param brand 品牌信息
     * @param categories 分类id
     */
    @PutMapping
    public ResponseEntity<Void> editBrand(Brand brand, @RequestParam(value = "categories") List<Long> categories) {

        try {
            //保存品牌信息
            this.brandService.editBrand(brand, categories);
            // 响应201
            return ResponseEntity.status(HttpStatus.CREATED).build();

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * 删除品牌
     *
     * @param id 品牌id
     */
    @DeleteMapping
    public ResponseEntity<Void> deleteBrand(@RequestParam(value = "id") Long id) {

        try {
            //删除品牌信息
            this.brandService.deleteBrand(id);
            // 响应201
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    /**
     * 根据分类id查品牌
     *
     * @param id 品牌id
     */
    @GetMapping("cid/{id}")
    public ResponseEntity<List<Brand>> queryByCategoryId(@PathVariable(value = "id") Long id) {

        try {
            //保存品牌信息
            List<Brand> result = this.brandService.queryByCategoryId(id);
            // 返回品牌
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * 根据多个id查询品牌
     * @param ids
     * @return
     */
    @GetMapping("list")
    public ResponseEntity<List<Brand>> queryBrandByIds(@RequestParam("ids") List<Long> ids){
        List<Brand> list = this.brandService.queryBrandByIds(ids);
        if(list == null){
            new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(list);
    }




}
