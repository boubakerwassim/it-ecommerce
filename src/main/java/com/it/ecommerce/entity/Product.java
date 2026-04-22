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
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(
    name = "products",
    uniqueConstraints = {
      @UniqueConstraint(name = "uk_products_sku", columnNames = {"sku"})
    })
public class Product extends AuditableEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank
  @Size(max = 64)
  @Column(name = "sku", nullable = false, length = 64)
  private String sku;

  @NotBlank
  @Size(max = 200)
  @Column(name = "name", nullable = false, length = 200)
  private String name;

  @Size(max = 10_000)
  @Column(name = "description", length = 10_000)
  private String description;

  @NotBlank
  @Size(max = 120)
  @Column(name = "brand", nullable = false, length = 120)
  private String brand;

  @NotNull
  @DecimalMin("0.00")
  @Column(name = "price", nullable = false, precision = 19, scale = 2)
  private BigDecimal price;

  @Min(0)
  @Column(name = "stock_qty", nullable = false)
  private int stockQty;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(
      name = "category_id",
      nullable = false,
      foreignKey = @jakarta.persistence.ForeignKey(name = "fk_products_category"))
  private Category category;

  @Column(name = "specifications", nullable = false, columnDefinition = "jsonb")
  private String specificationsJson = "{}";

  @Column(name = "rating_avg", nullable = false, precision = 3, scale = 2)
  private BigDecimal ratingAvg = BigDecimal.ZERO;

  @Column(name = "rating_count", nullable = false)
  private int ratingCount = 0;

  @Version
  @Setter(AccessLevel.NONE)
  private long version;
}

