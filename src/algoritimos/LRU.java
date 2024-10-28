package algoritimos;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name="lruBrean")
@ViewScoped
public class LRU {
	 List<Integer> memory = new ArrayList<>();
	 int pageFaults = 0;

	public int run(int[] pages, int frameSize) {

		for (int page : pages) {
			if (!memory.contains(page)) {
				if (memory.size() == frameSize) {
					memory.remove(0); // Remove the least recently used page
				}
				pageFaults++;
			} else {
				memory.remove((Integer) page); // Update recently used
			}
			memory.add(page);
		}

		return pageFaults;
	}

}
