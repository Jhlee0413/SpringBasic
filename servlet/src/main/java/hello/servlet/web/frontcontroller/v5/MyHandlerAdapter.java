package hello.servlet.web.frontcontroller.v5;

import hello.servlet.web.frontcontroller.ModelView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface MyHandlerAdapter {
    //handler : controller
    //adapter가 controller를 처리 할 수 있는지 반환
    boolean supports(Object handler);

    //adapter : 실제 컨트롤러 호출, ModelView를 반환
    //Object로 설정하는 이유? 유연하게 동작하기 위해서
    ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException;
}
