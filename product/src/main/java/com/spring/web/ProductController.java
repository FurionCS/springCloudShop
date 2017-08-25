package com.spring.web;


import com.google.common.base.Strings;
import com.spring.common.model.StatusCode;
import com.spring.common.model.response.ObjectDataResponse;
import com.spring.domain.model.Product;
import com.spring.domain.request.ProductUpdateRequest;

import com.spring.service.ProductService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

/**
 * @Description 产品控制器
 * @Author ErnestCheng
 * @Date 2017/5/27.
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @ApiOperation(value="添加产品")
    @PostMapping(value="/addProduct")
    public ObjectDataResponse addProduct(@Validated @RequestBody Product product, BindingResult result){
        product.setCreateTime(new Date());
        productService.addProduct(product);
         return new ObjectDataResponse();
    }

    @ApiOperation("更新产品信息")
    @PostMapping("/updateProduct")
    public ObjectDataResponse updateProduct(@Validated @RequestBody ProductUpdateRequest productUpdateRequest,BindingResult result){
        int flag=productService.updateProduct(productUpdateRequest);
        if(flag==0){
            return new ObjectDataResponse(StatusCode.Update_Fail,"更新失败");
        }
        return  new ObjectDataResponse();
    }

    /**
     * 通过产品id获得产品
     * @param productId
     * @return
     */
    @ApiOperation(value="通过产品id获得产品")
    @GetMapping("/getProductById")
    public ObjectDataResponse<Product> getProductById(@RequestParam("productId") Integer productId){
        if(productId==null||productId<1){
           return new ObjectDataResponse(StatusCode.Param_Error,"参数不对");
        }else{
          return new ObjectDataResponse<>(productService.getProductById(productId));
        }
    }

    @ApiOperation(value="删除产品通过产品id")
    @GetMapping()
    public ObjectDataResponse deleteProductByProductId(@RequestParam("productId") Integer productId){
        if(Objects.isNull(productId)|| productId<1){
            return new ObjectDataResponse(StatusCode.Param_Error,"参数不对");
        }else{
            int flag=productService.deleteProductByProductId(productId);
            if(flag==0){
              return new ObjectDataResponse(StatusCode.Update_Fail,"更新失败");
            }
        }
        return new ObjectDataResponse();
    }
}
