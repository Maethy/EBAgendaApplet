/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import business.EventUtil;
import java.awt.BorderLayout;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragSource;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import tables.EbReleventempdate;

/**
 *
 * @author jsmaya
 */
public class AgendaEvent extends JButton{
    private AgendaDay parentDay;
    private Calendar dateOfEvent;
    private String summary;
    private String description;
    private JLabel title;
    private JCheckBox userValid, adminValid;
    private Calendar start, end;
    private int id;
    private EbReleventempdate eventDate; 
    private boolean isWholeDayEvent;

    public AgendaEvent(){
        this.summary = "dummy";
        title = new JLabel(summary);
        add(title);
    }
    
    public AgendaEvent(AgendaDay parent, String summary) {
        this.summary = summary;
        this.parentDay = parent;
        this.dateOfEvent = parent.getDateOfDay();
        id = this.hashCode();
//        System.out.println("hash from parentday "+this.parentDay.hashCode()+" / "+parent.hashCode());
        title = new JLabel(this.dateOfEvent.get(Calendar.DAY_OF_MONTH)+" "+this.summary);//+" "+this.hashCode()+" "+this.parentDay.hashCode());
        setBackground(parentDay.getBackground());
        add(title);
        
        DragSource ds = new DragSource();
//        this.setTransferHandler(new AgendaEventTransferHandler());
        ds.createDefaultDragGestureRecognizer(this, DnDConstants.ACTION_MOVE, new DragGestureListImp());
        
//        this.addMouseListener(new MouseAdapter(){
          
//      public void mousePressed(MouseEvent e){
//        AgendaEvent ae = (AgendaEvent)e.getSource();
//        TransferHandler handle = ae.getTransferHandler();
//        handle.exportAsDrag(ae, e, TransferHandler.COPY);
//      }
//    });
        this.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(AgendaEvent.this, AgendaEvent.this.summary+" "+AgendaEvent.this.id);
            }
        });
    }
     public AgendaEvent(AgendaDay parent, String summary, int id, 
             Calendar start, Calendar end, boolean isFullDay) {
        this.summary = summary;
        this.parentDay = parent;
        this.dateOfEvent = parent.getDateOfDay();
        this.id = id;
        this.start = Calendar.getInstance();
        this.end = Calendar.getInstance();
        this.start.setTime(start.getTime());
        this.end.setTime(end.getTime());
        this.userValid = new JCheckBox("Valider Temps");
        this.adminValid = new JCheckBox("Verrouiller Temps");
        this.userValid.setContentAreaFilled(false);
        this.isWholeDayEvent = isFullDay;
        eventDate = EventUtil.getEventUser(this.id);
        this.setOpaque(false);
        this.setLayout(new BorderLayout());
//        System.out.println("hash from parentday "+this.parentDay.hashCode()+" / "+parent.hashCode());
        String bold, unbold;
        if(this.isWholeDayEvent){
            bold = "<b>";
            unbold = "</b>";
        }else{
            bold ="";
            unbold="";
        }
        title = new JLabel("<html><body>"+bold+
                +this.dateOfEvent.get(Calendar.DAY_OF_MONTH)
                +" "+this.summary+"<br>from "
                +this.start.get(Calendar.HOUR_OF_DAY)+":"+this.start.get(Calendar.MINUTE)
                +"<br> to "+this.end.get(Calendar.HOUR_OF_DAY)+":"
                +this.end.get(Calendar.MINUTE)+unbold+"</body></html>");
        this.setContentAreaFilled(false);
        this.setOpaque(true);
        setBackground(parentDay.getBackground());
        add(title, BorderLayout.CENTER);
//        add(userValid, BorderLayout.PAGE_END);
        
        this.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                displayEventDetail();            
            }
        });
        
        this.setToolTipText(eventDate.getEbRelEventEmpDateJobDesc());
    }
    
     private void displayEventDetail(){
         JFrame eventFrame;
         AgendaModEventDay modEventPanel;
         eventFrame = new JFrame("Détail évènement");
         eventFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
         modEventPanel = new AgendaModEventDay(eventFrame, eventDate);
         eventFrame.add(modEventPanel);
         eventFrame.pack();
         eventFrame.setVisible(true);
         
     }
     
    public AgendaEvent(AgendaEvent a){
        
        this.dateOfEvent = Calendar.getInstance();
        this.dateOfEvent.setTimeInMillis(a.dateOfEvent.getTimeInMillis());
        
        if(a.description !=null){
            this.description = String.copyValueOf(a.description.toCharArray());
        }
        
        this.id = a.id;
        this.start = a.start;
        this.end = a.end;
        this.isWholeDayEvent = a.isWholeDayEvent;
        this.parentDay = a.parentDay;
        this.summary = String.copyValueOf(a.summary.toCharArray());
        this.title = new JLabel(a.title.getText());
        this.eventDate = a.eventDate;
        this.setContentAreaFilled(false);
        setBackground(parentDay.getBackground());
        add(title);

        this.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                AgendaEvent.this.displayEventDetail();
            }
        });
       this.setToolTipText(eventDate.getEbRelEventEmpDateJobDesc());

        
    }

    
    
    public String getSummary() {
        return summary;
    }

    public AgendaDay getParentDay() {
        return parentDay;
    }

    public void setParentDay(AgendaDay parentDay) {
        this.parentDay = parentDay;
    }

    public Calendar getStart() {
        return start;
    }

    public void setStart(Calendar start) {
        this.start = start;
    }

    public Calendar getEnd() {
        return end;
    }

    public void setEnd(Calendar end) {
        this.end = end;
    }

    public boolean isIsWholeDayEvent() {
        return isWholeDayEvent;
    }

    
    
    
    @Override
    public String toString() {
        return summary;
    }
    
}
