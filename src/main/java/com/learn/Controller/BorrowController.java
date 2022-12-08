package com.learn.Controller;


import com.learn.Domain.Borrow;
import com.learn.Domain.PageBean;
import com.learn.Service.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author DingTian
 * @version 1.0
 */
@RestController
@RequestMapping("/borrows")
public class BorrowController {
    @Autowired
    private BorrowService borrowService;

    @PostMapping
    public boolean insert(@RequestBody Borrow borrow){
        System.out.println(borrow);
        return borrowService.addBorrow(borrow);
    }

    @DeleteMapping
    public boolean deleteById(@RequestParam("bookId") int bookId,@RequestParam("userId") int userId){
        System.out.println(bookId + "  " + userId);
        return borrowService.deleteById(bookId,userId);
    }

    @PostMapping("/selectByPageAndCondition")
    public PageBean<Borrow> selectByPageAndCondition(@RequestBody Borrow borrow , @RequestParam int currentPage, @RequestParam int pageSize){
        return borrowService.selectByPageAndCondition(currentPage,pageSize,borrow);
    }
//
}
