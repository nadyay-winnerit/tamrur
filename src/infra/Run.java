package infra;

public class Run {
    public static void main(String[] args) {
        int a=10, b=4;

        Reporter.reporter().message("hello");
        Reporter.reporter().error("xxxxx");
        Reporter r= Reporter.reporter();

        r.message("ggjkh");
        r.result("abc", a%b==0);  // like- if(a%b == 0)

        //complete the writing to the html file
        Reporter.reporter().completionHtmlFile();


    }
}
