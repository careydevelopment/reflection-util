package us.careydevelopment.util.reflection;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * This class makes it easy to use reflection on any object.
 * It accepts dot-notation as the field name. For example,
 * you can pass in address.city and it will get the address object
 * from the parent object and then get the city object from the
 * address object.
 *  
 * @author Brian Carey
 *
 */
public class ReflectionHelper {
    
    private static final Logger LOG = LoggerFactory.getLogger(ReflectionHelper.class);

    private Object object;
    
    public ReflectionHelper (Object object) {
        if (object == null) throw new ReflectionHelperException("Object is null!");
        
        this.object = object;
    }
    
    /**
     * Gets a string value from the given field or nested fields.
     * 
     * You can pass in "firstName" as the field or "address.city".
     * 
     * @param field
     * @return the string value of the last property specified
     */
    public String getString(String field) {
        if (StringUtils.isEmpty(field)) throw new ReflectionHelperException("Null or empty field passed in!");
        
        LOG.debug("Attempting to retrieve value from field " + field + " on object " + object.getClass().getSimpleName());
        
        List<String> nestedFields = FieldHelper.parse(field);
        LOG.debug("Nested fields include " + nestedFields);
        
        String str = GetterHelper.getStringValueFromNestedFields(nestedFields, object);
        LOG.debug("Returned value is " + str);
        
        return str;
    }
}
