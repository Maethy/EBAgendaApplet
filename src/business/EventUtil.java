/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import tables.EbEvents;
import tables.EbReleventempdate;
import tables.Employees;
import util.HibernateUtil;

/**
 *
 * @author Maethy
 */
public class EventUtil {
    private static Session session;
    private static Transaction transaction;
    
    public static void openSession(){
        session = HibernateUtil.getSessionFactory().openSession();
        
    }
    
    public static List<EbEvents> fetchEventFromTo(Calendar fromCal, 
            Calendar toCal){
        transaction = session.beginTransaction();
        java.util.Date from, to;
        from = fromCal.getTime();
        to = toCal.getTime();
        System.out.println(from);
        System.out.println(to);
        List <EbEvents> rs = new ArrayList();
        Criterion betweenDate = Restrictions.and(Restrictions.le("ebEventsStartDate", to),Restrictions.ge("ebEventsEndDate", from));
        Criteria criteria = session.createCriteria(EbEvents.class);
        criteria.add(betweenDate);
        rs = criteria.list();
        transaction.commit();
        return rs;
    }
    
    public static EbReleventempdate getEventUser(int eventId, int employeeId){
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
        return (EbReleventempdate)myQuery.uniqueResult();
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
        criteria.add(Restrictions.eq("disableEmpl", 0));
        return criteria.list();
    }
}
