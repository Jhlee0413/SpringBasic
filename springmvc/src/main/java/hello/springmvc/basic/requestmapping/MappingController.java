package hello.springmvc.basic.requestmapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
public class MappingController {

    private Logger log = LoggerFactory.getLogger(getClass());

    //두개의 URL 매핑도 허용
    //ex) "/hello-basic" 과 "hello-basic/"
    @RequestMapping(value = "/hello-basic", method = RequestMethod.GET)
    public String helloBasic(){
        log.info("hellobasic");
        return "ok";
    }

    /**
     * method 특정 Http 요청만 허용
     * ex) GET HEAD POST PUT PATCH DELETE
     */
    @RequestMapping(value = "/mapping-get-v1", method = RequestMethod.GET)
    public String mappingGetv1(){
        log.info("mapping-get-v1");
        return "v1 Ok";
    }

    /**
     * 축약 어노테이션
     * @GetMapping
     * @PostMapping
     * @PutMapping
     * @DeleteMapping
     * @PatchMapping
     */
    @GetMapping(value = "/mapping-get-v2")
    public String mappingGetv2() {
        log.info("mapping-get-v2");
        return "v2 Ok";
    }

    /**
     * pathvariable
     * 변수명이 같으면 생략가능
     * @PathVariable("userId") String userId -> @PathVariable userId
     */

    @GetMapping("/mapping/{userId}")
    public String mappingPath(@PathVariable("userId") String data){
        log.info("mappingPath userId={}", data);
        return "path Variable Ok";
    }

    //다중 사용
    @GetMapping("/mapping/{userId}/orders/{orderId}")
    public String mappingMultiPath(@PathVariable("userId") String data1, @PathVariable("orderId") String data2){
        log.info("mappingPath userId={}, orderId={}", data1, data2);
        return "path Variable Ok";
    }

    /**
     * 미디어 타입 조건 매칭
     */
    @PostMapping(value = "/mapping-consume", consumes = "application/json")
    public String mappingConsumes() {
        log.info("mapping Consumes");
        return "consumes ok";
    }

}
