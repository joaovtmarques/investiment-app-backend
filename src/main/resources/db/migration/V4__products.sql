CREATE TABLE categories {
  id uuid PRIMARY KEY DEFAULT gen_random_uuid(),
  name VARCHAR(50) NOT NULL UNIQUE,
  slug VARCHAR(50) NOT NULL UNIQUE,
  created_at TIMESTAMPTZ NOT NULL DEFAULT now()
}

CREATE TABLE products (
  id uuid PRIMARY KEY DEFAULT gen_random_uuid(),
  name VARCHAR(255) NOT NULL,
  description TEXT NOT NULL,
  category_id uuid NOT NULL REFERENCES categories(id) ON DELETE RESTRICT,
  min_investment NUMERIC(15,2) NOT NULL,
  max_units INTEGER NOT NULL CHECK (max_units > 0),
  units_sold INTEGER NOT NULL DEFAULT 0,
  estimated_return_percent NUMERIC(5,2) NOT NULL,
  estimated_days INTEGER NOT NULL,
  risk_level SMALLINT NOT NULL CHECK (risk_level BETWEEN 1 AND 5),
  roi_average_30d NUMERIC(6,2) NOT NULL,
  status VARCHAR(20) NOT NULL DEFAULT 'ACTIVE',
  created_at TIMESTAMPTZ NOT NULL DEFAULT now(),
  updated_at TIMESTAMPTZ NOT NULL DEFAULT now(),
  CONSTRAINT chk_status CHECK (
    (status = 'ACTIVE') OR (status = 'SOLD_OUT') OR (status = 'INACTIVE')
  )
);

CREATE TABLE product_metrics (
  id uuid PRIMARY KEY DEFAULT gen_random_uuid(),
  product_id uuid NOT NULL UNIQUE REFERENCES products(id) ON DELETE CASCADE,
  current_min_investment NUMERIC(15,2) NOT NULL,
  current_roi NUMERIC(6,2) NOT NULL,
  current_margin NUMERIC(6,2) NOT NULL,
  demand_index NUMERIC(6,2) NOT NULL,
  updated_at TIMESTAMPTZ NOT NULL DEFAULT now()
);

CREATE TRIGGER trg_products_updated_at
BEFORE UPDATE ON products
FOR EACH ROW
EXECUTE FUNCTION set_updated_at();

CREATE TRIGGER trg_product_metrics_updated_at
BEFORE UPDATE ON product_metrics
FOR EACH ROW
EXECUTE FUNCTION set_updated_at();

CREATE INDEX idx_products_status ON products(status);
CREATE INDEX idx_products_category ON products(category);
