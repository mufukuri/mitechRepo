package zw.co.mitech.mtutor.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name="Counter")
public class Counter {
	
	@Id  
    @GeneratedValue(strategy = GenerationType.IDENTITY)  
    private Long id;  
  
    private long count;  
    private long refId;  
    private int type;  
    private String entityClass;
    @Version
    private long version;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}
	public long getRefId() {
		return refId;
	}
	public void setRefId(long refId) {
		this.refId = refId;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getEntityClass() {
		return entityClass;
	}
	public void setEntityClass(String entityClass) {
		this.entityClass = entityClass;
	}
	public long getVersion() {
		return version;
	}
	public void setVersion(long version) {
		this.version = version;
	}  
    
    

}
