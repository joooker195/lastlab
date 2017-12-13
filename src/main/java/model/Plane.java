package model;

import javax.persistence.*;
import java.io.Serializable;

@SequenceGenerator(name = "SEQ_ID", sequenceName = "plane_seq_id")
@Entity
@Table(name = "plane")
public class Plane implements Serializable
{
    @javax.persistence.Id
    @GeneratedValue(generator = "SEQ_ID")
    private int id;

    @Column(name = "type")
    private String planeName = "No name";// имя конкретного самолета

    @Column(name = "title")
    private String planeNumber;// бортовой номер конкретного самолета, например AL-4DB,DS-GDD,N1KE,....

    @Column(name = "passegers")
    private int passengerSeatsCount;

    @Column(name = "fuelconst")
    private double fuelConsumption;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "FLIGHT_ID")
    private Flight flight;


    static double fuelConsumptionDefault=20.3;//расход топлива самолета Boeing747SP л/км
    static int passengerSeatsCountDefault=230;// число пассажирских мест в самолете типа Boeing747SP

    public Plane() {

    }
    public Plane(int id, String number){//конструктор экземпляра Boeing747SP, задает бортовой номер самолета(на практике большн и не надо)
        this.planeNumber=number;
        this.id=id;
        fuelConsumption=fuelConsumptionDefault;
        passengerSeatsCount=passengerSeatsCountDefault;
    }
    //дальше код для реализации интерфейса Plane, не делает ничего интересного
    public void setId(int id){
        this.id=id;
    }
    public int getId(){return id;}
    public String getName(){
        return this.planeName;
    }
    public void setName(String planeName){
        this.planeName=planeName;
    }

    public String getNumber(){
        return  this.planeNumber;
    }
    public void setNumber(String planeNumber){
        this.planeNumber=planeNumber;
    }

    public double getFuelConsumption(){
        return this.fuelConsumption;
    }
    public void setFuelConsumption(double fuelConsumption){
        this.fuelConsumption=fuelConsumption;
    }

    public int getPassengerSeatsCount(){
        return this.passengerSeatsCount;
    }
    public void setPassengerSeatsCount(int passengerSeatsCount){
        this.passengerSeatsCount=passengerSeatsCount;
    }
}
