package service;

import model.Buyer;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class BuyerService {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public List<Buyer> getAll(){//++
        String query = "from Buyer order by id";
        TypedQuery<Buyer> typedQuery = entityManager.createQuery(query, Buyer.class);
        List<Buyer> buyers = typedQuery.getResultList();
//        buyers.forEach( elm -> {
//            elm.getDiscounts().size();
//        });
        return buyers;
    }

    @Transactional
    public void addBuyer(Buyer buyer){
        entityManager.persist(buyer);
    }

    @Transactional
    public void updateBuyer(Buyer newBuyer){
        entityManager.merge(newBuyer);
    }

    @Transactional
    public Buyer getBuyerById(int id){
        return entityManager.find(Buyer.class,id);
    }

    @Transactional
    public void removeBuyer(Buyer buyer){
        entityManager.remove(buyer);
    }
}
