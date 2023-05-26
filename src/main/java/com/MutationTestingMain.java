package com;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;

import com.mutation.exception.InvalidProjectException;
import com.mutation.html.HtmlReader;
import com.mutation.service.MutationTestingService;

/**
 * Main class of application
 *
 */
public class MutationTestingMain
{
  
  private static final Logger logger = LogManager.getLogger(MutationTestingMain.class);
  
  /**
   * Main method of application  
   * 
   * @param args
   */
  public static void main(String[] args)
  {
    try
    {
      String projectNameSelected = projectNameSelected();
      MutationTestingService mutationTestingService =
          new MutationTestingService(projectNameSelected);
      boolean isTestingCompleted = mutationTestingService.setupSelectedProjectAndPerformTesting();
      if (isTestingCompleted)
      {
        HtmlReader htmlReader = new HtmlReader(projectNameSelected);
        htmlReader.showReport();
      }
    }
    catch (InvalidProjectException | XmlPullParserException | IOException e)
    {
      logger.error(e.getMessage());
    }
  }
  
  
  /**
   * Display list of available projects in student-assignment-solution folder
   * 
   * @return project name selected by user
   * @throws InvalidProjectException
   */
  private static String projectNameSelected() throws InvalidProjectException
  {
    String currentDirectory = System.getProperty("user.dir");
    File directoryPath =
        new File(currentDirectory + File.separator + "student-assignment-solution");
    if (directoryPath.list() == null)
    {
      throw new InvalidProjectException("No project found in student-assignment-solution folder");
    }
    // List of all files and directories
    String[] contents = directoryPath.list();
    System.out.println("Option | Project Name");
    for (int i = 0; i < contents.length; i++)
    {
      int count = i + 1;
      System.out.println(count + " | " + contents[i]);
    }
    System.out.println();
    System.out.println("Enter your option : ");
    Scanner sc = new Scanner(System.in);
    int selectedChoice = sc.nextInt();
    selectedChoice--;
    if (selectedChoice >= contents.length)
    {
      System.out.println("Entered option is invalid");
    }
    sc.close();
    return contents[selectedChoice];
  }
  
}
