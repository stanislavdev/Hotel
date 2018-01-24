import controller.commands.CommandFactory;
import controller.commands.commonCommands.SignUpCommand;
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
import static model.util.Constants.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SignUpCommandTester {
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
    private SignUpCommand signUpCommand = new SignUpCommand(userService);

    @Test
    public void correctSignUpCommand() {
        String email = "email@gmail.com";
        String password = "qwerty";

        when(request.getParameter(EMAIL_REGISTRATION_ATTRIBUTE)).thenReturn(email);
        when(request.getParameter(PASSWORD_REGISTRATION_ATTRIBUTE)).thenReturn(password);
        when(request.getSession()).thenReturn(session);

        when(user.getEmail()).thenReturn(email);
        when(user.getPassword()).thenReturn(password);

        doNothing().when(userService).signUp(any());
        when(userService.getUserByEmail(any())).thenReturn(Optional.empty());

        String path = signUpCommand.execute(request, response);
        assertEquals("redirect:" + CommandFactory.CLIENT_HOME_PAGE, path);
    }

    @Test
    public void incorrectEmailSignUpCommand() {
        String email = "emailgmail.com";
        String password = "qwerty";

        when(request.getParameter(EMAIL_REGISTRATION_ATTRIBUTE)).thenReturn(email);
        when(request.getParameter(PASSWORD_REGISTRATION_ATTRIBUTE)).thenReturn(password);
        when(request.getSession()).thenReturn(session);

        when(user.getEmail()).thenReturn(email);
        when(user.getPassword()).thenReturn(password);
        when(userService.getUserByEmail(any())).thenReturn(Optional.empty());

        String path = signUpCommand.execute(request, response);
        assertEquals(REGISTRATION_PAGE_JSP, path);
    }


    @Test
    public void existUserSignUpCommand() {
        String email = "email@gmail.com";
        String password = "qwerty";

        when(request.getParameter(EMAIL_REGISTRATION_ATTRIBUTE)).thenReturn(email);
        when(request.getParameter(PASSWORD_REGISTRATION_ATTRIBUTE)).thenReturn(password);
        when(request.getSession()).thenReturn(session);

        when(userService.getUserByEmail(any())).thenReturn(Optional.of(user));

        String path = signUpCommand.execute(request, response);
        assertEquals(REGISTRATION_PAGE_JSP, path);
    }
}
