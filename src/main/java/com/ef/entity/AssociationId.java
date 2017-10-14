//package com.ef.entity;
//
//import javax.persistence.EmbeddedId;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import java.io.Serializable;
//
///**
// * Created by Chaklader on Oct, 2017
// */
//public class AssociationId implements Serializable{
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @EmbeddedId
//    private Long ipAddressId;
//
//    @EmbeddedId
//    private Long httpMessageId;
//}
