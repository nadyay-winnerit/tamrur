package infra.reporter;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Reporter {

    private static Reporter reporter;
    private static SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm:ss");
    private static SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MM-yyyy");
    private File html;
    private static int errCounter = 0;
    private int id = 1;
    private List<Integer> listLevelId = new ArrayList<>();

//    private static Robot robot;
//    private static Rectangle rectangle = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
//    static {
//        try {
//            ImageUtils.robot = new Robot();
//        } catch (AWTException e) {
//            e.printStackTrace();
//        }
//    }

    public static Reporter reporter() {  //Check if a report has already been created
        if (reporter == null) {
            reporter = new Reporter();
        }
        return reporter;
    }

    //constructor
    private Reporter() {
        //create a new html file
        html = new File("reporter/Reporter.html");
        html.delete();
        listLevelId.add(0);
        //write the start text to the file
        appendHtml("<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head><title>Reporter</title>\n" +
                "    <link rel='icon' type='image/png' href='../resources/reporter/icon.png'>\n" +
                "    <style type='text/css'>\n" +
                "                #tree tr:hover {            background-color: #efefef;        }        th, td {            padding: 5px 7px 5px 7px;        }        th {            color: dodgerblue;            border-bottom: 2px solid #c5c5c5;        }        img[onclick] {            cursor: pointer;        }        pre {            white-space: pre-wrap;            overflow: auto;            height: auto;            max-height: 700px;        }        div[screen] {            position: absolute;            z-index: 1000;            width: 80%;            height: 80%;            top: 100px;            left: 150px;            background-color: #f5f5f5;            padding: 20px;            border: #cccccc;            border-style: solid;            box-shadow: 10px 5px #dddddd;        }        div[info] {            position: absolute;            z-index: 1000;            width: 80%;            height: 700px;            top: 100px;            left: 150px;            background-color: #f5f5f5;            padding: 20px;            border: #cccccc;            border-style: solid;            box-shadow: 10px 5px #dddddd;        }\n" +
                "    </style>\n" +
                "    <script type='text/javascript'>\n" +
                "                function showMoreInfo(elm) {            document.getElementById(elm.id + '_info').style.display = 'block';        }     \n" +
                "   function showScreenshot(elm) {            document.getElementById(elm.id + '_screen').style.display = 'block';        }     \n  " +
                " function closeWindow(elm) {            document.getElementById(elm.id.substring(1)).style.display = 'none';        }\n" +
                "function showLevel(getLevelId){" +
                "\ndocument.querySelector(\"tr[levelId='\"+getLevelId+\"']>td>img[src*='levelExpanded.png'][onclick]\").style.display='inline-block';" +
                "\ndocument.querySelector(\"tr[levelId='\"+getLevelId+\"']>td>img[src*='level.png'][onclick]\").style.display='none';" +
                //"\nvar myArrayRows=document.querySelectorAll(\"tr[levelId^='\"+getLevelId+\"_']\");" +
                "\nvar myArrayRows=document.querySelectorAll(\"tr[parentId='\"+getLevelId+\"']\");" +
                "\nfor(i=0;i<myArrayRows.length;i++)" +
                "\nmyArrayRows[i].style.display='table-row'}\n" +
                "function hideLevel(getLevelId){ " +
                "\n document.querySelector(\"tr[levelId='\"+getLevelId+\"' ]>td>img[src*='level.png'][onclick]\").style.display='inline-block';" +
                "\ndocument.querySelector(\"tr[levelId='\" +getLevelId+ \"']>td>img[src*='levelExpanded.png'][onclick]\").style.display='none';" +
                "\n var myArrayRows=document.querySelectorAll(\"tr[parentId^='\"+getLevelId+\"']\");" +
                "\nfor(i=0;i<myArrayRows.length;i++)" +
                "\nmyArrayRows[i].style.display='none' " +
                " }\n" +
                "    </script>\n" +
                "</head>\n" +
                "<body style='text-align: left; font-family: tahoma;'>\n" +
                "<div>\n" +
                "    <table>\n" +
                "        <tr>\n" +
                "            <td><b>Date:</b></td>\n" +
                "            <td>" + sdf1.format(new Date()) + "</td>\n" +
                "        </tr>\n" +
                "        <tr>\n" +
                "            <td><b></b></td>\n" +
                "            <td></td>\n" +
                "        </tr>\n" +
                "    </table>\n" +
                "</div>\n" +
                "<table id='tree' style='border: 1px;'>\n" +
                "    <tr style='background-color: #cbddf3;'>\n" +
                "        <th width='16'></th>\n" +
                "        <th width='100%'>Message</th>\n" +
                "        <th width='96'>Timestamp</th>\n" +
                "        <th width='20'>&nbsp;&nbsp;&nbsp;&nbsp;</th>\n" +
                "        <th width='20'>More</th>\n" +
                "    </tr>");
    }

    //Methods

    public void message(String msg, String moreInfo) {
        //update the last place in a list (add 1)
        listLevelId.set(listLevelId.size() - 1, listLevelId.get(listLevelId.size() - 1) + 1);
        String levelId = getLevelId();
        String parentId = getParentId();
        appendHtml("<tr id=" + id + " levelId='" + levelId + "' parentId='" + parentId + "' style='display: " + isOuter(parentId) + "'>\n" +
                "        <td><img src='../resources/reporter/passed.png'/></td>\n" +
                "        <td>" + levelImage(false, levelId, parentId) + msg + "</td>\n" +
                "        <td>" + sdf1.format(new Date()) + "</td>\n"
                + "<td/>");

        if (moreInfo != null)
            moreInfo(moreInfo);
        else
            appendHtml("<td/>");
        appendHtml("</tr>");

        id++;
    }

    private String getParentId() {
        ArrayList<Integer> srcList = new ArrayList<>(listLevelId);
        srcList.remove(listLevelId.size() - 1);
        return getReplaceToString(srcList.toString());
    }

    private String getLevelId() {

        return getReplaceToString(listLevelId.toString());
    }

    private String getReplaceToString(String toString) {
        return toString.replace("[", "").replace("]", "")
                .replace(", ", "_");
    }

    public void closeLevel() {
        //listLevelId.set(listLevelId.size() - 1, 0);
        listLevelId.remove(listLevelId.size() - 1);
    }

    public void openLevel(String msg, String moreInfo) {
        listLevelId.set(listLevelId.size() - 1, listLevelId.get(listLevelId.size() - 1) + 1);
        String levelId = getLevelId();
        String parentId = getParentId();
        listLevelId.add(0);
        System.out.println("enter to message");
        appendHtml("<tr id=" + id + " levelId='" + levelId + "' parentId='" + parentId + "' style='display: " + isOuter(parentId) + "'>\n" +
                "        <td><img src='../resources/reporter/passed.png'/></td>\n" +
                "        <td>" + levelImage(true, levelId, parentId) + msg + "</td>\n" +
                "        <td>" + sdf1.format(new Date()) + "</td>\n"
                + "<td/>");
        if (moreInfo != null)
            moreInfo(moreInfo);
        else
            appendHtml("<td/>");
        appendHtml("</tr>");

        id++;
    }

    private void moreInfo(String moreInfo) {
        appendHtml("<td>&nbsp;&nbsp;<img id=" + id + " src='../resources/reporter/more.png' width='18' onclick='showMoreInfo(this)'/>\n" +
                "            <div info id='" + id + "_info' style='display: none;'><img id='_" + id + "_info' src='../resources/reporter/close.png'\n" +
                "                                                              width='18' style='float: right;'\n" +
                "                                                              onclick='closeWindow(this)'/>\n" +
                "                <div><pre>" + moreInfo + "\n" +
                "</pre>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "        </td>");
    }

    public void error(String errMsg, String moreInfo) {


        //counter to count the error's number
        errCounter++;
        listLevelId.set(listLevelId.size() - 1, listLevelId.get(listLevelId.size() - 1) + 1);
        String levelId = getLevelId();
        String parentId = getParentId();
        appendHtml("<tr id=" + id + " levelId='" + levelId + "' style='display: " + isOuter(parentId) + ";'>\n" +
                "        <td><img src='../resources/reporter/failed.png'/></td>\n" +
                "        <td>" + levelImage(false, levelId, parentId) + errMsg + "</td>\n" +
                "        <td>" + sdf1.format(new Date()) + "</td>\n" +
                "        <td><img id='" + id + "' src='../resources/reporter/screenshot.png' onclick='showScreenshot(this)'/>\n" +
                "            <div screen id='" + id + "_screen' style='display: none;'><img id='_" + id + "_screen' src='../resources/reporter/close.png'\n" +
                "                                                                  width='18' style='float: right;'\n" +
                "                                                                  onclick='closeWindow(this)'/> <img\n" +
                "                    src='screenshots/screenshot_" + id + ".png' width='100%' height='100%'/></div>\n" +
                "        </td>");
        if (moreInfo != null)
            moreInfo(moreInfo);
        else
            appendHtml("</td>");
        appendHtml("</tr>");

        id++;
    }

    public void result(String rsltMsg, String moreInfo, boolean result) {
        System.out.println("enter to result");
        if (result)
            message(rsltMsg, moreInfo);
        else error(rsltMsg, moreInfo);
    }

    public String levelImage(boolean open, String levelId, String parentId) {
        String levelImage;
        StringBuilder allIndent = new StringBuilder();
        allIndent.append("<img src='../resources/reporter/levelIndent.png'/>".repeat(listLevelId.size() - 1));
        if (open) {
            levelImage = "<img src='../resources/reporter/level.png' style='display: inline-block' onclick='showLevel(\"" + levelId + "\")'/>";

            levelImage += "<img src='../resources/reporter/levelExpanded.png' style='display: none' onclick='hideLevel(\"" + levelId + "\")'/>";

        } else {
            levelImage = "<img src='../resources/reporter/levelIndent.png'/>";
            levelImage += "<img src='../resources/reporter/levelIndent.png'/>";

        }
        return allIndent + levelImage;
    }

    public String isOuter(String parentId) {
        String style;
        //if (listLevelId.size() == 1 || (listLevelId.size() == 2 && listLevelId.get(listLevelId.size() - 1) == 0))
        if (parentId.trim().isEmpty())
            style = "table-row";
        else
            style = "none";
        return style;
    }

    //get the text to write in the html file, save The sending function clear, without TRY/CATCH
    public void appendHtml(String str) {
        try {
            FileUtils.writeStringToFile(this.html, str, StandardCharsets.UTF_8, true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //check if there are no errors, return true. if there are any errors, return false
    public boolean ifNoErrors() {
        if (errCounter == 0)
            return true;
        else return false;
    }


    /**
     * complete the file - write the end of the table, body and html
     * <p>
     * !!!צריך למצוא דרך איך לקרוא לפונקציה הזו לא מהראשי, אלא מתוך המחלקה הנוכחית!!!!
     */
    public void completionHtmlFile() {
        try {
            FileUtils.writeStringToFile(this.html, "\n</table>\n" +
                    "</body>\n" +
                    "\n" +
                    "</html>", StandardCharsets.UTF_8, true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void resetErrorCounter() {
        errCounter = 0;
    }

    //func to take Screenshot
   /* public static void takeScreenshot(int id) {
        String screenshotFileName = "reporter/screenshots/screenshot_" + id + ".png";
        try {
            byte[] _screenshot;
            try {
                _screenshot = ((TakesScreenshot) Browser.driver()).getScreenshotAs(OutputType.BYTES);
            } catch (Throwable t) {
                BufferedImage img = ImageUtils.robot.createScreenCapture(ImageUtils.rectangle);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageIO.write(img, "png", baos);
                baos.flush();
                _screenshot = baos.toByteArray();
                baos.close();
            }
            byte[] screenshot = _screenshot;

            new Runnable() {
                @Override
                public void run() {
                    try {
                        FileOutputStream fos = new FileOutputStream(screenshotFileName);
                        fos.write(screenshot);
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }.run();

        } catch (Throwable e) {
            e.printStackTrace();
        }
    }*/

}
