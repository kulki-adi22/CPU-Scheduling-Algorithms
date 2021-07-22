package MainProject.src.Mainfun;

//import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import MainProject.src.Utlity.*;

public class FirstComeFirstServe extends CPUSchedule {
    @Override
    public void process() {
        Collections.sort(this.getRowList(), (Object o1, Object o2) -> // lambda expressions
        {
            if (((ProcessClass) o1).getAt() == ((ProcessClass) o2).getAt())
                return 0;
            else if (((ProcessClass) o1).getAt() < ((ProcessClass) o2).getAt())
                return -1;
            else
                return 1;
        });
        List<Instance> ganttChart = this.getGanttChart();
        for (ProcessClass itr : this.getRowList()) {
            if (ganttChart.isEmpty()) {
                ganttChart.add(new Instance(itr.getpn(), itr.getAt(), itr.getAt() + itr.getbt()));
            } else {
                Instance prev = ganttChart.get(ganttChart.size() - 1);// pointing to the previous process so
                                                                      // that getting wait time for current
                                                                      // process becomes easy
                ganttChart.add(new Instance(itr.getpn(), prev.getEndTime(), prev.getEndTime() + itr.getbt()));
            }
        }
        for (ProcessClass row : this.getRowList()) {
            row.setWait(this.getChart(row).getbegTime() - row.getAt());
            row.setTat(row.getwt() + row.getbt());
        }
    }// end of function
}// end of class FirstComeFirstServe
/*
 * class TestClass { public static void main(String[] args){ CPUSchedule obj1 =
 * new FirstComeFirstServe(); obj1.addRow(new ProcessClass("P1", 0, 3));
 * obj1.addRow(new ProcessClass("P3", 1, 2)); obj1.addRow(new ProcessClass("P2",
 * 3, 1)); obj1.addRow(new ProcessClass("P4", 2, 6)); for (ProcessClass row :
 * obj1.getRowList()) { System.out.println( row.getpn() + " " + row.getAt() +
 * " " + row.getbt() + " " + row.getwt() + " " + row.getTAT()); }
 * obj1.process(); System.out.println(); for (ProcessClass row :
 * obj1.getRowList()) { System.out.println( row.getpn() + " " + row.getAt() +
 * " " + row.getbt() + " " + row.getwt() + " " + row.getTAT()); } } }
 */
