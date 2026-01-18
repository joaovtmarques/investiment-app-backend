CREATE TABLE investments (
  id uuid PRIMARY KEY DEFAULT gen_random_uuid(),
  user_id uuid NOT NULL REFERENCES users(id) ON DELETE RESTRICT,
  product_id uuid NOT NULL REFERENCES products(id) ON DELETE RESTRICT,
  amount NUMERIC(15,2) NOT NULL CHECK (amount > 0),
  expected_return NUMERIC(15,2) NOT NULL CHECK (expected_return >= 0),
  bonus_return NUMERIC(15,2) NOT NULL DEFAULT 0 CHECK (bonus_return >= 0),
  status VARCHAR(20) NOT NULL DEFAULT 'ACTIVE',
  start_date TIMESTAMPTZ NOT NULL DEFAULT now(),
  end_date TIMESTAMPTZ NOT NULL CHECK (end_date > start_date),
  created_at TIMESTAMPTZ NOT NULL DEFAULT now() ,
  updated_at TIMESTAMPTZ NOT NULL DEFAULT now(),
  CONSTRAINT chk_status CHECK (
    (status = 'ACTIVE') OR (status = 'COMPLETED') OR (status = 'CANCELLED')
  )
);

CREATE TRIGGER trg_investments_updated_at
BEFORE UPDATE ON investments
FOR EACH ROW
EXECUTE FUNCTION set_updated_at();

CREATE INDEX idx_investments_user ON investments(user_id);
CREATE INDEX idx_investments_product ON investments(product_id);
CREATE INDEX idx_investments_status ON investments(status);
CREATE INDEX idx_investments_user_status ON investments(user_id, status);
