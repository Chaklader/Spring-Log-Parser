package com.ef.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Chaklader on Oct, 2017
 */
@Entity
@Table(name = "ALL_LOG_ENTRIES")
public class LogEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "IP_ADDRESS")
    private String Ip;

    @Column(name = "START_TIME")
    private Date time;

    @Column(name = "STATUS_CODE")
    private Integer code;

    public LogEntity() {
    }

    public LogEntity(String ip, Date time, Integer code) {
        Ip = ip;
        this.time = time;
        this.code = code;
    }

    public String getIp() {
        return Ip;
    }

    public void setIp(String ip) {
        Ip = ip;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LogEntity)) return false;

        LogEntity logEntity = (LogEntity) o;

        if (getCode() != logEntity.getCode()) return false;
        if (!getIp().equals(logEntity.getIp())) return false;
        return getTime().equals(logEntity.getTime());
    }

    @Override
    public int hashCode() {
        int result = getIp().hashCode();
        result = 31 * result + getTime().hashCode();
        result = 31 * result + getCode();
        return result;
    }

    @Override
    public String toString() {
        return "Log{" +
                "Ip='" + Ip + '\'' +
                ", time=" + time +
                ", code=" + code +
                '}';
    }
}
