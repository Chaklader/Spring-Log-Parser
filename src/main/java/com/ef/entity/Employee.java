//package com.ef.entity;
//
//import javax.persistence.Entity;
//import javax.persistence.Id;
//import javax.persistence.OneToMany;
//import java.util.List;
//
//@Entity
//public class Employee {
//
//    @Id
//    private long id;
//
//    @OneToMany(mappedBy = "employee")
//    private List<ProjectAssociation> projects;
//
//    public Employee() {
//    }
//
//    public Employee(long id) {
//        this.id = id;
//    }
//
//    public Employee(long id, List<ProjectAssociation> projects) {
//        this.id = id;
//        this.projects = projects;
//    }
//
//    public long getId() {
//        return id;
//    }
//
//    public void setId(long id) {
//        this.id = id;
//    }
//
//    public List<ProjectAssociation> getProjects() {
//        return projects;
//    }
//
//    public void setProjects(List<ProjectAssociation> projects) {
//        this.projects = projects;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof Employee)) return false;
//
//        Employee employee = (Employee) o;
//
//        if (getId() != employee.getId()) return false;
//        return getProjects().equals(employee.getProjects());
//    }
//
//    @Override
//    public int hashCode() {
//        int result = (int) (getId() ^ (getId() >>> 32));
//        result = 31 * result + getProjects().hashCode();
//        return result;
//    }
//}