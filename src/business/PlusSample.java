package business;

/*
 * Copyright (c) 2010 Google Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */



import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java7.auth.oauth2.FileCredentialStoreJava7;
import com.google.api.client.extensions.java6.auth.oauth2.FileCredentialStore;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.CalendarListEntry;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;
import com.google.api.services.calendar.model.Events;
import com.sun.org.apache.xpath.internal.operations.Plus;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Collections;
import java.util.List;
import java.util.TimeZone;


/**
 * @author Yaniv Inbar
 */
public class PlusSample {

  /**
   * Be sure to specify the name of your application. If the application name is {@code null} or
   * blank, the application will log a warning. Suggested format is "MyCompany-ProductName/1.0".
   */
  private static final String APPLICATION_NAME = "Agenda";
  
  /** Global instance of the HTTP transport. */
  private static HttpTransport HTTP_TRANSPORT;

  /** Global instance of the JSON factory. */
  private static final JsonFactory JSON_FACTORY = new JacksonFactory();

  
  private static Event event;
  private static Calendar calendar;
  private static Plus plus;

  /** Authorizes the installed application to access user's protected data. */
  private static Credential authorize() throws Exception {
    // load client secrets
    GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY,
//        new InputStreamReader(PlusSample.class.getResourceAsStream("/client_secrets.json")));
        new FileInputStream("client_secrets.json"));

    if (clientSecrets.getDetails().getClientId().startsWith("Enter")
        || clientSecrets.getDetails().getClientSecret().startsWith("Enter ")) {
      System.out.println(
          "Enter Client ID and Secret from https://code.google.com/apis/console/?api=plus "
          + "into plus-cmdline-sample/src/main/resources/client_secrets.json");
      System.exit(1);
    }
    // set up file credential store
   FileCredentialStore credentialStore = new FileCredentialStoreJava7(
        new File(System.getProperty("user.home"), ".credentials/calendar.json"), JSON_FACTORY);
    // set up authorization code flow
    GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
        HTTP_TRANSPORT, JSON_FACTORY, clientSecrets,
        Collections.singleton(CalendarScopes.CALENDAR)).setCredentialStore(credentialStore).build();
    // authorize
    Credential acia = new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
    System.err.println(acia.getAccessToken());
    return acia;
  }
  public String doIt(){
      String toReturn="";
      try {
      try {
        HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        // authorization
        Credential credential = authorize();
        // set up global Plus instance
        calendar = new Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential)
                .setApplicationName(APPLICATION_NAME).build();
        event = new Event();
 
        
//        System.out.println(calendar.calendarList().list().isEmpty());
        // run commands
        toReturn = listCalendars();
                insertEvent();

//        listActivities();
        // success!
      } catch (IOException e) {
        System.err.println(e.getMessage());
      }
    } catch (Throwable t) {
      t.printStackTrace();
    }
      return toReturn;
  }

  /**Insert event **/
  private static void insertEvent() throws IOException {
        event.setSummary("annif");
        Date startDate= new Date();
        Date endDate = new Date(startDate.getTime()+3600000);
        DateTime start = new DateTime(startDate, TimeZone.getTimeZone("UTC"));
        DateTime end = new DateTime(endDate, TimeZone.getTimeZone("UTC"));
        event.setStart(new EventDateTime().setDateTime(start));
        event.setEnd(new EventDateTime().setDateTime(end));
        Event createdEvent = calendar.events().insert("gn0o8obaggl72bm186pnksh0is@group.calendar.google.com", event).execute();
        System.out.println(createdEvent.getSummary());
  }
  /**List calendars. */
  private static String listCalendars() throws IOException{
      String toReturn="";
      //https://www.googleapis.com/calendar/v3/users/me/calendarList
      View.header1("Listing My Calendars");
      Calendar.CalendarList.List listCalendars = calendar.calendarList().list();
      listCalendars.setFields("items(id,summary)");
      com.google.api.services.calendar.model.CalendarList cList = listCalendars.execute();
      List<CalendarListEntry> listOfEntry = cList.getItems();
      for(CalendarListEntry cle : listOfEntry){
          System.out.println(cle.getSummary());
          toReturn+=" "+cle.getSummary();
          String pageToken = null;
          do{
            Events events = calendar.events().list(cle.getId()).setPageToken(pageToken).execute();
            List<Event> listEvent = events.getItems();
            for(Event e : listEvent){
                System.out.println(e.getSummary()+" "+e.getId());
            }
            pageToken = events.getNextPageToken();
          }while(pageToken !=null);
//          CalendarListEntry cld = (CalendarListEntry) cal;
//          System.out.println(cld.getSummary());
      }
      return toReturn;
  }
}