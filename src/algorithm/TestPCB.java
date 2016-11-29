package algorithm;

/**
 * Created by zihua on 16-11-29.
 */
public class TestPCB {
    public static void main(String[]args){

    }
}

class PCB{
    private int flag;
    private int priority;
    private int cpuTime;
    private int needCp;
    private String status;

    public PCB(){}
    public PCB(int flag,int priority,int cupTime,int needTime,int needCp,String status){
        this.flag=flag;
        this.priority=priority;
        this.cpuTime=cupTime;
        this.needCp=needCp;
        this.status=status;
    }

    public int getFlag(){
        return flag;
    }

    public int getPriority(){
        return priority;
    }

    public int getCpuTime(){
        return cpuTime;
    }

    public int getNeedCp(){
        return needCp;
    }

    public String getStatus(){
        return status;
    }

    
}
