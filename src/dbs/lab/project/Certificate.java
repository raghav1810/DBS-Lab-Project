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
@Table(name = "certificate", catalog = "course_payment", schema = "")
@NamedQueries({
    @NamedQuery(name = "Certificate.findAll", query = "SELECT c FROM Certificate c")
    , @NamedQuery(name = "Certificate.findByCertificateId", query = "SELECT c FROM Certificate c WHERE c.certificateId = :certificateId")
    , @NamedQuery(name = "Certificate.findByCourseId", query = "SELECT c FROM Certificate c WHERE c.courseId = :courseId")
    , @NamedQuery(name = "Certificate.findBySpecializationName", query = "SELECT c FROM Certificate c WHERE c.specializationName = :specializationName")
    , @NamedQuery(name = "Certificate.findByCustomerId", query = "SELECT c FROM Certificate c WHERE c.customerId = :customerId")
    , @NamedQuery(name = "Certificate.findByProfessorName", query = "SELECT c FROM Certificate c WHERE c.professorName = :professorName")})
public class Certificate implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "certificate_id")
    private Integer certificateId;
    @Basic(optional = false)
    @Column(name = "course_id")
    private int courseId;
    @Basic(optional = false)
    @Column(name = "specialization_name")
    private String specializationName;
    @Basic(optional = false)
    @Column(name = "customer_id")
    private int customerId;
    @Basic(optional = false)
    @Column(name = "professor_name")
    private String professorName;

    public Certificate() {
    }

    public Certificate(Integer certificateId) {
        this.certificateId = certificateId;
    }

    public Certificate(Integer certificateId, int courseId, String specializationName, int customerId, String professorName) {
        this.certificateId = certificateId;
        this.courseId = courseId;
        this.specializationName = specializationName;
        this.customerId = customerId;
        this.professorName = professorName;
    }

    public Integer getCertificateId() {
        return certificateId;
    }

    public void setCertificateId(Integer certificateId) {
        Integer oldCertificateId = this.certificateId;
        this.certificateId = certificateId;
        changeSupport.firePropertyChange("certificateId", oldCertificateId, certificateId);
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        int oldCourseId = this.courseId;
        this.courseId = courseId;
        changeSupport.firePropertyChange("courseId", oldCourseId, courseId);
    }

    public String getSpecializationName() {
        return specializationName;
    }

    public void setSpecializationName(String specializationName) {
        String oldSpecializationName = this.specializationName;
        this.specializationName = specializationName;
        changeSupport.firePropertyChange("specializationName", oldSpecializationName, specializationName);
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        int oldCustomerId = this.customerId;
        this.customerId = customerId;
        changeSupport.firePropertyChange("customerId", oldCustomerId, customerId);
    }

    public String getProfessorName() {
        return professorName;
    }

    public void setProfessorName(String professorName) {
        String oldProfessorName = this.professorName;
        this.professorName = professorName;
        changeSupport.firePropertyChange("professorName", oldProfessorName, professorName);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (certificateId != null ? certificateId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Certificate)) {
            return false;
        }
        Certificate other = (Certificate) object;
        if ((this.certificateId == null && other.certificateId != null) || (this.certificateId != null && !this.certificateId.equals(other.certificateId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dbs.lab.project.Certificate[ certificateId=" + certificateId + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
