package tables;
// Generated 23 juil. 2013 18:33:26 by Hibernate Tools 3.2.1.GA



/**
 * EbRelempeventId generated by hbm2java
 */
public class EbRelempeventId  implements java.io.Serializable {


     private int ebRelEmpEventEventId;
     private int ebRelEmpEventEmpId;

    public EbRelempeventId() {
    }

    public EbRelempeventId(int ebRelEmpEventEventId, int ebRelEmpEventEmpId) {
       this.ebRelEmpEventEventId = ebRelEmpEventEventId;
       this.ebRelEmpEventEmpId = ebRelEmpEventEmpId;
    }
   
    public int getEbRelEmpEventEventId() {
        return this.ebRelEmpEventEventId;
    }
    
    public void setEbRelEmpEventEventId(int ebRelEmpEventEventId) {
        this.ebRelEmpEventEventId = ebRelEmpEventEventId;
    }
    public int getEbRelEmpEventEmpId() {
        return this.ebRelEmpEventEmpId;
    }
    
    public void setEbRelEmpEventEmpId(int ebRelEmpEventEmpId) {
        this.ebRelEmpEventEmpId = ebRelEmpEventEmpId;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof EbRelempeventId) ) return false;
		 EbRelempeventId castOther = ( EbRelempeventId ) other; 
         
		 return (this.getEbRelEmpEventEventId()==castOther.getEbRelEmpEventEventId())
 && (this.getEbRelEmpEventEmpId()==castOther.getEbRelEmpEventEmpId());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + this.getEbRelEmpEventEventId();
         result = 37 * result + this.getEbRelEmpEventEmpId();
         return result;
   }   


}


