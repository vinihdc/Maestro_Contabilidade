package Model.Entidade;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;

import javax.swing.*;

public class Graficos extends JFrame {

    public int Despesas;

    public int Receita;

    public Graficos(int despesas, int receita) {

        Despesas = despesas;
        Receita = receita;



        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("Despesas", Despesas);
        dataset.setValue("Receita", Receita);
        JFreeChart ObjJFreeChar = ChartFactory.createPieChart(
                "Situação da Empresa",
                dataset,
                true,
                true,
                false



        );


        ChartPanel chartPanel = new ChartPanel(ObjJFreeChar);
        getContentPane().add(chartPanel);






    }

}
