/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.mitech.mtutor.session;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import zw.co.mitech.mtutor.entities.Student;

/**
 *
 * @author douglas
 */
@Repository("studentDao")
@Transactional
public class StudentFacade extends AbstractFacade<Student> {
   
	@PersistenceContext
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public StudentFacade() {
        super(Student.class);
    }
    
    
    public List<Student> findStudentByMobileNumber(String mobileNumber ){
    
    	List<Student>  students = new ArrayList<Student>();
		try {
			String ql = "SELECT s FROM  Student s WHERE s.mobileNumber =:mobileNumber";
  
			 Query q = em.createQuery(ql);
			 q.setParameter("mobileNumber", mobileNumber);
			students = (List<Student>) q.getResultList();
			
			
		} catch (Exception e) {
			
			return students;
		}
		
		return students;
    }
    
    public List<Student> getStudentsByPoints(){
    	List<Student>  students = new ArrayList<Student>();
    	try {
			String ql = "SELECT s FROM  Student s ORDER BY s.points DESC";
  
			 Query q = em.createQuery(ql);
			 //q.setMaxResults(5);
			students = (List<Student>) q.getResultList();
					
			} catch (Exception e) {
			
			e.printStackTrace();
			return students;
		}
    	
    	return students;
    }
    
    public List<Student> getStudentsByGradeAndPoints(Student student){
    	List<Student>  students = new ArrayList<Student>();
    	try {
			String ql = "SELECT s FROM  Student s WHERE s.academicLevelId =:academicLevelId ORDER BY s.points DESC, s.firstName ASC";
			EntityManager em = getEntityManager();
			 Query q = em.createQuery(ql);
			 q.setParameter("academicLevelId", student.getAcademicLevelId());
			 q.setMaxResults(5);
			students = (List<Student>) q.getResultList();
			
				} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return students;
		}
    	
    	return students;
    }
    
    public List<Student> getStudentsByGradeAndPoints(long grade){
    	List<Student>  students = new ArrayList<Student>();
    	try {
			String ql = "SELECT s FROM  Student s WHERE s.academicLevelId =:academicLevelId ORDER BY s.points DESC, s.firstName ASC";
  
			 Query q = em.createQuery(ql);
			 q.setParameter("academicLevelId", grade);
			 q.setMaxResults(5);
			students = (List<Student>) q.getResultList();
			 return new ArrayList<Student>(students);
			
		//	System.out.println(students.size()+"********** select Student by mobile = "+students);
		} catch (Exception e) {
			
			return students;
		}
    	
    	//return students;
    }

	public Student findStudentById(long id) {
		Student student = null;
		try {
			String ql = "SELECT s FROM  Student s WHERE s.id =:id";
  
			 Query q = em.createQuery(ql);
			 q.setParameter("id", id);
			student = (Student) q.getSingleResult();
			student.fetchParent(em);
		} catch (NoResultException e) {
			
			e.printStackTrace();
			return null;
		}
		
		return student;
	}
    public List<Student> getStudentByName(String name){
    	List<Student> students  = new ArrayList<Student>();
    	name =name.toLowerCase();
    	try {
    	String ql = "SELECT s FROM  Student s WHERE s.firstName =:firstName ORDER BY s.nameCount DESC";
    	 Query q = em.createQuery(ql);
		 q.setParameter("firstName", name);
		
		 students = (List<Student>) q.getResultList();
    	} catch (NoResultException e) 
		 {
				e.printStackTrace();
				return students;
			}
			
			return students;
		 
    }
    
}

    
