package com.gnanavad.utils.bday_wisher.model;

public class StudentDetails {
    private String name;
    private String batch;
    private String branchCode;
    private String dob;
    private String email;
    private String phoneNumber;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("StudentDetails [name=");
        builder.append(name);
        builder.append(", batch=");
        builder.append(batch);
        builder.append(", branchCode=");
        builder.append(branchCode);
        builder.append(", dob=");
        builder.append(dob);
        builder.append(", email=");
        builder.append(email);
        builder.append(", phoneNumber=");
        builder.append(phoneNumber);
        builder.append("]");
        return builder.toString();
    }

}
