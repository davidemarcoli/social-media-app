package com.social.dailylink;

import com.social.dailylink.controller.TestController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TestController.class)
@ContextConfiguration(classes = BackendApplication.class)
class AuthTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Give no token and forbid")
    @WithAnonymousUser
    void givenNoToken_whenGetSecureRequest_thenUnauthorized() throws Exception {
        mockMvc.perform(get("/api/test/user"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @DisplayName("Give user token and accept access")
    @WithMockUser(roles = "USER")
    void givenUserToken_whenGetSecureRequest_thenOk() throws Exception {
        mockMvc.perform(get("/api/test/user"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Give moderator token and accept access")
    @WithMockUser(roles = "MODERATOR")
    void givenModeratorToken_whenGetSecureRequest_thenOk() throws Exception {
        mockMvc.perform(get("/api/test/mod"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Give admin token and accept access")
    @WithMockUser(roles = "ADMIN")
    void givenAdminToken_whenGetSecureRequest_thenOk() throws Exception {
        mockMvc.perform(get("/api/test/admin"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Give user no token and forbid access")
    @WithMockUser(roles = "USER")
    void givenUserToken_whenGetSecureRequest_thenForbidden() throws Exception {
        mockMvc.perform(get("/api/test/mod"))
                .andExpect(status().isForbidden());
    }

    @Test
    @DisplayName("Give user no token and forbid access v.2")
    @WithMockUser(roles = "USER")
    void givenUserToken_whenGetSecureRequest_thenForbidden2() throws Exception {
        mockMvc.perform(get("/api/test/admin"))
                .andExpect(status().isForbidden());
    }

    @Test
    @DisplayName("Give user test authority and accept access")
    @WithMockUser(authorities = "TEST_AUTORITY")
    void givenTestAuthority_whenGetTestRequest_thenOk() throws Exception {
        mockMvc.perform(get("/api/test/authoritytest"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Give user fake test authority and forbid access")
    @WithMockUser(authorities = "FAKE_TEST_AUTORITY")
    void givenTestAuthority_whenGetTestRequest_thenForbidden() throws Exception {
        mockMvc.perform(get("/api/test/authoritytest"))
                .andExpect(status().isForbidden());
    }
}
