package hello.springmvc.basic.request;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestBodyStringController {

    @PostMapping("/request-body-string-v1")
    public void requestBodyStringV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody={}", messageBody);

        response.getWriter().write("ok");

        //test: postman : http://localhost:8080/request-body-string-v1
        //body: test
    }

    @PostMapping("/request-body-string-v2")
    public void requestBodyStringV2(InputStream inputStream, Writer responseWriter) throws IOException {
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        log.info("messageBody={}", messageBody);
        responseWriter.write("ok");
    }

    /**
     * HttpEntity<"type"> : HTTP Header, body 정보를 조회. 데이터와 type의 타입이 일치하면 Spring이 Body에 담아준다
     * 요청파라미터 조회기능은 아님
     */
    @PostMapping("/request-body-string-v3")
    public HttpEntity<String> requestBodyStringV3(HttpEntity<String> httpEntity){
                                                 //RequestEntity<String> requestEntity
        String body = httpEntity.getBody();
        log.info("messageBody={}", body);

        return new HttpEntity<>("ok");
        //ResponseEntity<String>("ok", HttpStatus.CREATED);

        //요청은 RequestEntity
        //반환은 ResponseEntity
    }


    //제일 많이 사용함
    @ResponseBody
    @PostMapping("/request-body-string-v4")
    public String requestBodyStringV4(@RequestBody String messageBody) {

        log.info("messageBody={}", messageBody);

        return "ok";

    }

    /**
     * RequestBody: Http body 정보를 편하게 조회하는 것
     * ResponseBody: Http 메세지 바디에 직접 응답결과 전달
     * view는 사용하지 않는다.
     */

}
