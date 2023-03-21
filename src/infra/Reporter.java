package infra;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Reporter {

    private static Reporter reporter;
    private static SimpleDateFormat sdf= new SimpleDateFormat("HH:mm:ss dd-MM-yyyy");
    private File html;

    public static Reporter reporter(){  //check if a report is existed
        if(reporter == null) {
            System.out.println("@@@@@@@@");
            reporter = new Reporter();
        }
        return reporter;
    }

    //constructor
    private Reporter(){
        System.out.println("!!!!!!!!!!!!!!!!!!!!!");

        //create a new html file, it's name is with the current time
        html= new File(new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date())+".html");

        //write the start text to the file
        try {
            FileUtils.writeStringToFile(this.html, "<!DOCTYPE html>\n" +
                    "<html>\n" +
                    "<head>\n" +
                    "    <title>Reporter</title>\n" +
                    "</head>\n" +
                    "\n" +
                    "<body>\n" +
                    "    <table>\n" +
                    "        <tr>\n" +
                    "            <th>Date</th>\n" +
                    "            <th>Severity</th>\n" +
                    "            <th>Message</th>\n" +
                    "        </tr>\n", StandardCharsets.UTF_8, true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        /*try {
            new FileWriter(html).write("<!DOCTYPE html>\n" +
                    "<html>\n" +
                    "<head>\n" +
                    "    <title>Reporter</title>\n" +
                    "</head>\n" +
                    "\n" +
                    "<body>\n" +
                    "    <table>\n" +
                    "        <tr>\n" +
                    "            <th>Date</th>\n" +
                    "            <th>Severity</th>\n" +
                    "            <th>Message</th>\n" +
                    "        </tr>\n" +
                    "    </table>\n" +
                    "</body>\n" +
                    "\n" +
                    "</html>");
        } catch (IOException e) {
            throw new RuntimeException(e);
            // titles(th): date-time , severity, message
        }*/
    }

    //methods

    public void message(String msg){
        System.out.println("enter to message");
        try {
            FileUtils.writeStringToFile(this.html, "<tr>\n" +
                    "<td>["+ sdf.format(new Date()) +"] </td>\n " +
                    "<td class= error>[INFO] </td>\n" +
                    "<td>"+msg+"</td>\n" +
                    "</tr>\n", StandardCharsets.UTF_8, true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void error(String errMsg){
        System.out.println("enter to error");
        try {
            FileUtils.writeStringToFile(this.html, "<tr>\n" +
                    "<td>["+ sdf.format(new Date()) +"] </td>\n " +
                    "<td class= error>[ERROR] </td>\n" +
                    "<td>"+ errMsg +"</td>\n" +
                    "</tr>\n", StandardCharsets.UTF_8, true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void result(String rsltMsg, boolean result){
        System.out.println("enter to result");
        if(result)
            message(rsltMsg);
        else error(rsltMsg);
    }

    //complete the file - write the end of the table, body and html
    public void completionHtmlFile(){
        try {
            FileUtils.writeStringToFile(this.html, "\n</table>\n" +
                    "</body>\n" +
                    "\n" +
                    "</html>", StandardCharsets.UTF_8, true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
