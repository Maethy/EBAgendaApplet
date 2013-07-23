/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragSource;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Calendar;
import java.util.Locale;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

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
    private Calendar start, end;
    private int id;
    
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
     public AgendaEvent(AgendaDay parent, String summary, int id, Calendar start, Calendar end) {
        this.summary = summary;
        this.parentDay = parent;
        this.dateOfEvent = parent.getDateOfDay();
        this.id = id;
        this.start = Calendar.getInstance();
        this.end = Calendar.getInstance();
        this.start.setTime(start.getTime());
        this.end.setTime(end.getTime());
//        System.out.println("hash from parentday "+this.parentDay.hashCode()+" / "+parent.hashCode());
        title = new JLabel("<html><body>"
                +this.dateOfEvent.get(Calendar.DAY_OF_MONTH)
                +" "+this.summary+"<br>from "
                +this.start.get(Calendar.HOUR_OF_DAY)+":"+this.start.get(Calendar.MINUTE)
                +"<br> to "+this.end.get(Calendar.HOUR_OF_DAY)+":"
                +this.end.get(Calendar.MINUTE)+"</body></html>");
        this.setContentAreaFilled(false);
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
        this.setContentAreaFilled(false);
        setBackground(parentDay.getBackground());
        add(title);

        this.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(AgendaEvent.this, AgendaEvent.this.summary+" "+AgendaEvent.this.id);
            }
        });

        
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

    
    
    @Override
    public String toString() {
        return summary;
    }
    
}
