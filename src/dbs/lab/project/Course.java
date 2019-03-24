/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbs.lab.project;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author raghav
 */
@Entity
@Table(name = "course", catalog = "course_payment", schema = "")
@NamedQueries({
    @NamedQuery(name = "Course.findAll", query = "SELECT c FROM Course c")
    , @NamedQuery(name = "Course.findByCourseId", query = "SELECT c FROM Course c WHERE c.courseId = :courseId")
    , @NamedQuery(name = "Course.findByCourseName", query = "SELECT c FROM Course c WHERE c.courseName = :courseName")
    , @NamedQuery(name = "Course.findByDomainId", query = "SELECT c FROM Course c WHERE c.domainId = :domainId")
    , @NamedQuery(name = "Course.findByDomainName", query = "SELECT c FROM Course c WHERE c.domainName = :domainName")
    , @NamedQuery(name = "Course.findBySpecializationName", query = "SELECT c FROM Course c WHERE c.specializationName = :specializationName")
    , @NamedQuery(name = "Course.findByCost", query = "SELECT c FROM Course c WHERE c.cost = :cost")
    , @NamedQuery(name = "Course.findByDuration", query = "SELECT c FROM Course c WHERE c.duration = :duration")})
public class Course implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "course_id")
    private Integer courseId;
    @Basic(optional = false)
    @Column(name = "course_name")
    private String courseName;
    @Basic(optional = false)
    @Column(name = "domain_id")
    private int domainId;
    @Basic(optional = false)
    @Column(name = "domain_name")
    private String domainName;
    @Basic(optional = false)
    @Column(name = "specialization_name")
    private String specializationName;
    @Basic(optional = false)
    @Column(name = "cost")
    private int cost;
    @Basic(optional = false)
    @Column(name = "duration")
    private int duration;

    public Course() {
    }

    public Course(Integer courseId) {
        this.courseId = courseId;
    }

    public Course(Integer courseId, String courseName, int domainId, String domainName, String specializationName, int cost, int duration) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.domainId = domainId;
        this.domainName = domainName;
        this.specializationName = specializationName;
        this.cost = cost;
        this.duration = duration;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        Integer oldCourseId = this.courseId;
        this.courseId = courseId;
        changeSupport.firePropertyChange("courseId", oldCourseId, courseId);
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        String oldCourseName = this.courseName;
        this.courseName = courseName;
        changeSupport.firePropertyChange("courseName", oldCourseName, courseName);
    }

    public int getDomainId() {
        return domainId;
    }

    public void setDomainId(int domainId) {
        int oldDomainId = this.domainId;
        this.domainId = domainId;
        changeSupport.firePropertyChange("domainId", oldDomainId, domainId);
    }

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        String oldDomainName = this.domainName;
        this.domainName = domainName;
        changeSupport.firePropertyChange("domainName", oldDomainName, domainName);
    }

    public String getSpecializationName() {
        return specializationName;
    }

    public void setSpecializationName(String specializationName) {
        String oldSpecializationName = this.specializationName;
        this.specializationName = specializationName;
        changeSupport.firePropertyChange("specializationName", oldSpecializationName, specializationName);
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        int oldCost = this.cost;
        this.cost = cost;
        changeSupport.firePropertyChange("cost", oldCost, cost);
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        int oldDuration = this.duration;
        this.duration = duration;
        changeSupport.firePropertyChange("duration", oldDuration, duration);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (courseId != null ? courseId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Course)) {
            return false;
        }
        Course other = (Course) object;
        if ((this.courseId == null && other.courseId != null) || (this.courseId != null && !this.courseId.equals(other.courseId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dbs.lab.project.Course[ courseId=" + courseId + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
