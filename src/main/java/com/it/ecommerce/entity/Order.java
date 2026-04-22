package com.it.ecommerce.entity;

import java.math.BigDecimal;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order extends AuditableEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "user_id", nullable = false, foreignKey = @jakarta.persistence.ForeignKey(name = "fk_orders_user"))
  private User user;

  @Enumerated(EnumType.STRING)
  @Column(name = "status", nullable = false, length = 20)
  private OrderStatus status = OrderStatus.PENDING;

  @NotNull
  @DecimalMin("0.00")
  @Column(name = "subtotal", nullable = false, precision = 19, scale = 2)
  private BigDecimal subtotal = BigDecimal.ZERO;

  @NotNull
  @DecimalMin("0.00")
  @Column(name = "tax", nullable = false, precision = 19, scale = 2)
  private BigDecimal tax = BigDecimal.ZERO;

  @NotNull
  @DecimalMin("0.00")
  @Column(name = "total", nullable = false, precision = 19, scale = 2)
  private BigDecimal total = BigDecimal.ZERO;

  @Enumerated(EnumType.STRING)
  @Column(name = "payment_method", nullable = false, length = 30)
  private PaymentMethod paymentMethod = PaymentMethod.CASH_ON_DELIVERY;

  @Enumerated(EnumType.STRING)
  @Column(name = "payment_status", nullable = false, length = 20)
  private PaymentStatus paymentStatus = PaymentStatus.UNPAID;

  @Column(name = "payment_reference", length = 200)
  private String paymentReference;

  @Valid
  @NotNull
  @Embedded
  private ShippingAddress shippingAddress;

  @Version
  @Setter(AccessLevel.NONE)
  private long version;
}

