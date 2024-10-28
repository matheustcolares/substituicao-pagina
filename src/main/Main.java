package main;

import java.util.Arrays;
import java.util.Scanner;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

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
	public void run() {
			setModel(new BarChartModel());
		 //Scanner scanner = new Scanner(System.in);

	        //System.out.print("Digite a sequência de páginas (separada por espaços): ");
	        //String[] input = scanner.nextLine().split(" ");
	        //int[] pages = Arrays.stream(input).mapToInt(Integer::parseInt).toArray();
	        int[] pages = {1,5,7,9,4,3,6,17,18,14,32};

	        System.out.print("Digite o número de quadros de página: ");
	        //int frameSize = scanner.nextInt();
	        int frameSize = 5;
	        
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
	        yAxis.setMax(100);
	        System.out.println("Método 1 (FIFO) - " + fifoFaults + " faltas de página");
	        System.out.println("Método 2 (LRU) - " + lruFaults + " faltas de página");
	        System.out.println("Método 3 (Clock) - " + clockFaults + " faltas de página");
	        System.out.println("Método 4 (NFU) - " + nfuFaults + " faltas de página");
	    }
	public BarChartModel getModel() {
		return model;
	}
	public void setModel(BarChartModel model) {
		this.model = model;
	}

	}

