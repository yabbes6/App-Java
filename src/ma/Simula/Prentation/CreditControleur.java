package ma.Simula.Prentation;

import lombok.Data;
import lombok.var;
import ma.Simula.Metier.ICreditMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

@Data @Controller("controleur")
public class CreditControleur implements ICreditControleur {

    @Autowired
    @Qualifier("metier")
    ICreditMetier creditMetier;

    @Override
    public void afficherMensualite(Long idCredit) throws Exception{
        System.out.println("Appel au service credit metier");
        var credit = creditMetier.calculerMensualite(idCredit);
        System.out.println(credit);
    }
}
