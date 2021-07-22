package MainProject.src.Mainfun;

import MainProject.src.Utlity.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PriorityPreemptive extends CPUSchedule {
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
        System.out.println(rows.size());
        int time = rows.get(0).getAt();

        while (!rows.isEmpty()) {
            List<ProcessClass> availableRows = new ArrayList<ProcessClass>();

            for (ProcessClass row : rows) {
                if (row.getAt() <= time) {
                    availableRows.add(row);
                }
            }

            Collections.sort(availableRows, (Object o1, Object o2) -> {
                if (((ProcessClass) o1).getPriority() == ((ProcessClass) o2).getPriority()) {
                    return 0;
                } else if (((ProcessClass) o1).getPriority() < ((ProcessClass) o2).getPriority()) {
                    return -1;
                } else {
                    return 1;
                }
            });

            ProcessClass row = availableRows.get(0);
            this.getGanttChart().add(new Instance(row.getpn(), time, ++time));
            row.setbt(row.getbt() - 1);

            if (row.getbt() == 0) {
                for (int i = 0; i < rows.size(); i++) {
                    if (rows.get(i).getpn().equals(row.getpn())) {
                        rows.remove(i);
                        break;
                    }
                }
            }
        }

        for (int i = this.getGanttChart().size() - 1; i > 0; i--) {
            List<Instance> timeline = this.getGanttChart();

            if (timeline.get(i - 1).getPName().equals(timeline.get(i).getPName())) {
                timeline.get(i - 1).setEndTime(timeline.get(i).getEndTime());
                timeline.remove(i);
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
 * class TestClass4 { public static void main(String[] args) { CPUSchedule psp =
 * new PriorityPreemptive(); psp.addRow(new ProcessClass("P1", 8, 1));
 * psp.addRow(new ProcessClass("P2", 5, 1)); psp.addRow(new ProcessClass("P3",
 * 2, 7)); psp.addRow(new ProcessClass("P4", 4, 3)); psp.addRow(new
 * ProcessClass("P5", 2, 8)); psp.addRow(new ProcessClass("P6", 4, 2));
 * psp.addRow(new ProcessClass("P7", 3, 5)); for (ProcessClass row :
 * psp.getRowList()) { System.out.println( row.getpn() + " " + row.getAt() + " "
 * + row.getbt() + " " + row.getwt() + " " + row.getTAT()); } psp.process();
 * System.out.println(); for (ProcessClass row : psp.getRowList()) {
 * System.out.println( row.getpn() + " " + row.getAt() + " " + row.getbt() + " "
 * + row.getwt() + " " + row.getTAT()); } } }
 */