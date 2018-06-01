package bookstore.controller;

import bookstore.book.Book;
import bookstore.book.BookService;
import bookstore.cart.CartService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.json.*;
import javax.json.stream.JsonGenerator;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@CrossOrigin("http://localhost:3000")
@WebServlet
@Controller
@RequestMapping("/book")
public class BookController extends HttpServlet{
    @Autowired
    private BookService bService;
    @Autowired
    private CartService cart;

    @RequestMapping(method = RequestMethod.OPTIONS)
    public void commonOptions2(HttpServletResponse response) throws IOException {
        response.setHeader("Access-Control-Allow-Methods", "HEAD,GET,POST,PUT,DELETE");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type,Content-Length,Authorization,If-Match,If-None-Match,X-Experience-API-Version,X-Experience-API-Consistent-Through");
        response.setHeader("Access-Control-Expose-Headers", "ETag,Last-Modified,Cache-Control,Content-Type,Content-Length,WWW-Authenticate,X-Experience-API-Version,X-Experience-API-Consistent-Through");
        response.setHeader("Access-Control-Allow-Origin", "*");
    }

    @RequestMapping("/query")
    protected void doQuery(String key, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        System.out.println("query : " + key);
        Book b = bService.findByTitleLike(key).get(0);
        System.out.println(buildJson(b));
        //parseJson(buildJson(b));
        out.print(buildJson(b));
    }

    List<Book> b = new ArrayList<>();
    @RequestMapping("/select")
    protected void doSelect(String Book,String Author,
                         String Language,String Published,String Sales,
                         HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(">>>>>>>>>>doSelect()<<<<<<<<<<<"+Book);
        cart.add(1,bService.queryByTitle(Book));
        PrintWriter out = resp.getWriter();
        out.print(bService.queryByTitle(Book).getPrice());
    }

    @RequestMapping("/add")
    protected void doAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(">>>>>>>>>>doAdd()<<<<<<<<<<<");
        PrintWriter out = resp.getWriter();
       // cart.add("react1",bService.queryByTitle(Book));
        out.println("add to cart");
    }

    @RequestMapping("/delete")
    protected void doDelete(String Book,String Author,
                         String Language,String Published,String Sales,
                         HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(">>>>>>>>>>doDelete()<<<<<<<<<<<"+Book);
        cart.delete(bService.queryByTitle(Book).getId());
        PrintWriter out = resp.getWriter();
        out.println(Book);
    }

    @RequestMapping("/getBook")
    protected void GetBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(">>>>>>>>>>doGETBOOK()<<<<<<<<<<<");
        PrintWriter out = resp.getWriter();
        Book b = bService.queryById(1);
        System.out.println(buildJson(b));
        //parseJson(buildJson(b));
        out.print(buildJson(b));


    }

    JsonStructure parsed;

    public String buildJson(Book b) {
        /* Build JSON Object Model */
        JsonObject model = Json.createObjectBuilder()
                .add("Book", b.getTitle())
                .add("Author", b.getAuthor())
                .add("Language", b.getLanguage())
                .add("Published", b.getPublished())
                .add("Sales", b.getSales())
             //   .add("book", Json.createArrayBuilder()
               //         .add(Json.createObjectBuilder()
                 //               .add("author",bService.queryById(3).getAuthor())
                   //             .add("language", bService.queryById(3).getLanguage())
                     //           .add("published", bService.queryById(3).getPublished())))

                .build();

        /* Write JSON Output */
        StringWriter stWriter = new StringWriter();
        try (JsonWriter jsonWriter = Json.createWriter(stWriter)) {
            jsonWriter.writeObject(model);
        }
        //return stWriter.toString();

        /* Write formatted JSON Output */
        Map<String,String> config = new HashMap<>();
        config.put(JsonGenerator.PRETTY_PRINTING, "");
        JsonWriterFactory factory = Json.createWriterFactory(config);

        StringWriter stWriterF = new StringWriter();
        try (JsonWriter jsonWriterF = factory.createWriter(stWriterF)) {
            jsonWriterF.writeObject(model);
        }
        return stWriterF.toString();
    }

    public void parseJson(String content) {
        /* Parse the data using the document object model approach */
        try (JsonReader reader = Json.createReader(new StringReader(content))) {
            parsed = reader.readObject();
        }

        /* Represent the DOM tree on a list for a JSF table */
        this.printTree(parsed, 0, "");
    }

    /* Used to populate rowList to display the DOM tree on a JSF table */
    public void printTree(JsonValue tree, int level, String key) {
        switch (tree.getValueType()) {
            case OBJECT:
                JsonObject object = (JsonObject) tree;
                for (int i = 0; i < level; i++)
                    System.out.print(" ");
                System.out.println( level + " " + tree.getValueType().toString()  + " " +  key + "--");
                for (String name : object.keySet()) {
                    this.printTree(object.get(name), level+1, name);
                }
                break;
            case ARRAY:
                JsonArray array = (JsonArray) tree;
                for (int i = 0; i < level; i++)
                    System.out.print(" ");
                System.out.println( level + " " + tree.getValueType().toString() + " " + key + "--");
                for (JsonValue val : array) {
                    this.printTree(val, level+1, "");
                }
                break;
            case STRING:
                JsonString st = (JsonString) tree;
                for (int i = 0; i < level; i++)
                    System.out.print(" ");
                System.out.println( level + " " + tree.getValueType().toString() + " " + key + " " + st.getString());
                break;
            case NUMBER:
                JsonNumber num = (JsonNumber) tree;
                for (int i = 0; i < level; i++)
                    System.out.print(" ");
                System.out.println( level + " " + tree.getValueType().toString() + " " + key + " " + num.toString());
                break;
            case FALSE:
            case TRUE:
            case NULL:
                String valtype = tree.getValueType().toString();
                for (int i = 0; i < level; i++)
                    System.out.print(" ");
                System.out.println( level + " " + valtype + " " + key + " " + valtype.toLowerCase());
                break;
        }
    }

}
