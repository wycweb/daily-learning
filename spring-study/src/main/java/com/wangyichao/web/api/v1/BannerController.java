package com.wangyichao.web.api.v1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class BannerController {

    @GetMapping("")
    public String test() {
        return "{\"headers\":{\"keyName1\":{\"keyDesc\":\"keyDesc1\"},\"keyName2\":{\"keyDesc\":\"keyDesc2\"},\"keyName3\":{\"keyDesc\":\"keyDesc3\"}},\"params\":[{\"keyName1\":\"value1\",\"keyName2\":\"value2\",\"keyName3\":\"value3\"},{\"keyName1\":\"value4\",\"keyName2\":\"value5\",\"keyName3\":\"value6\"}]}\n";
    }
}
