CREATE TABLE users
(
  id uuid PRIMARY KEY DEFAULT gen_random_uuid(),
  name VARCHAR(255) NOT NULL,
  email CITEXT UNIQUE,
  phone VARCHAR(20) UNIQUE,
  password_hash VARCHAR(255),
  provider VARCHAR(30) NOT NULL DEFAULT 'LOCAL',
  level_id uuid REFERENCES levels(id) ON DELETE RESTRICT,
  xp BIGINT NOT NULL DEFAULT 0,
  created_at TIMESTAMPTZ NOT NULL DEFAULT now(),
  updated_at TIMESTAMPTZ NOT NULL DEFAULT now(),
  is_active BOOLEAN NOT NULL DEFAULT TRUE,
  CONSTRAINT chk_password_by_provider CHECK (
    (provider = 'LOCAL' AND password_hash IS NOT NULL)
    OR (provider <> 'LOCAL' AND password_hash IS NULL)
  )
);

CREATE TABLE wallets (
  id uuid PRIMARY KEY DEFAULT gen_random_uuid(),
  user_id uuid NOT NULL UNIQUE REFERENCES users(id) ON DELETE CASCADE,
  available_balance NUMERIC(15,2) NOT NULL DEFAULT 0 CHECK (available_balance >= 0),
  invested_balance NUMERIC(15,2) NOT NULL DEFAULT 0 CHECK (invested_balance >= 0),
  receivable_balance NUMERIC(15,2) NOT NULL DEFAULT 0 CHECK (receivable_balance >= 0),
  updated_at TIMESTAMPTZ NOT NULL DEFAULT now()
);

CREATE OR REPLACE FUNCTION set_default_user_level()
RETURNS TRIGGER AS $$
BEGIN
  IF NEW.level_id IS NULL THEN
    SELECT id INTO NEW.level_id FROM levels WHERE name = 'BRONZE';
  END IF;
  RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_users_updated_at
BEFORE UPDATE ON users
FOR EACH ROW
EXECUTE FUNCTION set_updated_at();

CREATE TRIGGER trg_wallets_updated_at
BEFORE UPDATE ON wallets
FOR EACH ROW
EXECUTE FUNCTION set_updated_at();

CREATE TRIGGER trg_users_default_level
BEFORE INSERT ON users
FOR EACH ROW
EXECUTE FUNCTION set_default_user_level();

CREATE INDEX idx_users_is_active ON users(is_active);