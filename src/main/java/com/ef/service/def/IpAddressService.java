package com.ef.service.def;

import com.ef.entity.IpAddress;

import java.util.List;

/**
 * Created by Chaklader on Oct, 2017
 */
public interface IpAddressService {

    List<IpAddress> findAll();

    void save(IpAddress ipAddress);
}
