package MainProject.src.Mainfun;

import MainProject.src.Utlity.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import MainProject.src.Utlity.CPUSchedule;
import MainProject.src.Utlity.Instance;
import MainProject.src.Utlity.ProcessClass;

public class ShortestJobFirst extends CPUSchedule {
    @Override
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
            List<ProcessClass> availableRows = new ArrayList<ProcessClass>();

            for (ProcessClass row : rows) {
                if (row.getAt() <= time) {
                    availableRows.add(row);
                }
            }

            Collections.sort(availableRows, (Object o1, Object o2) -> {
                if (((ProcessClass) o1).getbt() == ((ProcessClass) o2).getbt()) {
                    return 0;
                } else if (((ProcessClass) o1).getbt() < ((ProcessClass) o2).getbt()) {
                    return -1;
                } else {
                    return 1;
                }
            });

            ProcessClass row = availableRows.get(0);
            this.getGanttChart().add(new Instance(row.getpn(), time, time + row.getbt()));
            time += row.getbt();

            for (int i = 0; i < rows.size(); i++) {
                if (rows.get(i).getpn().equals(row.getpn())) {
                    rows.remove(i);
                    break;
                }
            }
        }

        for (ProcessClass row : this.getRowList()) {
            row.setWait(this.getChart(row).getbegTime() - row.getAt());
            row.setTat(row.getwt() + row.getbt());
        }
    }
}

/*class TestClass2 {
    public static void main(String[] args) {
        CPUSchedule obj1 = new FirstComeFirstServe();
        obj1.addRow(new ProcessClass("P1", 0, 3));
        obj1.addRow(new ProcessClass("P3", 1, 2));
        obj1.addRow(new ProcessClass("P2", 1, 1));
        obj1.addRow(new ProcessClass("P4", 2, 6));
        for (ProcessClass row : obj1.getRowList()) {
            System.out.println(
                    row.getpn() + " " + row.getAt() + " " + row.getbt() + " " + row.getwt() + " " + row.getTAT());
        }
        obj1.process();
        System.out.println();
        for (ProcessClass row : obj1.getRowList()) {
            System.out.println(
                    row.getpn() + " " + row.getAt() + " " + row.getbt() + " " + row.getwt() + " " + row.getTAT());
        }
    }
}
*/