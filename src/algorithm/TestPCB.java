package algorithm;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by zihua on 16-11-29.
 */
public class TestPCB {
    public static void main(String[]args){
        int cpuTime=2;
        PCB p1=new PCB(1,1,cpuTime,1,"wait");
        PCB p2=new PCB(2,1,cpuTime,2,"wait");
        PCB p3=new PCB(3,1,cpuTime,3,"wait");
        PCB p4=new PCB(4,1,cpuTime,4,"wait");
        PCB p5=new PCB(5,1,cpuTime,5,"wait");

        Queue<PCB>queue=new LinkedList<>();
        queue.add(p1);
        queue.add(p2);
        queue.add(p3);
        queue.add(p4);
        queue.add(p5);
        while(!queue.isEmpty()){
            PCB t=queue.poll();
            exe(t,cpuTime);
            Queue<PCB>queue1=new LinkedList<>(queue);
            show(queue1);
            if(!t.getStatus().equals("finish"))queue.offer(t);

        }

    }

    public static void exe(PCB a,int cpu){
        if(a.getNeedCp()>0){
            a.setStatus("Run");
            a.setNeedCp(cpu);
            a.setCpuTime(cpu);
        }
        else {
            a.setStatus("finish");
        }
        System.out.println(a);
    }

    public static void show(Queue a){
        while(!a.isEmpty()){
            System.out.println(a.poll());
        }
    }
}

class PCB implements Comparable<PCB>{
    private int flag;
    private int priority;
    private int cpuTime;
    private int needCp;
    private String status;

    public PCB(){}
    public PCB(int flag,int priority,int cupTime,int needCp,String status){
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

    public void setStatus(String status){
        this.status=status;
    }

    public void setNeedCp(){
        needCp-=cpuTime;
    }

    public void setNeedCp(int t){
        needCp-=t;
    }


    public void setCpuTime(int cpuTime){
        this.cpuTime=cpuTime;
    }

    public void setPriority(int priority){
        this.priority=priority;
    }

    public void setPriority(){
        priority-=1;
    }

    @Override
    public int compareTo(PCB o) {
        if(priority>o.priority)return 1;
        else if(priority<o.priority)return -1;
        return 0;
    }

    @Override
    public String toString(){
        return "进程号："+flag+"  "+"优先级 : "+priority+"  "
                +"时间片： "+cpuTime+"  "+"还需时间片: "+needCp+"  "+"线程状态: "+status;
    }
}
