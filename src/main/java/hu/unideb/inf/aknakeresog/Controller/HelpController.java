package hu.unideb.inf.aknakeresog.Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * FXML Controller class
 *
 * @author Sándor Ferenc
 */
public class HelpController implements Initializable {

    private static Logger logger = (Logger) LoggerFactory.getLogger(MainController.class);
    
    @FXML
    private Label lbDescription;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        String tmp ="Játékszabályok! \n\n" +
                "Egy egyforma mezőkre osztott táblával indul a játék, \n" +  
                "ezek alatt rejtőzködnek az aknák. A tábla mérete és az aknák \n" +
                "száma a nehézségi szinttől függően változik. A mezők állapotai \n" +
                "a következők lehetnek: \n" +

                "lefedett (alaphelyzet), \n" +
                "feltárt, szomszédos aknával, \n" +
                "feltárt aknamentes, \n" +
                "zászlós (véleményünk szerint akna van alatta), \n" +
                "feltárt, robbanó aknával (ha egy mező ilyen állapotba kerül, a \n" + 
                "játék véget ér, a játékos a menetet elvesztette). A zászlós \n" +
                "állapot az egér jobb gombjával érhető el, csupán segítséget \n" +
                "nyújt a játékhoz. A játékot teljesíteni lehet anélkül is, \n" +
                "hogy akár csak egy mezőt is megjelölnénk zászlóval, ez azonban  \n" +
                "a játékban szerzett jelentős gyakorlatot és az egész játékmenet \n" +
                "során mindvégig komoly figyelmet igényel. \n\n" +
                "Egy mezőt feltárni kattintással lehet. Ha egy mező feltárult, \n" + 
                "és mellette akna található, akkor annak darabszámát egy számmal \n" +
                "fogja jelezni (egy mező mellett értelemszerűen maximum 8 akna  \n" +
                "lehet). Ha a játékos aknamentes környezetű mezőre kattint, akkor \n" +
                "az adott mezőhöz oldal- és sarokhatárosan csatlakozó (aknamentes) \n" +
                "mezők mindegyike feltárul, valamint az így feltáruló aknamentes  \n" +
                "szigettel szomszédos mezők is feltárulnak. \n" +

                "A program folyamatosan jelzi a még megjelöletlen aknák számát, \n" +
                "illetve az eltelt időt. A játék célja: teljesíteni a táblát a \n" +
                "lehető legrövidebb idő alatt. Ha aknára kattintunk, az adott  \n" +
                "mező \"felrobban\", tehát a játék véget ér, s az adott menetet \n" +
                "elvesztettük. Győzelemmel kizárólag abban az esetben fejeződik \n" +
                "be a játék, ha felfedtünk minden olyan mezőt, amely alatt nincs  \n" +
                "akna. A győzelem elérése nem függ attól, hogy hány aknát jelöltünk \n" +
                "meg zászlóval.";
        
        logger.info("Jatekszabalyzat betoltese!");
        
        lbDescription.setText(tmp);
    }    
    
}
