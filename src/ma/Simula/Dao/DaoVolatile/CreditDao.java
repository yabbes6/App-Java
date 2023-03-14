package ma.Simula.Dao.DaoVolatile;

import lombok.var;
import ma.Simula.Dao.IDao;
import ma.Simula.Modele.Credit;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Component("dao")
public class CreditDao  implements IDao<Credit, Long> {


    static Set<Credit> BD_Credit(){
        var credits = new HashSet<Credit>(
                Arrays.asList(
                        new Credit(1L,30000.00,120,2.5,"Amine",1.0),
                        new Credit(4L,600000.00,170,2.5,"Hamid",1.0),
                        new Credit(9L,80000.00,152,2.5,"Amina",1.0),
                        new Credit(5L,100000.00,160,2.5,"Hajaj",1.0)
                )) ;

        return credits;
    }
    @Override
    public Credit trouverParId(Long id) {
        System.out.println("[DAO - DS volatile] trouver le Crédit n° : " + id);
        return BD_Credit()
                .stream()
                .filter(credi -> credi.getId() == id)
                .findFirst()
                .orElse(null);

    }
}
