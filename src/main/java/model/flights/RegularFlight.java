package model.flights;

import model.Flight;
import model.Plane;
import model.Route;
import model.planes.FirstPlane;
import model.routes.RegularRoute;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/*
    рейс, совершаемый самолетом по маршруту
*/
@SequenceGenerator(name = "SEQ_ID", sequenceName = "regular_flight_seq_id")
@Entity
@Table(name = "flight")
public class RegularFlight implements Flight, Serializable
{
// double fuelPrice=26.80;//стоимость литра авиационного керосина(возможно стоит сделать какой-нибудь глобальной константой?)

    @javax.persistence.Id
    @GeneratedValue(generator = "SEQ_ID")
    private int id;

    @Column(name = "plane_id")
    // @ManyToOne
    private int planeId;

    @Column(name = "route_id")
    private int routeId;

    @Column(name = "takeofftime")
    private Date takeOffTime;

    @Column(name = "landingtime")
    private  Date landingTime;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "flight",cascade = CascadeType.ALL, targetEntity = FirstPlane.class)
    private Set<FirstPlane> planes = new HashSet<FirstPlane>(0);

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "RFlight",cascade = CascadeType.ALL,targetEntity = RegularRoute.class)
    private Set<RegularRoute> listRouts = new HashSet<>(0);

    public Set<RegularRoute> getListRouts() {
        return listRouts;
    }

    public void setListRouts(Set<RegularRoute> listRouts) {
        this.listRouts = listRouts;
    }

    public Set<FirstPlane> getPlanes() {
        return planes;
    }

    public void setPlanes(Set<FirstPlane> planes) {
        this.planes = planes;
    }

    public RegularFlight()
    {

    }

    public RegularFlight(int id,int planeId,int routeId,Date takeOffTime, Date landingTime){//конструктор создает рейс с самолетом, маршрутом и датами
        this.planeId=planeId;
        this.routeId=routeId;
        this.takeOffTime=takeOffTime;
        this.landingTime =landingTime;
        this.id=id;
    }
    public void setId(int id){
        this.id=id;
    }
    public int getId(){return id;}
    public int getPlane(){
        return this.planeId;
    }
    public void setPlane(Plane plane){
        this.planeId=plane.getId();
    }

    public int getRoute(){
        return this.routeId;
    }
    public void setRoute(Route route){
        this.routeId=route.getId();
    }

    public void setTakeOffTimeShedule(Date date){
        this.takeOffTime=date;
    }
    public Date getTakeOffTimeShedule(){
        return this.takeOffTime;
    }

    public void setLandingTimeShedule(Date date){
        this.landingTime =date;
    }
    public Date getLandingTimeShedule(){
        return this.landingTime;
    }
}
