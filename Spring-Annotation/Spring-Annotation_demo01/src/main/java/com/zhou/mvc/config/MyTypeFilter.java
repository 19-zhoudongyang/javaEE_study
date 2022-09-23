package com.zhou.mvc.config;

import org.springframework.core.io.Resource;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

import java.io.IOException;


public class MyTypeFilter implements TypeFilter {
    //metadataReader:读取到的当前正在扫描的类的信息
    //metadataReaderFactory:可以获取到其他任何类信息的
    @Override
    public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
        //获取当前类注解的信息
        AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();
        //获取当前正在扫描的类信息
        ClassMetadata classMetadata = metadataReader.getClassMetadata();
        //获取类名
        String className = classMetadata.getClassName();
        //获取当前类资源(类的路径)
        Resource resource = metadataReader.getResource();
        //如果类名包含"er",则对其进行扫描
        if ("er".contains(className)){
            return true;
        }
        return false;
    }
}
