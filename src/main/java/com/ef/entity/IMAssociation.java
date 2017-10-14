//package com.ef.entity;
//
//import org.springframework.stereotype.Service;
//
//import javax.persistence.*;
//import java.io.Serializable;
//
///**
// * Created by Chaklader on Oct, 2017
// */
//@Entity
//@Table(name = "IP_HTTP_MESSAGE")
////@IdClass(AssociationId.class)
//public class IMAssociation implements Serializable {
//
//
////    @Id
////    @GeneratedValue(strategy = GenerationType.IDENTITY)
////    private Long id;
//
//
//    @Id
//    @Column(name = "IP_ADDRESS_ID")
//    private Long ipAddressId;
//
//    @Id
//    @Column(name = "HTTP_MESSAGE_ID")
//    private Long httpMessageId;
//
//    @ManyToOne
//    @PrimaryKeyJoinColumn(name = "IP_ADDRESS_ID", referencedColumnName = "IP_ADDR_ID_2")
////    @JoinColumn(name = "ipAddressId", updatable = false, insertable = false, referencedColumnName = "IP_ADDR_ID_2")
//    private Ip ip;
//
//    @ManyToOne
//    @PrimaryKeyJoinColumn(name = "HTTP_MESSAGE_ID", referencedColumnName = "S_ID_2")
////    @JoinColumn(name = "httpMessageId", updatable = false, insertable = false, referencedColumnName = "S_ID_2")
//    private Message message;
//
//    //    @ManyToOne
//    @JoinColumn(name = "IP_ADDRESS_2", updatable = false, insertable = false, referencedColumnName = "IP_ADDRESS_2")
//    private String address;
//
//    @JoinColumn(name = "STATUS_CODE", updatable = false, insertable = false, referencedColumnName = "STATUS_ID_2")
//    private Long code;
//
//    @JoinColumn(name = "STATUS_MESSAGE", updatable = false, insertable = false, referencedColumnName = "STATUS_2")
//    private String codeMessage;
//
//    public IMAssociation() {
//    }
//
//    public IMAssociation(Long code, String codeMessage) {
//        this.code = code;
//        this.codeMessage = codeMessage;
//    }
//
//    public IMAssociation(String address, Long code, String codeMessage) {
//        this.address = address;
//        this.code = code;
//        this.codeMessage = codeMessage;
//    }
//
////    public IMAssociation(Long ipAddressId, Long httpMessageId, Ip ip, Message message, String address, Long code, String codeMessage) {
////        this.ipAddressId = ipAddressId;
////        this.httpMessageId = httpMessageId;
////        this.ip = ip;
////        this.message = message;
////        this.address = address;
////        this.code = code;
////        this.codeMessage = codeMessage;
////    }
//
//
////    public Long getIpAddressId() {
////        return ipAddressId;
////    }
////
////    public void setIpAddressId(Long ipAddressId) {
////        this.ipAddressId = ipAddressId;
////    }
////
////    public Long getHttpMessageId() {
////        return httpMessageId;
////    }
////
////    public void setHttpMessageId(Long httpMessageId) {
////        this.httpMessageId = httpMessageId;
////    }
//
//    public Ip getIp() {
//        return ip;
//    }
//
//    public void setIp(Ip ip) {
//        this.ip = ip;
//    }
//
//    public Message getMessage() {
//        return message;
//    }
//
//    public void setMessage(Message message) {
//        this.message = message;
//    }
//
//    public String getAddress() {
//        return address;
//    }
//
//    public void setAddress(String address) {
//        this.address = address;
//    }
//
//    public Long getCode() {
//        return code;
//    }
//
//    public void setCode(Long code) {
//        this.code = code;
//    }
//
//    public String getCodeMessage() {
//        return codeMessage;
//    }
//
//    public void setCodeMessage(String codeMessage) {
//        this.codeMessage = codeMessage;
//    }
//
////    @Override
////    public boolean equals(Object o) {
////        if (this == o) return true;
////        if (!(o instanceof IMAssociation)) return false;
////
////        IMAssociation that = (IMAssociation) o;
////
////        if (!getIpAddressId().equals(that.getIpAddressId())) return false;
////        if (!getHttpMessageId().equals(that.getHttpMessageId())) return false;
////        if (!getIp().equals(that.getIp())) return false;
////        if (!getMessage().equals(that.getMessage())) return false;
////        if (!getAddress().equals(that.getAddress())) return false;
////        if (!getCode().equals(that.getCode())) return false;
////        return getCodeMessage().equals(that.getCodeMessage());
////    }
////
////    @Override
////    public String toString() {
////        return "IMAssociation{" +
////                ", ipAddressId=" + ipAddressId +
////                ", httpMessageId=" + httpMessageId +
////                ", ip=" + ip +
////                ", message=" + message +
////                ", address='" + address + '\'' +
////                ", code=" + code +
////                ", codeMessage='" + codeMessage + '\'' +
////                '}';
////    }
//}
