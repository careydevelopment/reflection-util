package us.careydevelopment.util.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Includes a variety of helper methods for accessing
 * and invoking "getter" methods on an object.
 * 
 * @author Brian Carey
 *
 */
public class GetterHelper {

    private static final Logger LOG = LoggerFactory.getLogger(GetterHelper.class);

    
    /**
     * Given a Field object, this method will return the corresponding
     * getter method.
     * 
     * @param field
     * @param obj
     * @return getter method
     */
    protected static Method getGetterMethodFromField(final Field field, Object obj) {
        if (field == null) throw new ReflectionHelperException("Null field!");
        if (obj == null) throw new ReflectionHelperException("Null object!");
        
        Method method = null;
        
        Method[] objectMethods = obj.getClass().getDeclaredMethods();
        for (Method objectMethod : objectMethods) {
            final String methodName = objectMethod.getName().toLowerCase();
            
            if (methodName.startsWith("get")) {
                if (methodName.endsWith(field.getName().toLowerCase())) {
                    method = objectMethod;
                    break;
                }
            }
        }
        
        if (method == null) throw new ReflectionHelperException("Can't find getter method for field with name " + field.getName());
        
        return method;
    }
    
    /**
     * Invokes the given getter method
     * 
     * @param getter
     * @param currentObject
     * @return the object that the getter method returns
     */
    protected static Object invokeGetter(Method getter, Object currentObject) {
        Object obj = null;
        
        try {
            obj = getter.invoke(currentObject);
        } catch (InvocationTargetException ie) {
            LOG.error("Problem trying to invoke method!", ie);
            throw new ReflectionHelperException(ie.getMessage());
        } catch (IllegalAccessException ie) {
            LOG.error("Illegal access violation!", ie);
            throw new ReflectionHelperException(ie.getMessage());
        }
        
        return obj;
    }
    
    /**
     * Given a list of string objects, this method will step through each one as a field in a series
     * of nested objects. It will eventually return the String value from the last object.
     * 
     * For example: if the user passed in address.city as the string value, this method will first get
     * the address object from the parent object. Then it will get the city object from the address object.
     * 
     * @param nestedFields
     * @param currentObject
     * @return the value of the last property identified
     */
    protected static String getStringValueFromNestedFields(List<String> nestedFields, Object currentObject) {
        if (nestedFields == null || nestedFields.size() == 0) throw new ReflectionHelperException("Null or empty nested field list!");
        
        for (String fld : nestedFields) {
            Field objectField = FieldHelper.getFieldFromName(fld, currentObject);
            Method objectGetter = getGetterMethodFromField(objectField, currentObject);
            currentObject = invokeGetter(objectGetter, currentObject);
        }
        
        String val = (String)currentObject;
        return val;
    }
}
