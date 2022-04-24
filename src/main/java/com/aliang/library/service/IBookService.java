package com.aliang.library.service;

import com.aliang.library.entity.Book;

import java.util.List;
import java.util.Map;

public interface IBookService {

    long countBooks(Map<String, Object> conditions);

    List<Book> queryBooks(Map<String, Object> conditions);

    Book queryBookById(Integer bookId);

    Book addBook(Book book);

    Book updateBook(Book book);

    Book deleteBookById(Integer bookId);

    List<Book> queryBook4Ext(Map<String, Object> queryConditions);
}
