package MainProject.src.Utlity;

import java.util.ArrayList;
import java.util.List;

public abstract class CPUSchedule 
{
    public final List<ProcessClass> rows;
    public final List<Instance> gantt;
    public int timequantum;
    public CPUSchedule()
    {
        rows=new ArrayList<>();
        gantt=new ArrayList<>();
        timequantum=1;
    }
     public boolean addRow(ProcessClass row)
     {
       return rows.add(row);
     } 
     public void setTimeQuantum(int time)
     {
         this.timequantum=time;
     }
     public int getTimeQuantum()
     {
         return this.timequantum;
     }
     public double getAvgWaitTime()
     {
         double avg=0.0;
         for(ProcessClass itr:rows)
         {
             avg+=itr.getwt();
         }
         avg=avg/rows.size();
         return avg;
     }
     public double getAvgTAT()
     {
         double avg=0.0;
         for(ProcessClass itr:rows)
         {
             avg+=itr.getTAT();
         }
         avg=avg/rows.size();
         return avg;
     }
     public Instance getChart(ProcessClass row)
     {
       for(Instance itr : gantt)
       {
           if(row.getpn().equals(itr.getPName()))
           {
               return itr;
           }  
       }
       return null;
     }
     public List<Instance> getGanttChart()
     {
         return gantt;
     }
     public ProcessClass getRow(String processName)
     {
         for(ProcessClass itr:rows)
         {
             if(itr.getpn().equals(processName))
             {
                 return itr;
             }
         }
         return null;
     }
    public List<ProcessClass> getRowList()
    {
        return this.rows;
    }
    public abstract void process();
}

