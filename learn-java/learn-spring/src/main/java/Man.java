/**
 * Created by mazhibin on 16/7/25
 */
public class Man {
    private static int index = 0;
    private int i;
    private String name;

    private String country;

    private Man son;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Man getSon() {
        return son;
    }

    public void setSon(Man son) {
        this.son = son;
    }

    public Man(){
        i = index++;
        name = "null";
    }

    public Man(String name){
        i = index++;
        this.name = name;
    }

    public Man(String name,int age){
        i = index++;
        this.name = name+"("+age+")";
    }

    public static Man getMan(){
        return new Man("get by self");
    }

    public void speak(){
        System.out.println("["+i+"]hello "+name+" country:"+country);
    }

}
