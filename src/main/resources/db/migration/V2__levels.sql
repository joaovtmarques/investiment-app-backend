CREATE TABLE levels (
  id uuid PRIMARY KEY DEFAULT gen_random_uuid(),
  name VARCHAR(20) NOT NULL UNIQUE,
  min_xp BIGINT NOT NULL CHECK (min_xp >= 0),
  CONSTRAINT chk_name CHECK (
    name IN ('BRONZE', 'PRATA', 'OURO', 'PLATINA', 'DIAMANTE')
  )
);

INSERT INTO levels (name, min_xp) VALUES
('BRONZE', 0),
('PRATA', 100),
('OURO', 500),
('PLATINA', 1000),
('DIAMANTE', 2000);