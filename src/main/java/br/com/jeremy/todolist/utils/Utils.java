package br.com.jeremy.todolist.utils;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;


public class Utils {

    public static void copyNonNullProperties(Object source, Object target) {
        BeanUtils.copyProperties(source, target, getNullPropertyNames(source));
    }

    public static String[] getNullPropertyNames(Object source){
        final BeanWrapper src = new BeanWrapperImpl(source);

        PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();

        for (PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyDescriptor(pd.getName());
            if(srcValue == null) {
                emptyNames.add(pd.getName());
            }
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);

    }
    
}



    // public void copyNonNullProperties(Object source, Object target) {
    //     BeanUtilsBean notNull = new NullAwareBeanUtilsBean();
    //     try {
    //         notNull.copyProperties(target, source);
    //     } catch (IllegalAccessException | InvocationTargetException e) {
    //         throw new RuntimeException(e);
    //     }
    // }

    // private static class NullAwareBeanUtilsBean extends BeanUtilsBean {
    //     @Override
    //     public void copyProperty(Object dest, String name, Object value)
    //             throws IllegalAccessException, InvocationTargetException {
    //         if (value != null) {
    //             super.copyProperty(dest, name, value);
    //         }
    //     }
    // }

    // public String[] getNullPropertyNames(Object source){
    //     final BeanWrapper src = new BeanWrapperImpl(source);

    //     PropertyDescriptor[] pds = src.getPropertyDescriptors();

    //     Set<String> emptyNames = new HashSet<>();

    //     for (PropertyDescriptor pd : pds) {
    //         Object srcValue = src.getPropertyDescriptor(pd.getName());
    //         if(srcValue == null) {
    //             emptyNames.add(pd.getName());
    //         }
    //     }
    //     String[] result = new String[emptyNames.size()];
    //     return emptyNames.toArray(result);

    // }