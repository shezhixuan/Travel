// ProductPurchase.java
package org.zjsru.domain;

import lombok.Data;
import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class ProductPurchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    private int pricePaid; // 购买时支付的积分
    private Date purchaseDate;
    private int quantity = 1; // 购买数量，默认为1
}