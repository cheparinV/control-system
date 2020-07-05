package org.demo.project.controlsystem.service;

import org.demo.project.controlsystem.repository.PassRepository;
import org.junit.ClassRule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.lifecycle.Startables;

import java.time.Duration;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ContextConfiguration(initializers = {PassServiceTest.Initializer.class})
public class PassServiceTest {


    @ClassRule
    public static PostgreSQLContainer postgreSQLContainer =
            (PostgreSQLContainer) new PostgreSQLContainer("postgres:9.6")
                    .withDatabaseName("security_system")
                    .withUsername("user")
                    .withPassword("pass")
                    .withStartupTimeout(Duration.ofSeconds(600));

    @Autowired
    private PassService passService;

    @Autowired
    private PassRepository passRepository;

    @Test
    public void testEnterIntoRoom() {
        assertTrue(passService.userAction(2L, 4L, true));
    }

    @Test
    public void testExitFromRoom() {
        assertTrue(passService.userAction(2L, 4L, true));
        assertTrue(passService.userAction(2L, 4L, false));
    }

    @Test
    public void testAccessDenied() {
        assertFalse(passService.userAction(3L, 4L, true));
    }

    @Test
    public void testEnterIntoRoomWhenInRoom() {
        assertTrue(passService.userAction(2L, 4L, true));
        assertFalse(passService.userAction(2L, 8L, true));
    }

    @AfterEach
    public void after() {
        passRepository.deleteAll();
    }

    private static void startContainers() {
        Startables.deepStart(Stream.of(postgreSQLContainer)).join();
    }

    static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            startContainers();
            TestPropertyValues.of(
                    "spring.datasource.url=" + postgreSQLContainer.getJdbcUrl(),
                    "spring.datasource.username=" + postgreSQLContainer.getUsername(),
                    "spring.datasource.password=" + postgreSQLContainer.getPassword()
            ).applyTo(configurableApplicationContext.getEnvironment());
        }
    }
}
