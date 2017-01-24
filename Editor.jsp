<%-- 
    This is the poster of user's messages.
    Contact: Sergey Pitirimov, sergey.pitirimov@innopolis.ru
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Poster.Data"%>
<%@page import="org.springframework.security.core.Authentication"%>
<%@page import="org.springframework.security.core.context.SecurityContextHolder"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Your posts are here</title>
  </head>
  <body>
    <%
      Authentication authEditorPage = SecurityContextHolder.getContext().getAuthentication();
      if (authEditorPage.getName() != null)
      {
    %>
        <h1>${username}, you may create or edit your posts here!</h1>

        <form method="post" action="Login.htm">
          New post: <input type="text" name = "newpost" value="" size="80" autofocus>
          <input type="submit" value="Publish">
          <input type="hidden" name = "username" value="${username}">
          <%
            Data editData = new Data();
      
            /* Print all posts of user, who has logged in */
            for(String post: editData.getPosts(editData.getUserId(authEditorPage.getName())))
            {
          %>
              <br>Edit post: <input type="text" name="<%=editData.getPostId(post)%>" value="<%=post%>" size="80">
            <%
            }
            %>
        </form>
    <%
      }
      else
      {
        response.sendRedirect("Login.htm");
      }
    %>
  </body>
</html>