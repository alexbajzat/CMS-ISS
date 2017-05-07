package view.controllers;

/**
 * Created by Paul on 5/7/17.
 */
public class TestController extends BaseController {

    public void start() {
        String info = getData().get("info").toString();
        showDialog(info, "Checheraut alea alea");
    }

}
