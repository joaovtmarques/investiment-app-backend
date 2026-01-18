CREATE TABLE transactions (
  id uuid PRIMARY KEY DEFAULT gen_random_uuid(),
  user_id uuid NOT NULL REFERENCES users(id) ON DELETE RESTRICT,
  wallet_id uuid NOT NULL REFERENCES wallets(id) ON DELETE RESTRICT,
  investment_id uuid REFERENCES investments(id) ON DELETE RESTRICT,
  type VARCHAR(30) NOT NULL,
  amount NUMERIC(15,2) NOT NULL CHECK (amount > 0),
  balance_before NUMERIC(15,2) NOT NULL, 
  balance_after NUMERIC(15,2) NOT NULL CHECK (balance_after >= 0),
  created_at TIMESTAMPTZ NOT NULL DEFAULT now(),
  CONSTRAINT chk_type CHECK (
    type IN ('DEPOSIT','INVEST','RETURN','BONUS','WITHDRAW')
  ),
  CONSTRAINT chk_balance_after CHECK (
    balance_after = balance_before + amount 
    OR balance_after = balance_before - amount
  )
);

CREATE INDEX idx_transactions_user ON transactions(user_id);
CREATE INDEX idx_transactions_wallet ON transactions(wallet_id);
CREATE INDEX idx_transactions_investment ON transactions(investment_id);
CREATE INDEX idx_transactions_type ON transactions(type);