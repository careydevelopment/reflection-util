package us.careydevelopment.util.reflection.harness;

import us.careydevelopment.model.Address;

public class AddressHarness {

    public static final String STREET1 = "123 Main St.";
    public static final String CITY = "Anytown";
    public static final String STATE = "NC";
    
    public static Address getValidAddress() {
        final Address address = new Address();

        address.setCity(CITY);
        address.setState(STATE);
        address.setStreet1(STREET1);
        
        return address;
    }
    
}
