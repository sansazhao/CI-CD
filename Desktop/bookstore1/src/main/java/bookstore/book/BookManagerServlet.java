package bookstore.book;

import net.sf.json.JSONArray;
import org.springframework.web.bind.annotation.CrossOrigin;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * Servlet implementation class UserManagerServlet
 */
@CrossOrigin("http://localhost:3000")
@WebServlet(urlPatterns = "/BookManager")
public class BookManagerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BookManagerServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
            HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
            PrintWriter out = response.getWriter();
            response.setContentType("text/html;charset=utf-8");
            System.out.println("This is a book manager");

            List<Book> result = HibernateUtil.getSessionFactory()
                    .getCurrentSession().createQuery("from Book").list(); 
            Iterator<Book> it = result.iterator();
            
            ArrayList<JSONArray> booksJson = new ArrayList<JSONArray>();
            while (it.hasNext()) {
                Book book = (Book) it.next();
                ArrayList<String> arrayList = new ArrayList<String>();
                arrayList.add(book.getTitle());
                arrayList.add(book.getAuthor());
                arrayList.add(book.getLanguage());
                arrayList.add(book.getPublished());
                arrayList.add(book.getSales());                             
                booksJson.add(JSONArray.fromObject(arrayList));
            }
            JSONArray books = JSONArray.fromArray(booksJson.toArray());


            System.out.println(books);

           out.println(books);
           out.flush();
           out.close();
           HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
        }
        catch (Exception ex) {
            HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().rollback();
            if ( ServletException.class.isInstance( ex ) ) {
                throw (ServletException) ex;
            }
            else {
                throw new ServletException( ex );
            }
        }
	}

}
