/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import beans.AgendaDay;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import tables.EbCli;
import tables.EbEvents;
import tables.EbReleventempdate;
import tables.Employees;
import ui.TestApplet;
import util.HibernateUtil;

/**
 *
 * @author Maethy
 */
public class EventUtil {
    private static Session session;
    private static Transaction transaction;
    private static TestApplet tApp;
    
    public static void openSession(){
        session = HibernateUtil.getSessionFactory().openSession();
        
    }
    
    public static List<EbEvents> fetchEventFromTo(Calendar fromCal, 
            Calendar toCal){
        transaction = session.beginTransaction();
        java.util.Date from, to;
        from = fromCal.getTime();
        to = toCal.getTime();
        List <EbEvents> rs = new ArrayList();
        Criterion betweenDate = Restrictions.and(Restrictions.le("ebEventsStartDate", to),Restrictions.ge("ebEventsEndDate", from));
        Criteria criteria = session.createCriteria(EbEvents.class);
        criteria.add(betweenDate);
        rs = criteria.list();
        transaction.commit();
        return rs;
    }
    
    public static List<EbCli> getClients(){
        return session.createCriteria(EbCli.class).list();
    }

    public static TestApplet gettApp() {
        return tApp;
    }

    public static void settApp(TestApplet tApp) {
        EventUtil.tApp = tApp;
    }
    
    
    
    public static List<EbReleventempdate> getEventUser(int eventId, int employeeId){
        String query = "Select * from eb.eb_releventempdate as a where"
                + " a.eb_relEventEmpDateEventId ="+eventId+
                " AND a.eb_relEventEmpDateEmployee ="+ employeeId;
        Query myQuery = session.createSQLQuery(query)
                .addEntity(EbReleventempdate.class);
//        List<EbReleventempdate> listEventdate = session.createCriteria
//                (EbReleventempdate.class).add(Restrictions.eq("idebRelEventEmpDateEventId", eventId))
//                .add(Restrictions.eq("idebRelEventEmpDateEmployeeId", employeeId))
//                .list();
//        EbReleventempdateId id = 
//                (EbReleventempdateId)session.createCriteria
//                (EbReleventempdateId.class)
//                .add(Restrictions.eq("ebRelEventDateEventId", eventId))
//                .add(Restrictions.eq("ebRelEventEmpDateEmployee", employeeId))
//                .uniqueResult();
//        System.out.println(id);
//        EbReleventempdate eventEmpDate = (EbReleventempdate)
//                session.createCriteria(EbReleventempdate.class)
//                .add(Restrictions.idEq(id)).uniqueResult();
//        System.out.println(eventEmpDate);
        return (List<EbReleventempdate>)myQuery.list();
    }
    public static EbEvents addEvent(String eventName, String eventDesc, 
            Calendar eventDate, Calendar endDate, int idCli, Employees[] participants){
        EbEvents newEvent;
        int cpt=0;
        newEvent = new EbEvents(eventDate.getTime(), idCli);
        if(endDate!=null){
            newEvent.setEbEventsEndDate(endDate.getTime());
        }
        if(eventName!=null){
            newEvent.setEbEventsName(eventName);
        }
        if(eventDesc!=null){
            newEvent.setEbEventsDesc(eventDesc);
        }
        
        session.beginTransaction();
        session.saveOrUpdate(newEvent);
        session.getTransaction().commit();
        for(Employees currentEmployee : participants){
            Calendar startTime, endTime;
            startTime = Calendar.getInstance();
            startTime.setTimeInMillis(eventDate.getTimeInMillis());
            endTime = Calendar.getInstance();
            endTime.setTimeInMillis(endDate.getTimeInMillis());
            endTime.set(Calendar.DAY_OF_YEAR, 
                    startTime.get(Calendar.DAY_OF_YEAR));
            
            while(startTime.get(Calendar.DAY_OF_YEAR)<=endDate.get(Calendar.DAY_OF_YEAR)){
                addRelempeventdate(newEvent, currentEmployee, startTime, endTime, eventDesc);
                startTime.add(Calendar.DAY_OF_YEAR, 1);
                endTime.add(Calendar.DAY_OF_YEAR, 1);
                cpt++;
            }
            //addRelempeventdate(newEvent, currentEmployee, startTime, endTime, eventDesc);

            
        }
        System.out.println(cpt+ "rows inserted");
        return newEvent;
    }
    
    public static int addRelempeventdate(EbEvents event, Employees employee, 
            Calendar start, Calendar end, String desc){
        EbReleventempdate eventEmpDate = new EbReleventempdate(); 
        eventEmpDate.setEbEvents(event);
        eventEmpDate.setEmployees(employee);
        eventEmpDate.setEbRelEventEmpDateStart(start.getTime());
        eventEmpDate.setEbRelEventEmpDateEnd(end.getTime());
        eventEmpDate.setEbRelEventEmpDateJobDesc(desc);
        session.beginTransaction();
        session.saveOrUpdate(eventEmpDate);
        session.getTransaction().commit();
        return eventEmpDate.getIdebRelEventEmpDate();
        
    }
    
    public static List<EbEvents> preciseEventList(Calendar fromCal, Calendar toCal, List<EbEvents> list){
        java.util.Date from, to;
        from = fromCal.getTime();
        to = toCal.getTime();
        List<EbEvents> preciseList = new ArrayList();
        for(EbEvents ee : list){
            if(ee.getEbEventsStartDate().before(to)&&ee.getEbEventsEndDate().after(from)){
                preciseList.add(ee);
            }
        }
        return preciseList;
    }
    
    public static List<Employees> getEmployeeList(){
        Criteria criteria = session.createCriteria(Employees.class);
        criteria.add(Restrictions.eq("disableEmpl", false));
        return criteria.list();
    }
}
