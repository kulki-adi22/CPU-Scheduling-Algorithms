package MainProject.src.Mainfun;

import MainProject.src.Utlity.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoundRobin extends CPUSchedule {
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
        int timeQuantum = this.getTimeQuantum();

        while (!rows.isEmpty()) {
            ProcessClass row = rows.get(0);
            int bt = (row.getbt() < timeQuantum ? row.getbt() : timeQuantum);
            this.getGanttChart().add(new Instance(row.getpn(), time, time + bt));
            time += bt;
            rows.remove(0);

            if (row.getbt() > timeQuantum) {
                row.setbt(row.getbt() - timeQuantum);

                for (int i = 0; i < rows.size(); i++) {
                    if (rows.get(i).getAt() > time) {
                        rows.add(i, row);
                        break;
                    } else if (i == rows.size() - 1) {
                        rows.add(row);
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
