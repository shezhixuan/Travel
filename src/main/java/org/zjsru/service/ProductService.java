package org.zjsru.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zjsru.dao.ProductPurchaseRepository;
import org.zjsru.dao.ProductRepository;
import org.zjsru.domain.Product;
import org.zjsru.domain.ProductPurchase;

import java.util.Date;
import java.util.List;
@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private ProductPurchaseRepository purchaseRepository;
    // 获取所有商品
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    // 搜索商品
    public List<Product> searchProducts(String searchTerm) {
        return productRepository.findByNameContainingIgnoreCase(searchTerm);
    }

    // 获取单个商品
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    // 更新商品
    public Product updateProduct(Long id, Product productDetails) {
        return productRepository.findById(id)
                .map(product -> {
                    product.setName(productDetails.getName());
                    product.setImageUrl(productDetails.getImageUrl());
                    product.setPrice(productDetails.getPrice());
                    product.setStock(productDetails.getStock());
                    return productRepository.save(product);
                })
                .orElse(null);
    }

    // 购买商品
    public boolean purchaseProduct(Long productId, Long userId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("商品不存在"));

        if (product.getStock() > 0) {
            // 检查用户积分是否足够
            if (userService.deductPoints(userId, product.getPrice())) {
                product.setStock(product.getStock() - 1);
                productRepository.save(product);

                // 记录购买
                ProductPurchase purchase = new ProductPurchase();
                purchase.setUser(userService.getUserById(userId).orElseThrow(() -> new RuntimeException("用户不存在")));
                purchase.setProduct(product);
                purchase.setPricePaid(product.getPrice());
                purchase.setPurchaseDate(new Date());
                purchaseRepository.save(purchase);

                return true;
            } else {
                throw new RuntimeException("积分不足");
            }
        } else {
            throw new RuntimeException("库存不足");
        }
    }
}
