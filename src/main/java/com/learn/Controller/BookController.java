package com.learn.Controller;

import com.learn.Domain.Book;
import com.learn.Domain.PageBean;
import com.learn.Service.BookService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author DingTian
 * @version 1.0
 */
@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping
    public boolean insert(@RequestBody Book book) {
        System.out.println(book);
        return bookService.addBook(book);
    }

    @PutMapping("/{id}")
    public boolean update(@PathVariable int id, @RequestBody Book book) {
        System.out.println(book);
        return bookService.updateById(id, book);
    }

    @DeleteMapping("/{id}")
    public boolean deleteById(@PathVariable int id) {
        return bookService.deleteById(id);
    }

    @DeleteMapping
    public boolean deleteByIds(@RequestBody int[] ids) {
        return bookService.deleteByIds(ids);
    }

    @PostMapping("/page")
//    接口路径：/books/page
    public PageBean<Book> selectByPageAndCondition(@RequestBody Book book, @RequestParam int currentPage, @RequestParam int pageSize) {
        return bookService.selectByPageAndCondition(currentPage, pageSize, book);
    }

}
