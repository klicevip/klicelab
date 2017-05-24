/**
 * Created by klice on 2017/5/22.
 * 用户集成测试
 */
package klicelab.integration;

import klicelab.model.Session;
import klicelab.service.SessionService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private SessionService sessionService;

    private MockMvc mockMvc;
    MockHttpSession mockHttpSession;

    @Before
    public void setup(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        initMockSession();
    }

    void initMockSession(){
        mockHttpSession = new MockHttpSession(webApplicationContext.getServletContext());
        String sessionId = mockHttpSession.getId();
        Session session = new Session();
        session.setId(sessionId);
        session.setCreatedTime(new Date());
        session.setUserId(1);
        sessionService.save(session);
    }

    @Test
    public void UserIndex() throws Exception {
        mockMvc.perform(get("/user/index"))
                .andExpect(status().is3xxRedirection());

        mockMvc.perform(get("/user/index").session(mockHttpSession))
                .andExpect(status().isOk());
    }
}
