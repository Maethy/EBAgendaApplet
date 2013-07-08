/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import com.toedter.calendar.JCalendar;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author jsmaya
 */
public class AgendaPanel extends JPanel {
    private javax.swing.JLabel title;    
    private List<AgendaWeek> weekList;
    private JCalendar jcalendar;
    private Calendar startCalendar;
    private Calendar endCalendar;
    private JPanel header;
    
    
    public AgendaPanel(Calendar startCalendar, Calendar endCalendar){    
        this.setSize(800, 480);
        this.startCalendar = Calendar.getInstance();
        this.endCalendar = endCalendar;
        this.startCalendar.setTime(startCalendar.getTime());
        initComponents();
    }
    
    private void initComponents(){
        header = new JPanel();
        title = new JLabel("EuroBrevets Agenda");
        add(title);
        header.setLayout(new GridLayout(1,7));
        for(int i=0; i<7; i++){
            header.add(new javax.swing.JLabel(commons.Constants.DAY_OF_WEEK[i]));
        }
        add(header);
        int nbWeeks = 1;
        if(endCalendar != null){
//            System.out.println(endCalendar.get(Calendar.WEEK_OF_YEAR));
//            System.out.println(startCalendar.get(Calendar.WEEK_OF_YEAR));
            nbWeeks = endCalendar.get(Calendar.WEEK_OF_YEAR)-
                    startCalendar.get(Calendar.WEEK_OF_YEAR);
            nbWeeks++;
        }
        System.out.println(nbWeeks);

        for(int i=0; i<nbWeeks;i++){
            add(new AgendaWeek(startCalendar));
            startCalendar.add(Calendar.WEEK_OF_YEAR, 1);
        }
        revalidate();
        setLayout(new GridLayout(0,1));
    }
}
