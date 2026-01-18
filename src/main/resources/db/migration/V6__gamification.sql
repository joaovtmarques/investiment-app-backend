CREATE TABLE levels (
  id uuid PRIMARY KEY DEFAULT gen_random_uuid(),
  name VARCHAR(20) NOT NULL UNIQUE,
  min_xp BIGINT NOT NULL CHECK (min_xp >= 0),
  CONSTRAINT chk_name CHECK (
    name IN ('BRONZE', 'PRATA', 'OURO', 'PLATINA', 'DIAMANTE')
  )
);

CREATE TABLE user_achievements (
  id uuid PRIMARY KEY DEFAULT gen_random_uuid(),
  user_id uuid NOT NULL REFERENCES users(id) ON DELETE RESTRICT,
  code VARCHAR(50) NOT NULL,
  title VARCHAR(255),
  earned_at TIMESTAMPTZ NOT NULL DEFAULT now()
);

CREATE INDEX idx_gamification_user ON user_achievements(user_id);
CREATE INDEX idx_gamification_code ON user_achievements(code);

CREATE TABLE daily_missions (
  id uuid PRIMARY KEY DEFAULT gen_random_uuid(),
  title VARCHAR(100) NOT NULL,
  reward_xp BIGINT NOT NULL DEFAULT 0 CHECK (reward_xp >= 0),
  reward_bonus NUMERIC(15,2) NOT NULL DEFAULT 0,
  active BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE user_missions (
  id uuid PRIMARY KEY DEFAULT gen_random_uuid(),
  user_id uuid NOT NULL REFERENCES users(id) ON DELETE RESTRICT,
  mission_id uuid NOT NULL REFERENCES daily_missions(id) ON DELETE RESTRICT,
  completed BOOLEAN NOT NULL DEFAULT false,
  completed_at TIMESTAMPTZ,
  UNIQUE (user_id, mission_id)
);

