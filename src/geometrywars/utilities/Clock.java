package geometrywars.utilities;





public class Clock {
    private long start;
    

    public void start(){
        this.start = System.currentTimeMillis();
    }
    public long timePassed(){
        return (System.currentTimeMillis() - start);
    }
    
}