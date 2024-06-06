package org.nrt.core.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.doThrow;

@SpringBootTest(classes = org.nrt.core.MainApplication.class)
public class DatabaseConnectionCheckerTest {

    @Autowired
    private DatabaseConnectionChecker databaseConnectionChecker;

    @MockBean
    private JdbcTemplate jdbcTemplate;

    @Test
    public void testSuccessfulConnection(){
        assertTrue(databaseConnectionChecker.checkConnection());
    }

    @Test
    public void testFailedConnection(){
        doThrow(new RuntimeException()).when(jdbcTemplate).execute("SELECT 1");
        assertFalse(databaseConnectionChecker.checkConnection());
    }

}
