package model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@SequenceGenerator(name = "SEQ_ID", sequenceName = "buyer_seq_id")
@Entity
@Table(name = "buyer")
public class Buyer
{
    private static final long serialVersionUID = -4665355705722204062L;

    @Transient
    public static final String ID_VALUE = "id";

    @Transient
    public static final String FIRST_NAME_VALUE = "firstName";

    @Transient
    public static final String MIDDLE_NAME_VALUE = "middleName";

    @Transient
    public static final String LAST_NAME_VALUE = "lastName";

    @Transient
    public static final String BIRTH_DATE_VALUE = "birthDate";

    @Transient
    public static final String PHONE_NUMBER_VALUE = "phoneNumber";

    @Transient
    public static final String LIVING_ADDRESS_VALUE = "livingAddress";

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

    @OneToMany(mappedBy = "buyer",cascade = CascadeType.ALL,fetch = FetchType.LAZY,targetEntity = Discount.class)
    @JsonManagedReference
    private Set<Discount> discounts = new HashSet<Discount>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sale_id")
    @JsonBackReference
    private Sale sale;

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }

    public Buyer() {
    }

    public Buyer( String firstName, String middleName, String lastName, Date birthDate, String phoneNumber, String livingAddress) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.livingAddress = livingAddress;
    }

    public Buyer(String firstName, String middleName, String lastName, Date birthDate, String phoneNumber, String livingAddress, Set<Discount> discounts) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.livingAddress = livingAddress;
        this.discounts = discounts;
    }

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

    public Set<Discount> getDiscounts() {
        return discounts;
    }

    public void setDiscounts(Set<Discount> discounts) {
        this.discounts = discounts;
    }
}
