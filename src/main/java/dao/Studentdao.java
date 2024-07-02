package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import dto.Student;
import dto.Trainer;

public class Studentdao {
	public EntityManager getEntityManager()
	{
		return Persistence.createEntityManagerFactory("mockonetomany").createEntityManager();
	}
	public void saveStudent(int trainerId,Student student)
	{
		EntityManager entityManager=getEntityManager();
		Trainer trainer=entityManager.find(Trainer.class,trainerId);
		if(trainer!=null)
		{
			EntityTransaction entityTransaction=entityManager.getTransaction();
			entityTransaction.begin();
			entityManager.persist(student);
			List <Student> students=trainer.getStudents();
			students.add(student);
			trainer.setStudents(students);
			entityManager.merge(trainer);
			
			entityTransaction.commit();
		}
		else {
			System.out.println("invalid person id");
		}
	}
public void updateStudent(int studentId)
{
	EntityManager entityManager=getEntityManager();
	Student student=entityManager.find(Student.class,studentId);
	if(student!=null)
	{
		EntityTransaction entityTransaction=entityManager.getTransaction();
		entityTransaction.begin();
		student.setStudentId(studentId);
		entityManager.merge(student);
		entityTransaction.commit();
		
	}
	else {
		System.out.println("invalid person id");
	}
}
public void deleteStudent(int studentId)
{
	EntityManager entityManager=getEntityManager();
	Student student=entityManager.find(Student.class,studentId);
	if(student!=null)
	{
		EntityTransaction entityTransaction=entityManager.getTransaction();
		entityTransaction.begin();
		entityManager.remove(student);
		entityTransaction.commit();
		
	}
	else {
		System.out.println("invalid person id");
	}
}
public void findStudent(int studentId)
{
	EntityManager entityManager=getEntityManager();
	Student student=entityManager.find(Student.class,studentId);
	if(student!=null)
	{
		System.out.println(student);
		
	}
	else {
		System.out.println("invalid person id");
	}
}
}
