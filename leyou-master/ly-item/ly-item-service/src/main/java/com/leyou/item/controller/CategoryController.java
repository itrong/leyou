package com.leyou.item.controller;

import com.leyou.item.po.Category;
import com.leyou.item.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    /**
     * 根据父节点id查商品分类
     *
     * @param pid 父节点id
     */
    @GetMapping("/list")
    public ResponseEntity<List<Category>> queryCategoryListByParentId(@RequestParam(value = "pid", defaultValue = "0") Long pid) {


        try {
            if (pid == null || pid.longValue() < 0) {
                // pid为null或者小于等于0，响应400
                return ResponseEntity.badRequest().build();
            }
            // 执行查询操作
            List<Category> categoryList = this.categoryService.queryCategoryListByParentId(pid);

            // 响应200
            return ResponseEntity.ok(categoryList);
        } catch (Exception e) {
            e.printStackTrace();
            // 响应500
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    /**
     * 新增品牌分类
     *
     * @param category 品牌分类实体
     * @return
     */
    @PostMapping
    public ResponseEntity<Void> saveCategory(Category category) {

        try {
            //校验父节点是否存在
            List<Category> categoryList = this.categoryService.queryCategoryListById(category.getParentId());
            if (CollectionUtils.isEmpty(categoryList)) {
                // 返回结果集为空，响应406
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
            }
            //执行保存操作
            categoryService.saveCategory(category);
            // 响应201
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 响应500
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }


    /**
     * 删除品牌分类
     *
     * @param id 主键
     */
    @DeleteMapping
    public ResponseEntity<Void> deleteById(@RequestParam(value = "id") Long id) {


        try {
            System.out.println("111");
            //执行删除操作
            categoryService.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 响应500
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    /**
     * 编辑品牌分类
     *
     * @param category 品牌分类实体
     * @return
     */
    @PutMapping
    public ResponseEntity<Void> editCategory(Category category) {

        try {

            //执行编辑操作
            categoryService.editCategory(category);
            // 响应201
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 响应500
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
    /**
     * 根据品牌获得分类列表
     *
     * @param id 品牌id
     * @return
     */
    @GetMapping("bid")
    public ResponseEntity<List<Category>> queryByBrandId(@RequestParam(value = "id") Long id) {

        try {
            //根据品牌获得分类列表
            List<Category> categories = this.categoryService.queryByBrandId(id);
            // 响应201
            return ResponseEntity.ok(categories);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 响应500
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    /**
     * 根据分类id集合查询分类名称
     * @param ids
     * @return
     */
    @GetMapping("names")
    public ResponseEntity<List<String>> queryNameByIds(@RequestParam("ids")List<Long> ids){
        List<String> list = categoryService.queryNameByIds(ids);
        if (list == null || list.size() < 1){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }else {
            return ResponseEntity.ok(list);
        }
    }

    /**
     * 根据分类id集合查询分类名称
     * @param ids
     * @return
     */
    @GetMapping("all")
    public ResponseEntity<List<Category>> queryCategoryByIds(@RequestParam("ids")List<Long> ids){
        List<Category> list = categoryService.queryCategoryByIds(ids);
        if (list == null || list.size() < 1){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }else {
            return ResponseEntity.ok(list);
        }
    }

    /**
     * 根据3级分类id，查询1~3级的分类
     * @param id
     * @return
     */
    @GetMapping("all/level")
    public ResponseEntity<List<Category>> queryAllByCid3(@RequestParam("id") Long id){
        List<Category> list = this.categoryService.queryAllByCid3(id);
        if (list == null || list.size() < 1) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(list);
    }


}
