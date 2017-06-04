package com.company;

public class Contacts {
    private String firstName;
    private String lastName;
    private String phone;
    private String email;

    public String getfirstName() {
        return firstName;
    }
 /*   public void setfirstName(String firstName) {
        this.firstName = firstName;
    }*/
    public String getlastName() {
        return lastName;
    }
/*    public void setlastName(String lastName) {
        this.lastName = lastName;
    }*/
    public String getPhone() {
        return phone;
    }
/*    public void setPhone(String phone) {
        this.phone = phone;
    }*/

    public String getEmail() {
        return email;
    }
/*    public void setEmail(String email) {
        this.email = email;
    }*/


    @Override
    public String toString() {
        return "First Name : "+firstName+
                " \nLast Name : "+lastName+
                " \nPhone number : "+phone+
                " \nEmail : "+email;
    }
}