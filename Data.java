package Poster;

import java.util.LinkedList;
import java.util.List;
import javax.annotation.ManagedBean;
import org.apache.log4j.PropertyConfigurator;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Projections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ManagedBean
public class Data
{
  private String name;
  private int userId;
  private int postId;
  
  private static final String LOGGER_PROPERTIES_FILE = "/home/pitirimov/Javaworks/Poster/log4j.properties";
  private static Logger logger = LoggerFactory.getLogger(Data.class);
  
  public static final int ALL_USERS = -1;
  
  public Data()
  {
    super();
    /* Logger configuration */
    PropertyConfigurator.configure(LOGGER_PROPERTIES_FILE);
  }

  public void addUser(final String userName, final String userPassword)
  {
    int maximumUserIdNumber = 0;

    SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
    Session session = sessionFactory.openSession();
    session.beginTransaction();

    /* Get the primary key values for users */
    Criteria criteria = session.createCriteria(Users.class, "Users");
    criteria.setProjection(Projections.projectionList().add(Projections.property("Users.key"), "Users.key"));
    List<Integer> primaryKey = criteria.list();

    /* Collect all users into the linked list */
    for(Integer userKey: primaryKey)
    {
      System.out.println("User's key is " + userKey);
      
      Users userWithMaxUserId = (Users)session.get(Users.class, userKey);

      if (userWithMaxUserId.getUserId() > maximumUserIdNumber)
      {
        maximumUserIdNumber = userWithMaxUserId.getUserId();
      }
    }

    /* Create the next user */
    Users user = new Users(maximumUserIdNumber + 1, userName, userPassword);

    session.save(user);
    session.getTransaction().commit();
    session.close();
  }

  public String getName(final int userId)
  {
    SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
    Session session = sessionFactory.openSession();
    session.beginTransaction();

    /* Get the primary key values for users */
    Criteria criteria = session.createCriteria(Users.class, "Users");
    criteria.setProjection(Projections.projectionList().add(Projections.property("Users.key"), "Users.key"));
    List<Integer> primaryKey = criteria.list();

    /* Collect all users into the linked list */
    for(Integer userKey: primaryKey)
    {
      Users user = (Users)session.get(Users.class, userKey);
      if (user.getUserId() == userId)
      {
        this.name = user.getName();
        session.close();
        return (name);
      }
    }

    /* There is no user with required ID in the database */
    session.close();
    return "Unknown user";
  }
  
  public int getUserId(final String userName)
  {
    SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
    Session session = sessionFactory.openSession();
    session.beginTransaction();

    /* Get the primary key values for users */
    Criteria criteria = session.createCriteria(Users.class, "Users");
    criteria.setProjection(Projections.projectionList().add(Projections.property("Users.key"), "Users.key"));
    List<Integer> primaryKey = criteria.list();

    /* Collect all users into the linked list */
    for(Integer userKey: primaryKey)
    {
      Users user = (Users)session.get(Users.class, userKey);
      if (user.getName().equals(userName))
      {
        this.userId = user.getUserId();
        session.close();
        return (userId);        
      }
    }

    /* There is no user with such user's name */
    session.close();
    return (-1);
  }

  /* This method returns post ID by the post content from the database */
  public int getPostId(final String userPost)
  {
    SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
    Session session = sessionFactory.openSession();
    session.beginTransaction();

    /* Get the primary key values for posts */
    Criteria criteria = session.createCriteria(Posts.class, "Posts");
    criteria.setProjection(Projections.projectionList().add(Projections.property("Posts.key"), "Posts.key"));
    List<Integer> primaryKey = criteria.list();

    /* Collect all posts into the linked list */
    for(Integer postKey: primaryKey)
    {
      Posts post = (Posts)session.get(Posts.class, postKey);
      if (post.getPost().equals(userPost))
      {
        this.postId = post.getPostId();
        session.close();
        return (postId);
      }
    }

    /* There is no post with such content */
    session.close();
    return (-1);
  }

  /* This method saves updated editPost into the database by its post ID and returns post ID */
  public int updatePost(final int postId, final String updatedPost)
  {
    SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
    Session session = sessionFactory.openSession();
    session.beginTransaction();

    /* Get the primary key values for posts */
    Criteria criteria = session.createCriteria(Posts.class, "Posts");
    criteria.setProjection(Projections.projectionList().add(Projections.property("Posts.key"), "Posts.key"));
    List<Integer> primaryKey = criteria.list();

    /* Collect all posts into the linked list */
    for(Integer postKey: primaryKey)
    {
      Posts post = (Posts)session.get(Posts.class, postKey);

      /* Update the post */
      if (post.getPostId() == postId)
      {
        post.setPost(updatedPost);
        session.save(post);
        session.getTransaction().commit();
      }
    }

    session.close();

    return (postId);
  }

  /* This method adds the post to the database */
  public int addPost(final int userId, String postToAdd)
  {
    int maximumPostIdNumber = 0;

    SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
    Session session = sessionFactory.openSession();
    session.beginTransaction();

    /* Get the primary key values for posts */
    Criteria criteria = session.createCriteria(Posts.class, "Posts");
    criteria.setProjection(Projections.projectionList().add(Projections.property("Posts.key"), "Posts.key"));
    List<Integer> primaryKey = criteria.list();

    /* Collect all posts into the linked list */
    for(Integer postKey: primaryKey)
    {
      Posts postWithMaxPostId = (Posts)session.get(Posts.class, postKey);

      if (postWithMaxPostId.getPostId() > maximumPostIdNumber)
      {
        maximumPostIdNumber = postWithMaxPostId.getPostId();
      }
    }

    /* Create and save new post */
    Posts post = new Posts(userId, maximumPostIdNumber + 1, postToAdd);
    session.save(post);
    session.getTransaction().commit();
    session.close();

    return (maximumPostIdNumber + 1);
  }

  /* This method returns the linked list of user's name and his post content */
  public LinkedList<String> getPosts(final int userId)
  {
    /* Create the linked list of names and posts */
    LinkedList<String> linkedListOfNamesAndPosts = new LinkedList<String>();

    SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
    Session session = sessionFactory.openSession();
    session.beginTransaction();

    /* Get the primary key values for posts */
    Criteria criteria = session.createCriteria(Posts.class, "Posts");
    criteria.setProjection(Projections.projectionList().add(Projections.property("Posts.key"), "Posts.key"));
    List<Integer> primaryKey = criteria.list();

    /* Collect all posts into the linked list */
    for(Integer postKey: primaryKey)
    {
      Posts post = (Posts)session.get(Posts.class, postKey);

      if (userId == ALL_USERS)
      {
        /* Insert the user's name to the linked list */
        linkedListOfNamesAndPosts.addLast(getName(post.getUserId()));
        /* Insert the post content to the linked list */
        linkedListOfNamesAndPosts.addLast(post.getPost());
      }
      else
      {
        /* Insert the post of required user */
        if (post.getUserId() == userId)
        {
          linkedListOfNamesAndPosts.addLast(post.getPost());
        }
      }
    }

    session.close();

    return linkedListOfNamesAndPosts;
  }

  /* This method deletes the post from the database */
  public int deletePost(final int postId)
  {

    SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
    Session session = sessionFactory.openSession();
    session.beginTransaction();

    /* Get the primary key values for posts */
    Criteria criteria = session.createCriteria(Posts.class, "Posts");
    criteria.setProjection(Projections.projectionList().add(Projections.property("Posts.key"), "Posts.key"));
    List<Integer> primaryKey = criteria.list();

    /* Collect all posts into the linked list */
    for(Integer postKey: primaryKey)
    {
      Posts post = (Posts)session.get(Posts.class, postKey);
      if (post.getPostId() == postId)
      {
        Object persistentInstance = session.load(Posts.class, postKey);

        if (persistentInstance != null)
        {
          session.delete(persistentInstance);
          session.getTransaction().commit();
        }
      }
    }

    session.flush();
    session.close();
    return (0);
  }
}