package tuto_db4o;

import java.util.*;

public class Car {
    private String model;
    private Pilot pilot;
    private List history;

    public Car(String model) {
        this(model,new ArrayList());
    }

    public Car(String model,List history) {
        this.model=model;
        this.pilot=null;
        this.history=history;
    }

    public Pilot getPilot() {
        return pilot;
    }

    public void setPilot(Pilot pilot) {
        this.pilot=pilot;
    }

    public String getModel() {
        return model;
    }

    public SensorReadout[] getHistory() {
        return (SensorReadout[])history.toArray(
                new SensorReadout[history.size()]);
    }
    
    public void snapshot() {
        history.add(new SensorReadout(poll(),new Date(),this));
    }
    
    protected double[] poll() {
        int factor=history.size()+1;
        return new double[]{0.1d*factor,0.2d*factor,0.3d*factor};
    }
    
    public String toString() {
        return model+"["+pilot+"]/"+history.size();
    }
}
