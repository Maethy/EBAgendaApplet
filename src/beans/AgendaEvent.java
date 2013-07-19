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
    private Date start, end;
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
        title = new JLabel(this.summary);//+" "+this.hashCode()+" "+this.parentDay.hashCode());
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
     public AgendaEvent(AgendaDay parent, String summary, int id, Date start, Date end) {
        this.summary = summary;
        this.parentDay = parent;
        this.dateOfEvent = parent.getDateOfDay();
        this.id = id;
//        System.out.println("hash from parentday "+this.parentDay.hashCode()+" / "+parent.hashCode());
        title = new JLabel(this.summary+" "+start+" to "+end);//+" "+this.hashCode()+" "+this.parentDay.hashCode());
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
        
        this.isWholeDayEvent = a.isWholeDayEvent;
        this.parentDay = a.parentDay;
        this.summary = String.copyValueOf(a.summary.toCharArray());
        this.title = new JLabel(a.title.getText());

        
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

    
    
    @Override
    public String toString() {
        return summary;
    }
    
}
