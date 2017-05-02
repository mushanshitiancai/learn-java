package 数据结构.堆;

/**
 * Created by mazhibin on 17/3/27
 */
public class MinHeap {
    private int[] data = new int[1024];
    private int size = 0;

    public MinHeap() {
    }

    public MinHeap(int[] input) {
        System.arraycopy(input, 0, data, 0, input.length);
        size = input.length;
        for (int i = input.length / 2 - 1; i >= 0; i--) {
            fixDown(i);
        }
    }


    private void fixUp(int cur) {
        if (data == null || size <= 1) return;

        int prev = (cur - 1) / 2;

        while (prev >= 0 && data[cur] < data[prev]) {
            int temp = data[prev];
            data[prev] = data[cur];
            data[cur] = temp;

            cur = prev;
            prev = (prev - 1) / 2;
        }
    }

    private void fixDown(int cur) {
        if (data == null || size <= 1) return;

        int next = cur * 2 + 1;

        while (next < size) {
            if (next + 1 < size && data[next + 1] < data[next]) {
                next++;
            }
            
            if(data[cur] <= data[next]){
               break; 
            }

            int temp = data[cur];
            data[cur] = data[next];
            data[next] = temp;

            cur = next;
            next = next * 2 + 1;
        }
    }

    public void add(int value) {
        data[size++] = value;
        fixUp(size - 1);
    }

    public void delete() {
        data[0] = data[--size];
        fixDown(0);
    }

    public static void main(String[] args) {
        MinHeap minHeap = new MinHeap();

        minHeap.add(40);
        minHeap.add(10);
        minHeap.add(30);
        minHeap.add(5);
        print(minHeap);

        System.out.println();

        minHeap.add(15);
        print(minHeap);

        System.out.println();

        minHeap.delete();
        print(minHeap);

        System.out.println("== from array");

        print(new MinHeap(new int[]{9, 12, 17, 30, 50, 20, 60, 65, 4, 19}));
    }

    public static void print(MinHeap minHeap) {
        int curLine = -1;
        for (int i = 0; i < minHeap.size; i++) {
            int line = log(i + 1, 2);
            if (curLine != line) {
                System.out.println();
                curLine = line;
            }

            System.out.print(minHeap.data[i] + " ");
        }
        System.out.println("size: " + minHeap.size);

    }

    static int log(int x, int base) {
        return (int) (Math.log(x) / Math.log(base));
    }
}
