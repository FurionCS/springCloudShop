package com.spring.common.model.util.tools;

import com.sun.istack.internal.logging.Logger;
import org.hibernate.validator.internal.xml.PropertyType;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Mr.Cheng on 2017/9/24.
 */
public class BeanToMapUtil {
        private static final Logger LOGGER= Logger.getLogger(BeanToMapUtil.class);

        private BeanToMapUtil(){}
        /**
         * 将一个 Map 对象转化为一个 JavaBean
         * @param type 要转化的类型
         * @param map 包含属性值的 map
         * @return 转化出来的 JavaBean 对象
         * @throws IntrospectionException
         *             如果分析类属性失败
         * @throws IllegalAccessException
         *             如果实例化 JavaBean 失败
         * @throws InstantiationException
         *             如果实例化 JavaBean 失败
         * @throws InvocationTargetException
         *             如果调用属性的 setter 方法失败
         */
        public static Object convertMap(Class type, Map map)
                throws IntrospectionException, IllegalAccessException,
                InstantiationException, InvocationTargetException {
            BeanInfo beanInfo = Introspector.getBeanInfo(type); // 获取类属性
            Object obj = type.newInstance(); // 创建 JavaBean 对象

            // 给 JavaBean 对象的属性赋值
            PropertyDescriptor[] propertyDescriptors =  beanInfo.getPropertyDescriptors();
            for (int i = 0; i< propertyDescriptors.length; i++) {
                PropertyDescriptor descriptor = propertyDescriptors[i];
                String propertyName = descriptor.getName();

                if (map.containsKey(propertyName)) {
                    // 下面一句可以 try 起来，这样当一个属性赋值失败的时候就不会影响其他属性赋值。
                    Object value = map.get(propertyName);

                    Object[] args = new Object[1];
                    args[0] = value;
                    try {
                        Class classes=descriptor.getPropertyType();
                        if(classes.isEnum()){
                            descriptor.getWriteMethod().invoke(obj,Enum.valueOf(classes,value.toString()));
                        }else {
                            descriptor.getWriteMethod().invoke(obj, args[0]);
                        }
                    }catch (Exception e){
                        LOGGER.warning("转换类型失败：",args);
                    }
                }
            }
            return obj;
        }
        /**
         * 将一个 JavaBean 对象转化为一个  Map
         * @param bean 要转化的JavaBean 对象
         * @return 转化出来的  Map 对象
         * @throws IntrospectionException 如果分析类属性失败
         * @throws IllegalAccessException 如果实例化 JavaBean 失败
         * @throws InvocationTargetException 如果调用属性的 setter 方法失败
         */
        public static Map convertBean(Object bean)
                throws IntrospectionException, IllegalAccessException, InvocationTargetException {
            Class type = bean.getClass();
            Map returnMap = new HashMap();
            BeanInfo beanInfo = Introspector.getBeanInfo(type);

            PropertyDescriptor[] propertyDescriptors =  beanInfo.getPropertyDescriptors();
            for (int i = 0; i< propertyDescriptors.length; i++) {
                PropertyDescriptor descriptor = propertyDescriptors[i];
                String propertyName = descriptor.getName();
                if (!propertyName.equals("class")) {
                    Method readMethod = descriptor.getReadMethod();
                    Object result = readMethod.invoke(bean, new Object[0]);
                    if (result != null) {
                        returnMap.put(propertyName, result);
                    } else {
                        returnMap.put(propertyName, "");
                    }
                }
            }
            return returnMap;
        }
}
