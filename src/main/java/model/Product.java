package model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@SequenceGenerator(name = "SEQ_ID", sequenceName = "product_seq_id")
@Entity
@Table(name = "product")
public class Product
{
    private static final long serialVersionUID = -372011220359696447L;

    @Transient
    public static final String ID_VALUE = "id";

    @Transient
    public static final String NAME_VALUE = "name";

    @Transient
    public static final String UNIT_COAST_VALUE = "unitCoast";

    @Transient
    public static final String UNIT_NAME_VALUE = "unitName";

    @Transient
    public static final String SALE_ID = "selectedSale";

    @Id
    @GeneratedValue(generator = "SEQ_ID")
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "unitcoast")
    private int unitCoast;

    @Column(name = "unitname")
    private String unitName;

    @OneToMany(mappedBy = "product",fetch = FetchType.LAZY,targetEntity = Discount.class,cascade = CascadeType.ALL)
    private Set<Discount> discounts = new HashSet<Discount>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sale_id")
    private Sale sale;


    public Set<Discount> getDiscounts() {
        return discounts;
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }

    public void setDiscounts(Set<Discount> discounts) {
        this.discounts = discounts;
    }

    public Product(String name, int unitCoast, String unitName, Set<Discount> discounts) {
        this.name = name;
        this.unitCoast = unitCoast;
        this.unitName = unitName;
        this.discounts = discounts;
    }

    public Product(String name, int unitCoast, String unitName) {
        this.name = name;
        this.unitCoast = unitCoast;
        this.unitName = unitName;
    }

    public Product(String name, int unitCoast, String unitName, Sale sale) {
        this.name = name;
        this.unitCoast = unitCoast;
        this.unitName = unitName;
        this.sale = sale;
    }

    public Product(int id, String name, int unitCoast, String unitName) {
        this.id = id;
        this.name = name;
        this.unitCoast = unitCoast;
        this.unitName = unitName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUnitCoast() {
        return unitCoast;
    }

    public void setUnitCoast(int unitCoast) {
        this.unitCoast = unitCoast;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }
}
