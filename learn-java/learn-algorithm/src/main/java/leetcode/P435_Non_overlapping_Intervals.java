package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mazhibin on 17/1/10
 */
public class P435_Non_overlapping_Intervals {

    public static void main(String[] args) {
        
    }
    
    public int eraseOverlapIntervals(Interval[] intervals) {
        List<Interval> list = new ArrayList<>();
        
        for (int i = 0; i < intervals.length; i++) {
            Interval cur = intervals[i];

            boolean merged = false;    
            for (Interval interval : list) {
                if(interval.end == cur.start){
                    interval.end = cur.end;
                    merged = true;
                    break;
                }
                if(interval.start == cur.end) {
                    interval.start = cur.start;
                    merged = true;
                    break;
                }
            }
            if(merged) continue;
            list.add(cur);
        }

        for (Interval interval : list) {
            
        }
        
        return 0;
    }
}

class Interval {
    int start;
    int end;

    Interval() {
        start = 0;
        end = 0;
    }

    Interval(int s, int e) {
        start = s;
        end = e;
    }
}
