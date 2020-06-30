package ru.aniskov.petproject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
public class WebSecurityConfigTest {

    @Autowired
    private TestRestTemplate template;

//    @MockBean
//    private UserRepository repository;

//    @Before
//    public void setUp() {
//        QuizUser admin = new QuizUser("Admin", "ADMIN");
//
//        Mockito.when(repository.findByName(admin.getName()))
//                .thenReturn(admin);
//    }

    @Test
    public void givenAuthRequestOnUserNew_shouldSucceedWith200() throws Exception {
        ResponseEntity<String> result = template.withBasicAuth("AdminUser", "password")
                .postForEntity("/api/v1/user/new?name=NewUser&role=USER&password=$2a$10$KaYaTRdEuKkdy.QbQFoxC.hW6UlEB3O5nQ45ztGtZis3u2gH7yIei", null, String.class);
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void givenAuthRequestOnUserNew_shouldSucceedWith403() throws Exception {
        ResponseEntity<String> result = template.withBasicAuth("User", "password")
                .postForEntity("/api/v1/user/new?name=NewUser&role=USER&password=$2a$10$KaYaTRdEuKkdy.QbQFoxC.hW6UlEB3O5nQ45ztGtZis3u2gH7yIei", null, String.class);
        assertEquals(HttpStatus.FORBIDDEN, result.getStatusCode());
    }

    @Test
    public void givenUnauthRequestOnUserNew_shouldSucceedWith401() throws Exception {
        ResponseEntity<String> result = template.withBasicAuth("", "")
                .postForEntity("/api/v1/user/new?name=NewUser&role=USER&password=$2a$10$KaYaTRdEuKkdy.QbQFoxC.hW6UlEB3O5nQ45ztGtZis3u2gH7yIei", null, String.class);
        assertEquals(HttpStatus.UNAUTHORIZED, result.getStatusCode());
    }

    @Test
    public void givenAuthRequestOnUser_shouldSucceedWith200() throws Exception {
        ResponseEntity<String> result = template.withBasicAuth("AdminUser", "password")
                .getForEntity("/api/v1/user/all", String.class);
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void givenAuthRequestOnUser_shouldSucceedWith403() throws Exception {
        ResponseEntity<String> result = template.withBasicAuth("User", "password")
                .getForEntity("/api/v1/user/all", String.class);
        assertEquals(HttpStatus.FORBIDDEN, result.getStatusCode());
    }

    @Test
    public void givenUnauthRequestOnUser_shouldSucceedWith401() throws Exception {
        ResponseEntity<String> result = template.withBasicAuth("", "")
                .getForEntity("/api/v1/user/all", String.class);
        assertEquals(HttpStatus.UNAUTHORIZED, result.getStatusCode());
    }

}
