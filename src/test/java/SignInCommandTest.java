import controller.commands.CommandFactory;
import controller.commands.commonCommands.SignInCommand;
import model.entities.Role;
import model.entities.User;
import model.services.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.Optional;

import static controller.commands.Command.REGISTRATION_PAGE_JSP;
import static model.util.Constants.EMAIL_ATTRIBUTE;
import static model.util.Constants.PASSWORD_ATTRIBUTE;
import static model.util.Constants.REDIRECT_TO;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SignInCommandTest {
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private HttpSession session;
    @Mock
    private User user;
    @Mock
    private UserService userService;
    @InjectMocks
    private SignInCommand signInCommand = new SignInCommand(userService);

    @Test
    public void correctSignIn() {
        String email = "admin@admin.com";
        String password = "admin-password";

        when(request.getParameter(EMAIL_ATTRIBUTE)).thenReturn(email);
        when(request.getParameter(PASSWORD_ATTRIBUTE)).thenReturn(password);
        when(request.getSession()).thenReturn(session);

        user = new User.UserBuilder()
                .email(email)
                .password(password)
                .role(Role.ADMIN.name())
                .id(1)
                .build();

        when(userService.signIn(anyString(), anyString())).thenReturn(Optional.of(user));

        String path = signInCommand.execute(request, response);
        assertEquals(REDIRECT_TO + CommandFactory.ADMIN_HOME_PAGE, path);
    }
}
