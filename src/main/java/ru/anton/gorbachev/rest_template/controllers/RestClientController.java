package ru.anton.gorbachev.rest_template.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.anton.gorbachev.rest_template.models.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "/admin")
public class RestClientController {
    private final RestTemplate restTemplate;
    private final String URL = "http://localhost:8081/admin/all/";

    @Autowired
    public RestClientController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping(value = "/allUsers")
    public List<User> allUsers(HttpServletRequest request) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, getTokenFromCookie(request));
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<List<User>> response = restTemplate.exchange(
                URL,
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<List<User>>() {});
        return response.getBody();
    }

    @PostMapping(value = "/addUser")
    public User addUser(@RequestBody User user, HttpServletRequest request) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set(HttpHeaders.AUTHORIZATION, getTokenFromCookie(request));
        HttpEntity<User> entity = new HttpEntity<>(user, headers);
        ResponseEntity<User> response = restTemplate.postForEntity(URL, entity, User.class);
        return response.getBody();
    }

    @GetMapping(value = "/edit")
    public User getUser(@RequestParam("id") Long id, HttpServletRequest request) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set(HttpHeaders.AUTHORIZATION, getTokenFromCookie(request));
        HttpEntity<User> entity = new HttpEntity<>(headers);
        ResponseEntity<User> response = restTemplate.exchange(URL + id, HttpMethod.GET, entity, User.class);
        return response.getBody();
    }

    @PutMapping(value = "/edit")
    public User editUser(@RequestBody User user, HttpServletRequest request) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set(HttpHeaders.AUTHORIZATION, getTokenFromCookie(request));
        HttpEntity<User> entity = new HttpEntity<>(user, headers);
        ResponseEntity<User> response = restTemplate.exchange(URL, HttpMethod.PUT, entity, User.class);
        return response.getBody();
    }

    @DeleteMapping(value = "/delete")
    public void deleteUser(@RequestBody Long id, HttpServletRequest request) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set(HttpHeaders.AUTHORIZATION, getTokenFromCookie(request));
        HttpEntity<User> entity = new HttpEntity<>(headers);
        restTemplate.exchange(URL + id, HttpMethod.DELETE, entity, User.class);
    }

    public String getTokenFromCookie(HttpServletRequest request) {
        for (Cookie cookie : request.getCookies()) {
            if (cookie.getName().equals("token")) {
                return cookie.getValue();
            }
        }
        return null;
    }
}
