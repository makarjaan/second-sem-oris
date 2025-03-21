package com.makarova.controller;

import com.makarova.service.ApiClientService;
import org.springframework.web.bind.annotation.*;

@RestController
public class ApiClientController {

    private final ApiClientService apiClientService;

    public ApiClientController(ApiClientService apiClientService) {
        this.apiClientService = apiClientService;
    }

    @GetMapping("/get")
    public String get(@RequestParam("id") String id) {
        return apiClientService.sendGetRequest(id);
    }

    @PostMapping("/post")
    public String post(@RequestBody() String userJson) {
        return apiClientService.sendPostRequest(userJson);
    }

    @PutMapping("/put")
    public String put(@RequestParam("id") String id, @RequestBody() String userJson) {
        return apiClientService.sendPutRequest(id, userJson);
    }

    @DeleteMapping("/delete")
    public String delete(@RequestParam("id") String id) {
        return apiClientService.sendDeleteRequest(id);
    }
}
