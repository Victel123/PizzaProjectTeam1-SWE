//Author: Garrett Dasher

class Customer {
    private String name;
    private String email;
    private String phoneNumber;
    private Address address;
    private Payment payment;

    public Customer(String name, String email, String phoneNumber, Address address, Payment payment){
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.payment = payment;
    }

    //Return Methods
    public String getName(){
        return name;
    }

    public String getPhoneNumber(){
        return phoneNumber;
    }

    public Address getAddress(){
        return address;
    }

    public Payment getPaymentMethod(){
        return payment;
    }

    public String getEmail(){
        return email;
    }

    //Mutator Methods
    public void setName(String name){
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }

    public void setAddress(Address address){
        this.address = address;
    }

    public void setPaymentMethod(Payment payment){
        this.payment = payment;
    }

    public void setEmail(String email){
        this.email = email;
    }

    @Override
    public String toString(){
        return "Name: " + name + "\nPhone Number: " + phoneNumber + "\nAddress: " + address + "\nPayment Method: " + payment;
    }
}