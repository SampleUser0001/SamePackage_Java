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

            Thread.sleep(2000);
            this.logger.log("hogehoge");
            Thread.sleep(1000);
            throw new Exception();
        } catch(Exception e) {
            this.logger.log(e);
        } finally {
            this.logger.remove();
        }
        
    }

}
