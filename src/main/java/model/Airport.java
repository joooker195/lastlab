package model;

import javax.persistence.*;
import java.io.Serializable;

/*
    класс международного аэропорта.
*/
@SequenceGenerator(name = "SEQ_ID", sequenceName = "SEQ_ID")
@Entity
@Table(name = "airport")
public class Airport implements Serializable
{
    @javax.persistence.Id
    @GeneratedValue(generator = "SEQ_ID")
    @Column(name = "port_id")
    private int id;

    @Column(name = "title")
    private String airportName = "ggg";// имя конкретного самолета

    @Column(name = "location")
    private String airportLocation;// борт

    public Route getPortOffTime() {
        return portOffTime;
    }

    public void setPortOffTime(Route portOffTime) {
        this.portOffTime = portOffTime;
    }

    public Route getLandingTime() {
        return landingTime;
    }

    public void setLandingTime(Route landingTime) {
        this.landingTime = landingTime;
    }

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "TAKE_ID")
    private Route portOffTime;


    @ManyToOne(fetch =FetchType.LAZY)
    @JoinColumn(name = "LANDING_ID")
    private Route landingTime;


    public Airport()
    {

    }

    public Airport(int id, String airportName, String airportLocation){
        this.airportName=airportName;
        this.airportLocation=airportLocation;
        this.id=id;
    }
    public void setId(int id){
        this.id=id;
    }
    public int getId(){return id;}
    public String getName(){
        return this.airportName;
    }
    public void setName(String airportName){
        this.airportName=airportName;
    }
    public String getLocation(){
        return this.airportLocation;
    }
    public void setLocation(String airportLocation){
        this.airportLocation=airportLocation;
    }
}
