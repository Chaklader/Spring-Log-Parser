//package com.ef.entity;
//
//import javax.persistence.*;
//
//@Entity
//@Table(name = "PROJ_EMP")
//@IdClass(ProjectAssociationId.class)
//public class ProjectAssociation {
//    @Id
//    private long employeeId;
//    @Id
//    private long projectId;
//    @Column(name = "IS_PROJECT_LEAD")
//    private boolean isProjectLead;
//    @ManyToOne
//    @PrimaryKeyJoinColumn(name = "EMPLOYEEID", referencedColumnName = "ID")
//  /* if this JPA model doesn't create a table for the "PROJ_EMP" entity,
//  *  please comment out the @PrimaryKeyJoinColumn, and use the ff:
//  *  @JoinColumn(name = "employeeId", updatable = false, insertable = false)
//  * or @JoinColumn(name = "employeeId", updatable = false, insertable = false, referencedColumnName = "id")
//  */
//    private Employee employee;
//    @ManyToOne
//    @PrimaryKeyJoinColumn(name = "PROJECTID", referencedColumnName = "ID")
//  /* the same goes here:
//  *  if this JPA model doesn't create a table for the "PROJ_EMP" entity,
//  *  please comment out the @PrimaryKeyJoinColumn, and use the ff:
//  *  @JoinColumn(name = "projectId", updatable = false, insertable = false)
//  * or @JoinColumn(name = "projectId", updatable = false, insertable = false, referencedColumnName = "id")
//  */
//    private Project project;
//
//
//    public ProjectAssociation() {
//    }
//
//    public ProjectAssociation(long employeeId, long projectId, boolean isProjectLead, Employee employee, Project project) {
//        this.employeeId = employeeId;
//        this.projectId = projectId;
//        this.isProjectLead = isProjectLead;
//        this.employee = employee;
//        this.project = project;
//    }
//
//    public long getEmployeeId() {
//        return employeeId;
//    }
//
//    public void setEmployeeId(long employeeId) {
//        this.employeeId = employeeId;
//    }
//
//    public long getProjectId() {
//        return projectId;
//    }
//
//    public void setProjectId(long projectId) {
//        this.projectId = projectId;
//    }
//
//    public boolean isProjectLead() {
//        return isProjectLead;
//    }
//
//    public void setProjectLead(boolean projectLead) {
//        isProjectLead = projectLead;
//    }
//
//    public Employee getEmployee() {
//        return employee;
//    }
//
//    public void setEmployee(Employee employee) {
//        this.employee = employee;
//    }
//
//    public Project getProject() {
//        return project;
//    }
//
//    public void setProject(Project project) {
//        this.project = project;
//    }
//
//    public void setIsTeamLead(boolean bol){
//
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof ProjectAssociation)) return false;
//
//        ProjectAssociation that = (ProjectAssociation) o;
//
//        if (getEmployeeId() != that.getEmployeeId()) return false;
//        if (getProjectId() != that.getProjectId()) return false;
//        if (isProjectLead() != that.isProjectLead()) return false;
//        if (!getEmployee().equals(that.getEmployee())) return false;
//        return getProject().equals(that.getProject());
//    }
//
//    @Override
//    public int hashCode() {
//        int result = (int) (getEmployeeId() ^ (getEmployeeId() >>> 32));
//        result = 31 * result + (int) (getProjectId() ^ (getProjectId() >>> 32));
//        result = 31 * result + (isProjectLead() ? 1 : 0);
//        result = 31 * result + getEmployee().hashCode();
//        result = 31 * result + getProject().hashCode();
//        return result;
//    }
//}