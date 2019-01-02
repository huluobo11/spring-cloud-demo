package com.imooc.product.provider.controller;


import com.imooc.product.provider.VO.ProductInfoVO;
import com.imooc.product.provider.VO.ProductVO;
import com.imooc.product.provider.dataobject.ProductCategory;
import com.imooc.product.provider.dataobject.ProductInfo;
import com.imooc.product.provider.dto.CartDTO;
import com.imooc.product.provider.service.CategoryService;
import com.imooc.product.provider.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.imooc.product.common.vo.Result;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("list")
    public Result list(){
        // 1。查询所有在架的商品
        List<ProductInfo> productInfoList = productService.findUpAll();
        // 2。获取类目type列表
        List<Integer> categoryTypeList = productInfoList.stream()
                .map(ProductInfo::getCategoryType)
                .collect(Collectors.toList());
        // 3。从数据库查询类目
        List<ProductCategory> categoryList = categoryService.findByCategoryTypeIn(categoryTypeList);

        // 4。构造数据
        List<ProductVO> productVOList = new ArrayList<>();
        categoryList.forEach(category -> {
            ProductVO productVO = new ProductVO();
            productVO.setCategoryName(category.getCategoryName());
            productVO.setCategoryType(category.getCategoryType());
            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            productInfoList.forEach(productInfo -> {
                if (productInfo.getCategoryType().equals(category.getCategoryType())) {
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo, productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            });
            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);
        });
        return Result.success(productVOList);
    }

    /**
     * 获取商品列表
     * @param productIdList
     */
    @PostMapping("listForOrder")
    public List<ProductInfo> listForOrder(@RequestBody List<String> productIdList){
        return productService.findProductList(productIdList);
    }

    @PostMapping("decreaseStock")
    public void decreaseStock(@RequestBody List<CartDTO> cartDTOList){
        productService.decreaseStock(cartDTOList);
    }
}
