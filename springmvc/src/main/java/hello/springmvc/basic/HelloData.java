package hello.springmvc.basic;

import lombok.Data;
import org.springframework.web.bind.annotation.RestController;

@Data
public class HelloData {
    private String username;
    private int age;
}
