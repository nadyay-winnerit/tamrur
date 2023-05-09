import infra.reporter.Reporter;

public class Run {
    public static void main(String[] args) {
        int a = 10, b = 4;

        Reporter.reporter().message("msg parent", null);
        Reporter.reporter().error("fail after close level1", null);
        Reporter.reporter().result("ok ", "info info", true);
        Reporter.reporter().openLevel("open another level1", "image change");
        Reporter.reporter().message("msg inside level1", null);
        Reporter.reporter().error("errorl in the Level", null);
        Reporter.reporter().openLevel("open another level1", "image change");
        Reporter.reporter().message("msg inside level1", null);
        Reporter.reporter().error("errorl in the Level", null);
        Reporter.reporter().closeLevel();
        Reporter.reporter().openLevel("open level2 inside level 1", "image change");
        Reporter.reporter().message("msg inside level2 ", null);
        Reporter.reporter().openLevel("open level3 inside level2", "image change");
        Reporter.reporter().message("msg inside level3", null);
        Reporter.reporter().closeLevel();
        Reporter.reporter().closeLevel();
        Reporter.reporter().closeLevel();
        Reporter.reporter().openLevel("open leve 1 after close all", "image change");
        Reporter.reporter().message("msg inside level1", null);
        Reporter.reporter().closeLevel();
        Reporter.reporter().openLevel("open level1 (after close prev level)", "image change");
        Reporter.reporter().message("msg1 inside level1", "more more");
        Reporter.reporter().result("parent error", null, false);
        Reporter.reporter().message("msg2 inside level1", null);
        Reporter.reporter().closeLevel();
        Reporter.reporter().message("msg1 parent after close    ", null);
        Reporter.reporter().message("msg2 parent after close", "more more");
        Reporter.reporter().message("msg3 parent after close", "more more");
        Reporter.reporter().result("parent error", null, false);


        // Reporter.reporter().error("xxxxx");
//        Reporter r= Reporter.reporter();
//
//        r.message("ggjkh");
//        r.result("abc", a%b==0);  // like- if(a%b == 0)

        //complete the writing to the html file
        //Reporter.reporter().completionHtmlFile();


    }
}
