package com.axis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.axis.entity.Book;
import com.axis.repository.BookRepository;

@Service
public class BookService {
	
	@Autowired
	private BookRepository bookRepo;
	public void save(Book b) 
	{
		bookRepo.save(b);
		
	}
	public List<Book> getAllBook(){
		return bookRepo.findAll();
	}
	public Book getBookById(int id) {
		return bookRepo.findById(id).get();
	}

}
