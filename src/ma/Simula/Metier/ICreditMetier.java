package ma.Simula.Metier;


import ma.Simula.Modele.Credit;

@FunctionalInterface
public interface ICreditMetier {

    Credit calculerMensualite (Long idCredit) throws Exception;
}
