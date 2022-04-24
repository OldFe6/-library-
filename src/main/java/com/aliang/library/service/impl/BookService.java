package com.aliang.library.service.impl;

import com.aliang.library.entity.Book;
import com.aliang.library.mapper.BookMapper;
import com.aliang.library.mapper.BookMapper4Ext;
import com.aliang.library.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BookService implements IBookService {

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private BookMapper4Ext bookMapper4Ext;

    @Override
    public long countBooks(Map<String, Object> conditions) {
        return bookMapper4Ext.countByConditions(conditions);
    }

    @Override
    public List<Book> queryBooks(Map<String, Object> conditions) {
        return bookMapper4Ext.selectByConditions(conditions);
    }

    @Override
    public Book queryBookById(Integer bookId) {
        return bookMapper.selectByPrimaryKey(bookId);
    }

    @Override
    public Book addBook(Book book) {
        int counts = bookMapper.insert(book);
        if (counts == 0) {
            return null;
        }
        return bookMapper.selectByPrimaryKey(book.getId());
    }

    @Override
    public Book updateBook(Book book) {
        int counts = bookMapper.updateByPrimaryKey(book);
        if (counts == 0) {
            return null;
        }
        return bookMapper.selectByPrimaryKey(book.getId());
    }

    @Override
    public Book deleteBookById(Integer bookId) {
        Book book = bookMapper.selectByPrimaryKey(bookId);
        if (book == null) {
            return null;
        }
        bookMapper.deleteByPrimaryKey(bookId);
        return book;
    }

    @Override
    public List<Book> queryBook4Ext(Map<String, Object> queryConditions) {
        return bookMapper4Ext.selectByConditions(queryConditions);
    }
}
