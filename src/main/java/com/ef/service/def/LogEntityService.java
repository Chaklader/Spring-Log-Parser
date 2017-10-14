package com.ef.service.def;

import com.ef.entity.LogEntity;

import java.util.List;

/**
 * Created by Chaklader on Oct, 2017
 */
public interface LogEntityService {

    List<LogEntity> findAll();

    void save(LogEntity logEntity);

    void saveAllLogEntities(List<LogEntity> logEntities);
}
