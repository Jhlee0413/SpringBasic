package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Controller의 반환값이 String 이면 뷰 이름으로 인식
 * @RestController은 반환 값으로 뷰를 찾는것이 아니라 Http 메세지 바디에 바로 입력
 */
//@Slf4j
@RestController
public class LogTestController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping("/log-test")
    public String LogTest() {
        String name = "Spring";

        //로그 사용시 지향
        //System.out.println("name=" + name);

        //log level = trace < debug < info < warn < error
        //개발 서버 : debug / 운영 서버 : info
        log.trace("trace log={}", name);
        log.debug("debug log={}", name);
        log.info("info log={}", name);
        log.warn("warn log={}", name);
        log.error("error log={}", name);

        return "ok";
    }

}
