package model;

import javax.persistence.*;
import java.util.Date;

@SequenceGenerator(name = "SEQ_ID", sequenceName = "discount_seq_id")
@Entity
@Table(name = "discount")
public class Discount
{
    private static final long serialVersionUID = -2952730516290458737L;

    @Transient
    public static final String ID_VALUE = "id";

    @Transient
    public static final String ACTUAL_FROM_VALUE = "actualFrom";

    @Transient
    public static final String ACTUAL_TO_VALUE = "actualTo";

    @Transient
    public static final String AMOUNT_DISCOUNT_VALUE = "amountDiscount";

    @Transient
    public static final String BUYER_ID = "selectedBuyer";

    @Transient
    public static final String PRODUCT_ID = "selectedProduct";

    @Id
    @GeneratedValue(generator = "SEQ_ID")
    @Column(name = "id")
    private int id;

    @Column(name = "actingfrom")
    private Date actualFrom;

    @Column(name = "actingto")
    private Date actualTo;

    @Column(name = "amountDiscount")
    private int amountDiscount;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "buyer_id")
    private Buyer buyer;


    public Discount() {
    }

    public Discount(Date actualFrom, Date actualTo, int amountDiscount, Product product) {
        this.actualFrom = actualFrom;
        this.actualTo = actualTo;
        this.amountDiscount = amountDiscount;
        this.product = product;
    }

    public Discount(Date actualFrom, Date actualTo, int amountDiscount, Product product, Buyer buyer) {
        this.actualFrom = actualFrom;
        this.actualTo = actualTo;
        this.amountDiscount = amountDiscount;
        this.product = product;
        this.buyer = buyer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getActualFrom() {
        return actualFrom;
    }

    public void setActualFrom(Date actualFrom) {
        this.actualFrom = actualFrom;
    }

    public Date getActualTo() {
        return actualTo;
    }

    public void setActualTo(Date actualTo) {
        this.actualTo = actualTo;
    }


    public int getAmountDiscount() {
        return amountDiscount;
    }

    public void setAmountDiscount(int amountDiscount) {
        this.amountDiscount = amountDiscount;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }
}
