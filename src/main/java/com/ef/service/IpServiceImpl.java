//package com.ef.service;
//
//import com.ef.entity.Ip;
//import com.ef.repository.IpRepository;
//import com.ef.service.def.IpService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
///**
// * Created by Chaklader on Oct, 2017
// */
//@Service
//public class IpServiceImpl implements IpService{
//
//    @Autowired
//    private IpRepository ipRepository;
//
//    @Override
//    @Transactional(rollbackFor = RuntimeException.class)
//    public void saveAllIps(List<Ip> ipList) {
//        ipRepository.save(ipList);
//    }
//}
