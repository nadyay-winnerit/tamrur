package infra.reporter;

import infra.AutomationException;
import infra.ui.Browser;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
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

    private static Robot robot;
    private static Rectangle rectangle = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());

    static {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

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
                "\nvar myArrayRows=document.querySelectorAll(\"tr[parentId='\"+getLevelId+\"']\");" +
                "\nfor(i=0;i<myArrayRows.length;i++)" +
                "\nmyArrayRows[i].style.display='table-row'}\n" +
                "function hideLevel(getLevelId){ " +
                "\n document.querySelector(\"tr[levelId='\"+getLevelId+\"' ]>td>img[src*='level.png'][onclick]\").style.display='inline-block';" +
                "\ndocument.querySelector(\"tr[levelId='\" +getLevelId+ \"']>td>img[src*='levelExpanded.png'][onclick]\").style.display='none';" +
                "\n var myArrayRows=document.querySelectorAll(\"tr[parentId^='\"+getLevelId+\"']\");" +
                "\nfor(i=0;i<myArrayRows.length;i++)" +
                "\n{" +
                "\nvar imgElement = myArrayRows[i].querySelector(\" td img[src*='levelExpanded.png']\")" +
                "\n if(imgElement!=null)" +
                "\n {imgElement.style.display='none';" +
                "\n  myArrayRows[i].querySelector(\" td img[src*='level.png']\").style.display='inline-block';}" +
                "\nmyArrayRows[i].style.display='none';" +

                "\n}" +
                " }\n" +
                "    </script>\n" +
                "</head>\n" +
                "<body style='text-align: left; font-family: tahoma;'>\n" +
                "<div>\n" +
                "    <table>\n" +
                "        <tr>\n" +
                "            <td><b>Date:</b></td>\n" +
                "            <td>" + sdf2.format(new Date()) + "</td>\n" +
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
        _reportRow(msg, moreInfo, true, false);

    }

    public void error(String errMsg, String moreInfo) {
        //counter to count the error's number
        errCounter++;
        _reportRow(errMsg, moreInfo, false, false);
    }

    public void openLevel(String msg, String moreInfo) {
        _reportRow(msg, moreInfo, true, true);
        listLevelId.add(0);
    }

    private String _createRowHtml(String msg, String moreInfo, boolean isPass, boolean isOpen) {
        listLevelId.set(listLevelId.size() - 1, listLevelId.get(listLevelId.size() - 1) + 1);
        String levelId = getLevelId();
        String parentId = getParentId();
        String date = sdf1.format(new Date());
        String imgSrc = (isPass ? "passed.png" : "failed.png");
        appendHtml("<tr id='" + id + "' levelId='" + levelId + "' parentId='" + parentId + "' style='display: " + isOuter(parentId) + "'>\n" +
                "        <td><img src='../resources/reporter/" + imgSrc + "'/></td>\n" +
                "        <td>" + levelImage(isOpen, levelId, parentId) + msg + "</td>\n" +
                "        <td>" + date + "</td>\n" +
                (!isPass ? "        <td><img id='" + id + "' src='../resources/reporter/screenshot.png' onclick='showScreenshot(this)'/>\n" +
                        "            <div screen id='" + id + "_screen' style='display: none;'><img id='_" + id + "_screen' src='../resources/reporter/close.png'\n" +
                        "                                                                  width='18' style='float: right;'\n" +
                        "                                                                  onclick='closeWindow(this)'/> <img\n" +
                        "                    src='screenshots/screenshot_" + id + ".png' width='100%' height='100%'/></div>\n" +
                        "        </td>" : "<td/>"));
        if (moreInfo != null)
            moreInfo(moreInfo);
        else
            appendHtml("<td/>");
        appendHtml("</tr>");

        id++;
        return date;
    }

    private String getParentId() {
        ArrayList<Integer> srcList = new ArrayList<>(listLevelId);
        srcList.remove(listLevelId.size() - 1);
        return getReplaceToString(srcList);
    }

    private String getLevelId() {

        return getReplaceToString(listLevelId);
    }


    public void error(String errMsg, String moreInfo, Throwable e) {
        String moreInfoWithThrowable;
        moreInfoWithThrowable = (moreInfo != null ? moreInfo : "");
        moreInfoWithThrowable += "/n/r " + AutomationException.printAble(e);
        errCounter++;
        _reportRow(errMsg, moreInfoWithThrowable, false, false);

    }

    private String getReplaceToString(List<Integer> list) {
        String str = list.toString();
        return str.replace("[", "").replace("]", "")
                .replace(", ", "_");
    }

    public void closeLevel() {
        listLevelId.remove(listLevelId.size() - 1);
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


    public void result(String rsltMsg, String moreInfo, boolean result) {
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
        if (parentId.trim().isEmpty())
            style = "table-row";
        else
            style = "none";
        return style;
    }


    //get the text to write in the html file, save The sending function clear, without TRY/CATCH
    private void appendHtml(String msg) {
        try {

            FileUtils.writeStringToFile(this.html, msg, StandardCharsets.UTF_8, true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void _reportRow(String msg, String moreInfo, boolean isPass, boolean isOpen) {

        String date = _createRowHtml(msg, moreInfo, isPass, isOpen);//האם יש ענין להחזיר את הdate

        System.out.println("[" + date + "][" + (isPass ? "MSG" : "ERR") + "]" + msg + (moreInfo == null ? "" : "\r\n\t" + moreInfo));
    }


    //check if there are no errors, return true. if there are any errors, return false
    public boolean ifNoErrors() {
        return errCounter == 0;
    }

    public void resetErrorCounter() {
        errCounter = 0;
    }

    //func to take Screenshot
    public static void takeScreenshot(int id) {
        if (!Browser.isOpen())
            return;
        String screenshotFileName = "reporter/screenshots/screenshot_" + id + ".png";
        try {
            byte[] _screenshot;
            try {
                _screenshot = ((TakesScreenshot) Browser.driver()).getScreenshotAs(OutputType.BYTES);
            } catch (Throwable t) {
                BufferedImage img = robot.createScreenCapture(rectangle);
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
    }
}

