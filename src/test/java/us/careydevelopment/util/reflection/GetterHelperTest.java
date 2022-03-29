package us.careydevelopment.util.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import us.careydevelopment.model.Employee;
import us.careydevelopment.util.reflection.harness.AddressHarness;
import us.careydevelopment.util.reflection.harness.EmployeeHarness;

public class GetterHelperTest {

    private static final String FIRST_NAME = "firstName";
    private static final Employee EMPLOYEE = EmployeeHarness.getValidEmployee();
    private static final Field FIRST_NAME_FIELD = FieldHelper.getFieldFromName(FIRST_NAME, EMPLOYEE);

    
    @Test
    public void testGetGetterMethodFromFieldWithValidInputs() {
        Method method = GetterHelper.getGetterMethodFromField(FIRST_NAME_FIELD, EMPLOYEE);
        Assertions.assertEquals("getFirstName", method.getName());
    }
    
    @Test
    public void testInvokeGetterWithValidInputs() {
        Method method = GetterHelper.getGetterMethodFromField(FIRST_NAME_FIELD, EMPLOYEE);
        Object obj = GetterHelper.invokeGetter(method, EMPLOYEE);
        
        Assertions.assertNotNull(obj);
        Assertions.assertTrue(obj instanceof String);
        Assertions.assertTrue(((String)obj).equals(EmployeeHarness.FIRST_NAME));
    }
    
    @Test
    public void testGetStringValueFromNestedFieldsWithValidInputs() {
        List<String> list = List.of("address", "city");
        String value = GetterHelper.getStringValueFromNestedFields(list, EMPLOYEE);
        
        Assertions.assertNotNull(value);
        Assertions.assertEquals(value, AddressHarness.CITY);
    }
    
    @Test
    public void testGetStringValueFromNestedFieldsWithEmptyList() {
        List<String> list = new ArrayList<>();
        Assertions.assertThrows(ReflectionHelperException.class, () -> GetterHelper.getStringValueFromNestedFields(list, EMPLOYEE));
    }
    
}
