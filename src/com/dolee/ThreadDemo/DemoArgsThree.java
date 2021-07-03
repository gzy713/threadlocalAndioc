package com.dolee.ThreadDemo;

import com.dolee.ThreadDemo1.DemoArgsFour;
import com.dolee.annotation.Diyautowire;


public class DemoArgsThree {


    @Diyautowire
    DemoArgsFour DemoArgsFour;


    void callMethod(){

        DemoArgsFour.callMethod();

    }

}
