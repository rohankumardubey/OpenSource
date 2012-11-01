/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javaio.highperf;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import junit.framework.TestCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Chetan Narsude <chetan@malhar-inc.com>
 */
public class ClientTest extends TestCase
{
  private static final Logger logger = LoggerFactory.getLogger(ClientTest.class);

  public ClientTest(String testName)
  {
    super(testName);
  }

  public void testSomeMethod() throws IOException
  {
    Process proc = Runtime.getRuntime().exec(new String[] {
              "/bin/sh",
              "-c",
              "nc -l 5033 2>/tmp/nc | dd bs=4096 of=/dev/null 2>/tmp/dd"
            });


    Client client = new Client("cnlindes", 5033);
    client.run();

    BufferedInputStream buffer = new BufferedInputStream(proc.getInputStream());
    BufferedReader commandOutput = new BufferedReader(new InputStreamReader(buffer));
    try {
      String line;
      while ((line = commandOutput.readLine()) != null) {
        System.out.println("command output: " + line);
      }//end while

      commandOutput.close();
    }
    catch (IOException e) {
      //log and/or handle it
    }//end catch

  }
}
