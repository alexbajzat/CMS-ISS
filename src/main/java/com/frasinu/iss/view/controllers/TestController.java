package com.frasinu.iss.view.controllers;

import org.springframework.stereotype.Controller;

/**
 * Created by Paul on 5/7/17.
 */
@Controller(value = "TestController")
public class TestController extends BaseController {

    public void start() {
        showDialog(getData().get("info").toString(), "Checheraut alea alea");
    }

}
