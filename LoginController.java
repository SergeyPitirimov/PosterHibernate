package Poster;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.ParameterizableViewController;

public class LoginController extends ParameterizableViewController
{
  private static final String LOGGER_PROPERTIES_FILE = "/home/pitirimov/Javaworks/Poster/log4j.properties";
  private static Logger logger = LoggerFactory.getLogger(LoginController.class);
  
  public LoginController()
  {
    super();
    /* Logger configuration */
    PropertyConfigurator.configure(LOGGER_PROPERTIES_FILE);
  }

  protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception
  {
    ModelAndView mvc = new ModelAndView("Login");

    if ((request != null) && (response != null))
    {
      /* Create the local storage for database access */
      Data editorData = new Data();

      /* Add the new post and update already saved posts */
      if (request.getParameter("username") != null)
      {
        for(String element: editorData.getPosts(editorData.getUserId(request.getParameter("username"))))
        {
          /* Update or delete the post if it is necessary */
          if (!((request.getParameter(Integer.toString(editorData.getPostId(element)))).equals(element)))
          {
            if (!((request.getParameter(Integer.toString(editorData.getPostId(element)))).equals("")))
            {
              /* Update the post */
              editorData.updatePost(editorData.getPostId(element), request.getParameter(Integer.toString(editorData.getPostId(element))));
            }
            else
            {
              /* Delete the post because it is empty */
              editorData.deletePost(editorData.getPostId(element));
            }
          }
        }

        /* Add the new post */
        if (!request.getParameter("newpost").equals(""))
        {
          editorData.addPost(editorData.getUserId(request.getParameter("username")), request.getParameter("newpost"));
        }
      }
    }

    return mvc;
  }
}
