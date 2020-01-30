package ru.anton.gorbachev.rest_template.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import ru.anton.gorbachev.rest_template.models.UserDTO;

import java.util.List;

@Controller
@RequestMapping(value = "/admin")
public class RestClientController {
    private final RestTemplate restTemplate;

    @Autowired
    public RestClientController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping(value = "/allUsers")
    public List<UserDTO> allUsers() {
        String url = "http://localhost:8081/admin/all";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<List<UserDTO>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<List<UserDTO>>() {});
        return response.getBody();
    }

//    @PostMapping(value = "/addUser",
//            consumes = MediaType.APPLICATION_JSON_VALUE,
//            produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<User> addUser(@RequestBody User user) {
//        return null;
//    }
//
//    @GetMapping(value = "/editUser")
//    public ResponseEntity<User> getUser(@RequestParam("id") Long id) {
//        return null;
//    }
//
//    @PutMapping(value = "/editUser")
//    public ResponseEntity<User> editUser(@RequestBody User user) {
//        return null;
//    }
//
//    @DeleteMapping(value = "/deleteUser")
//    public ResponseEntity<Long> deleteUser(@RequestBody Long id) {
//        return null;
//    }
}
