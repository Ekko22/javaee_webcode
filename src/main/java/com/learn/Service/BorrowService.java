package com.learn.Service;

import com.learn.Domain.Book;
import com.learn.Domain.Borrow;
import com.learn.Domain.PageBean;

/**
 * @author DingTian
 * @version 1.0
 */
public interface BorrowService {
    boolean addBorrow(Borrow borrow);
    boolean deleteById(int bookId,int userId);
    PageBean<Borrow> selectByPageAndCondition (int currentPage , int pageSize, Borrow borrow);

}
