package com.learn.Dao;

import com.learn.Domain.Book;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author DingTian
 * @version 1.0
 */
@Mapper
public interface BookDao {

    @Insert("insert into book(book_ISBN, book_name, book_publisher, book_author) values(#{ISBN},#{name},#{publisher},#{author})")
    @ResultMap("BookResultMap")
    int insert(Book book);
    int updateById(@Param("id")int id,@Param("book") Book book);
    @Delete("delete from book where book_id = #{id}")
    @ResultMap("BookResultMap")
    int deleteById(Integer id);
    @Select("select * from book where book_id = #{id}")
    @ResultMap("BookResultMap")
    Book selectById(Integer id);
    @Select("select * from book")
    @ResultMap("BookResultMap")
    List<Book> selectAll();

    int deleteByIds(@Param("ids") int[] ids);

    int selectTotalCountByCondition(Book book);

    List<Book> selectByPageAndCondition(@Param("begin") int begin,@Param("size") int size,@Param("book") Book book);
}
