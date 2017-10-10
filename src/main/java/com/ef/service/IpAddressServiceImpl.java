package com.ef.service;

import com.ef.entity.IpAddress;
import com.ef.repository.IpAddressRepository;
import com.ef.service.def.IpAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Chaklader on Oct, 2017
 */
@Service
public class IpAddressServiceImpl implements IpAddressService {

    @Autowired
    private IpAddressRepository ipAddressRepository;

    @Override
    public List<IpAddress> findAll() {
        return (List<IpAddress>)ipAddressRepository.findAll();
    }

    @Override
    public void save(IpAddress ipAddress) {
        ipAddressRepository.save(ipAddress);
    }
}
