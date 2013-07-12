/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

/**
 *
 * @author MpUser
 */
public class TransferableEvent implements Transferable {

    private AgendaEvent event;
    private AgendaDay day;
    public static DataFlavor agendaEventFlavor = new DataFlavor(AgendaEvent.class, 
            AgendaEvent.class.getSimpleName());
    public static DataFlavor agendaDayFlavor = new DataFlavor(AgendaDay.class, 
            AgendaDay.class.getSimpleName());
    
    public TransferableEvent(AgendaEvent event, AgendaDay agendaDay){
        this.event = event; 
        this.day = agendaDay;
        System.out.println("-----------------------------");
        System.out.println("Hash from day to transfer "+this.event.hashCode());
        System.out.println("Hash in transfer from day "+event.hashCode());        
        System.out.println("Hash from day parentday to transfer "+this.day.hashCode());
        System.out.println("Hash in transfer parentday from day "+agendaDay.hashCode());
        System.out.println("-----------------------------");
    }
    
    @Override
    public DataFlavor[] getTransferDataFlavors() {
        return new DataFlavor[] {agendaEventFlavor};
    }

    @Override
    public boolean isDataFlavorSupported(DataFlavor flavor) {
        return flavor.equals(agendaEventFlavor);
    }

    @Override
    public Object getTransferData(DataFlavor flavor) throws 
            UnsupportedFlavorException, IOException {
        if (flavor.equals(agendaEventFlavor)){
            System.out.println("..............transferable returning event "+this.event.hashCode()+"..................");
            return this.event;
        }
        if(flavor.equals(agendaDayFlavor)){
            System.out.println("..............transferable returning day "+this.day.hashCode()+"..................");
            return this.day;
        }else{
            throw new UnsupportedFlavorException(flavor);
        }
    }
}
    