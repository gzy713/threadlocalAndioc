package com.dolee.ThreadDemo1;

import com.dolee.context.Thread_Context;

public class DemoArgsFour {


    public void callMethod(){

        System.out.println( Thread_Context.getContext().threadLocalString.get());
        System.out.println( Thread_Context.getContext().threadLocalInteger.get());

    }

}
