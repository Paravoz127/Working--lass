package kpfu.itis.group11_801.kilin.workingClass.database;

public class CompanyRegistrationObject {
    private Company company;
    private int code;

    public CompanyRegistrationObject(Company company, int code) {
        this.company = company;
        this.code = code;
    }

    public Company getCompany() {
        return company;
    }

    public int getCode() {
        return code;
    }
}
