package com.spring.web;

import com.google.common.base.Preconditions;
import com.spring.common.model.response.PageResponse;
import com.spring.domain.model.ProductCategory;
import com.spring.domain.request.ProductCategoryRequest;
import com.spring.service.ProductCategoryService;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 产品分类
 * Created by ErnestCheng on 2017/9/27.
 */
@RestController
@RequestMapping("/product/category")
public class ProductCategoryController {
    private static final Logger LOGGER= Logger.getLogger(ProductCategoryController.class);

    @Autowired
    private ProductCategoryService productCategoryService;

    @PostMapping("/findProductCategory")
    @ApiOperation("分页获得产品分类")
    public PageResponse findProductCategory(@Validated @RequestBody ProductCategoryRequest request, BindingResult result){
       List<ProductCategory> productCategoryList=productCategoryService.findProductCategory(request.getProductCategoryStatus(),request.getSortOrderStart(),request.getSortOrderEnd(),request.getPageIndex(),request.getPageSize());
       Integer count=productCategoryService.getProductCategoryCount(request.getProductCategoryStatus(),request.getSortOrderStart(),request.getSortOrderEnd());
       return new PageResponse(productCategoryList,count);
    }

}
