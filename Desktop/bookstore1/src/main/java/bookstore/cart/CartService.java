package bookstore.cart;

import bookstore.book.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CartService {
    @Autowired
    private CartDao cartRepo;//code10

    public List<Cart> queryByUserid(int id) {
        return cartRepo.queryBookByUserid(id);
    }

    @Transactional
    public int delete(int id){
        return cartRepo.deleteCartByBookid(id);
    }
    public Cart add(int id,Book b) {
        Cart c = new Cart();
        c.setBookid(b.getId());
        c.setBookprice(b.getPrice());
        c.setUserid(1);
        System.out.println(b.getPrice());
        return cartRepo.save(c);
    }
}