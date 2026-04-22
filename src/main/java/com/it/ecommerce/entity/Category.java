package com.it.ecommerce.entity;

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
import jakarta.validation.constraints.NotBlank;
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
    name = "categories",
    uniqueConstraints = {
      @UniqueConstraint(name = "uk_categories_slug", columnNames = {"slug"})
    })
public class Category extends AuditableEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank
  @Size(max = 120)
  @Column(name = "name", nullable = false, length = 120)
  private String name;

  @NotBlank
  @Size(max = 140)
  @Column(name = "slug", nullable = false, length = 140)
  private String slug;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "parent_id", foreignKey = @jakarta.persistence.ForeignKey(name = "fk_categories_parent"))
  private Category parent;

  @Version
  @Setter(AccessLevel.NONE)
  private long version;
}

