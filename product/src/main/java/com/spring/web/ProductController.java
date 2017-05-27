package com.spring.web;

import com.spring.common.model.StatusCode;
import com.spring.domain.model.Product;
import com.spring.domain.response.ProductResponse;
import com.spring.service.ProductService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @Description 产品控制器
 * @Author ErnestCheng
 * @Date 2017/5/27.
 */
@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @ApiOperation(value="添加产品")
    @PostMapping(value="addProduct")
    public ProductResponse addProduct(@RequestBody Product product){
        ProductResponse productResponse=new ProductResponse();
        if(product==null||"".equals(product.getName())||"".equals(product.getPrice())){
            productResponse.setCode(StatusCode.Fail_Code);
            productResponse.setMessage("参数不正确");
            return productResponse;
        }else{
            product.setCreateTime(new Date());
            productService.addProduct(product);
            return productResponse;
        }
    }

}
