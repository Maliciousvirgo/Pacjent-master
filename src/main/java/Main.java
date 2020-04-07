import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Patient> patientList = new ArrayList<>();
        patientList.add(new Patient("Jakub", "Dąbrowski", new BigInteger("99087666341"),100.0, ""));
        patientList.add(new Patient("Mikołaj", "Romanowski", new BigInteger("12345678910"),520.0,""));
        patientList.add(new Patient("Dora", "Biel", new BigInteger("94129977911"),900.99, ""));
        patientList.add(new Patient("Jan", "Kowalski", new BigInteger("82345678910"),450.99, ""));
        patientList.add(new Patient("Anna", "Dąbrowska", new BigInteger("84675978910"),600.99, ""));
        patientList.add(new Patient("Iza", "Kozak", new BigInteger("94778978910"),700.99, ""));


        ApachePOIExcelWrite.createExcel(patientList);

    }
}
