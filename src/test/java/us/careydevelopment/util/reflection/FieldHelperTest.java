package us.careydevelopment.util.reflection;

import java.lang.reflect.Field;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import us.careydevelopment.model.Employee;
import us.careydevelopment.util.reflection.harness.EmployeeHarness;

public class FieldHelperTest {

    @Test
    public void testParseWithMultipleNestedFields() {
        final String fieldName = "employee.address.city";
        final List<String> fields = FieldHelper.parse(fieldName);
        final List<String> expectedFields = List.of("employee", "address", "city");
        
        Assertions.assertEquals(expectedFields, fields);
    }
    
    @Test
    public void testParseWithSingleField() {
        final String fieldName = "address";
        final List<String> fields = FieldHelper.parse(fieldName);
        final List<String> expectedFields = List.of("address");
        
        Assertions.assertEquals(expectedFields, fields);
    }
    
    @Test
    public void testParseWithNullFieldName() {
        final String fieldName = null;
        
        Assertions.assertThrows(ReflectionHelperException.class, () -> FieldHelper.parse(fieldName));
    }
    
    @Test
    public void testParseWithEmptyFieldName() {
        final String fieldName = "";
        
        Assertions.assertThrows(ReflectionHelperException.class, () -> FieldHelper.parse(fieldName));
    }
    
    @Test
    public void testParseWithWhitespaceOnlyFieldName() {
        final String fieldName = "    ";
        
        Assertions.assertThrows(ReflectionHelperException.class, () -> FieldHelper.parse(fieldName));
    }
    
    @Test
    public void testGetFieldFromNameWithNullFieldName() {
        final String fieldName = null;
        final Employee employee = EmployeeHarness.getValidEmployee();
        
        Assertions.assertThrows(ReflectionHelperException.class, () -> FieldHelper.getFieldFromName(fieldName, employee));
    }
    
    @Test
    public void testGetFieldWithEmptyFieldName() {
        final String fieldName = "";
        final Employee employee = EmployeeHarness.getValidEmployee();
        
        Assertions.assertThrows(ReflectionHelperException.class, () -> FieldHelper.getFieldFromName(fieldName, employee));
    }
    
    @Test
    public void testGetFieldWithWhitespaceOnlyFieldName() {
        final String fieldName = "    ";
        final Employee employee = EmployeeHarness.getValidEmployee();
        
        Assertions.assertThrows(ReflectionHelperException.class, () -> FieldHelper.getFieldFromName(fieldName, employee));
    }

    @Test
    public void testGetFieldFromNameWithValidField() {
        final String fieldName = "firstName";
        final Employee employee = EmployeeHarness.getValidEmployee();
        final Field field = FieldHelper.getFieldFromName(fieldName, employee);
        
        Assertions.assertNotNull(field);
        Assertions.assertEquals(fieldName, field.getName());
    }
    
}
