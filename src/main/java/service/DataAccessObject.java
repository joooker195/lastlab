package service;

import exception.FlightNotFoundException;
import exception.PlaneNotFoundException;
import exception.RouteNotFoundException;
import model.Airport;
import model.Flight;
import model.Plane;
import model.Route;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DataAccessObject
{
    /**
     * @throws SQLException
     * @throws ClassNotFoundException
     */

    private static Hdb.Plane dbPlane = new Hdb.Plane();
    private static Hdb.Airport dbAirport = new Hdb.Airport();
    private static Hdb.Route dbRoute = new Hdb.Route();
    private static Hdb.Flight dbFlight = new Hdb.Flight();

    public static List<Plane> getPlanes() throws  ClassNotFoundException {
        return dbPlane.getAll();
    }
    public static Plane getPlane(int id) throws  PlaneNotFoundException {
        return dbPlane.get(id);
    }
    public static ArrayList<Plane> getPlaneSearch(String id,String name,String number,
                                                  String passengerSeatsCount,String fuelCons) throws ClassNotFoundException {
        return dbPlane.getWhere(id, name, number, passengerSeatsCount, fuelCons);
    }
    public static void setPlane(Plane plane) {
        dbPlane.update(plane);
    }
    public static void addPlane(Plane plane) {
        dbPlane.add(plane);
    }
    public static void deletePlane(int id) {
        dbPlane.remove(id);
    }



    public static List<Airport> getAirports() {
        return dbAirport.getAll();
    }
    public static List<Airport> getAirportsSearch(String id,String name,String location){
        return dbAirport.getWhere(id,location,name);
    }
    public static Airport getAirport(int id) throws RouteNotFoundException{
        return dbAirport.get(id);
    }
    public static void setAirport(Airport airport){
        dbAirport.update(airport);
    }
    public static void addAirport(Airport airport){
        dbAirport.add(airport);
    }
    public static void deleteAirport(int id){
        dbAirport.remove(id);
    }


    public static List<Route> getRoutes(){
        return dbRoute.getAll();
    }
    public static List<Route> getRoutesSearch(String id, String takeOffId, String landId,String dist){
        return dbRoute.getWhere(id, dist,takeOffId, landId);
    }
    public static Route getRoute(int id) throws RouteNotFoundException {
        return dbRoute.get(id);
    }
    public static void setRoute(Route route){
        dbRoute.update(route);
    }
    public static void addRoute(Route route){
        dbRoute.add(route);
    }
    public static void deleteRoute(int id){
        dbRoute.remove(id);
    }


    public static List<Flight> getFlights()throws java.text.ParseException{
        return dbFlight.getAll();
    }
    public static List<Flight> getFlightsSearch(String id,String routeId, String planeId,
                                                String takeOffTime, String landTime)throws java.text.ParseException{

        return dbFlight.getWhere(id,planeId, routeId, takeOffTime, landTime);
    }
    public static Flight getFlight(int id) throws FlightNotFoundException,java.text.ParseException{
        return dbFlight.get(id);
    }
    public static void setFlight(Flight flight){
        dbFlight.update(flight);
    }
    public static void addFlight(Flight flight){
        dbFlight.add(flight);
    }
    public static void deleteFlight(int id){
        dbFlight.remove(id);
    }
}
