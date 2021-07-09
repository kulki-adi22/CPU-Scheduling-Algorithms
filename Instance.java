package Utitlity;

public class Instance {
    public final String processName;
    public final int begTime;
    public int endTime;

    public Instance(String pn, int st, int et)
    {
        this.processName=pn;
        this.begTime=st;
        this.endTime=et;
    }
    public String getPName()
    {
        return this.processName;
    }
    public int getbegTime()
    {
        return this.begTime;
    }
    public int getEndTime()
    {
        return this.endTime;
    }
    public void setEndTime(int endT)
    {
        this.endTime=endT;
    }
}
