package kz.itstep.model;

import kz.itstep.annotations.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.sql.Date;
import java.util.Objects;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Table("customer")
public class Customer {
    private Long id;
    private String lName;
    private String fName;
    private Date bDate;
    private String email;
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof Customer customer)) return false;
//        return Objects.equals(getId(), customer.getId()) && Objects.equals(getlName(), customer.getlName()) && Objects.equals(getfName(), customer.getfName()) && Objects.equals(getbDate(), customer.getbDate()) && Objects.equals(getEmail(), customer.getEmail());
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(getId(), getlName(), getfName(), getbDate(), getEmail());
//    }
//
//    @Override
//    public String toString() {
//        return "Customer{" +
//                "id=" + id +
//                ", lName='" + lName + '\'' +
//                ", fName='" + fName + '\'' +
//                ", bDate=" + bDate +
//                ", email='" + email + '\'' +
//                '}';
//    }
//
//    public Customer() {
//    }
//
//    public Customer(Long id, String lName, String fName, Date bDate, String email) {
//        this.id = id;
//        this.lName = lName;
//        this.fName = fName;
//        this.bDate = bDate;
//        this.email = email;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getlName() {
//        return lName;
//    }
//
//    public void setlName(String lName) {
//        this.lName = lName;
//    }
//
//    public String getfName() {
//        return fName;
//    }
//
//    public void setfName(String fName) {
//        this.fName = fName;
//    }
//
//    public Date getbDate() {
//        return bDate;
//    }
//
//    public void setbDate(Date bDate) {
//        this.bDate = bDate;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
}
