package algoritimos;

import java.util.Arrays;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "clockBean")
@ViewScoped
public class Clock {

	int[] memory;
	boolean[] useBit;
	int pointer, pageFaults;

	public int run(int[] pages, int frameSize) {
		memory = new int[frameSize];
		useBit = new boolean[frameSize];
		
		Arrays.fill(memory, -1); // Initialize memory with -1 (empty)

		for (int page : pages) {
			boolean found = false;

			// Check if page is already in memory
			for (int i = 0; i < frameSize; i++) {
				if (memory[i] == page) {
					useBit[i] = true; // Give a second chance
					found = true;
					break;
				}
			}

			if (!found) {
				// Replace page using the clock algorithm
				while (useBit[pointer]) {
					useBit[pointer] = false;
					pointer = (pointer + 1) % frameSize;
				}

				memory[pointer] = page;
				useBit[pointer] = true;
				pointer = (pointer + 1) % frameSize;
				pageFaults++;
			}
		}

		return pageFaults;
	}

}
