package com.anvna.blog.util;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName MyBeanUtils
 * @Description 获取所有属性为空属性的属性名数组
 * @Author an_vna
 * @Date 2020/10/22 20:48
 * @Version V1.0
 **/
public class MyBeanUtils {

    public static String[] getNullFieldsName(Object source){
        BeanWrapper beanWrapper = new BeanWrapperImpl(source);
        PropertyDescriptor[] pds = beanWrapper.getPropertyDescriptors();
        List<String> nullFieldsNames = new ArrayList<>();
        for( PropertyDescriptor pd : pds ){
            String fieldsName = pd.getName();
            if( beanWrapper.getPropertyValue(fieldsName) == null ){
                nullFieldsNames.add(fieldsName);
            }
        }
        return nullFieldsNames.toArray(new String[nullFieldsNames.size()]);
    }

}
