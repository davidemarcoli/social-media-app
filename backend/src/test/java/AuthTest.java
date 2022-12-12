import com.social.dailylink.BackendApplication;
import com.social.dailylink.controllers.TestController;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TestController.class)
@WithMockUser
@ContextConfiguration(classes = BackendApplication.class)
public class AuthTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithAnonymousUser
    public void givenNoToken_whenGetSecureRequest_thenUnauthorized() throws Exception {
        // cant find get method in MockMvc, why?
        mockMvc.perform(get("/api/test/user"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(roles = "USER")
    public void givenUserToken_whenGetSecureRequest_thenOk() throws Exception {
        mockMvc.perform(get("/api/test/user"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "MODERATOR")
    public void givenModeratorToken_whenGetSecureRequest_thenOk() throws Exception {
        mockMvc.perform(get("/api/test/mod"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void givenAdminToken_whenGetSecureRequest_thenOk() throws Exception {
        mockMvc.perform(get("/api/test/admin"))
                .andExpect(status().isOk());
    }

//    @Test
//    @WithMockUser(roles = "USER")
//    public void givenUserToken_whenGetSecureRequest_thenForbidden() throws Exception {
//        mockMvc.perform(get("/api/test/mod"))
//                .andExpect(status().isForbidden());
//    }
//
//    @Test
//    @WithMockUser(roles = "USER")
//    public void givenUserToken_whenGetSecureRequest_thenForbidden2() throws Exception {
//        mockMvc.perform(get("/api/test/admin"))
//                .andExpect(status().isForbidden());
//    }

}
