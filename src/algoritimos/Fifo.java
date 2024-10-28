package algoritimos;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "fifoBean")
@ViewScoped
public class Fifo {
	
	 Queue<Integer> queue = new LinkedList<>();
     Set<Integer> memory = new HashSet<>();
     int pageFaults = 0;
     
     public int run(int[] pages, int frameSize) {
	     for (int page : pages) {
	         if (!memory.contains(page)) {
	             if (memory.size() == frameSize) {
	                 int removedPage = queue.poll();
	                 memory.remove(removedPage);
	             }
	             memory.add(page);
	             queue.add(page);
	             pageFaults++;
	         }
	     }
		return pageFaults;
     }


}
