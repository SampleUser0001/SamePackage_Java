/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package ittimfn.same;

import ittimfn.same.controller.Controller;

public class App {
    public void exec(String[] args) {
        Controller c1 = new Controller();
        Controller c2 = new Controller();

        c1.exec("hoge");
        c2.exec("piyo");
    }

    public static void main(String[] args) {
        new App().exec(args);;
    }
}
