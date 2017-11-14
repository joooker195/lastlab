package model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@SequenceGenerator(name = "SEQ_ID", sequenceName = "buyersequence")
@Entity
@Table(name = "client")
public class Buyer implements Serializable {

    private static final long serialVersionUID = -4665355705722204062L;
    @Id
    @GeneratedValue(generator = "SEQ_ID")
    @Column(name = "id")
    private int id;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "middlename")
    private String middleName;

    @Column(name = "lastname")
    private String lastName;

    @Temporal(TemporalType.DATE)
    @Column(name = "birthdate")
    private Date birthDate;

    @Column(name = "phonenumber")
    private String phoneNumber;

    @Column(name = "livingaddress")
    private String livingAddress;

//    @OneToMany(mappedBy = "buyer",cascade = CascadeType.ALL,fetch = FetchType.LAZY,targetEntity = Discount.class)
//    @JsonManagedReference
//    private Set<Discount> discounts = new HashSet<Discount>();


    public Buyer() {
    }

    public Buyer(int id, String firstName, String middleName, String lastName, Date birthDate, String phoneNumber, String livingAddress) {
        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.livingAddress = livingAddress;
    }
//
//    public Buyer(String firstName, String middleName, String lastName, Date birthDate, String phoneNumber, String livingAddress, Set<Discount> discounts) {
//        this.firstName = firstName;
//        this.middleName = middleName;
//        this.lastName = lastName;
//        this.birthDate = birthDate;
//        this.phoneNumber = phoneNumber;
//        this.livingAddress = livingAddress;
//        this.discounts = discounts;
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getLivingAddress() {
        return livingAddress;
    }

    public void setLivingAddress(String livingAddress) {
        this.livingAddress = livingAddress;
    }

//    public Set<Discount> getDiscounts() {
//        return discounts;
//    }
//
//    public void setDiscounts(Set<Discount> discounts) {
//        this.discounts = discounts;
//    }
}
