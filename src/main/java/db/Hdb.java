package db;

import model.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@Repository
public class Hdb
{

    @Repository
    public static class Buyer
    {
        @PersistenceContext
        private EntityManager entityManager;

        @Transactional
        public List<model.Buyer> getAll(){//++
            String query = "from Buyer order by id";
            TypedQuery<model.Buyer> typedQuery = entityManager.createQuery(query, model.Buyer.class);
            List<model.Buyer> buyers = typedQuery.getResultList();
            buyers.forEach( elm -> {
                elm.getDiscounts().size();
            });
            return buyers;
        }

        @Transactional
        public void addBuyer(model.Buyer buyer){
            entityManager.persist(buyer);
        }

        @Transactional
        public void updateBuyer(model.Buyer newBuyer){
            entityManager.merge(newBuyer);
        }

        @Transactional
        public model.Buyer getBuyerById(int id){
            return entityManager.find(model.Buyer.class,id);
        }

        @Transactional
        public void removeBuyer(model.Buyer buyer){
            entityManager.remove(entityManager.contains(buyer) ? buyer : entityManager.merge(buyer));
        }
    }

    @Repository
    public static class Product
    {
        @PersistenceContext
        private EntityManager entityManager;

        public Product() {
        }

        public EntityManager getEntityManager() {
            return entityManager;
        }

        public void setEntityManager(EntityManager entityManager) {
            this.entityManager = entityManager;
        }

        @Transactional
        public List<model.Product> getAll(){//++
            String query = "from Product order by id";
            TypedQuery<model.Product> typedQuery = entityManager.createQuery(query, model.Product.class);
            List<model.Product> resultList = typedQuery.getResultList();
            resultList.forEach( elm -> {
                elm.getDiscounts().size();
            });
            return resultList;
        }

        @Transactional
        public void saveProducr(model.Product product){
            entityManager.persist(product);
        }

        @Transactional
        public void removeProduct(model.Product product){
            entityManager.remove(product);
        }

        @Transactional
        public model.Product findProductById(int id){
            return entityManager.find(model.Product.class,id);
        }

        @Transactional
        public void update(model.Product product){
            entityManager.merge(product);
        }

        @Transactional
        public void delete(model.Product product){
            entityManager.remove(entityManager.contains(product) ? product : entityManager.merge(product));
        }
    }

    @Repository
    public static class Discount
    {
        @PersistenceContext
        private EntityManager entityManager;

        public Discount(){

        }

        @Transactional
        public List<model.Discount> getAllDisc(){
            String query = "from Discount order by id";
            TypedQuery<model.Discount> typedQuery = entityManager.createQuery(query, model.Discount.class);
            List<model.Discount> discounts = typedQuery.getResultList();
            return discounts;
        }

        @Transactional
        public void addDiscount(model.Discount discount) throws Exception{
            entityManager.merge(discount);
        }

        @Transactional
        public void updateDiscount(model.Discount discount){
            entityManager.merge(discount);
        }

        @Transactional
        public model.Discount findDiscountById(int id){
            return entityManager.find(model.Discount.class,id);
        }

        @Transactional
        public void deleteDiscount(model.Discount discount){
            entityManager.remove(entityManager.contains(discount) ? discount : entityManager.merge(discount));
        }
    }

    @Repository
    public static class Sale
    {
        public Sale()
        {

        }

        @PersistenceContext
        private EntityManager entityManager;

        @Transactional
        public List<model.Sale> getAllSells(){
            String query = "from Sale order by id";
            TypedQuery<model.Sale> typedQuery = entityManager.createQuery(query, model.Sale.class);
            List<model.Sale> sales = typedQuery.getResultList();
            sales.forEach( elm -> {
                elm.getProducts().size();// lazy init
            });
            sales.forEach(sale->sale.getProducts().forEach(product -> product.getDiscounts().size()));
            sales.forEach(sale->sale.getBuyers().forEach(buyer -> buyer.getDiscounts().size()));
            sales.forEach(sale->sale.getBuyers().size());
           // sales.forEach(sale->sale.getSellers().size());
            return sales;
        }

        @Transactional
        public void addSale(model.Sale sale){
            entityManager.merge(sale);
        }

        @Transactional
        public void updateSale(model.Sale sale){
            entityManager.merge(sale);
        }

        @Transactional
        public model.Sale getSaleById(int id) {
            return entityManager.find(model.Sale.class,id);
        }

        @Transactional
        public void deleteSale(model.Sale sale){
            entityManager.remove(entityManager.contains(sale) ? sale : entityManager.merge(sale));
        }
    }

    @Repository
    public static class Seller
    {
        @PersistenceContext
        private EntityManager entityManager;

        public Seller()
        {

        }

        @Transactional
        public List<model.Seller> getAllSellers(){
            String query = "from Seller order by id";
            TypedQuery<model.Seller> typedQuery = entityManager.createQuery(query, model.Seller.class);
            List<model.Seller> sellers = typedQuery.getResultList();
            sellers.forEach(seller->seller.getSale().getProducts().size());
            sellers.forEach(seller -> seller.getSale().getProducts().forEach(product -> product.getDiscounts().size()));
            sellers.forEach(seller -> seller.getSale().getBuyers().size());
            return sellers;
        }

        @Transactional
        public model.Seller getSellerById(int id){
            return entityManager.find(model.Seller.class,id);
        }

        @Transactional
        public void addSeller(model.Seller seller){
            entityManager.merge(seller);
        }

        @Transactional
        public void updateSeller(model.Seller seller){
            entityManager.merge(seller);
        }

        @Transactional
        public void deleteSeller(model.Seller seller){
            entityManager.remove(entityManager.contains(seller) ? seller : entityManager.merge(seller));
        }
    }

}
