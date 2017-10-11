package com.ef.service;

import com.ef.entity.HttpInfoMessage;
import com.ef.repository.HttpInfoMessageRepository;
import com.ef.service.def.HttpInfoMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Chaklader on Oct, 2017
 */
@Service
@Transactional
public class HttpInfoMessageServiceImpl implements HttpInfoMessageService {

    @Autowired
    private HttpInfoMessageRepository httpInfoMessageRepository;

    /*
    * Find all the HttpInfoMessages from the database
    * */
    @Override
    public List<HttpInfoMessage> findAll() {
        return (List<HttpInfoMessage>) httpInfoMessageRepository.findAll();
    }

    /*
    * save single entry of the  HttpInfoMessage to the database
    * */
    @Override
    public void save(HttpInfoMessage httpInfoMessage) {
        httpInfoMessageRepository.save(httpInfoMessage);
    }


    /*
    * save the list of the HttpInfoMessages to the database
    * */
    @Override
    public void saveAllHttpInfoMessages(List<HttpInfoMessage> httpInfoMessages) {
        httpInfoMessageRepository.save(httpInfoMessages);
    }
}
