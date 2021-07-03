package com.dolee.ThreadDemo;

import com.dolee.annotation.Diyautowire;
import com.dolee.annotation.ParseAnnotationUtils;
import com.dolee.context.Thread_Context;

import java.io.IOException;
import java.util.Map;

public class Demo {

    @Diyautowire
    DemoArgs demoArgs;

    void setLocal(){
        Thread_Context.getContext().threadLocalString.set("String");
        Thread_Context.getContext().threadLocalInteger.set(123);
    }



    public static void main(String[] args) throws Exception {
        Demo demo = new Demo();
        Thread_Context.getContext().cache.put(demo.getClass().getName(),demo);
        ParseAnnotationUtils.preseAnno(demo.getClass().getClassLoader());
        demo.setLocal();
        demo.demoArgs.callMethod();



    }

}
