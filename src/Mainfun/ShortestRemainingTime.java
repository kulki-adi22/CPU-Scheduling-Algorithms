package MainProject.src.Mainfun;

import MainProject.src.Utlity.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Map;
import java.util.HashMap;

public class ShortestRemainingTime extends CPUSchedule {
    public void process() {
        Collections.sort(this.getRowList(), (Object o1, Object o2) -> {
            if (((ProcessClass) o1).getAt() == ((ProcessClass) o2).getAt()) {
                return 0;
            } else if (((ProcessClass) o1).getAt() < ((ProcessClass) o2).getAt()) {
                return -1;
            } else {
                return 1;
            }
        });
        List<ProcessClass> rows = copyFun.copyList(this.getRowList());
        int time = rows.get(0).getAt();
        while (!rows.isEmpty()) {
            List<ProcessClass> availableProcessClass = new ArrayList<ProcessClass>();
            for (ProcessClass itr : rows) {
                if (itr.getAt() <= time) {
                    availableProcessClass.add(itr);
                }
            }
            Collections.sort(availableProcessClass, (Object o1, Object o2) -> {
                if (((ProcessClass) o1).getbt() == ((ProcessClass) o2).getbt()) {
                    return 0;
                } else if (((ProcessClass) o1).getbt() < ((ProcessClass) o2).getbt()) {
                    return -1;
                } else {
                    return 1;
                }
            });
            ProcessClass row1 = availableProcessClass.get(0);
            this.getGanttChart().add(new Instance(row1.getpn(), time, ++time));
            row1.setbt(row1.getbt() - 1);
            if (row1.getbt() == 0) {
                for (int i = 0; i < rows.size(); i++) {
                    if (rows.get(i).getpn().equals(row1.getpn())) {
                        rows.remove(i);
                        break;
                    }
                }
            }
        }
        Map map = new HashMap();

        for (ProcessClass row : this.getRowList()) {
            map.clear();

            for (Instance event : this.getGanttChart()) {
                if (event.getPName().equals(row.getpn())) {
                    if (map.containsKey(event.getPName())) {
                        int w = event.getbegTime() - (int) map.get(event.getPName());
                        row.setWait(row.getwt() + w);
                    } else {
                        row.setWait(event.getbegTime() - row.getAt());
                    }

                    map.put(event.getPName(), event.getEndTime());
                }
            }

            row.setTat(row.getwt() + row.getbt());
        }
    }
}

/*
 * class TestClass1 { public static void main(String[] args) { CPUSchedule obj1
 * = new FirstComeFirstServe(); obj1.addRow(new ProcessClass("P1", 0, 3));
 * obj1.addRow(new ProcessClass("P3", 1, 2)); obj1.addRow(new ProcessClass("P2",
 * 1, 1)); obj1.addRow(new ProcessClass("P4", 2, 6)); for (ProcessClass row :
 * obj1.getRowList()) { System.out.println( row.getpn() + " " + row.getAt() +
 * " " + row.getbt() + " " + row.getwt() + " " + row.getTAT()); }
 * obj1.process(); System.out.println(); for (ProcessClass row :
 * obj1.getRowList()) { System.out.println( row.getpn() + " " + row.getAt() +
 * " " + row.getbt() + " " + row.getwt() + " " + row.getTAT()); } } }
 */