package hello.servlet.basic.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

/**
 * 1. 파라미터 전송기능
 * http://localhost:8080/request-param?username=hello&age=20
 */

@WebServlet(name = "requestParamServlet", urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //전체 파라미터 조회
        System.out.println("[전체 파라미터 조회] - start");
        req.getParameterNames().asIterator() //전체 순환 (모든 데이터)
                .forEachRemaining(paramName -> System.out.println(paramName + "=" + req.getParameter(paramName))); //전체 데이터 출력

        System.out.println("[전체 파라미터 조회] - end");
        System.out.println();

        //단일 파라미터 조회
        System.out.println("[단일 파라미터 조회] - start");
        String username = req.getParameter("username");
        String age = req.getParameter("age");

        System.out.println("usernae = " + username);
        System.out.println("age = " + age);
        System.out.println("[단일 파라미터 조회] - end");
        System.out.println();

        System.out.println("[이름이 같은 복수 파라미터 조회] - start");
        //http://localhost:8080/request-param?username=goodnight&age=27&username=hello 의 경
        String[] values = req.getParameterValues("username");
        for (String value : values) {
            System.out.println("username = " + value);
        }
        System.out.println("[이름이 같은 복수 파라미터 조회] - end");
        System.out.println();

        resp.getWriter().write("green");

        /**
         * tips
         * username=hello&username=lee 의 경우
         * parameter: username
         * value: hello or lee
         *
         * request.getParameter()  =  하나의 파라미터 이름에 대해서는 단 하나의 값만 있을 때 사용
         * request.getParameterValues() 사용
         *
         */

        /** Post
         * 요청 URL : http://localhost:8080/request-param
         * content-type: application/x-www-form-urlencoded
         * message-body: username=hello&age=20
         *
         * application/x-www-form-urlencoded 이 형식은 GET 방식과 쿼리 파라미터가 같다
         * 조회 매서드는 그대로 사용가능
         *
         * GET 방식과 POST 방식의 차이는 Content-Type의 유무이다(GET = Content-Type x / POST = Content-Type O)
         *
         */

        /**
         * 포스트맨 사용
         */
    }
}
