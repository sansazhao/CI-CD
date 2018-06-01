package bookstore.cart;

import bookstore.book.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartDao extends JpaRepository<Cart, Integer> {

    Cart save(Cart c);

    int deleteCartByBookid(int id);

    Cart deleteByBookid(int id);

    @Query("select b from Cart b where b.userid=:userid")
    List<Cart> queryBookByUserid(@Param("userid")int id);

}
