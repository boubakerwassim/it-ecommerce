package com.it.ecommerce.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Embeddable
public class ShippingAddress {
  @NotBlank
  @Size(max = 200)
  @Column(name = "ship_line1", nullable = false, length = 200)
  private String line1;

  @Size(max = 200)
  @Column(name = "ship_line2", length = 200)
  private String line2;

  @NotBlank
  @Size(max = 100)
  @Column(name = "ship_city", nullable = false, length = 100)
  private String city;

  @NotBlank
  @Size(max = 100)
  @Column(name = "ship_state", nullable = false, length = 100)
  private String state;

  @NotBlank
  @Size(max = 30)
  @Column(name = "ship_postal_code", nullable = false, length = 30)
  private String postalCode;

  @NotBlank
  @Size(max = 2)
  @Column(name = "ship_country_code", nullable = false, length = 2)
  private String countryCode;
}

