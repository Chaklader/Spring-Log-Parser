package com.ef.service;

import com.ef.entity.IpAddress;
import com.ef.repository.IpAddressRepository;
import com.ef.service.def.IpAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Chaklader on Oct, 2017
 */
@Service
public class IpAddressServiceImpl implements IpAddressService {

    @Autowired
    private IpAddressRepository ipAddressRepository;

    @Override
    @Transactional(readOnly = true)
    public List<IpAddress> findAll() {
        return (List<IpAddress>)ipAddressRepository.findAll();
    }

    @Override
    @Transactional
    public void save(IpAddress ipAddress) {
        ipAddressRepository.save(ipAddress);
    }

    @Override
    @Transactional
    public void saveAllIpAddressses(List<IpAddress> ipAddresses) {
        ipAddressRepository.save(ipAddresses);
    }
}
