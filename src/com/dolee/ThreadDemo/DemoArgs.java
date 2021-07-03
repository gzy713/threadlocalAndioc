package com.dolee.ThreadDemo;

import com.dolee.annotation.Diyautowire;

public class DemoArgs {

    @Diyautowire
    DemoArgsThree demoArgsThree;


    void callMethod(){

        demoArgsThree.callMethod();

    }


}
