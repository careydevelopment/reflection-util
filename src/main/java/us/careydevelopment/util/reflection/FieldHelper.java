package us.careydevelopment.util.reflection;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

/**
 * Handles field-level parsing and processing.
 * 
 * @author Brian Carey
 *
 */
public class FieldHelper {

    /**
     * Takes a dot-notation string (e.g., address.city or address.street1)
     * and transforms it into List of field names.
     * 
     * @param dotSeparatedFieldNames
     * @return list of nested fields
     */
    protected static List<String> parse(String dotSeparatedFieldNames) {
        if (StringUtils.isBlank(dotSeparatedFieldNames)) throw new ReflectionHelperException("Field name is null!");
        
        List<String> nestedFields = new ArrayList<>();
        
        if (!StringUtils.isBlank(dotSeparatedFieldNames)) {
            String[] parts = dotSeparatedFieldNames.split("\\.");
            
            for (String part : parts) {
                nestedFields.add(part.trim());
            }            
        }
        
        return nestedFields;
    }
    
    /**
     * Gets the actual field object from its name.
     * 
     * Throws a runtime exception if the field doesn't exist
     * 
     * @param fieldName
     * @param obj
     * @return field
     */
    protected static Field getFieldFromName(String fieldName, Object obj) {
        if (StringUtils.isBlank(fieldName)) throw new ReflectionHelperException("Field name is null!");
        
        Field field = null;
        Field[] objectFields = obj.getClass().getDeclaredFields();
        
        for (Field objectField : objectFields) {
            if (objectField.getName().equals(fieldName)) {
                field = objectField;
                break;
            }
        }
        
        if (field == null) throw new ReflectionHelperException("Can't find field with name " + fieldName);
        
        return field;
    }
}
