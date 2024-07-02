package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import dto.Trainer;

public class Trainerdao {
public EntityManager getEntityManager()
{
	return Persistence.createEntityManagerFactory("mockonetomany").createEntityManager();
}
public void saveTrainer(Trainer trainer)
{
	EntityManager entityManager=getEntityManager();
	EntityTransaction entityTransaction=entityManager.getTransaction();
	entityTransaction.begin();
	entityManager.persist(trainer);
	entityTransaction.commit();
}
public void updateTrainer(int trainerId, Trainer trainer)
{
	EntityManager entityManager=getEntityManager();
	Trainer trainerdb=entityManager.find(Trainer.class,trainerId);
	if(trainerdb!=null)
	{
		EntityTransaction entityTransaction=entityManager.getTransaction();
		entityTransaction.begin();
		trainer.setTrainerId(trainerId);
		trainer.setStudents(trainer.getStudents());
		entityManager.merge(trainer);
		entityTransaction.commit();
	}
	else {
		System.out.println("invalid trainer Id");
	}
}
public void deleteTrainer(int trainerId)
{
	EntityManager entityManager=getEntityManager();
	Trainer trainer=entityManager.find(Trainer.class,trainerId);
	if(trainer!=null)
	{
		EntityTransaction entityTransaction=entityManager.getTransaction();
		entityTransaction.begin();
		entityManager.remove(trainer);
		entityTransaction.commit();
	}
	else {
		System.out.println("invalid trainer");
	}
}
public void findTrainer(int trainerId)
{
	EntityManager entityManager=getEntityManager();
	Trainer trainer=entityManager.find(Trainer.class,trainerId);
	if(trainer!=null)
	{
		System.out.println(trainer);
	}
	else {
		System.out.println("invalid trainer");
	}
}
}
