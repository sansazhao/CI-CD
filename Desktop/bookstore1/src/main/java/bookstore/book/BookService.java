package bookstore.book;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookDao bookRepo;//code10

    public Book queryById(int id) {
        return bookRepo.queryById(id).get(0);
    }

    public Book queryByTitle(String t) {
        return bookRepo.queryByTitle(t).get(0);
    }

    public List<Book> queryByAuthor(String au) {
        return bookRepo.queryBookByAuthor(au);
    }

    public Book create(Book b) {
        return bookRepo.save(b);
    }

    public Book update(Book book) {
        return bookRepo.save(book);
    }

    public List<Book>findByTitleLike(String tit){
        return bookRepo.findByTitleLike(tit);
    }
}