import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class FibonacciChart extends Application {

    public static long iterativeFibonacci(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        long prev = 0;
        long curr = 1;
        for (int i = 2; i <= n; i++) {
            long next = prev + curr;
            prev = curr;
            curr = next;
        }
        return curr;
    }

    public static long recursiveFibonacci(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        return recursiveFibonacci(n - 1) + recursiveFibonacci(n - 2);  
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Fibonacci Runtime Comparison");
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Input");
        yAxis.setLabel("Time (nanoseconds)");
        final LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Fibonacci Runtime Comparison");

        XYChart.Series iterativeSeries = new XYChart.Series();
        iterativeSeries.setName("Iterative Fibonacci");

        XYChart.Series recursiveSeries = new XYChart.Series();
        recursiveSeries.setName("Recursive Fibonacci");

        for (int i = 0; i <= 40; i++) {          //applying nanoseconds as the time variant to the chart
            long startTime = System.nanoTime(); 
            iterativeFibonacci(i);
            long endTime = System.nanoTime();
            long elapsedTime = endTime - startTime;
            iterativeSeries.getData().add(new XYChart.Data(i, elapsedTime));

            startTime = System.nanoTime();
            recursiveFibonacci(i);
            endTime = System.nanoTime();
            elapsedTime = endTime - startTime;
            recursiveSeries.getData().add(new XYChart.Data(i, elapsedTime));
        }

        Scene scene = new Scene(lineChart, 1600, 1200);
        lineChart.getData().add(iterativeSeries);
        lineChart.getData().add(recursiveSeries);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
