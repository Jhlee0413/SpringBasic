package hello.servlet.web.frontcontroller.v3;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v2.ControllerV2;
import hello.servlet.web.frontcontroller.v2.controller.MemberFormControllerV2;
import hello.servlet.web.frontcontroller.v2.controller.MemberListControllerV2;
import hello.servlet.web.frontcontroller.v2.controller.MemberSaveControllerV2;
import hello.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// *: 하위 모든 컨트롤러 호출
@WebServlet(name = "frontControllerServletV3", urlPatterns = "/front-controller/v3/*")
public class FrontControllerServletV3 extends HttpServlet {

    private Map<String, ControllerV3> controllerV1Map = new HashMap<>();

    // cmd + n : constructor none
    public FrontControllerServletV3() {
        controllerV1Map.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
        controllerV1Map.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
        controllerV1Map.put("/front-controller/v3/members", new MemberListControllerV3());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FrontControllerServletV2.service");

        String requestURI = request.getRequestURI();

        ControllerV3 controllerV3 = controllerV1Map.get(requestURI);

        if (controllerV3 == null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        //1. paramMap 넘겨주기
        Map<String, String> paramMap = createParamMap(request);
        ModelView mv = controllerV3.process(paramMap);


        //2. url 맵핑
        String viewName = mv.getViewName();//논리이름

        MyView view = viewResolver(viewName);
        view.render(mv.getModel(), request, response);

    }

    //파라미터를 넘겨주기 위한 로직 함수 생성
    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>(); //map 객체 생성
        request.getParameterNames().asIterator() //request 의 모든 Parameter 모두 꺼내오기
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }

    private MyView viewResolver(String viewName) {
        // 폴더 경로가 바뀌어도 한번만 수정하면 된다.
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }
}
