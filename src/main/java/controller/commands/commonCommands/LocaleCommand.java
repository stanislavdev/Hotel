package controller.commands.commonCommands;

import controller.commands.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

public class LocaleCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String lang = request.getParameter("language");
        if (lang == null) {
            lang = "en";
        }
        Locale locale = new Locale(lang);
        request.getSession().setAttribute("locale", locale);
        return request.getParameter("currentPage");
    }
}
