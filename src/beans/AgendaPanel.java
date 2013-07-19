/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import business.EventUtil;
import com.toedter.calendar.JCalendar;
import java.awt.GridLayout;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import tables.EbEvents;

/**
 *
 * @author jsmaya
 */
public class AgendaPanel extends JPanel {
    private javax.swing.JLabel title;    
    private List<AgendaWeek> weekList;
    private List<AgendaUser> userList;
    private JCalendar jcalendar;
    private Calendar startCalendar;
    private Calendar endCalendar;
    
    
    public AgendaPanel(){
        this.setSize(800, 480);
        this.startCalendar = Calendar.getInstance();
        this.endCalendar = Calendar.getInstance();
        initComponents();
    }
    
    public AgendaPanel(Calendar startCalendar, Calendar endCalendar, 
            List<AgendaUser> userList){    
        this.userList = userList;
        this.setSize(800, 480);
        this.startCalendar = Calendar.getInstance();
        this.endCalendar = endCalendar;
        this.startCalendar.setTime(startCalendar.getTime());
        initComponents();
    }

    private void initComponents(){
        title = new JLabel("EuroBrevets Agenda");  
       
        add(title);
        add(new JSeparator());
        int nbWeeks = 4;
        int nbYears = 1;
        if(endCalendar != null){
            //Calcule le nombre de semaines à afficher
            nbWeeks = endCalendar.get(Calendar.WEEK_OF_YEAR)-
                    startCalendar.get(Calendar.WEEK_OF_YEAR);
            nbWeeks++;  //au moins 1 semaine
            //Calcule le nombre d'années à afficher
            nbYears = endCalendar.get(Calendar.YEAR)-
                    startCalendar.get(Calendar.YEAR);
            if(nbYears>0){      //l'année de fin est au moins l'année suivante à celle du début
//                if(nbWeeks<0){
//                    nbWeeks=nbWeeks+52;
//                    System.out.println("nbWeeks négatif");
//                }else{
                    nbWeeks = (nbYears * 52) + nbWeeks;
                    System.out.println("nbWeeks supérieur à 1 an donc "+nbWeeks);
//                }
            }
//            nbYears++;  //au moins l'année en cours
//            nbWeeks = (nbYears * 52) + nbWeeks;
        }
//        System.out.println(nbWeeks);
        int currentDay = startCalendar.get((Calendar.DAY_OF_WEEK)-1)%7;
        int lastCurrentDay = endCalendar.get((Calendar.DAY_OF_WEEK)-1)%7;
//        currentDay--;
        startCalendar.add(Calendar.DAY_OF_YEAR, -currentDay);
        endCalendar.add(Calendar.DAY_OF_YEAR, 6-lastCurrentDay);
        EventUtil.openSession();
        List<EbEvents> rs = EventUtil.fetchEventFromTo(startCalendar, endCalendar);

        for(int i=0; i<nbWeeks;i++){
            add(new AgendaWeek(this, startCalendar, rs, userList));
            startCalendar.add(Calendar.WEEK_OF_YEAR, 1);
            add(new JSeparator());           
            add(new JSeparator());
        }
        revalidate();
//        setLayout(new GridLayout(0,1));
        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.PAGE_AXIS));
    }
    
}
        
