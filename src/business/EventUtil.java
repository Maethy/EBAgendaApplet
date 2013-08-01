/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import tables.EbWorktimetable;
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
        if(session!=null&& session.isOpen()){
            session.close();
        }
        session = HibernateUtil.getSessionFactory().openSession();
        
    }
    
    public static List<EbEvents> fetchEventFromTo(Calendar fromCal, 
            Calendar toCal){
        transaction = session.beginTransaction();
        java.util.Date from, to;
        from = fromCal.getTime();
        to = toCal.getTime();
        List <EbEvents> rs;
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
       session.beginTransaction();
        String query = "Select * from ebjava.eb_releventempdate as a where"
                + " a.eb_relEventEmpDateEventId ="+eventId+
                " AND a.eb_relEventEmpDateEmployee ="+ employeeId;
        Query myQuery = session.createSQLQuery(query)
                .addEntity(EbReleventempdate.class);
        session.getTransaction().commit();
        return (List<EbReleventempdate>)myQuery.list();
    }
    
    public static EbReleventempdate getEventUser(int eventEmpId){
        session.beginTransaction();

        String query = "Select * from ebjava.eb_releventempdate as a where"
                + " a.ideb_relEventEmpDate ="+eventEmpId;
        Query myQuery = session.createSQLQuery(query)
                .addEntity(EbReleventempdate.class);
        session.getTransaction().commit();
        return (EbReleventempdate)myQuery.uniqueResult();
    }
        
    public static List<Employees> getOtherParticipants(int eventId, Date date){
        session.beginTransaction();

        String query = "Select * from ebjava.eb_releventempdate as a "
                + "JOIN ebjava.employees as e on a.eb_relEventEmpDateEmployee=e.id"
                + " where a.eb_relEventEmpDateEventId = "+eventId
                +" AND a.eb_relEventEmpDateDate = '"+date+"'";
        Query myQuery = session.createSQLQuery(query)
                .addEntity(Employees.class);
        session.getTransaction().commit();

        return (List<Employees>)myQuery.list();
    }
    
    public static EbEvents addEvent(String eventName, String eventDesc, 
            Calendar eventDate, Calendar endDate, int idCli, 
            Employees[] participants, boolean isFullDay){
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
            endTime.setTime(endDate.getTime());
            endTime.set(Calendar.DAY_OF_YEAR, 
                    startTime.get(Calendar.DAY_OF_YEAR));

            while(startTime.before(endDate)){
                addRelempeventdate(newEvent, currentEmployee, startTime, 
                        endTime, eventDesc, isFullDay);
                startTime.add(Calendar.DAY_OF_YEAR, 1);
                endTime.add(Calendar.DAY_OF_YEAR, 1);
                cpt++;
            }
            //addRelempeventdate(newEvent, currentEmployee, startTime, endTime, eventDesc);

            
        }
        System.out.println(cpt+ "rows inserted");
        return newEvent;
    }
    
//    public static void closeSession(){
//        System.out.println("finalize");
//        if(session!=null){
//            session.close();
//        }
//    }
    public static EbWorktimetable getWorkTime(int emp, Calendar date){
        EbWorktimetable wtt=null;
        Calendar dateOfWork = Calendar.getInstance();
        dateOfWork.setTime(date.getTime());
        dateOfWork.set(Calendar.HOUR_OF_DAY, 0);
        dateOfWork.set(Calendar.MINUTE, 0);
        dateOfWork.set(Calendar.SECOND, 0);
        Criteria criteria = session.createCriteria(EbWorktimetable.class);
        criteria.add(Restrictions.eq("ebWorkTimeTableEmp", emp));
        criteria.add(Restrictions.eq("ebWorkTimeTableDate", dateOfWork.getTime()));
        try{
            wtt = (EbWorktimetable) criteria.uniqueResult();
        }catch (Exception e){
            
        }
        return wtt;
    }
    
    public static Employees getEmployee(int id){
        Criteria criteria = session.createCriteria(Employees.class);
        criteria.add(Restrictions.eq("id", id));
        Employees emp = null;
        try{
            emp = (Employees) criteria.uniqueResult();
        }catch (Exception e){
            
        }
        return emp;
    }
    public static EbWorktimetable addWorkTime(int emp, Calendar date, int hour, int minute){
        EbWorktimetable wtt = new EbWorktimetable();
        wtt.setEbWorkTimeTableEmp(emp);
        wtt.setEbWorkTimeTableDate(date.getTime());
        wtt.setEbWorkTimeTableNbHours(hour);
        wtt.setEbWorkTimeTableNbMin(minute);
        session.beginTransaction();
        session.saveOrUpdate(wtt);
        session.getTransaction().commit();
        return wtt;
    }
    public static int addRelempeventdate(EbEvents event, Employees employee, 
            Calendar start, Calendar end, String desc, boolean isFullDay){
        EbReleventempdate eventEmpDate = new EbReleventempdate(); 
        eventEmpDate.setEbEvents(event);
        eventEmpDate.setEmployees(employee);
        eventEmpDate.setEbRelEventEmpDateStart(start.getTime());
        eventEmpDate.setEbRelEventEmpDateEnd(end.getTime());
        eventEmpDate.setEbRelEventEmpDateJobDesc(desc);
        eventEmpDate.setEbRelEventEmpDateDate(start.getTime());
        eventEmpDate.setEbRelEventEmpDateFullDay(isFullDay);
        session.beginTransaction();
        session.saveOrUpdate(eventEmpDate);
        session.getTransaction().commit();
        return eventEmpDate.getIdebRelEventEmpDate();
        
    }
    
    public static void modRelempeventdate(EbReleventempdate eventEmp, Integer eventId,
            Calendar start, Calendar end, Integer empId, String eventName,
            String eventDesc, String jobDesc, Employees[] empList, 
            EbCli client, boolean fullDay){
        
            EbReleventempdate relEvent = eventEmp;
            EbEvents events = relEvent.getEbEvents();
            System.out.println(events.getEbEventsName());
            
            if(true/*modDate*/){
                events.setEbEventsStartDate(start.getTime());

                int cpt =0;
                for(Employees currentEmployee : empList){
                     List<EbReleventempdate> list = 
                            getEventUser(events.getIdebEvent(), 
                             currentEmployee.getId());
                    Calendar startTime, endTime;
                    startTime = Calendar.getInstance();
                    startTime.setTimeInMillis(start.getTimeInMillis());
                    endTime = Calendar.getInstance();
                    endTime.setTime(end.getTime());
                    endTime.set(Calendar.DAY_OF_YEAR, 
                            startTime.get(Calendar.DAY_OF_YEAR));
                    startTime.set(Calendar.HOUR_OF_DAY, 0);
                    startTime.set(Calendar.MINUTE, 0);
                    startTime.set(Calendar.SECOND, 0);
                    
                    while(startTime.before(end)){
                        EbReleventempdate event = null;
                        Criteria criteria = session.createCriteria
                                (EbReleventempdate.class);
                        criteria.add(Restrictions.eq
                                ("employees", 
                                currentEmployee));
                        criteria.add(Restrictions.eq
                                ("ebEvents", 
                                events));
                        criteria.add(Restrictions.eq("ebRelEventEmpDateDate", 
                                startTime.getTime()));
                        try{
                           event = (EbReleventempdate)
                                    criteria.uniqueResult();
                        }catch (Exception e){
                            System.err.println(e.getMessage());
                        }
                        if(event == null){
                            addRelempeventdate(events, currentEmployee, 
                                    startTime, endTime, eventDesc, 
                                    fullDay);
                            cpt++;
                        }
                        startTime.add(Calendar.DAY_OF_YEAR, 1);
                        endTime.add(Calendar.DAY_OF_YEAR, 1);
                    }
                }
                System.out.println(cpt);
            }
            
            relEvent.setEbRelEventEmpDateStart(start.getTime());
            relEvent.setEbRelEventEmpDateEnd(end.getTime());
            relEvent.setEbRelEventEmpDateJobDesc(jobDesc);
            relEvent.setEbRelEventEmpDateFullDay(fullDay);
            
            events.setEbEventsIdCli(client.getIdebCli());
            events.setEbEventsName(eventName);
            events.setEbEventsDesc(eventDesc);

            
            session.beginTransaction();
            session.update(relEvent);
            session.update(events);
            session.getTransaction().commit();
    }
    
    public static void deleteRelEventEmpDate(Integer relEventId, 
            Integer eventId, Integer empId){
         
        String query = "DELETE FROM eb_releventempdate WHERE"
                + " ideb_relEventEmpDate=" +relEventId;
        if(empId!=null){
            query = "DELETE FROM eb_releventempdate WHERE"
            +" eb_relEventEmpDateEmployee = " +empId
            +" AND eb_relEventEmpDateEventId = "+eventId;
        }else{
            if(eventId!=null){
                 query = "DELETE FROM eb_releventempdate WHERE"
                + " eb_relEventEmpDateEventId= " +eventId;
             }
        }
  
        session.beginTransaction();
        Query myQuery = session.createSQLQuery(query);
        myQuery.executeUpdate();
        deleteOrphanEvent();
        session.getTransaction().commit();
    }
    private static void deleteOrphanEvent(){
        String query = "DELETE FROM ebjava.eb_events WHERE ideb_event " +
                        "NOT IN (select b.eb_relEventEmpDateEventId from "
                     + " ebjava.eb_releventempdate as b)";
        Query myQuery = session.createSQLQuery(query);
        try{
            myQuery.executeUpdate();
        }catch (Exception e){
            System.err.println(e.getCause());
        }
    }
    
    public static List<EbEvents> preciseEventList(Calendar fromCale, Calendar toCale, List<EbEvents> list){
        java.util.Date from, to;
        Calendar fromCal, toCal;
        fromCal = Calendar.getInstance();
        toCal = Calendar.getInstance();
        fromCal.setTime(fromCale.getTime());
        toCal.setTime(toCale.getTime());
        fromCal.set(Calendar.HOUR_OF_DAY, 0);
        fromCal.set(Calendar.MINUTE, 0);
        fromCal.set(Calendar.SECOND, 0);
        toCal.set(Calendar.HOUR_OF_DAY, 0);
        toCal.set(Calendar.MINUTE, 0);
        toCal.set(Calendar.SECOND, 0);
        fromCal.add(Calendar.MINUTE, -1);
        from = fromCal.getTime();
        to = toCal.getTime();

        
        List<EbEvents> preciseList = new ArrayList();
        for(EbEvents ee : list){
            if((ee.getEbEventsStartDate().before(to)||
                    ee.getEbEventsStartDate().equals(to))
                    &&(ee.getEbEventsEndDate().after(from)||
                    (ee.getEbEventsEndDate().equals(from)))){
//                System.out.println(ee.getEbEventsStartDate());
//                System.out.println(ee.getEbEventsEndDate());
                preciseList.add(ee);
            }
        }
        return preciseList;
    }
    
    public static List<Employees> getEmployeeList(){
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Employees.class);
        criteria.add(Restrictions.eq("disableEmpl", false));
        session.getTransaction().commit();
        return criteria.list();
    }
}
