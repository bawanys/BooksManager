package com.bee;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class BooksManager {
	private static EntityManagerFactory factory;
	private static EntityManager entityManager;

	public static void main(String[] args) {
		begin();
		
		//create();
		//update();
		//find();
		query();
		remove();
		query();
		end();

	}
	
	private static void remove() {
		Integer primaryKey = 5;
		Book reference = entityManager.getReference(Book.class, primaryKey);
		entityManager.remove(reference);
	}
	
	private static void query() {
		String jpql = "Select b From Book b";
		Query query = entityManager.createQuery(jpql);
		List<Book> listBooks = query.getResultList();
		for (Book b : listBooks ) {
			System.out.println(b.toString());
		}
	}
	
	private static void find() {
		Integer primaryKey = 3;
		Book book = entityManager.find(Book.class, primaryKey);
		System.out.println(book.toString());
	}
	
	private static void update() {
		Book existingBook = new Book();
		existingBook.setBookId(5);
		existingBook.setTitle("Green Egg and Ham");
		existingBook.setAuthor("Seuss");
		existingBook.setPrice(15);
		
		entityManager.merge(existingBook);
	}

	private static void end() {
		entityManager.getTransaction().commit();
		entityManager.close();
		factory.close();
	}

	private static void begin() {
		factory = Persistence.createEntityManagerFactory("BookUnit");
		entityManager = factory.createEntityManager();
		
		entityManager.getTransaction().begin();
	}

	private static void create() {
		Book newBook = new Book();
		newBook.setTitle("Smell my socks");
		newBook.setAuthor("David Walliams");
		newBook.setPrice(56);
		
		entityManager.persist(newBook);
	}

}
