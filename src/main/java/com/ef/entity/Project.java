//package com.ef.entity;
//
//import javax.persistence.Entity;
//import javax.persistence.Id;
//import javax.persistence.OneToMany;
//import java.util.ArrayList;
//import java.util.List;
//
//@Entity
//public class Project {
//
//    @Id
//    private long id;
//    @OneToMany(mappedBy = "project")
//    private List<ProjectAssociation> employees;
//    // Add an employee to the project.
//    // Create an association object for the relationship and set its data.
//
//    public void addEmployee(Employee employee, boolean teamLead) {
//
//        ProjectAssociation association = new ProjectAssociation();
//
//        association.setEmployee(employee);
//        association.setProject(this);
//        association.setEmployeeId(employee.getId());
//        association.setProjectId(this.getId());
//        association.setIsTeamLead(teamLead);
//
//        if (this.employees == null) {
//            this.employees = new ArrayList<>();
//        }
//
//        this.employees.add(association);
//
//        // Also add the association object to the employee.
//        employee.getProjects().add(association);
//    }
//
//
//    public Project() {
//    }
//
//    public Project(long id) {
//        this.id = id;
//    }
//
//    public Project(long id, List<ProjectAssociation> employees) {
//        this.id = id;
//        this.employees = employees;
//    }
//
//
//    public long getId() {
//        return id;
//    }
//
//    public void setId(long id) {
//        this.id = id;
//    }
//
//    public List<ProjectAssociation> getEmployees() {
//        return employees;
//    }
//
//    public void setEmployees(List<ProjectAssociation> employees) {
//        this.employees = employees;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof Project)) return false;
//
//        Project project = (Project) o;
//
//        if (getId() != project.getId()) return false;
//        return getEmployees().equals(project.getEmployees());
//    }
//
//    @Override
//    public int hashCode() {
//        int result = (int) (getId() ^ (getId() >>> 32));
//        result = 31 * result + getEmployees().hashCode();
//        return result;
//    }
//}