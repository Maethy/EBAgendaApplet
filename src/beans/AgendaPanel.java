/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import business.EventUtil;
import com.toedter.calendar.JCalendar;
import java.awt.Component;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
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
    private List<AgendaUser> userList, oldList;
    private JCalendar jcalendar;
    private Calendar startCalendar;
    private Calendar endCalendar;
    private Calendar newStart, newEnd;
    
    public AgendaPanel(){
        this.setSize(800, 480);
        this.startCalendar = Calendar.getInstance();
        this.endCalendar = Calendar.getInstance();
        initComponents();
    }
    
    public AgendaPanel(Calendar startCalendar, Calendar endCalendar, 
            List<AgendaUser> userList){    
        this.userList = userList;
        this.oldList = new ArrayList();
        this.oldList.addAll(userList);
        this.setSize(800, 480);
        this.startCalendar = Calendar.getInstance();
        this.endCalendar = endCalendar;
        this.startCalendar.setTime(startCalendar.getTime());
        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.PAGE_AXIS));
        
        initComponents();
    }
    
    public void ReloadAgendaPanel(Calendar startCalendar, Calendar endCalendar){
//        System.out.println(this.startCalendar.get(Calendar.DAY_OF_MONTH)+" "+startCalendar.get(Calendar.DAY_OF_MONTH));
//        System.out.println(this.endCalendar.get(Calendar.DAY_OF_MONTH)+" "+endCalendar.get(Calendar.DAY_OF_MONTH));
        int currentDay = startCalendar.get((Calendar.DAY_OF_WEEK)-1)%7;
        int lastCurrentDay = endCalendar.get((Calendar.DAY_OF_WEEK)-1)%7;
        newStart = Calendar.getInstance();
        newEnd = Calendar.getInstance();
        newStart.setTime(startCalendar.getTime());
        newEnd.setTime(endCalendar.getTime());
        newStart.add(Calendar.DAY_OF_YEAR, -currentDay);
        newEnd.add(Calendar.DAY_OF_YEAR, 6-lastCurrentDay);
        if(this.startCalendar.get(Calendar.DAY_OF_MONTH)==
                startCalendar.get(Calendar.DAY_OF_MONTH)&&
                this.startCalendar.get(Calendar.MONTH)==
                startCalendar.get(Calendar.MONTH)&&
                        this.startCalendar.get(Calendar.YEAR)==
                startCalendar.get(Calendar.YEAR)&&
                this.endCalendar.get(Calendar.DAY_OF_MONTH)==
                endCalendar.get(Calendar.DAY_OF_MONTH)&&
                this.endCalendar.get(Calendar.MONTH)==
                endCalendar.get(Calendar.MONTH)&&
                        this.endCalendar.get(Calendar.YEAR)==
                endCalendar.get(Calendar.YEAR)&& 
                this.oldList.equals(this.userList)){
            List<EbEvents> rs = EventUtil.fetchEventFromTo(newStart, newEnd);
            for(Component comp : this.getComponents()){
                if(comp instanceof AgendaWeek){
                    AgendaWeek week = (AgendaWeek) comp;
                    week.reloadWeek(rs);
                }
            }
            
        }else{
            this.oldList=new ArrayList();
            this.oldList.addAll(userList);       
            this.setSize(800, 480);
            this.startCalendar = Calendar.getInstance();
            this.endCalendar = endCalendar;
            this.startCalendar.setTime(startCalendar.getTime());
            setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.PAGE_AXIS));

            initComponents();
        }
    }

    
    private void initComponents(){
        removeAll();

        
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
                    nbWeeks = (nbYears * 52) + nbWeeks;
            }
        }
        int currentDay = startCalendar.get((Calendar.DAY_OF_WEEK)-1)%7;
        int lastCurrentDay = endCalendar.get((Calendar.DAY_OF_WEEK)-1)%7;
//        currentDay--;
        newStart = Calendar.getInstance();
        newEnd = Calendar.getInstance();
        newStart.setTime(startCalendar.getTime());
        newEnd.setTime(endCalendar.getTime());
        newStart.add(Calendar.DAY_OF_YEAR, -currentDay);
        newEnd.add(Calendar.DAY_OF_YEAR, 6-lastCurrentDay);
        List<EbEvents> rs = EventUtil.fetchEventFromTo(newStart, newEnd);

        for(int i=0; i<nbWeeks;i++){
            add(new AgendaWeek(this, newStart, rs, userList));
            newStart.add(Calendar.WEEK_OF_YEAR, 1);
            add(new JSeparator());           
        }
        revalidate();
    }
    
}
        
