package com.learn.Service.Impl;

import com.learn.Controller.BookController;
import com.learn.Dao.BookDao;
import com.learn.Domain.Book;
import com.learn.Domain.PageBean;
import com.learn.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author DingTian
 * @version 1.0
 */
@Component
public class BookServiceImpl implements BookService {
    @Autowired
    private BookDao bookDao;

    @Override
    public boolean addBook(Book book) {
       return bookDao.insert(book) > 0;
    }

    @Override
    public boolean deleteByIds(int[] ids) {
       return bookDao.deleteByIds(ids) > 0;
    }

    @Override
    public PageBean<Book> selectByPageAndCondition(int currentPage, int pageSize, Book book) {
        int begin = (currentPage - 1)*pageSize;
        int size = pageSize;
        //因为要进行模糊查询，所以要先设置一下
        String bookName = book.getName();
        if( bookName != null && bookName.length() > 0){
            book.setName("%"+bookName+"%");
        }
        String author = book.getAuthor();
        if( author != null && author.length() > 0){
            book.setAuthor("%"+author+"%");
        }
        String bookISBN = book.getISBN();
        if(bookISBN != null && bookISBN.length() > 0){
            book.setISBN("%"+bookISBN+"%");
        }
        String publisher = book.getPublisher();
        if(publisher != null && publisher.length() > 0){
            book.setPublisher("%"+publisher+"%");
        }
        String states = book.getStates();
        if(states != null && states.length() > 0){
            book.setStates("%"+states+"%");
        }
        System.out.println(book);
        List<Book> books = bookDao.selectByPageAndCondition(begin,size,book);
        int totalCount  = bookDao.selectTotalCountByCondition(book);
        PageBean<Book> bookPageBean = new PageBean<>();
        bookPageBean.setList(books);
        bookPageBean.setTotalCount(totalCount);
//        System.out.println(bookPageBean);
        return bookPageBean;
    }

    @Override
    public boolean deleteById(int id) {
        return bookDao.deleteById(id) > 0;
    }

    @Override
    public boolean updateById(int id, Book book) {
        return bookDao.updateById(id,book) > 0;
    }
}
