package com.ef.entity;

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
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "STATUS_ID")
    private Long statusId;

    @Column(name = "STATUS")
    @NotEmpty
    private String status;

    public HttpInfoMessage(String status) {
        this.status = status;
    }

    public HttpInfoMessage() {

    }

    public HttpInfoMessage(Long statusId, String status, List<IpAddress> ipAddresses) {
        this.statusId = statusId;
        this.status = status;
        this.ipAddresses = ipAddresses;
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

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "httpInfoMessages")
    private List<IpAddress> ipAddresses = new ArrayList<>();

    public List<IpAddress> getIpAddresses() {
        return ipAddresses;
    }

    public void setIpAddresses(List<IpAddress> ipAddresses) {
        this.ipAddresses = ipAddresses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HttpInfoMessage)) return false;

        HttpInfoMessage httpInfoMessage1 = (HttpInfoMessage) o;

        if (!getStatusId().equals(httpInfoMessage1.getStatusId())) return false;
        return getStatus().equals(httpInfoMessage1.getStatus());
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
