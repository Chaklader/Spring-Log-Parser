package com.ef.service;

import com.ef.entity.HttpInfoMessage;
import com.ef.repository.HttpInfoMessageRepository;
import com.ef.service.def.HttpInfoMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Chaklader on Oct, 2017
 */
@Service
public class HttpInfoMessageServiceImpl implements HttpInfoMessageService {

    @Autowired
    private HttpInfoMessageRepository httpInfoMessageRepository;

    @Override
    public List<HttpInfoMessage> findAll() {
        return (List<HttpInfoMessage>) httpInfoMessageRepository.findAll();
    }

    @Override
    public void save(HttpInfoMessage httpInfoMessage) {
        httpInfoMessageRepository.save(httpInfoMessage);
    }
}
