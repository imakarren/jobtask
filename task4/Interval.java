public class Interval implements Comparable <Interval> {
    private String start;
    private String end;

    public Interval (String start, String end) {
        this.start = start;
        this.end = end;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    @Override
    public int compareTo(Interval interval) {
        int result = this.start.compareTo(interval.start);
        if (result == 0)
            this.end.compareTo(interval.end);
        return result;
    }

    @Override
    public String toString() {
        return start+" "+end+"\n";
    }

    public boolean intersection (Interval interval) {
        if (this.start == interval.start & this.end == interval.end || this.end.compareTo(interval.start) > 0)
            return true;
        return false;
    }
}
