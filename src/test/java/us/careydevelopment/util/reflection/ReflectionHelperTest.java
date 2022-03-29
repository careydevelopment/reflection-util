package us.careydevelopment.util.reflection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import us.careydevelopment.model.Employee;
import us.careydevelopment.util.reflection.harness.EmployeeHarness;

public class ReflectionHelperTest {

    private static final Employee EMPLOYEE = EmployeeHarness.getValidEmployee();
    
    @Test
    public void testGetStringWithSingleValidField() {
        final ReflectionHelper reflectionHelper = new ReflectionHelper(EMPLOYEE);
        final String firstName = reflectionHelper.getString("firstName");
        
        Assertions.assertEquals(EmployeeHarness.FIRST_NAME, firstName);
    }
    
    @Test
    public void testGetStringWithSingleInvalidField() {
        final ReflectionHelper reflectionHelper = new ReflectionHelper(EMPLOYEE);
        Assertions.assertThrows(ReflectionHelperException.class, () -> reflectionHelper.getString("bogus"));
    }
    
    @Test
    public void testGetStringWithSingleEmptyField() {
        final ReflectionHelper reflectionHelper = new ReflectionHelper(EMPLOYEE);
        Assertions.assertThrows(ReflectionHelperException.class, () -> reflectionHelper.getString(""));
    }
}
