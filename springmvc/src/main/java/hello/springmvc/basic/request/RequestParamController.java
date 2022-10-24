package hello.springmvc.basic.request;

import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {
    /**
     * 쿼리 파라미터 : http://localhost:8080/request-param-v1?username=hello&age=20
     * '?' 를 통해 전송하고자 하는 데이터 입력
     */
    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username={}, age={}", username, age);

        response.getWriter().write("ok");
    }

    @ResponseBody //Controller이면서 String은 viewResolver이다. 따라서 ResponseBody 어노테이션 사용
    @RequestMapping("/request-param-v2")
    public String requestParamV2(
            /**
             * 조회: /requset-param-v2?username=ljh&age30
             */
            @RequestParam("username") String memberName, //url에 데이터를 받는 효과
            @RequestParam("age") int memberAge){

        log.info("memberName={}, memberAge={}", memberName, memberAge);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(
            /**
             * 변수명과 param의 명칭을 통일하면 v2에 작성한 @RequestParam("변수명") 생략 가능
             */
            @RequestParam String username,
            @RequestParam int age){

        log.info("memberName={}, memberAge={}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4(String username, int age){
        /**
         * 파라미터와 param의 명칭이 같으면 v3에서 작성한 @RequestParam 생략가능
         */
        log.info("memberName={}, memberAge={}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(
            @RequestParam(required = true) String username,
            @RequestParam(required = false) Integer age){
        /**
         * required = true : 없으면 오류 / default 값
         * required = false : 없어도 괜찮음
         *
         * int : can`t input null
         * Integer = Null ok
         *
         * null: 알 수 없는 값 != "", 0 이 아니다.
         */
        log.info("memberName={}, memberAge={}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(
            @RequestParam(required = true, defaultValue = "guest") String username,
            @RequestParam(required = false, defaultValue = "-1") int age){
        /**
         * default : 값이 들어오지 않았을 때 기본 값
         * default가 있으면 required 가 의미가 없다.
         * "" 의 빈 문자열인 경우 default가 적용
         */
        log.info("memberName={}, memberAge={}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(@RequestParam Map<String, Object> paramMap){

        log.info("memberName={}, memberAge={}", paramMap.get("username"), paramMap.get("age"));
        return "ok";
    }

    /**
     * @ModelAttribute: 어노테이션이 붙는 객체를 생성함
     * 프로퍼티:
     */
    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(//@RequestParam String username, @RequestParam int age){
                                   @ModelAttribute HelloData helloData){
        //HelloData helloData = new HelloData();
        //helloData.setUsername("hello");
        //helloData.setAge(20);

        //log.info("username={}, age={}", username, age);
        //log.info("helloDate={}", helloData);
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        return "ok";
    }

    /**
     * @ModelAttribute 생략가능
     * String, int, Integer : @RequestParam
     * 나머지: @ModelAttribute
     * 생성된 객체와 변수명이 일치해야한다.
     */

    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String modelAttributeV2(HelloData helloData){

        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        return "ok";
    }
}
