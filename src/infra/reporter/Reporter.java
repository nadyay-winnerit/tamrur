package infra.reporter;

import infra.AutomationException;
import infra.ui.Browser;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;

import javax.imageio.ImageIO;
import java.awt.Rectangle;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.*;

public class Reporter {

    private static Reporter reporter;
    private static SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm:ss");
    private static SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MM-yyyy");
    private File html;
    private static int errCounter = 0;
    private int id = 1;
    private List<Integer> listLevelId = new ArrayList<>();
    private boolean hasScreenshot;

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
                "        #tree tr:hover {\n" +
                "            background-color: #efefef;\n" +
                "        }\n" +
                "        th, td {\n" +
                "            padding: 5px 7px 5px 7px;\n" +
                "        }\n" +
                "        th {\n" +
                "            color: dodgerblue;\n" +
                "            border-bottom: 2px solid #c5c5c5;\n" +
                "        }\n" +
                "        img[onclick] {\n" +
                "            cursor: pointer;\n" +
                "        }\n" +
                "        pre {\n" +
                "            white-space: pre-wrap;\n" +
                "            overflow: auto;\n" +
                "            height: auto;\n" +
                "            max-height: 700px;\n" +
                "        }\n" +
                "        div[screen] {\n" +
                "            position: absolute;\n" +
                "            z-index: 1000;\n" +
                "            width: 80%;\n" +
                "            height: 80%;\n" +
                "            top: 100px;\n" +
                "            left: 150px;\n" +
                "            background-color: #f5f5f5;\n" +
                "            padding: 20px;\n" +
                "            border: #cccccc;\n" +
                "            border-style: solid;\n" +
                "            box-shadow: 10px 5px #dddddd;\n" +
                "        }\n" +
                "        div[info] {\n" +
                "            position: absolute;\n" +
                "            z-index: 1000;\n" +
                "            width: 80%;\n" +
                "            height: 700px;\n" +
                "            top: 100px;\n" +
                "            left: 150px;\n" +
                "            background-color: #f5f5f5;\n" +
                "            padding: 20px;\n" +
                "            border: #cccccc;\n" +
                "            border-style: solid;\n" +
                "            box-shadow: 10px 5px #dddddd;\n" +
                "        }\n" +
                "    </style>\n" +
                "    <script type='text/javascript'>\n" +
                "        function showMoreInfo(elm) {\n" +
                "            document.getElementById(elm.id + '_info').style.display = 'block';\n" +
                "        }\n" +
                "        function showScreenshot(elm) {\n" +
                "            document.getElementById(elm.id + '_screen').style.display = 'block';\n" +
                "        }\n" +
                "        function closeWindow(elm) {\n" +
                "            document.getElementById(elm.id.substring(1)).style.display = 'none';\n" +
                "        }\n" +
                "        function showLevel(getLevelId) {\n" +
                "            document.querySelector(\"tr[levelId='\"+getLevelId+\"']>td>img[src*='levelExpanded.png'][onclick]\").style.display='inline-block';\n" +
                "            document.querySelector(\"tr[levelId='\"+getLevelId+\"']>td>img[src*='level.png'][onclick]\").style.display='none';\n" +
                "            var myArrayRows=document.querySelectorAll(\"tr[parentId='\"+getLevelId+\"']\");\n" +
                "            for(i=0;i<myArrayRows.length;i++)\n" +
                "                myArrayRows[i].style.display='table-row';\n" +
                "            }\n" +
                "        function hideLevel(getLevelId) {\n" +
                "            document.querySelector(\"tr[levelId='\"+getLevelId+\"' ]>td>img[src*='level.png'][onclick]\").style.display='inline-block';\n" +
                "            document.querySelector(\"tr[levelId='\" +getLevelId+ \"']>td>img[src*='levelExpanded.png'][onclick]\").style.display='none';\n" +
                "            var myArrayRows=document.querySelectorAll(\"tr[parentId^='\"+getLevelId+\"']\");\n" +
                "            for(i=0;i<myArrayRows.length;i++) {\n" +
                "                var imgElement = myArrayRows[i].querySelector(\" td img[src*='levelExpanded.png']\")\n" +
                "                if(imgElement!=null) {\n" +
                "                    imgElement.style.display='none';\n" +
                "                    myArrayRows[i].querySelector(\" td img[src*='level.png']\").style.display='inline-block';\n" +
                "                }\n" +
                "                myArrayRows[i].style.display='none';\n" +
                "            }\n" +
                "        }\n" +
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
                "            <td><b>Errors Count:</b></td>\n" +
                "            <td>" + errCounter + "</td>\n" +
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
                "    </tr>\n");
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

    public Reporter hasScreenshot() {
        this.hasScreenshot = true;
        return this;
    }

    private String _createRowHtml(String msg, String moreInfo, boolean isPass, boolean isLevel) {
        listLevelId.set(listLevelId.size() - 1, listLevelId.get(listLevelId.size() - 1) + 1);
        String levelId = getLevelId();
        String parentId = getParentId();
        String date = sdf1.format(new Date());

        appendHtml("<tr id='" + id + "' levelId='" + levelId + "' parentId='" + parentId + "' style='display: " + isOuter(parentId) + "'>\n" +
                "        <td><img src='../resources/reporter/" + (isPass ? "passed.png" : "failed.png") + "'/></td>\n" +
                "        <td>" + levelImage(isLevel, levelId) + msg + "</td>\n" +
                "        <td>" + date + "</td>\n");

        screenshot(!isPass || hasScreenshot);
        if (!isPass) {
            takeScreenshot();
        }

        moreInfo(moreInfo);
        appendHtml("</tr>");

        id++;
        return date;
    }

    private void screenshot(boolean hasScreenshot) {
        this.hasScreenshot = false;
        if (!hasScreenshot) {
            appendHtml("<td/>");
            return;
        }
        appendHtml("<td><img id='" + id + "' src='../resources/reporter/screenshot.png' onclick='showScreenshot(this)'/>\n" +
                "    <div screen id='" + id + "_screen' style='display: none;'>\n" +
                "        <img id='_" + id + "_screen' src='../resources/reporter/close.png'" +
                "             width='18' style='float: right;' onclick='closeWindow(this)'/>\n" +
                "        <img src='screenshots/screenshot_" + id + ".png' width='100%' height='100%'/>\n" +
                "    </div>\n" +
                "</td>");
    }

    public void error(String errMsg, String moreInfo, Throwable e) {
        String moreInfoWithThrowable = (moreInfo != null ? moreInfo : "");
        moreInfoWithThrowable += "/n/r " + AutomationException.printAble(e);
        errCounter++;
        _reportRow(errMsg, moreInfoWithThrowable, false, false);
    }

    public void closeLevel() {
        listLevelId.remove(listLevelId.size() - 1);
    }

    private void moreInfo(String moreInfo) {
        if (moreInfo == null) {
            appendHtml("<td/>");
            return;
        }
        appendHtml("<td>&nbsp;&nbsp;<img id=" + id + " src='../resources/reporter/more.png' width='18' onclick='showMoreInfo(this)'/>\n" +
                "    <div info id='" + id + "_info' style='display: none;'>\n" +
                "        <img id='_" + id + "_info' src='../resources/reporter/close.png'" +
                "              width='18' style='float: right;' onclick='closeWindow(this)'/>\n" +
                "        <div><pre>" + moreInfo + "</pre></div>\n" +
                "    </div>\n" +
                "</td>");
    }


    public void result(String rsltMsg, String moreInfo, boolean result) {
        if (result)
            message(rsltMsg, moreInfo);
        else
            error(rsltMsg, moreInfo);
    }

    private String getParentId() {
        ArrayList<Integer> srcList = new ArrayList<>(listLevelId);
        srcList.remove(listLevelId.size() - 1);
        return getReplaceToString(srcList);
    }

    private String getLevelId() {
        return getReplaceToString(listLevelId);
    }

    private String getReplaceToString(List<Integer> list) {
        return list.toString().replace("[", "").replace("]", "").replace(", ", "_");
    }

    private String levelImage(boolean open, String levelId) {
        StringBuilder allIndent = new StringBuilder();
        allIndent.append("<img src='../resources/reporter/levelIndent.png'/>");//.repeat(listLevelId.size() - 1));
        String levelImage;
        if (open) {
            levelImage = "<img src='../resources/reporter/level.png' style='display: inline-block' onclick='showLevel(\"" + levelId + "\")'/>";
            levelImage += "<img src='../resources/reporter/levelExpanded.png' style='display: none' onclick='hideLevel(\"" + levelId + "\")'/>";
        } else {
            levelImage = "<img src='../resources/reporter/levelIndent.png'/>";
            levelImage += "<img src='../resources/reporter/levelIndent.png'/>";
        }
        return allIndent + levelImage;
    }

    private String isOuter(String parentId) {
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

    private void _reportRow(String msg, String moreInfo, boolean isPass, boolean isLevel) {

        String date = _createRowHtml(msg, moreInfo, isPass, isLevel);//האם יש ענין להחזיר את הdate

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
    public void takeScreenshot() {
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

