import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigInteger;
import java.util.List;
import java.util.Random;

@Data
@AllArgsConstructor
public class PatientService {


    private List<Patient> patientList;


    public boolean isRegistered(BigInteger pesel) {
        boolean isRegistered = false;

        for (Patient patient : patientList) {
            if (patient.getPesel().equals(pesel)) {
                isRegistered = Boolean.TRUE;
            }
        }
        return isRegistered;
    }

    public boolean isRegistered(String name, String surname) {
        boolean isRegistered = false;

        for (Patient patient : patientList) {
            if (patient.getName().equals(name) && patient.getSurname().equals(surname)) {
                isRegistered = Boolean.TRUE;
            }
        }
        return isRegistered;
    }

    public Double checkWallet(String name, String surname) {
        Double checkWallet = 0.0;
        for (Patient patient : patientList) {
            if (patient.getName().equals(name) && patient.getSurname().equals(surname)) {
                checkWallet = patient.getWallet();
            }
        }
        return checkWallet;
    }

    public Double checkWallet(BigInteger pesel) {
        Double checkWallet = 0.0;
        for (Patient patient : patientList) {
            if (patient.getPesel().equals(pesel)) {
                checkWallet = patient.getWallet();
            }
        }
        return checkWallet;
    }

    public void deletePatient(String name, String surname, BigInteger pesel) {
        Patient deletePatient = null;
        for (Patient patient : patientList) {
            if (patient.getName().equals(name) && patient.getSurname().equals(surname) && patient.getPesel().equals(pesel)) {
                deletePatient = patient;
            }
        }
        patientList.remove(deletePatient);
    }

}
