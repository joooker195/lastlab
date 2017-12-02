package service;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class Hdb
{
    @Repository
    static class Plane
    {
        @PersistenceContext
        private static EntityManager entityManager;

        @Transactional
        public List<model.Plane> getAll(){//++
            String query = "from Plane order by id";
            TypedQuery<model.Plane> typedQuery = entityManager.createQuery(query, model.Plane.class);
            List<model.Plane> planes = typedQuery.getResultList();
            return planes;
        }

        @Transactional
        public void add(model.Plane plane){
            entityManager.persist(plane);
        }

        @Transactional
        public void update(model.Plane plane){
            entityManager.merge(plane);
        }

        @Transactional
        public model.Plane get(int id){
            return entityManager.find(model.Plane.class, id);
        }

        @Transactional
        public void remove(int id)
        {
            model.Plane plane = this.get(id);
            entityManager.remove(plane);
        }

        @Transactional
        public ArrayList<model.Plane> getWhere(String id,
                                               String name, String number, String passengerSeatsCount, String fuelCons)
        {
            List<model.Plane> planes = this.getAll();
            ArrayList<model.Plane> arr = new ArrayList<>();
            for(model.Plane plane: planes)
            {
                if( String.valueOf(plane.getId()).equals(id) || plane.getNumber().equals(number) || plane.getName().equals(name)
                        || String.valueOf(plane.getFuelConsumption()).equals(fuelCons)
                        || String.valueOf(plane.getPassengerSeatsCount()).equals(passengerSeatsCount))
                {
                    arr.add(plane);
                }
            }
            if(arr.size() == 0)
            {
                return (ArrayList) planes;
            }
            else
            {
                return arr;
            }
        }

    }

    @Repository
    static class Airport
    {
        @PersistenceContext
        private EntityManager entityManager;

        @Transactional
        public List<model.Airport> getAll(){//++
            String query = "from modal.Airport order by id";
            TypedQuery<model.Airport> typedQuery = entityManager.createQuery(query, model.Airport.class);
            List<model.Airport> airports = typedQuery.getResultList();
            return airports;
        }

        @Transactional
        public void add(model.Airport airport){
            entityManager.persist(airport);
        }

        @Transactional
        public void update(model.Airport airport){
            entityManager.merge(airport);
        }

        @Transactional
        public model.Airport get(int id){
            return entityManager.find(model.Airport.class,id);
        }

        @Transactional
        public void remove(int id){

            model.Airport airport = this.get(id);
            entityManager.remove(airport);
        }

        @Transactional
        public ArrayList<model.Airport> getWhere(String id,String location,String title)
        {
            List<model.Airport> airports = this.getAll();
            ArrayList<model.Airport> arr = new ArrayList<>();
            for(model.Airport airport: airports)
            {
                if(String.valueOf(airport.getId()).equals(id) || airport.getLocation().equals(location)
                        || airport.getName().equals(title))
                {
                    arr.add(airport);
                }
            }
            if(arr.size() == 0)
            {
                return (ArrayList) airports;
            }
            else
            {
                return arr;
            }
        }
    }

    @Repository
    static class Route
    {
        @PersistenceContext
        private EntityManager entityManager;

        @Transactional
        public List<model.Route> getAll(){//++
            String query = "from modal.Airport order by id";
            TypedQuery<model.Route> typedQuery = entityManager.createQuery(query, model.Route.class);
            List<model.Route> routes = typedQuery.getResultList();
            return routes;
        }

        @Transactional
        public void add(model.Route route){
            entityManager.persist(route);
        }

        @Transactional
        public void update(model.Route route){
            entityManager.merge(route);
        }

        @Transactional
        public model.Route get(int id){
            return entityManager.find(model.Route.class,id);
        }

        @Transactional
        public void remove(int id){
            model.Route route = this.get(id);
            entityManager.remove(route);
        }

        @Transactional
        public ArrayList<model.Route> getWhere(String id, String distance, String tp, String lp)
        {
            List<model.Route> routes = this.getAll();
            ArrayList<model.Route> arr = new ArrayList<>();
            for(model.Route r: routes)
            {
                if(String.valueOf(r.getId()).equals(id) || String.valueOf(r.getDistance()).equals(distance) || String.valueOf(r.getLandingPort()).equals(lp)
                        || String.valueOf(r.getTakeOffPort()).equals(tp))
                {
                    arr.add(r);
                }
            }
            if(arr.size() == 0)
            {
                return (ArrayList) routes;
            }
            else
            {
                return arr;
            }
        }
    }

    @Repository
    static class Flight
    {
        @PersistenceContext
        private EntityManager entityManager;

        @Transactional
        public List<model.Flight> getAll(){//++
            String query = "from modal.Flight order by id";
            TypedQuery<model.Flight> typedQuery = entityManager.createQuery(query, model.Flight.class);
            List<model.Flight> flights = typedQuery.getResultList();
            return flights;
        }

        @Transactional
        public void add(model.Flight flight){
            entityManager.persist(flight);
        }

        @Transactional
        public void update(model.Flight flight){
            entityManager.merge(flight);
        }

        @Transactional
        public model.Flight get(int id){
            return entityManager.find(model.Flight.class,id);
        }

        @Transactional
        public void remove(int id){
            model.Flight flight = this.get(id);
            entityManager.remove(flight);
        }

        @Transactional
        public ArrayList<model.Flight> getWhere(String id, String idp, String idr, String tiof, String lt)
        {
            List<model.Flight> flights = this.getAll();
            ArrayList<model.Flight> arr = new ArrayList<>();
            for(model.Flight f: flights)
            {
                if(String.valueOf(f.getId()).equals(id) || String.valueOf(f.getRoute()).equals(idr)
                        || String.valueOf(f.getPlane()).equals(idp) || f.getLandingTimeShedule().equals(lt) || f.getTakeOffTimeShedule().equals(tiof))
                {
                    arr.add(f);
                }
            }
            if(arr.size() == 0)
            {
                return (ArrayList) flights;
            }
            else
            {
                return arr;
            }
        }
    }

}
