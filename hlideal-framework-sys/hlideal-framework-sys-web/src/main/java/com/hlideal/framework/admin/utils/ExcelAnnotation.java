package com.hlideal.framework.admin.utils;

import java.lang.annotation.*;

/**
 * Created by Intellij idea
 * User: liu.y
 * Date: 2016/12/27 0027 17:03
 * Description:
 * To change this template use File | Setting | File and Code Templates
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ExcelAnnotation {

    boolean export_excel() default  false;

}
