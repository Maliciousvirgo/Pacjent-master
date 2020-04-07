import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigInteger;

@Data
@AllArgsConstructor
public class Patient {
    private String name ;
    private String surname ;
    private BigInteger pesel ;
    private Double wallet ;
    private String coronaTest ;
}
