package bookstore.dao;


import bookstore.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
* Book类接口
* spring-data-jpa 动态查询
* */
@Repository
public interface BookDao extends JpaRepository<Book, Integer> {

    //public void del(@Param("userEntity") User userEntity);

    // public void update(@Param("userEntity") User userEntity);

    // public void insert(@Param("userEntity") User userEntity);
    @Query("select u from Book u where u.id=:id")
    List<Book> queryById(@Param("id")int id);

    @Query("select u from Book u where u.title=:title")
    List<Book> queryByTitle(@Param("title")String title);

    @Query("select u from Book u where u.author=:author")
    List<Book> queryBookByAuthor(@Param("author")String author);

    List<Book> findByTitleLike(@Param("title")String title);

    @Query("select u from Book u")
    List<Book> queryAllBy();

}
