package Poster;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.ParameterizableViewController;

public class EditorController extends ParameterizableViewController
{
  private static final String LOGGER_PROPERTIES_FILE = "/home/pitirimov/Javaworks/Poster/log4j.properties";
  private static Logger logger = LoggerFactory.getLogger(EditorController.class);
  
  public EditorController()
  {
    super();
    /* Logger configuration */
    PropertyConfigurator.configure(LOGGER_PROPERTIES_FILE);
  }
  
  protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception
  {
    Authentication authEditor = SecurityContextHolder.getContext().getAuthentication();
    ModelAndView mvc = new ModelAndView();

    if ((request != null) && (response != null))
    {
      /* Create the local storage for database access */
      Data loginData = new Data();

      /* Go to the user's page */
      if (!((authEditor.getName()).equals("")))
      {
        if (loginData.getUserId(authEditor.getName()) < 0)
        {
          /* Create new user */
          loginData.addUser(authEditor.getName(), SecurityContextHolder.getContext().getAuthentication().getCredentials().toString());
        }

        /* Go to the user's page */
        mvc.addObject("username", authEditor.getName());
        mvc.setViewName("Editor");
      }
      else
      {
        /* Go to the initial page */
        mvc.setViewName("Login");
      }
    }

    return mvc;
  }
}