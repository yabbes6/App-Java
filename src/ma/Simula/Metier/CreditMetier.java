package ma.Simula.Metier;

import lombok.Data;
import lombok.var;
import ma.Simula.Dao.IDao;
import ma.Simula.Modele.Credit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


@Data @Service("metier")
public class CreditMetier implements ICreditMetier {

    @Autowired
            @Qualifier("dao")
    IDao<Credit, Long> creditDao;

    public Credit calculerMensualite (Long idCredit) throws Exception{

        var credit = creditDao.trouverParId(idCredit);
        if (credit == null){
            throw new Exception("Credit introuvable");
        }else
        {
            double mensualite =0.0;
            double taux = credit.getTaux();
            double c = credit.getCapital_emprunter();
            int nbrMois = credit.getNbr_mois();

            taux = taux/1200;

            mensualite = (c*taux)/(1-Math.pow((1+taux),-1*nbrMois));
            mensualite = Math.round(mensualite*100)/100;

            credit.setMensualite(mensualite);
        }
        return credit;
    }
}
