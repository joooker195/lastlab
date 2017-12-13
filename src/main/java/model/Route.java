package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/*
    маршрут между двумя точками
*/
@SequenceGenerator(name = "SEQ_ID", sequenceName = "regular_route_seq_id")
@Entity
@Table(name = "route")
public class Route implements Serializable
{
    // private Airport takeOffPort; //..откуда
    //  private Airport landingPort;//..куда
    @javax.persistence.Id
    @GeneratedValue(generator = "SEQ_ID")
    @Column(name = "route_id")
    private int id;

    @Column(name = "takeoffport")
    private int takeOffPortId;

    @Column(name = "landingport")
    private int landingPortId;

    @Column(name = "distance")
    private double distance;// расстояние

    @OneToMany(mappedBy = "portOffTime",fetch = FetchType.EAGER, cascade = CascadeType.ALL, targetEntity = Airport.class)
    private Set<Flight> ListPartsOfTime = new HashSet<Flight>(0);

    @OneToMany(mappedBy = "landingTime",cascade = CascadeType.ALL,fetch = FetchType.LAZY,targetEntity = Airport.class)
    private Set<Flight> ListLandingOfTime = new HashSet<Flight>(0);

    public Flight getRFlight() {
        return RFlight;
    }

    public void setRFlight(Flight RFlight) {
        this.RFlight = RFlight;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FLIGHT_ID")
    private Flight RFlight;

    public Set<Flight> getListPartsOfTime() {
        return ListPartsOfTime;
    }

    public void setListPartsOfTime(Set<Flight> listPartsOfTime) {
        ListPartsOfTime = listPartsOfTime;
    }

    public Set<Flight> getListLandingOfTime() {
        return ListLandingOfTime;
    }

    public void setListLandingOfTime(Set<Flight> listLandingOfTime) {
        ListLandingOfTime = listLandingOfTime;
    }

    public Route()
    {

    }

    public Route(int id, int takeOffPort, int landingPort, double distance){//конструктор инициализирует все переменные
        this.takeOffPortId=takeOffPort;
        this.landingPortId=landingPort;
        this.distance=distance;
        this.id=id;
    }
    public int getId(){return id;}
    public void setId(int id){
        this.id=id;
    }
    public int getTakeOffPort(){
        return this.takeOffPortId;
    }
    public void setTakeOffPort(int takeOffPort){
        this.takeOffPortId=takeOffPort;
    }

    public int getLandingPort(){
        return this.landingPortId;
    }
    public void setLandingPort(int landingPort){
        this.landingPortId=landingPort;
    }

    public double getDistance(){
        return this.distance;
    }
    public void setDistance(double distance){this.distance=distance;}
}
