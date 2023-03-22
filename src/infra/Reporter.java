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
    private static int errCounter= 0;

    public static Reporter reporter(){  //Check if a report has already been created
        if(reporter == null) {
            reporter = new Reporter();
        }
        return reporter;
    }

    //constructor
    private Reporter(){
        //create a new html file, it's name is with the current time
        SimpleDateFormat reporterTS= new SimpleDateFormat("yyyyMMdd_HHmmss");
        html= new File(reporterTS.format(new Date())+".html");

        //write the start text to the file
        appendHtml("<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "    <title >Reporter</title>\n" +
                "\n" +
                "</head>\n" +
                "\n" +
                "<body style='text-align: left;'>\n" +
                "    <table style='background-color: ivory'>\n" +
                "        <tr style='background-color: peachpuff; text-align: left;'>\n" +
                "            <th>Date</th>\n" +
                "            <th>Severity</th>\n" +
                "            <th>Message</th>\n" +
                "        </tr>\n");
    }

    //Methods

    public void message(String msg){
        System.out.println("enter to message");
        appendHtml("<tr>\n" +
                "<td>["+ sdf.format(new Date()) +"] </td>\n " +
                "<td class= error>[INFO] </td>\n" +
                "<td>"+msg+"</td>\n" +
                "</tr>\n");
    }

    public void error(String errMsg){
        System.out.println("enter to error");

        //counter to count the error's number
        errCounter++;

        appendHtml("<tr style=\"color: red\">\n" +
                "<td>["+ sdf.format(new Date()) +"] </td>\n " +
                "<td class= error>[ERROR] </td>\n" +
                "<td>"+ errMsg +"</td>\n" +
                "</tr>\n");
    }

    public void result(String rsltMsg, boolean result){
        System.out.println("enter to result");
        if(result)
            message(rsltMsg);
        else error(rsltMsg);
    }

    //get the text to write in the html file, save The sending function clear, without TRY/CATCH
    public void appendHtml(String str){
        try {
            FileUtils.writeStringToFile(this.html, str, StandardCharsets.UTF_8, true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //check if there are no errors, return true. if there are any errors, return false
    public boolean ifNoErrors(){
        if(errCounter == 0)
            return true;
        else return false;
    }


    /**complete the file - write the end of the table, body and html
     *
     *  !!!צריך למצוא דרך איך לקרוא לפונקציה הזו לא מהראשי, אלא מתוך המחלקה הנוכחית!!!!
     */
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
