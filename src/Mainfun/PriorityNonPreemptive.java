package MainProject.src.Mainfun;

import MainProject.src.Utlity.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PriorityNonPreemptive extends CPUSchedule {
    @Override
    public void process() {
        Collections.sort(this.getRowList(), (Object o1, Object o2) -> {
            if ((((ProcessClass) o1)).getAt() == ((ProcessClass) o2).getAt()) {
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
                if ((((ProcessClass) o1)).getPriority() == ((ProcessClass) o2).getPriority()) {
                    return 0;
                } else if ((((ProcessClass) o1)).getPriority() < ((ProcessClass) o2).getPriority()) {
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