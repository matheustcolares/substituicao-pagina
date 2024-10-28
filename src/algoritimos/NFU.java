package algoritimos;

import java.util.Arrays;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name="nfuBean")
@ViewScoped
public class NFU {
	int[] memory;
    int[] counters;
    int pageFaults;
	
	 public int run(int[] pages, int frameSize) {
	        memory = new int[frameSize];
	        counters = new int[frameSize];
	        pageFaults = 0;

	        Arrays.fill(memory, -1); // Initialize memory with -1 (empty)

	        for (int page : pages) {
	            boolean found = false;

	            // Check if page is already in memory
	            for (int i = 0; i < frameSize; i++) {
	                if (memory[i] == page) {
	                    counters[i]++;
	                    found = true;
	                    break;
	                }
	            }

	            if (!found) {
	                // Find the page with the smallest counter (least frequently used)
	                int minIndex = 0;
	                for (int i = 1; i < frameSize; i++) {
	                    if (counters[i] < counters[minIndex]) {
	                        minIndex = i;
	                    }
	                }

	                memory[minIndex] = page;
	                counters[minIndex] = 1;
	                pageFaults++;
	            }
	        }

	        return pageFaults;
	    }


}
