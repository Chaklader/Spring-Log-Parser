package com.ef.repository;

import com.ef.entity.LogEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Chaklader on Oct, 2017
 */
public interface LogEntryRepository extends CrudRepository<LogEntity, Long>{

}
