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
//public class Message {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "S_ID_2")
//    private Long sId;
//
//    @Column(name = "STATUS_ID_2")
//    private Long statusId;
//
//    @NotEmpty
//    @Column(name = "STATUS_2")
//    private String status;
//
//    //  @OneToMany(mappedBy = "message")
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "message")
//    private List<IMAssociation> message;
//
//    public Message() {
//
//    }
//
//    public Message(Long statusId) {
//        this.statusId = statusId;
//    }
//
//    public Message(Long statusId, String status) {
//        this.statusId = statusId;
//        this.status = status;
//    }
//
//    public Message(Long statusId, String status, List<IMAssociation> iPAddresses2) {
//        this.statusId = statusId;
//        this.status = status;
//        this.message = iPAddresses2;
//    }
//
//    public Long getsId() {
//        return sId;
//    }
//
//    public void setsId(Long sId) {
//        this.sId = sId;
//    }
//
//    public Long getStatusId() {
//        return statusId;
//    }
//
//    public void setStatusId(Long statusId) {
//        this.statusId = statusId;
//    }
//
//    public String getStatus() {
//        return status;
//    }
//
//    public void setStatus(String status) {
//        this.status = status;
//    }
//
//    public List<IMAssociation> getMessage() {
//        return message;
//    }
//
//    public void setMessage(List<IMAssociation> message) {
//        this.message = message;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof Message)) return false;
//
//        Message message1 = (Message) o;
//
//        if (!getsId().equals(message1.getsId())) return false;
//        if (!getStatusId().equals(message1.getStatusId())) return false;
//        if (!getStatus().equals(message1.getStatus())) return false;
//        return getMessage() != null ? getMessage().equals(message1.getMessage()) : message1.getMessage() == null;
//    }
//
//    @Override
//    public int hashCode() {
//        int result = getsId().hashCode();
//        result = 31 * result + getStatusId().hashCode();
//        result = 31 * result + getStatus().hashCode();
//        result = 31 * result + (getMessage() != null ? getMessage().hashCode() : 0);
//        return result;
//    }
//
//    @Override
//    public String toString() {
//        return "Message{" +
//                "sId=" + sId +
//                ", statusId=" + statusId +
//                ", status='" + status + '\'' +
//                ", message=" + message +
//                '}';
//    }
//}
