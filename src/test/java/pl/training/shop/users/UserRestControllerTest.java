package pl.training.shop.users;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserRestController.class)
@ExtendWith(SpringExtension.class)
public class UserRestControllerTest {

    private static final long USER_ID = 1;

    private final User user = new User();

    @MockBean
    private UserService userService;
    @MockBean
    private UserMapper userMapper;
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        user.setId(1L);
        user.setFirstName("Jan");
        user.setLastName("Kowalski");
        user.setEmail("jan.kowalski@training.pl");

        UserTransferObject userTransferObject = new UserTransferObject();
        userTransferObject.setFirstName(user.getFirstName());
        userTransferObject.setLastName(user.getLastName());
        userTransferObject.setEmailAddress(user.getEmail());

        when(userMapper.toUserTransferObject(user)).thenReturn(userTransferObject);
        when(userService.getById(USER_ID)).thenReturn(user);
    }

    @Test
    void shouldReturnUserById() throws Exception {
        mockMvc.perform(get("/api/users/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is(user.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(user.getLastName())))
                .andExpect(jsonPath("$.emailAddress", is(user.getEmail())));
    }

}
