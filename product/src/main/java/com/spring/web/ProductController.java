package com.spring.web;

import com.google.common.base.Strings;
import com.spring.common.model.StatusCode;
import com.spring.domain.model.Product;
import com.spring.domain.request.ProductUpdateRequest;
import com.spring.domain.response.ObjectDataResponse;
import com.spring.domain.response.ProductResponse;
import com.spring.service.ProductService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
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
    public ProductResponse addProduct(@Validated @RequestBody Product product, BindingResult result){
        ProductResponse productResponse=new ProductResponse();
        if(product==null|| Strings.isNullOrEmpty(product.getName())||product.getPrice().compareTo(BigDecimal.ZERO)<0){
            productResponse.setCode(StatusCode.Fail_Code);
            productResponse.setMessage("参数不正确");
            return productResponse;
        }else{
            product.setCreateTime(new Date());
            productService.addProduct(product);
            return productResponse;
        }
    }

    @ApiOperation("更新产品信息")
    @PostMapping("/updateProduct")
    public ProductResponse updateProduct(@Validated @RequestBody ProductUpdateRequest productUpdateRequest,BindingResult result){
        ProductResponse productResponse=new ProductResponse();
        int flag=productService.updateProduct(productUpdateRequest);
        if(flag==0){
            productResponse.setCode(StatusCode.Update_Fail);
            productResponse.setMessage("更新失败");
        }
        return  productResponse;
    }

    /**
     * 通过产品id获得产品
     * @param productId
     * @return
     */
    @ApiOperation(value="通过产品id获得产品")
    @RequestMapping(value="getProductById",method = RequestMethod.GET)
    public ObjectDataResponse<Product> getProductById(@RequestParam("productId") Integer productId){
        ObjectDataResponse objectDataResponse=new ObjectDataResponse();
        if(productId==null||productId<1){
                objectDataResponse.setCode(StatusCode.Param_Error);
                objectDataResponse.setMessage("参数不对");
        }else{
           Product product= productService.getProductById(productId);
            if(product==null){
                objectDataResponse.setCode(StatusCode.Data_Not_Exist);
                objectDataResponse.setMessage("数据不存在");
            }else{
                objectDataResponse.setData(product);
            }
        }
        return objectDataResponse;
    }

}
