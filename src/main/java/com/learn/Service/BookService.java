package com.learn.Service;

import com.learn.Domain.Book;
import com.learn.Domain.PageBean;

import java.util.List;

/**
 * @author DingTian
 * @version 1.0
 */
public interface BookService {

    boolean addBook(Book book);
    boolean deleteByIds(int[] ids);
    //分页查询
    PageBean<Book> selectByPageAndCondition (int currentPage , int pageSize, Book book);

    boolean deleteById(int id);

    boolean updateById(int id,Book book);

}
