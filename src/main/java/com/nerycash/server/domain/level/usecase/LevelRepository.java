package com.nerycash.server.domain.level.usecase;

import com.nerycash.server.domain.level.enums.LevelName;
import com.nerycash.server.domain.level.model.Level;

public interface LevelRepository {

  Level save(Level level);

  Level findByName(LevelName name);

}
