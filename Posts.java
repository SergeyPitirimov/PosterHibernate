package Poster;

import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Posts
{
  private int userId;
  private int postId;
  private String post;
  private int key;
  
  private static final String LOGGER_PROPERTIES_FILE = "/home/pitirimov/Javaworks/Poster/log4j.properties";
  private static Logger logger = LoggerFactory.getLogger(Posts.class);
  
  public Posts()
  {
    super();
  }

  public Posts(int userId, int postId, String post)
  {
    super();
    /* Logger configuration */
    PropertyConfigurator.configure(LOGGER_PROPERTIES_FILE);
    
    this.userId = userId;
    this.postId = postId;
    this.post = post;
  }

  public int getUserId()
  {
    return userId;
  }

  public void setUserId(int userId)
  {
    this.userId = userId;
  }

  public int getPostId()
  {
    return postId;
  }

  public void setPostId(int postId)
  {
    this.postId = postId;
  }

  public String getPost()
  {
    return post;
  }

  public void setPost(String post)
  {
    this.post = post;
  }

  public int getKey()
  {
    return key;
  }

  public void setKey(int key)
  {
    this.key = key;
  }

}