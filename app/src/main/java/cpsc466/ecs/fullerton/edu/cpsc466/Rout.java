package cpsc466.ecs.fullerton.edu.cpsc466;

public class Rout {
    private String name;
    private String date;
    private String rout;
    private String distance;
    private String time;

    public Rout(String nname, String ndate, String nrout, String ndistance, String ntime){
        name = nname;
        date = ndate;
        rout = nrout;
        distance = ndistance;
        time = ntime;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getRout() {
        return rout;
    }

    public String getDistance() {
        return distance;
    }

    public String getTime() {
        return time;
    }
}
