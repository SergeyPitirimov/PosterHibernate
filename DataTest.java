package Poster;

import java.util.LinkedList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class DataTest
{
  
  public DataTest()
  {
  }
  
  @BeforeClass
  public static void setUpClass()
  {
  }
  
  @AfterClass
  public static void tearDownClass()
  {
  }
  
  @Before
  public void setUp()
  {
  }
  
  @After
  public void tearDown()
  {
  }

  /**
   * Test of addUser method, of class Data.
   */
  @Test
  public void testAddUser()
  {
    System.out.println("addUser");
    String userName = "";
    String userPassword = "";
    Data instance = new Data();
    instance.addUser(userName, userPassword);
    // TODO review the generated test code and remove the default call to fail.
//    fail("The test case is a prototype.");
  }

  /**
   * Test of getName method, of class Data.
   */
  @Test
  public void testGetName()
  {
    System.out.println("getName");
    int userId = 0;
    Data instance = new Data();
    String expResult = "";
    String result = instance.getName(userId);
//    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
//    fail("The test case is a prototype.");
  }

  /**
   * Test of getUserId method, of class Data.
   */
  @Test
  public void testGetUserId()
  {
    System.out.println("getUserId");
    String userName = "";
    Data instance = new Data();
    int expResult = 0;
    int result = instance.getUserId(userName);
//    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
//    fail("The test case is a prototype.");
  }

  /**
   * Test of getPostId method, of class Data.
   */
  @Test
  public void testGetPostId()
  {
    System.out.println("getPostId");
    String userPost = "";
    Data instance = new Data();
    int expResult = 0;
    int result = instance.getPostId(userPost);
//    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
//    fail("The test case is a prototype.");
  }

  /**
   * Test of updatePost method, of class Data.
   */
  @Test
  public void testUpdatePost()
  {
    System.out.println("updatePost");
    int postId = 0;
    String updatedPost = "";
    Data instance = new Data();
    int expResult = 0;
    int result = instance.updatePost(postId, updatedPost);
//    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
//    fail("The test case is a prototype.");
  }

  /**
   * Test of addPost method, of class Data.
   */
  @Test
  public void testAddPost()
  {
    System.out.println("addPost");
    int userId = 0;
    String postToAdd = "";
    Data instance = new Data();
    int expResult = 0;
    int result = instance.addPost(userId, postToAdd);
//    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
//    fail("The test case is a prototype.");
  }

  /**
   * Test of getPosts method, of class Data.
   */
  @Test
  public void testGetPosts()
  {
    System.out.println("getPosts");
    int userId = 0;
    Data instance = new Data();
    LinkedList<String> expResult = null;
    LinkedList<String> result = instance.getPosts(userId);
//    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
//    fail("The test case is a prototype.");
  }

  /**
   * Test of deletePost method, of class Data.
   */
  @Test
  public void testDeletePost()
  {
    System.out.println("deletePost");
    int postId = 0;
    Data instance = new Data();
    int expResult = 0;
    int result = instance.deletePost(postId);
//    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
//    fail("The test case is a prototype.");
  }
}
