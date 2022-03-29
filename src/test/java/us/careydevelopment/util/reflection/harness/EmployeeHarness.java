package us.careydevelopment.util.reflection.harness;

import us.careydevelopment.model.Address;
import us.careydevelopment.model.Employee;

public class EmployeeHarness {

    public static final String FIRST_NAME = "Bri";
    public static final String LAST_NAME = "Care";
    
    public static Employee getValidEmployee() {
        final Employee employee = new Employee();
        final Address address = AddressHarness.getValidAddress();
        
        employee.setAddress(address);
        employee.setFirstName(FIRST_NAME);
        employee.setLastName(FIRST_NAME);
        
        return employee;
    }
}
