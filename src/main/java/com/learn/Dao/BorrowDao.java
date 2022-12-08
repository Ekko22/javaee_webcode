package com.learn.Dao;

import com.learn.Domain.Book;
import com.learn.Domain.Borrow;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;

import java.util.List;

/**
 * @author DingTian
 * @version 1.0
 */
@Mapper
public interface BorrowDao {

    @Insert("insert into borrow(book_id, user_id) values (#{bookId},#{userId})")
    int insert(Borrow borrow);

    @Delete("delete from borrow where book_id = #{bookId} and user_id = #{userId}")
    @ResultMap("BorrowResultMap")
    int deleteById(@Param("bookId") int bookId,@Param("userId") int userId);

    int selectTotalCountByCondition(Borrow borrow);

    List<Borrow> selectByPageAndCondition(@Param("begin") int begin, @Param("size") int size, @Param("borrow") Borrow borrow);
}
