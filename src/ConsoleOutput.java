public class ConsoleOutput implements OutputStrategy {

    @Override
    public void writeReport(String report) {
        System.out.println(report);
    }

}