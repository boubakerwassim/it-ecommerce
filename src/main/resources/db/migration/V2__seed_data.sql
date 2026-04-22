-- Categories (IT products)
INSERT INTO categories (name, slug, parent_id, version, created_at, updated_at)
VALUES
  ('Laptops', 'laptops', NULL, 0, now(), now()),
  ('Desktops', 'desktops', NULL, 0, now(), now()),
  ('Components', 'components', NULL, 0, now(), now()),
  ('Peripherals', 'peripherals', NULL, 0, now(), now()),
  ('Networking', 'networking', NULL, 0, now(), now()),
  ('Accessories', 'accessories', NULL, 0, now(), now());

-- Sub-categories under Components
INSERT INTO categories (name, slug, parent_id, version, created_at, updated_at)
SELECT v.name, v.slug, c.id, 0, now(), now()
FROM (VALUES
  ('CPU', 'components-cpu'),
  ('GPU', 'components-gpu'),
  ('RAM', 'components-ram'),
  ('SSD', 'components-ssd')
) AS v(name, slug)
JOIN categories c ON c.slug = 'components';

-- Sample products
INSERT INTO products (
  sku, name, description, brand, price, stock_qty, category_id, specifications, rating_avg, rating_count, version, created_at, updated_at
)
SELECT
  p.sku, p.name, p.description, p.brand, p.price, p.stock_qty, c.id,
  p.specifications::jsonb, 0, 0, 0, now(), now()
FROM (
  VALUES
    ('LAP-DELL-5520', 'Dell Latitude 5520 (i7, 16GB, 512GB SSD)', 'Business laptop with 15.6" display.', 'Dell', 1299.99, 20, 'laptops',
     '{"cpu":"Intel Core i7","ram":"16GB","storage":"512GB SSD","display":"15.6"}') ,
    ('GPU-NV-4070S', 'NVIDIA GeForce RTX 4070 SUPER 12GB', 'High-performance GPU for gaming and AI workloads.', 'NVIDIA', 699.00, 15, 'components-gpu',
     '{"vram":"12GB","chipset":"RTX 4070 SUPER","cooling":"Dual-fan"}'),
    ('SSD-SAMS-990P-1T', 'Samsung 990 PRO 1TB NVMe SSD', 'PCIe 4.0 NVMe SSD.', 'Samsung', 119.99, 50, 'components-ssd',
     '{"capacity":"1TB","interface":"NVMe PCIe 4.0","read":"7450MB/s"}'),
    ('MON-LG-27GP', 'LG 27" QHD 165Hz Gaming Monitor', '27-inch QHD IPS 165Hz monitor.', 'LG', 329.99, 30, 'peripherals',
     '{"size":"27","resolution":"2560x1440","refreshRate":"165Hz","panel":"IPS"}'),
    ('RTR-TP-AX55', 'TP-Link Archer AX55 Wi-Fi 6 Router', 'Dual-band Wi-Fi 6 router.', 'TP-Link', 99.99, 40, 'networking',
     '{"wifi":"Wi-Fi 6","bands":"Dual-band","ports":"4xLAN"}')
) AS p(sku, name, description, brand, price, stock_qty, category_slug, specifications)
JOIN categories c ON c.slug = p.category_slug;

-- Default admin user
-- Password hash is a placeholder and will be updated when the Auth module is implemented.
INSERT INTO users (email, password_hash, role, full_name, phone, enabled, version, created_at, updated_at)
VALUES (
  'admin@it-shop.local',
  '$2a$10$2b2ZqS8VgTq0z8t9pYdYbO6p7GQeO5m1G1v0wHqQ9pQO5iZk5Kp3a',
  'ADMIN',
  'Default Admin',
  NULL,
  true,
  0,
  now(),
  now()
);

