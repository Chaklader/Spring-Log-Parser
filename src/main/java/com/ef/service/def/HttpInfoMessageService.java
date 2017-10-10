package com.ef.service.def;

import com.ef.entity.HttpInfoMessage;
import com.ef.entity.IpAddress;

import java.util.List;

/**
 * Created by Chaklader on Oct, 2017
 */
public interface HttpInfoMessageService {

    List<HttpInfoMessage> findAll();

    void save(HttpInfoMessage httpInfoMessage);
}
