package model;

import db.DataAccessObject;
import exception.ComandException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * модель управляет источником данных, поддерживает работоспособность контроллера
 */
public class Model
{
    public Model(){
    }
    /**
     * возвращает весь список рейсов
     * @return список рейсов
     */
    public ArrayList getFlights()throws java.text.ParseException,IOException, ClassNotFoundException{
        return (ArrayList) DataAccessObject.getFlights();
    }
    public ArrayList getFlights(String id,String routeId,String planeId, String takeOffTime,String landingTime)throws  ComandException,java.text.ParseException{
        return (ArrayList) DataAccessObject.getFlightsSearch(id,routeId, planeId, takeOffTime, landingTime);
    }
    /**
     * возвращает весь список маршрутов
     * @return список маршрутов
     */
    public ArrayList getRoutes()throws IOException, ClassNotFoundException{
        return (ArrayList) DataAccessObject.getRoutes();
    }
    public ArrayList getRoutes(String id,String takeOffId,String LandId, String distanse)throws ComandException{
        return (ArrayList) DataAccessObject.getRoutesSearch(id, takeOffId, LandId, distanse);
    }
    /**
     * возвращает весь список аэропортов
     * @return список аэропортов
     */
    public ArrayList getAirports()throws IOException, ClassNotFoundException{
        return (ArrayList) DataAccessObject.getAirports();
    }
    public ArrayList getAirports(String id, String name,String location)throws ComandException{
        return(ArrayList) DataAccessObject.getAirportsSearch(id, name, location);
    }
    /**
     * возвращает весь список самолетов
     * @return список самолетов
     */
    public ArrayList getPlanes()throws IOException, ClassNotFoundException{
        return (ArrayList) DataAccessObject.getPlanes();
    }
    public ArrayList getPlanes(String id,String name,String number,
                               String passengerSeatsCount,String fuelCons) throws ComandException, ClassNotFoundException {
        return DataAccessObject.getPlaneSearch(id, name, number, passengerSeatsCount, fuelCons);
    }
    /**
     * выбор рейса по его id
     * @param id идентификатор элемента в коллекции
     * @return Рейс
     */
    public Flight takeFlight(int id)throws java.text.ParseException{
        return DataAccessObject.getFlight(id);
    }
    /**
     * выбор маршрута по его id
     * @param id идентификатор элемента в коллекции
     * @return маршрут
     */
    public Route takeRoute(int id) {
        return DataAccessObject.getRoute(id);
    }
    /**
     * выбор аэропорта по его id
     * @param id идентификатор элемента в коллекции
     * @return аэропорт
     */
    public Airport takeAirport(int id){
        return DataAccessObject.getAirport(id);
    }
    /**
     * выбор самолета по его id
     * @param id идентификатор элемента в коллекции
     * @return самолет
     */
    public Plane takePlane(int id)throws IOException, ClassNotFoundException{
        return DataAccessObject.getPlane(id);
    }
    public void setPlane(Plane plane)throws IOException, ClassNotFoundException{
        DataAccessObject.setPlane(plane);

    }
    public void setAirport(Airport airport)throws IOException, ClassNotFoundException{
        DataAccessObject.setAirport(airport);
    }
    public void setRoute(Route route)throws IOException, ClassNotFoundException{
        DataAccessObject.setRoute(route);
    }
    public void setFlight(Flight flight)throws IOException, ClassNotFoundException{
        DataAccessObject.setFlight(flight);
    }
    /**
     * добавляет самолет в список, возвращает его номер в списке
     * (если существует - заменяет кго)
     * если в списке есть объект с таким id - заменяет его, иначе добавляет новый
     * @param plane добавлекмый самолет
     * @return индекс самолета в коллекции после добавления
     */
    public void addPlane(Plane plane)throws IOException, ClassNotFoundException{
        DataAccessObject.addPlane(plane);
    }
    /**
     * добавляет аэропорт в список, возвращает его номер в списке
     * (если существует - заменяет его)
     * если в списке есть объект с таким id - заменяет его, иначе добавляет новый
     * @param airport добавляемый аэропорт
     * @return индекс аэропорта в коллекции после добавления
     */
    public void addAirport(Airport airport)throws IOException, ClassNotFoundException{
        DataAccessObject.addAirport(airport);
    }
    /**
     * добавляет маршрут в список, возвращает его номер в списке
     * (если существует - заменяет его)
     * если в списке есть объект с таким id - заменяет его, иначе добавляет новый
     * @param route добавляемый маршрут
     * @return индекс маршрута в коллекции после добавления
     */
    public void addRoute(Route route)throws IOException, ClassNotFoundException{
        DataAccessObject.addRoute(route);
    }
    /**
     * добавляет рейс в список, возвращает его номер в списке
     * (если существует - заменяет его)
     * если в списке есть объект с таким id - заменяет его, иначе добавляет новый
     * @param flight добавляемый рейс
     * @return индекс рейса в коллекции после добавления
     */
    public void addFlight(Flight flight)throws IOException, ClassNotFoundException{
        DataAccessObject.addFlight(flight);
    }

    /**
     * удаляет рейс
     * @param id - идентификатор рейса
     */
    public void deleteFlight(int id)throws IOException, ClassNotFoundException{
        DataAccessObject.deleteFlight(id);
    }
    /**
     * удаляет аэропорт
     * @param id - идентификатор аэропорта
     */
    public void deleteAirport(int id)throws IOException, ClassNotFoundException{
        DataAccessObject.deleteAirport(id);
        DataAccessObject.deleteAirport(id);
    }
    /**
     * удаляет маршрут
     * @param id - идентификатор маршрута
     */
    public void deleteRoute(int id)throws IOException, ClassNotFoundException{
        DataAccessObject.deleteRoute(id);
    }
    /**
     * удаляет самолет
     * @param id - идентификатор самолета
     */
    public void deletePlane(int id)throws IOException, ClassNotFoundException{
        DataAccessObject.deletePlane(id);
    }
    public void loadData() throws IOException, ClassNotFoundException {
        try
        {
            DataAccessObject.initConnection();
        }
        catch(ClassNotFoundException e)
        {
            System.err.println(e.toString());
            System.err.println("unable to connect database");
        }
    }
}
