//package com.ef.entity;
//
//import org.hibernate.validator.constraints.NotEmpty;
//
//import javax.persistence.*;
//import java.util.List;
//
///**
// * Created by Chaklader on Oct, 2017
// */
//@Entity
//public class Ip {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "IP_ADDR_ID_2")
//    private Long id;
//
//    @Column(name = "IP_ADDRESS_2")
//    @NotEmpty
//    private String address;
//
//    //    @OneToMany(mappedBy = "ip")
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ip")
//    private List<IMAssociation> httpInfoMessages2;
//
//    public Ip() {
//
//    }
//
//    public Ip(String address) {
//        this.address = address;
//    }
//
//    public Ip(String address, List<IMAssociation> httpInfoMessages2) {
//        this.address = address;
//        this.httpInfoMessages2 = httpInfoMessages2;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
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
//    public List<IMAssociation> getHttpInfoMessages2() {
//        return httpInfoMessages2;
//    }
//
//    public void setHttpInfoMessages2(List<IMAssociation> httpInfoMessages) {
//        this.httpInfoMessages2 = httpInfoMessages;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof Ip)) return false;
//
//        Ip ip = (Ip) o;
//
//        if (!getId().equals(ip.getId())) return false;
//        if (!getAddress().equals(ip.getAddress())) return false;
//        return getHttpInfoMessages2() != null ? getHttpInfoMessages2().equals(ip.getHttpInfoMessages2()) : ip.getHttpInfoMessages2() == null;
//    }
//
//    @Override
//    public int hashCode() {
//        int result = getId().hashCode();
//        result = 31 * result + getAddress().hashCode();
//        result = 31 * result + (getHttpInfoMessages2() != null ? getHttpInfoMessages2().hashCode() : 0);
//        return result;
//    }
//
//    @Override
//    public String toString() {
//        return "Ip{" +
//                "id=" + id +
//                ", address='" + address + '\'' +
//                ", httpInfoMessages2=" + httpInfoMessages2 +
//                '}';
//    }
//}
