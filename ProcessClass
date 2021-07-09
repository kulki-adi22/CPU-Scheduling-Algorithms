package Utitlity;

import java.util.ArrayList;
import java.util.List;

//FCFS, priority preemptive, sjf, round robin  
public class ProcessClass {
    public String pName;
    public int arrTime;
    public int burstTime;
    public int waitTime;
    public int priority;
    public int tat;

    public ProcessClass(String pName, int arrTime, int burstTime, int priority, int waitTime, int tat) {
        this.tat = tat;
        this.pName = pName;
        this.arrTime = arrTime;
        this.burstTime = burstTime;
        this.priority = priority;
        this.waitTime = waitTime;
    }

    public ProcessClass(String pn, int at, int bt)// FCFS,SJF,RoundRobin
    {
        this.pName = pn;
        this.burstTime = bt;
        this.arrTime = at;
        this.priority = 0;
        this.waitTime = 0;
        this.tat = 0;
    }

    public ProcessClass(String pName, int arrTime, int burstTime, int priority) { //Priority 
        this(pName, arrTime, burstTime, priority, 0, 0);
    }

    public void setBurst(int bt) {
        this.burstTime = bt;
    }

    public void setTat(int tat) {
        this.tat = tat;
    }

    public void setWait(int wt) {
        this.waitTime = wt;
    }

    public String getpn() {
        return this.pName;
    }

    public int getAt() {
        return this.arrTime;
    }

    public int getbt() {
        return this.burstTime;
    }

    public int getPriority() {
        return this.priority;
    }

    public int getwt()
    {
        return this.waitTime;
    }

    public int getTAT(){
        return this.tat;
    }

    public static List<ProcessClass> copyList( List<ProcessClass> original)
    {
        List<ProcessClass>duplicate = new ArrayList();
        for (ProcessClass itrvar : duplicate) {
            duplicate.add(new ProcessClass(itrvar.getpn(), itrvar.getAt(), itrvar.getbt(), itrvar.getPriority()));
        }
        return duplicate;
    }

}
