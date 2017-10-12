package com.ef.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chaklader on Oct, 2017
 */
@Entity
public class IpAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IP_ADDR_ID")
    private Long id;

    @Column(name = "IP_ADDRESS")
    @NotEmpty
    private String address;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "IP_ADDR_STATUS",
            joinColumns = @JoinColumn(name = "IP_ADDRESS_ID", referencedColumnName = "IP_ADDR_ID"),
            inverseJoinColumns = @JoinColumn(name = "STATUS_ID", referencedColumnName = "S_ID"))
    private List<HttpInfoMessage> httpInfoMessages = new ArrayList<>();

    public IpAddress() {

    }

    public IpAddress(String address) {
        this.address = address;
    }

    public IpAddress(String address, List<HttpInfoMessage> httpInfoMessages) {
        this.address = address;
        this.httpInfoMessages = httpInfoMessages;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<HttpInfoMessage> getHttpInfoMessages() {
        return httpInfoMessages;
    }

    public void setHttpInfoMessages(List<HttpInfoMessage> httpInfoMessages) {
        this.httpInfoMessages = httpInfoMessages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IpAddress)) return false;

        IpAddress ipAddress = (IpAddress) o;

        if (!getId().equals(ipAddress.getId())) return false;
        return getAddress().equals(ipAddress.getAddress());
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getAddress().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "IpAddress{" +
                "id=" + id +
                ", address='" + address + '\'' +
                '}';
    }
}
