package hello.springmvc.basic.request;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

@Slf4j
@RestController //view가 아닌 html 바디에 전송
public class RequestHeaderController {

    @RequestMapping("/headers")
    public String headers(HttpServletRequest request,
                          HttpServletResponse response,
                          HttpMethod httpMethod, //http method 조회
                          Locale locale, //locale 정보 조회
                          @RequestHeader MultiValueMap<String, String> headerMap, //한개 이상 받을때, 하나의 키에 여러 값을 받을 수 있음
                          @RequestHeader("host") String host, //특정 http 헤더 조회
                          @CookieValue(value = "myCookie", required = false) String cookie
                          ) {
        log.info("request={}" , request);
        log.info("response={}", response);
        log.info("httpMethod={}", httpMethod);
        log.info("locale={}", locale);
        log.info("headerMap={}", headerMap);
        log.info("header host={}", host);
        log.info("myCookie={}", cookie);

        return "headers ok";
    }
}

/**
 * @SLF4J
 * private static final org.slf4j.Logger log =
 * org.slf4j.LoggerFactory.getLogger(RequestHeaderController.class);
 * 자동 생성성 *
 */
