package shop._8.controller.rest_controller;

import org.apache.coyote.Response;
import org.hibernate.mapping.Collection;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop._8.dto.condition.OrderSearchCondition;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api")
public class HelloController {

    @GetMapping("/hello")
    public String helloV1(@RequestParam List<Long> ids) {
        System.out.println(ids.toString());
        return "OK";
    }

    @GetMapping("/hello2")
    public String helloV2(@ModelAttribute OrderSearchCondition orderSearchCondition,
                          @RequestParam(required = false) Pageable pageable) {
        System.out.println(orderSearchCondition.toString());
        if (pageable == null) {
            pageable = PageRequest.of(0, 20);
        }
        System.out.println(pageable.getOffset() + ", " + pageable.getPageSize());
        return "OK";
    }
}
