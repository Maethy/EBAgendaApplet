/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.awt.Cursor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragSource;

/**
 *
 * @author MpUser
 */
public class DragGestureListImp implements DragGestureListener {
 
		@Override
		public void dragGestureRecognized(DragGestureEvent event) {
			Cursor cursor = null;
                        AgendaEvent agendaEvent = (AgendaEvent) event.getComponent();
                        System.out.println("+++++++++++++++++++++++++++++++++++++");
                        System.out.println("agendaEvent = "+agendaEvent.hashCode());
                        System.out.println("+++++++++++++++++++++++++++++++++++++");

			if (event.getDragAction() == DnDConstants.ACTION_MOVE) {
				cursor = DragSource.DefaultMoveDrop;
			}
			
			event.startDrag(cursor, new TransferableEvent(agendaEvent, agendaEvent.getParentDay()));
		}
                
	}
