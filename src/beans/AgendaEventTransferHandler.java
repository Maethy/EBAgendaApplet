/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.InputEvent;
import java.io.IOException;
import javax.swing.JComponent;
import javax.swing.TransferHandler;

/**
 *
 * @author MpUser
 */
public class AgendaEventTransferHandler extends TransferHandler {

    @Override
    public boolean canImport(TransferSupport support) {
        if(!support.isDrop()){
            return false;
        }
        
        if(!support.isDataFlavorSupported(TransferableEvent.agendaEventFlavor)){
            return false;
        }
        boolean moveSupported = (MOVE & support.getSourceDropActions()) == MOVE ;
        if (moveSupported) {
            support.setDropAction(MOVE);
            return true;
        }
        return false;
    }

    @Override
    public int getSourceActions(JComponent c) {
        return MOVE;
    }

    @Override
    public boolean importData(TransferSupport support) {
        if(!canImport(support)){
            return false;
        }
        
        Transferable data = support.getTransferable();
        AgendaEvent agendaEvent = new AgendaEvent();
        try{
            agendaEvent = (AgendaEvent) data.getTransferData(TransferableEvent.agendaEventFlavor);
            System.out.println("transfered");
        }catch (UnsupportedFlavorException ufe){
            ufe.printStackTrace();
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
        
        return true;
    }
}
