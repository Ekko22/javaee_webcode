package com.learn.Service.Impl;

import com.learn.Dao.BorrowDao;
import com.learn.Domain.Book;
import com.learn.Domain.Borrow;
import com.learn.Domain.PageBean;
import com.learn.Service.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author DingTian
 * @version 1.0
 */
@Component
public class BorrowServiceImpl implements BorrowService {

    @Autowired
    private BorrowDao borrowDao;


    @Override
    public boolean addBorrow(Borrow borrow) {
        return borrowDao.insert(borrow) > 0;
    }

    @Override
    public boolean deleteById(int bookId,int userId) {
        return borrowDao.deleteById(bookId,userId) > 0;
    }

    @Override
    public PageBean<Borrow> selectByPageAndCondition(int currentPage, int pageSize, Borrow borrow) {
        int begin = (currentPage - 1)*pageSize;
        int size = pageSize;
        List<Borrow> borrows = borrowDao.selectByPageAndCondition(begin,size,borrow);
        int totalCount  = borrowDao.selectTotalCountByCondition(borrow);
        PageBean<Borrow> borrowPageBean = new PageBean<>();
        borrowPageBean.setList(borrows);
        borrowPageBean.setTotalCount(totalCount);
//        System.out.println(borrowPageBean);
        return borrowPageBean;
    }
}
