package com.social.dailylink;

import com.social.dailylink.controller.TestController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TestController.class)
@ContextConfiguration(classes = BackendApplication.class)
class ExceptionHandlerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Catch AccessDenied Exception and return ")
    @WithMockUser(roles = "USER")
    void testExceptionHandler() throws Exception {
        mockMvc.perform(get("/api/test/exceptionAccess"))
                .andExpect(status().isForbidden());
    }
}
