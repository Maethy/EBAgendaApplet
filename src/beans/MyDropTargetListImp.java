/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetListener;

/**
 *
 * @author MpUser
 */

public class MyDropTargetListImp extends DropTargetAdapter implements
			DropTargetListener {
 
		private DropTarget dropTarget;
                private AgendaDay dropSource;
		private AgendaDay panel;
 
		public MyDropTargetListImp(AgendaDay agendaDay) {
			this.panel = agendaDay;
                        
			dropTarget = new DropTarget(panel, DnDConstants.ACTION_MOVE, this,
					true, null);
		}
 
                @Override
		public void drop(DropTargetDropEvent event) {
			try {
				Transferable tr = event.getTransferable();
				AgendaEvent agendaEvent = (AgendaEvent) tr.getTransferData(TransferableEvent.agendaEventFlavor);
                                System.out.println("*****************************");
                                System.out.println(agendaEvent.equals(tr.getTransferData(TransferableEvent.agendaEventFlavor)));
                                System.out.println("from transferable "+(tr.getTransferData(TransferableEvent.agendaEventFlavor)).hashCode());          
                                System.out.println("got from transferable "+(tr.getTransferData(TransferableEvent.agendaEventFlavor)).hashCode());      
                                dropSource = (AgendaDay) tr.getTransferData(TransferableEvent.agendaDayFlavor);
                                System.out.println("drop source : "+dropSource.hashCode());
                                System.out.println("*****************************");
				if (event.isDataFlavorSupported(TransferableEvent.agendaEventFlavor)) {
					event.acceptDrop(DnDConstants.ACTION_MOVE);
                                        dropSource.dragAgendaEvent(agendaEvent);
                                        this.panel.dropAgendaEvent(agendaEvent);
                                        
                                        event.dropComplete(true);
					return;
				}
                                System.out.println("Drop incomplete");
				event.rejectDrop();
			} catch (Exception e) {
				e.printStackTrace();
				event.rejectDrop();
			}
		}
                
	}
