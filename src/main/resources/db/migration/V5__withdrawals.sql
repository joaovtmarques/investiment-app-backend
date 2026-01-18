CREATE TABLE withdrawals (
  id uuid PRIMARY KEY DEFAULT gen_random_uuid(),
  user_id uuid NOT NULL REFERENCES users(id) ON DELETE RESTRICT,
  wallet_id uuid NOT NULL REFERENCES wallets(id) ON DELETE RESTRICT,
  amount NUMERIC(15,2) NOT NULL CHECK (amount > 0),
  method VARCHAR(20) NOT NULL CHECK (method IN ('PIX', 'TED')),
  status VARCHAR(20) NOT NULL DEFAULT 'PENDING',
  requested_at TIMESTAMPTZ NOT NULL DEFAULT now(),
  processed_at TIMESTAMPTZ NULL,
  CONSTRAINT chk_status CHECK (
    status IN ('PENDING', 'APPROVED', 'REJECTED', 'PAID')
  ),
  CONSTRAINT chk_processed_after_requested CHECK (
    processed_at IS NULL OR processed_at >= requested_at
  )
);

CREATE INDEX idx_withdrawals_user ON withdrawals(user_id);
CREATE INDEX idx_withdrawals_status ON withdrawals(status);
CREATE INDEX idx_withdrawals_method ON withdrawals(method);