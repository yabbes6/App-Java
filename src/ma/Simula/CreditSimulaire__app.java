package ma.Simula;

import javafx.application.Application;
import lombok.Data;
import lombok.var;
import ma.Simula.Dao.DaoVolatile.CreditDao;
import ma.Simula.Dao.IDao;
import ma.Simula.Metier.CreditMetier;
import ma.Simula.Metier.ICreditMetier;
import ma.Simula.Modele.Credit;
import ma.Simula.Prentation.CreditControleur;
import ma.Simula.Prentation.ICreditControleur;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Properties;
import java.util.Scanner;

@Data
public class CreditSimulaire__app {

    static ICreditControleur creditControleur;
    static Scanner clavier = new Scanner(System.in);

    public static void main(String[] args) throws Exception{
        test3();
    }

    public static void test3() throws Exception{
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-IOC.xml");
        //creditControleur = (ICreditControleur) context.getBean("controleur");
        creditControleur = context.getBean(ICreditControleur.class);
        creditControleur.afficherMensualite(1L);
    }
    public static void test2() throws Exception{
        String daoClass ;
        String serviceClass;
        String controllerClass;

        Properties properties = new Properties();

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream propertiesFile = classLoader.getResourceAsStream("Config.properties");


        if(propertiesFile==null) throw new Exception("fichier config introuvable !!!");
        else {
            try {
                properties.load(propertiesFile);
                daoClass = properties.getProperty("DAO");
                serviceClass = properties.getProperty("SERVICE");
                controllerClass = properties.getProperty("CONTROLLER");

                propertiesFile.close();
            }
            catch(IOException e){
                throw new Exception("Probleme de chargement des proprietes du fichier config");
            }finally {
                properties.clear();
            }
        }

        try {
            Class cDao = Class.forName(daoClass);
            Class cMetier = Class.forName(serviceClass);
            Class cController = Class.forName(controllerClass);

            var dao = (IDao<Credit,Long>)cDao.getDeclaredConstructor().newInstance();
            var metier = (ICreditMetier)cMetier.getDeclaredConstructor().newInstance();
            creditControleur = (ICreditControleur) cController.getDeclaredConstructor().newInstance();

            Method setDao = cMetier.getMethod("setCreditDao",IDao.class);
            setDao.invoke(metier, dao);

            Method setMetier = cController.getMethod("setCreditDao", ICreditMetier.class);
            setMetier.invoke(creditControleur,metier);

            creditControleur.afficherMensualite(1L);
        }
        catch(Exception e){e.printStackTrace();}
    }
    public static void test1(){
        var dao     =   new CreditDao();
        var metier  =   new CreditMetier();
        var controleur  =   new CreditControleur();

        metier      .setCreditDao(dao);
        controleur  .setCreditMetier(metier);

        String rep = "";
        do{
            System.out.println("=> [Test 1] Calcule de Mensualite de credit <=\n");
            try{
                String input = "";
                while (true) {
                    System.out.println("=> Entrez l'id du credit : ");
                    input = clavier.nextLine();
                    if(estUnNombre(input)) break;
                    System.err.println("Entree nom valide !!!");
                }
                long id = Long.parseLong(input);
                controleur.afficherMensualite(id);
            } catch (Exception e){
                System.err.println(e.getMessage());
            }
            System.out.println("Voulez vous quittez (oui/non) ? ");
            rep = clavier.nextLine();
        }while(!rep.equalsIgnoreCase("oui"));
        System.out.println("Au revoir ^_^");

    }

    private static boolean estUnNombre(String input) {
        try{Integer.parseInt(input); return true;}
        catch(Exception e) { return false;}
    }
}
