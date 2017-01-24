package Poster;

import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Users
{
  private int userId;
  private String name;
  private String password;
  private int key;
  
  private static final String LOGGER_PROPERTIES_FILE = "/home/pitirimov/Javaworks/Poster/log4j.properties";
  private static Logger logger = LoggerFactory.getLogger(Users.class);

  public Users()
  {
    super();
  }
  
  public Users(int userId, String name, String password)
  {
    super();
    /* Logger configuration */
    PropertyConfigurator.configure(LOGGER_PROPERTIES_FILE);

    this.userId = userId;
    this.name = name;
    this.password = password;
  }

  public int getUserId()
  {
    return userId;
  }

  public void setUserId(int userId)
  {
    this.userId = userId;
  }

  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public String getPassword()
  {
    return password;
  }

  public void setPassword(String password)
  {
    this.password = password;
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