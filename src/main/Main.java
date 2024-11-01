package main;

import java.util.Random;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

import algoritimos.Clock;
import algoritimos.Fifo;
import algoritimos.LRU;
import algoritimos.NFU;

@ManagedBean(name="mainBean")
@SessionScoped
public class Main {
	private BarChartModel model;
	private int numberPages;
	private int numberFrame;
	
	public void run() {
			setModel(new BarChartModel());
	
	        int[] pages = criarArrayAleatorio(this.numberPages);
	        
	       for(int i =0;i<pages.length;i++) {
	    	   System.out.println(pages[i]);
	       }

	        int frameSize = this.numberFrame;
	        
	        Fifo fifo = new Fifo();
	        LRU lru = new LRU();
	        Clock clock = new Clock();
	        NFU nfu = new NFU();

	        int fifoFaults = fifo.run(pages, frameSize);
	        int lruFaults = lru.run(pages, frameSize);
	        int clockFaults = clock.run(pages, frameSize);
	        int nfuFaults = nfu.run(pages, frameSize);
	        
	        ChartSeries algoritimos = new ChartSeries();
	        
	        algoritimos.set("FIFO", fifoFaults);
	        algoritimos.set("LRU", lruFaults);
	        algoritimos.set("Clock", clockFaults);
	        algoritimos.set("NFU", nfuFaults);
	        
	        model.addSeries(algoritimos);
	        model.setTitle("Algoritimos de substituição de pagina");
	        model.setLegendPosition("ne");
	        
	        Axis xAxis = model.getAxis(AxisType.X);
	        xAxis.setLabel("Algoritimos");
	        
	        Axis yAxis = model.getAxis(AxisType.Y);
	        yAxis.setLabel("Quantidade de PaginasFaults");
	        yAxis.setMin(0);
	        yAxis.setMax(this.numberPages);
	        System.out.println("Método 1 (FIFO) - " + fifoFaults + " faltas de página");
	        System.out.println("Método 2 (LRU) - " + lruFaults + " faltas de página");
	        System.out.println("Método 3 (Clock) - " + clockFaults + " faltas de página");
	        System.out.println("Método 4 (NFU) - " + nfuFaults + " faltas de página");
	    }
	public static int[] criarArrayAleatorio(int tamanho) {
        Random random = new Random();
        int[] array = new int[tamanho];

        // Preenche o array com valores aleatórios entre 0 e 9
        for (int i = 0; i < tamanho; i++) {
            array[i] = random.nextInt(10); // Gera números entre 0 e 9
        }

        return array;
    }
	public BarChartModel getModel() {
		return model;
	}
	public void setModel(BarChartModel model) {
		this.model = model;
	}
	public int getNumberPages() {
		return numberPages;
	}
	public void setNumberPages(int numberPages) {
		this.numberPages = numberPages;
	}
	public int getNumberFrame() {
		return numberFrame;
	}
	public void setNumberFrame(int numberFrame) {
		this.numberFrame = numberFrame;
	}

	}

