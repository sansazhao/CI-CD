package bookstore.controller;


import bookstore.book.BookService;
import bookstore.userService.UserService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@CrossOrigin("http://localhost:3000")
@WebServlet
@Controller
public class UserController extends HttpServlet{
    String result = "false";
    @RequestMapping(method = RequestMethod.OPTIONS)
    public void commonOptions1(HttpServletResponse response) throws IOException {
        response.setHeader("Access-Control-Allow-Methods", "HEAD,GET,POST,PUT,DELETE");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type,Content-Length,Authorization,If-Match,If-None-Match,X-Experience-API-Version,X-Experience-API-Consistent-Through");
        response.setHeader("Access-Control-Expose-Headers", "ETag,Last-Modified,Cache-Control,Content-Type,Content-Length,WWW-Authenticate,X-Experience-API-Version,X-Experience-API-Consistent-Through");
        response.setHeader("Access-Control-Allow-Origin", "*");
    }

    @Autowired
    private UserService uService;

    @RequestMapping("/login")
     protected void doLogin(String name,String pwd,HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
            //doPost(req, resp);
        out.print(uService.login(name,pwd));
        System.out.println("login："+ name +"  pwd:"+ pwd);
    }


    @RequestMapping("/regis")
    protected void doRegis(String name,String pwd,HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(">>>>>>>>>>doRegis<<<<<<<<<<<  name:"+name+" pwd:"+pwd);
        PrintWriter out = resp.getWriter();
        //doPost(req, resp);
        out.print(uService.regis(name,pwd));
        System.out.println("login："+ name +"  pwd:"+ pwd);
    }

    @ResponseBody
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(">>>>>>>>>>doPost()<<<<<<<<<<<");
        PrintWriter out = resp.getWriter();
        out.println(result);
    }
}
