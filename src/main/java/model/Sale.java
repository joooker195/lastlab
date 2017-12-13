package model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@SequenceGenerator(name = "SEQ_ID", sequenceName = "sale_seq_id")
@Entity
@Table(name = "sale")
public class Sale
{
    private static final long serialVersionUID = -1346203997433464035L;

    @Transient
    public static final String ID_VALUE = "id";

    @Transient
    public static final String ORDER_DATE_VALUE = "orderDate";

    @Transient
    public static final String DELIVERY_DATE_VALUE = "deliveryDate";

    @Transient
    public static final String AMOUNT_PRODUCT_VALUE = "amountProduct";

    @Transient
    public static final String SELECTED_BUYER_ID = "selectedBuyer";

    @Id
    @GeneratedValue(generator = "SEQ_ID")
    @Column(name = "id")
    private int id;

    @Temporal(TemporalType.DATE)
    @Column(name ="orderdate")
    private Date orderDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "deliverydare")
    private Date deliveryDate;


    @Column(name = "amountproduct")
    private int amountProduct;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "sale",targetEntity = Product.class)
    @JsonManagedReference
    private Set<Product> products = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "buyer_id")
    private Buyer buyer;



    public Sale() {
    }

    public Sale(int id, Date orderDate, Date deliveryDate, Set<Product> products, Set<Buyer> buyers, Set<Seller> sellers, int amountProduct) {
        this.id = id;
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
        this.amountProduct = amountProduct;
    }

    public Sale(Date orderDate, Date deliveryDate, int amountProduct, Set<Product> products) {
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
        this.amountProduct = amountProduct;
        this.products = products;
    }

    public Sale(Date orderDate, Date deliveryDate, int amountProduct, Buyer buyer) {
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
        this.amountProduct = amountProduct;
        this.buyer = buyer;
    }

    public Sale(Date orderDate, Date deliveryDate, int amountProduct, Set<Product> products, Buyer buyer) {
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
        this.amountProduct = amountProduct;
        this.products = products;
        this.buyer = buyer;
    }

    public int getId() {
        return id;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public int getAmountProduct() {
        return amountProduct;
    }

    public void setAmountProduct(int amountProduct) {
        this.amountProduct = amountProduct;
    }
}
