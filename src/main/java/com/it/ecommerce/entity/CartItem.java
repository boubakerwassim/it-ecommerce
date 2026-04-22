package com.it.ecommerce.entity;

import java.math.BigDecimal;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.persistence.Version;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(
    name = "cart_items",
    uniqueConstraints = {
      @UniqueConstraint(name = "uk_cart_items_cart_product", columnNames = {"cart_id", "product_id"})
    })
public class CartItem extends AuditableEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(
      name = "cart_id",
      nullable = false,
      foreignKey = @jakarta.persistence.ForeignKey(name = "fk_cart_items_cart"))
  private Cart cart;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(
      name = "product_id",
      nullable = false,
      foreignKey = @jakarta.persistence.ForeignKey(name = "fk_cart_items_product"))
  private Product product;

  @Min(1)
  @Column(name = "quantity", nullable = false)
  private int quantity;

  @NotNull
  @Column(name = "unit_price_snapshot", nullable = false, precision = 19, scale = 2)
  private BigDecimal unitPriceSnapshot;

  @Version
  @Setter(AccessLevel.NONE)
  private long version;
}

