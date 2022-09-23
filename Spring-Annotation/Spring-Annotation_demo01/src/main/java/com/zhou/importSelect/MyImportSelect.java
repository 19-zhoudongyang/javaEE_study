package com.zhou.importSelect;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;


public class MyImportSelect implements ImportSelector {
    //AnnotationMetadata:当前标注@Import注解的类的所有注解信息
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {

        //返回值就是导入到容器的全类名
        return new String[]{"com.zhou.conditional.WindowsConditional"};
    }
}
