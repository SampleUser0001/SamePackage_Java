package ittimfn.same.controller;

import ittimfn.same.logger.AppLogger;

public class Controller {
    private AppLogger logger;

    public Controller() {
        this.logger = new AppLogger();
    }

    public void exec(String value) {
        try {
            this.logger.put(value);

            Thread.sleep(1000);
            this.logger.log("hogehoge");
        } catch(Exception e) {

        } finally {
            this.logger.remove();
        }
        
    }

}
