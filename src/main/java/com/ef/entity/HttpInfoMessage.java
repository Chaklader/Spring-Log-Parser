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
public class HttpInfoMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "S_ID")
    private Long sId;

    @Column(name = "STATUS_ID")
    private Long statusId;

    @NotEmpty
    @Column(name = "STATUS")
    private String status;

    public HttpInfoMessage() {

    }

    public HttpInfoMessage(String status) {
        this.status = status;
    }

    public HttpInfoMessage(Long statusId, String status) {
        this.statusId = statusId;
        this.status = status;
    }

    public HttpInfoMessage(Long statusId, String status, List<IpAddress> ipAddresses) {
        this.statusId = statusId;
        this.status = status;
        this.ipAddress = ipAddresses;
    }

    public Long getsId() {
        return sId;
    }

    public void setsId(Long sId) {
        this.sId = sId;
    }

    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "httpInfoMessages")
    private List<IpAddress> ipAddress = new ArrayList<>();

    public List<IpAddress> getIpAddress2s() {
        return ipAddress;
    }

    public void setIpAddress2s(List<IpAddress> ipAddresses) {
        this.ipAddress = ipAddresses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HttpInfoMessage)) return false;

        HttpInfoMessage httpInfoMessage21 = (HttpInfoMessage) o;

        if (!getStatusId().equals(httpInfoMessage21.getStatusId())) return false;
        return getStatus().equals(httpInfoMessage21.getStatus());
    }

    @Override
    public int hashCode() {
        int result = getStatusId().hashCode();
        result = 31 * result + getStatus().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Status{" +
                "statusId=" + statusId +
                ", status='" + status + '\'' +
                '}';
    }
}
