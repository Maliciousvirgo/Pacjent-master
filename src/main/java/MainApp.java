import wyjatki.InsufficientFundsException;
import wyjatki.TooManyPatientException;

import java.math.BigInteger;
import java.util.*;

public class MainApp {

    private static Scanner scanner;
    private static PatientService patientService;
    private static List<Patient> patientList;
    private static ApachePOIExcelWrite apachePOIExcelWrite;

    public static void main(String[] args) {
        ApachePOIExcelRead apachePOIExcelRead = new ApachePOIExcelRead();
        apachePOIExcelWrite = new ApachePOIExcelWrite();
        patientList = apachePOIExcelRead.getPatientList();
        patientService = new PatientService(patientList);
        scanner = new Scanner(System.in);
        patientService = new PatientService(patientList);
        scanner = new Scanner(System.in);
        System.out.println("");
        System.out.println("Wybierz akcje: \n0 - Zakończ działanie \n1 - Sprawdź czy pacjent jest zarejestrowany \n2 - Zarejestruj pacjenta \n3 - Sprawdź stan konta pacjenta \n4 - Usuń pacjenta z rejestru \n5 - Dokonaj badania na koronawirusa");
        Integer action = scanner.nextInt();
        chooseTypeSearching(action);
    }

    public static void menuGlowne() {
        System.out.println("0 - Zakończ działanie");
        System.out.println("1 - Sprawdź czy pacjent jest zarejestrowany");
        System.out.println("2 - Zarejestruj pacjenta ");
        System.out.println("3 - Sprawdź stan konta pacjenta");
        System.out.println("4 - Usuń pacjenta z rejestru");
        System.out.println("5 - Dokonaj badania na koronawirusa");
        Integer action = scanner.nextInt();
        chooseTypeSearching(action);
    }

    public static void chooseTypeSearching(Integer action) {
        switch (action) {
            case 0:
                break;
            case 1:
                isRegistered();
                break;
            case 2:
                try {
                    registerPatient();
                } catch (TooManyPatientException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case 3:
                checkPrice();

            case 4:
                deletePatient();
                System.out.println(patientList);
                break;
            case 5:
                koronaTest();

            default:
                break;
        }
    }

    private static void registerPatient() throws TooManyPatientException {

        System.out.println("W celu zarejestrowania pacjenta podaj: Imię, nazwisko, PESEL oraz cenę wizyty:");
        System.out.println("Podaj imię: ");
        String name = scanner.next();
        System.out.println("Podaj nazwisko: ");
        String surname = scanner.next();
        System.out.println("Podaj PESEL: ");
        BigInteger pesel = scanner.nextBigInteger();
        System.out.println("Podaj stan portfela: ");
        Double wallet = scanner.nextDouble();

        if (patientService.isRegistered(pesel)) {
            throw new TooManyPatientException();
        }
        patientList.add(new Patient(name, surname, pesel, wallet, null));
        ApachePOIExcelWrite.createExcel(patientList);

        System.out.println("Udało się zarejestrować nowego pacjenta");
        System.out.println(patientList);

        System.out.println("1. Powrot do menu glownego");
        System.out.println("2. Zakoncz program");
        Integer action = scanner.nextInt();
        switch (action) {
            case 1:
                if (action == 1) {
                    menuGlowne();
                } else if (action == 2) {
                    System.exit(0);
                }
        }
    }


    private static void checkPrice() {

        System.out.println("Aby sprawdzić stan konta podaj: \n1 - Imię i nazwisko \n2 - PESEL ");
        Integer action = scanner.nextInt();
        switch (action) {
            case 1:
                String name = scanner.next();
                String surname = scanner.next();
                System.out.println("Stan konta tego pacjenta wynosi: " + patientService.checkWallet(name, surname));
            case 2:
                BigInteger pesel = scanner.nextBigInteger();
                System.out.println("Stan konta tego pacjenta wynosi: " + patientService.checkWallet(pesel));

        }

    }

    private static void deletePatient() {
        System.out.println("Czy na pewno chcesz usunąć pacjenta ? ");
        System.out.println("1. Tak");
        System.out.println("2. Nie");
        Integer action = scanner.nextInt();
        if (action == 1) {
            System.out.println("W celu usunięcia pacjenta podaj imię, nazwisko oraz PESEL osoby, którą chcesz usunąć z rejestru: ");
            String name = scanner.next();
            String surname = scanner.next();
            BigInteger pesel = scanner.nextBigInteger();
            patientService.deletePatient(name, surname, pesel);
            System.out.println("Udało Ci się usunąć pacjenta z rejestru");
        } else {
            switch (action) {
                case 1:
                    System.out.println("1. Powrot do menu glownego");
                    System.out.println("2. Zakoncz program");
                    action = scanner.nextInt();
                    if (action == 1) {
                        menuGlowne();
                    } else if (action == 2) {
                        System.exit(0);
                    }
            }
        }


    }

    private static void isRegistered() {
        System.out.println("Sprawdź czy pacjent jest zarejestrowany po: \n0 - Zakończ działanie \n1 - imieniu i nazwisku \n2 - numerze PESEL");
        Integer action = scanner.nextInt();

        switch (action) {
            case 0:
                break;
            case 1:
                System.out.println("Podaj imię: ");
                String name = scanner.next();
                System.out.println("Podaj nazwisko: ");
                String surname = scanner.next();
                if (patientList.contains(name) && patientList.contains(surname)) {
                    System.out.println("Pacjent o takim imieniu i nazwisku już występuje, proszę o podanie nr PESEL: ");
                    String pesel = scanner.next();
                    if (patientList.contains(pesel)) {
                        System.out.println("Pacjent o takich danych jest już zarejestrowany!");
                    } else {
                        System.out.println(patientService.isRegistered(new BigInteger(pesel)));
                    }
                } else {
                    System.out.println(patientService.isRegistered(name, surname));
                }
                menuGlowne();
            case 2:
                System.out.println("Podaj pesel: ");
                String pesel = scanner.next();
                if (patientList.contains(pesel)) {
                    System.out.println("Pacjent o takich danych jest już zarejestrowany!");
                } else {
                    System.out.println(patientService.isRegistered(new BigInteger(pesel)));
                }
                break;
            default:
                switch (action) {
                    case 1:
                        System.out.println("1. Powrot do menu glownego");
                        System.out.println("2. Zakoncz program");
                        action = scanner.nextInt();
                        if (action == 1) {
                            menuGlowne();
                        } else if (action == 2) {
                            System.exit(0);
                        }
                }
        }
    }

    private static void koronaTest() {
        int price = 500;
        double pay;
        System.out.println("Czy chcesz dokonać testu na koronawirus na wszystkich pacjentach? (Koszt = 500 zł) \n0 - Nie. \n1 - Tak.");
        Integer action = scanner.nextInt();

        switch (action) {
            case 0:
                break;
            case 1:
                for (Patient patient : patientList) {
                    if (patient.getWallet() > price) {
                        List<Patient> withTest = new ArrayList<>(Arrays.asList(patient));
                        for (Patient patient1 : withTest) {
                            pay = patient.getWallet() - price;
                            patient.setWallet(pay);
                            Random random = new Random();
                            Integer random1 = random.nextInt(2);
                            System.out.println(random1);
                            switch (random1) {
                                case 1:
                                    patient.setCoronaTest("Chory");
                                    break;
                                case 2:
                                    patient.setCoronaTest("Zdrowy");
                                    break;
                            }
                            System.out.println("Udało się przeprowadzić test dla pacjenta: " + patient1);
                        }

                    } else if (patient.getWallet() < price) {
                        List<Patient> withoutTest = new ArrayList<>(Arrays.asList(patient));
                        for (Patient patient2 : withoutTest) {
                            patient.setCoronaTest("Nie przeprowadzono testu");
                            System.out.println("Brak wystarczających środków dla pacjenta: " + patient2);
                        }
                    }
                }
        }
        ApachePOIExcelWrite.createExcel(patientList);

        switch (action) {
            case 1:
                System.out.println("1. Powrot do menu glownego");
                System.out.println("2. Zakoncz program");
                action = scanner.nextInt();
                if (action == 1) {
                    menuGlowne();
                } else if (action == 2) {
                    System.exit(0);
                }
        }
    }
}


