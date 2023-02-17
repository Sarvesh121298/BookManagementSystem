package com.axis.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.axis.entity.Book;
import com.axis.entity.MyBookList;
import com.axis.service.BookService;
import com.axis.service.MyBookListService;

@Controller
public class BookController {
	
	@Autowired
	private BookService service;
	
	@Autowired
	private MyBookListService myBookService;
	
	
	
	@GetMapping("/")
	public String home()
	{
		return"home";
	}
	@GetMapping("/book_register")
	public String bookRegister()
	{
		return "bookRegister";
	}
	
	@GetMapping("/available_books")
	public ModelAndView getallBook()
	{
		List<Book>list=service.getAllBook();
		return new ModelAndView("bookList","book",list);
	}
	
	@PostMapping("/save")
	public String addBook(@ModelAttribute Book b)
	{
		service.save(b);
		return "redirect:/available_books";
	}
	@GetMapping("/my_books")
	public String getBooks(Model model) 
	{
		List<MyBookList>list=myBookService.getAllMyBooks();
		model.addAttribute("book",list);
		return "myBooks";
	}
	
	@RequestMapping("/mylist/{id}")
	public String getMyList(@PathVariable("id") int id) {
		Book b = service.getBookById(id);
		MyBookList mb = new MyBookList(b.getId(),b.getName(),b.getAuthor(),b.getPrice());
		myBookService.saveMyBooks(mb);
		return "redirect:/my_books";
	}
}
