package zw.co.mitech.mtutor.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name="SchoolClass")
public class SchoolClass  implements Serializable, Persistable {
	
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	private String joinCode;
	private long teacherId;
	private long academicLevelId;
	private long schoolId;
	private String className;
	private String classType;
	
	@ElementCollection(fetch=FetchType.EAGER)
	private List<Long> subjectIds;
	
	@Version
 	private long version;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getJoinCode() {
		return joinCode;
	}
	public void setJoinCode(String joinCode) {
		this.joinCode = joinCode;
	}
	public long getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(long teacherId) {
		this.teacherId = teacherId;
	}
	public long getAcademicLevelId() {
		return academicLevelId;
	}
	public void setAcademicLevelId(long academicLevelId) {
		this.academicLevelId = academicLevelId;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	@Override
	public void fetchParent(EntityManager em) {
		// TODO Auto-generated method stub
		
	}
	public String getClassType() {
		return classType;
	}
	public void setClassType(String classType) {
		this.classType = classType;
	}
	public List<Long> getSubjectIds() {
		return subjectIds;
	}
	public void setSubjectIds(List<Long> subjectIds) {
		this.subjectIds = subjectIds;
	}
	public long getVersion() {
		return version;
	}
	public void setVersion(long version) {
		this.version = version;
	}
	public long getSchoolId() {
		return schoolId;
	}
	public void setSchoolId(long schoolId) {
		this.schoolId = schoolId;
	}
	
    
}
