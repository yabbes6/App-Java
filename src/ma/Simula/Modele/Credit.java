package ma.Simula.Modele;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.var;

@Data@AllArgsConstructor@NoArgsConstructor
public class Credit {
    private Long id;
    private Double capital_emprunter;
    private Integer nbr_mois;
    private Double taux;
    private String  demandeur;
    private Double mensualite;

    @Override
    public String toString(){
        var creditStr="=====================================================    \n";
        creditStr+="=> Credit n° "+getId()+"                                    \n";
        creditStr+="=> Nom du demandeur de credit :"+getDemandeur()+"           \n";
        creditStr+="=>______________________________________________________    \n";
        creditStr+="=> Capitale Empunte           : " +getCapital_emprunter()+"DH\n";
        creditStr+="=> Nombre de mois             : " + getNbr_mois() + "mois       \n";
        creditStr+="=> Taux mensuel               : "+getTaux()+"%              \n";
        creditStr+="=>______________________________________________________    \n";
        creditStr+="=> Mensualite                 : "
                +(getMensualite() == 0.0 ? "Non-Calculer" : getMensualite()+"Dh/mois")+"\n";
        creditStr+="=====================================================    \n";

        return creditStr;
    }

}
