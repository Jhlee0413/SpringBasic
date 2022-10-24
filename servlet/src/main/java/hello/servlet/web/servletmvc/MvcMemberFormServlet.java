package hello.servlet.web.servletmvc;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "mvcMemberFormServlet", urlPatterns = "/servlet-mvc/members/new-form")
public class MvcMemberFormServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /**
         * /WEB-INF 이 경로 안에 있으면 직접 JSP 호출 할 수 없음
         * MVC 특징인 controller를 통해 JSP호출
         */
        String viewPath = "/WEB-INF/views/new-form.jsp";

        RequestDispatcher dispatcher = req.getRequestDispatcher(viewPath);

        //다른 서블릿이나 JSP로 이동할 수 있는 기능. 서버 내부에서 다시 호출 발생
        dispatcher.forward(req, resp);
    }
}
