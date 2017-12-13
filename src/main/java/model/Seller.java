package model;

import javax.persistence.*;
import java.util.Date;

@SequenceGenerator(name = "SEQ_ID", sequenceName = "seller_seq_id")
@Entity
@Table(name = "seller")
public class Seller
{
    private static final long serialVersionUID = -2766873791972479486L;

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
    public static final String EMAIL_VALUE = "email";

    @Transient
    public static final String DELIVERY_ADDRESS_VALUE = "deliveryAddress";

    @Transient
    public static final String SALE_ID="saleSelect";


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

    @Column(name = "email")
    private String email;

    @Column(name = "deliveryaddress")
    private String deliveryAddress;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sale_id")
    private Sale sale;

    public Seller() {
    }

    public Seller(int id, String firstName, String middleName, String lastName, Date birthDate, String email, String deliveryAddress) {
        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.email = email;
        this.deliveryAddress = deliveryAddress;
    }

    public Seller(String firstName, String middleName, String lastName, Date birthDate, String email, String deliveryAddress, Sale sale) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.email = email;
        this.deliveryAddress = deliveryAddress;
        this.sale = sale;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }
}
