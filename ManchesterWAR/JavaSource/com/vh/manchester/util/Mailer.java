package com.vh.manchester.util;

import java.util.Date;
import java.util.HashSet;
import java.util.Properties;


import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.vh.manchester.ejb.Task;
import com.vh.manchester.ejb.User;

public class Mailer {

	private static Logger log = Logger.getLogger(Mailer.class);
	private static final boolean IS_DEBUG = log.isDebugEnabled();

	private static final String STYLE = "html, body {height:100%; width:100%}" + 
                                      "body {font:.8em verdana,arial,sans-serif; background-color:#ccc; color:#333;}\n" +
			                                "h1 {font-size:1em; background-color:#093; color:#ccc; padding:.5em .8em; margin:0;}\n" +
			                                "div#container {width:80%; border:solid 2px #093; text-align:left; margin:auto; background-color:white; color:#333;}\n" +
			                                ".pseudoTable {font-size:10px; margin:4px 1em; padding:0; border:0;}\n" +
			                                ".pseudoTable label {font-weight:bold; float:left; width:20%;}\n" +
			                                ".red {font-weight:bold; color:#cc0000;}" +
                                      ".yellow {font-weight:bold; color:#ffcc00;}" +
                                      ".green {font-weight:bold; color:#093;}";

  /**
   * Creates the subject line of the email.
   * If udu==null, it will create a New Task subject line.
   * Otherwise, it will craete a Task Updated subject line.
   * 
   * @param task
   * @param flag
   * @param request
   * @param locale
   * @return
   * @throws Exception
   */
  private static String createSubject(Task task, HttpServletRequest request, String locale, User udu) throws Exception {
    StringBuffer buf = new StringBuffer();
    
    buf.append("[hub] ");
    if(udu==null) buf.append(MessageUtils.getMessage(Constants_Scope.LABELS_KEY, "com.vh.email.Newtask",  request, locale));
    else buf.append(MessageUtils.getMessage(Constants_Scope.LABELS_KEY, "com.vh.email.Taskupdated", request, locale));
    buf.append(" #");
    buf.append(task.getId());
    buf.append(": ");
    buf.append(task.getTitle());
    
    return buf.toString();
  }


  /**
   * Creates the body of the email.
   * If udu==null, it will create a New Task email body.
   * Otherwise it will create a Task Updated email body.
   * 
   * @param task
   * @param flag
   * @param request
   * @param locale
   * @return
   * @throws Exception
   */
  private static String createBody(Task task, HttpServletRequest request, String locale, User udu) throws Exception {    
    StringBuffer body = new StringBuffer();
    Integer taskStatus = task.getTaskStatus().getId();
    
    body.append("<html><head><title></title><style>");
    body.append(STYLE);
    body.append("</style></head>\n\n");
    body.append("<body><div style=\"text-align:center;\"><div id=\"container\">");
    body.append("<h1>Tech hub notification system</h1><br/>\n\n");
    
    //Id
    body.append("<fieldset class=\"pseudoTable\"><label>");
    body.append(MessageUtils.getMessage(Constants_Scope.LABELS_KEY, "com.vh.label.Id", request, locale));
    body.append(":</label>");
    body.append(task.getId());
    body.append("</fieldset>\n");
    //Title
    body.append("<fieldset class=\"pseudoTable\"><label>");
    body.append(MessageUtils.getMessage(Constants_Scope.LABELS_KEY, "com.vh.label.Title", request, locale));
    body.append(":</label>");
    body.append(task.getTitle());
    body.append("</fieldset>\n");
    //Summary
    body.append("<fieldset class=\"pseudoTable\"><label>");
    body.append(MessageUtils.getMessage(Constants_Scope.LABELS_KEY, "com.vh.label.Summary", request, locale));
    body.append(":</label><br/>");
    body.append(task.getSummary());
    body.append("</fieldset><br/>\n\n");
    //Comment
    if(udu!=null) {
      body.append("<fieldset class=\"pseudoTable\"><label>");
      body.append(MessageUtils.getMessage(Constants_Scope.LABELS_KEY, "com.vh.label.Comment", request, locale));
      body.append(":</label>");
      body.append(task.getComment());
      body.append("</fieldset>\n");
    }
    //Project
    body.append("<fieldset class=\"pseudoTable\"><label>");
    body.append(MessageUtils.getMessage(Constants_Scope.LABELS_KEY, "com.vh.label.Project", request, locale));
    body.append(":</label>");
    body.append(task.getProject().getDesc());
    body.append("</fieldset>\n");
    //Status
    body.append("<fieldset class=\"pseudoTable\"><label>");
    body.append(MessageUtils.getMessage(Constants_Scope.LABELS_KEY, "com.vh.label.Status", request, locale));
    if(taskStatus.equals(Constants.TASKSTATUS_OPEN)) body.append(":</label><span class=\"red\">");
    else if(taskStatus.equals(Constants.TASKSTATUS_RSLVD)) body.append(":</label><span class=\"yellow\">");
    else body.append(":</label><span class=\"green\">");
    body.append(task.getTaskStatus().getDesc());
    body.append("</span></fieldset>\n");
    //Deadline
    body.append("<fieldset class=\"pseudoTable\"><label>");
    body.append(MessageUtils.getMessage(Constants_Scope.LABELS_KEY, "com.vh.label.PlannedDate", request, locale));
    body.append(":</label>");
    body.append(task.getPlannedDate());
    body.append("</fieldset>\n");
    //Assigned to
    body.append("<fieldset class=\"pseudoTable\"><label>");
    body.append(MessageUtils.getMessage(Constants_Scope.LABELS_KEY, "com.vh.label.Assignedto", request, locale));
    body.append(":</label>");
    body.append(task.getWho().getUsername());
    body.append("</fieldset>\n");
    //Reported by
    body.append("<fieldset class=\"pseudoTable\"><label>");
    body.append(MessageUtils.getMessage(Constants_Scope.LABELS_KEY, "com.vh.label.Reportedby", request, locale));
    body.append(":</label>");
    body.append(task.getCreatedBy().getUsername());
    body.append("</fieldset>\n");
    //Last updated by, last updated on
    if(udu!=null) {
      body.append("<fieldset class=\"pseudoTable\"><label>");
      body.append(MessageUtils.getMessage(Constants_Scope.LABELS_KEY, "com.vh.label.Lastupdatedby", request, locale));
      body.append(":</label>");
      body.append(udu.getUsername());
      body.append("</fieldset>\n");
      body.append("<fieldset class=\"pseudoTable\"><label>");
      body.append(MessageUtils.getMessage(Constants_Scope.LABELS_KEY, "com.vh.label.Lastupdatedon", request, locale));
      body.append(":</label>\n");
      body.append(new Date());
      body.append("</fieldset>");
    }
    body.append("</div></div></body></html>");
    
    return body.toString();
  }

  
  /**
   * Send html email utility method.
   * 
   * @param fromName
   * @param fromAddr
   * @param toName
   * @param toAddr
   * @param subject
   * @param body
   */
  private static void sendHtmlMessage(String fromName, String fromAddr, String toName, String toAddr, String subject, String body) {
    Properties props = new Properties();                      // Create properties for the Session
    props.put("mail.smtp.host", Constants.SMTPServer);        // If using static Transport.send(), need to specify the mail server here
    //props.put("mail.debug", "true");                          // To see what is going on behind the scene

    try {
      Session session = Session.getInstance(props);           // Get a session
      Transport bus = session.getTransport("smtp");           // Get a Transport object to send e-mail

      bus.connect();                                          // Connect only once here: Transport.send() disconnects after each send
      //bus.connect(host, "username", "password");            // Usually, no username and password is required for SMTP, if so, use this line

      Message msg = new MimeMessage(session);                 // Instantiate a message
      msg.setFrom(new InternetAddress(fromAddr, fromName));   // Set message attributes...
      InternetAddress[] address = {new InternetAddress(toAddr, toName)};
      msg.setRecipients(Message.RecipientType.TO, address);
      msg.setSubject(subject);
      msg.setSentDate(new java.util.Date());
      msg.setContent(body, "text/html");

      bus.sendMessage(msg, address);                          // Send message, close buffer
      bus.close();
    } catch(Exception e) {
      log.error("Could not send email: " + e.toString());
      e.printStackTrace();
    }
  }


  /**
   * This is the only public method in the class.
   * It sends the New/Update Task emails to (1) assignee, (2) task creator and
   * (3) user doing the update (applies only for Update Task emails).
   * 
   * @param task
   * @param request
   * @param udu
   */
	public static void sendTaskEmail(Task task, HttpServletRequest request, User udu) {
		try {
			String fromName   = task.getCreatedBy().getUsername();
			String fromAddr   = task.getCreatedBy().getEmail();
			String fromLocale = task.getCreatedBy().getLocale().getLoc();
			String toName     = task.getWho().getUsername();
			String toAddr     = task.getWho().getEmail();
			String toLocale   = task.getWho().getLocale().getLoc();
      String subject, body;

      HashSet set = new HashSet();                              //contains email addresses already emailed
      subject = createSubject(task, request, toLocale, udu);    //send email to task assignee...
      body    = createBody(task, request, toLocale, udu);
      sendHtmlMessage(fromName, fromAddr, toName, toAddr, subject, body);
      set.add(toAddr);
      if(!set.contains(fromAddr)) {                             //send email to task creator...
        subject = createSubject(task, request, fromLocale, udu);
        body    = createBody(task, request, fromLocale, udu);
        sendHtmlMessage(fromName, fromAddr, fromName, fromAddr, subject, body);            
      }
      set.add(fromAddr);
      if((udu!=null) && !(set.contains(udu.getEmail()))) {      //send email to user doing the update...
        String uduName   = udu.getUsername();
        String uduAddr   = udu.getEmail();
        String uduLocale = udu.getLocale().getLoc();
        subject = createSubject(task, request, uduLocale, udu);
        body    = createBody(task, request, uduLocale, udu);
        sendHtmlMessage(fromName, fromAddr, uduName, uduAddr, subject, body);        
      }
		} catch(Exception e) {
			log.error("Send email error: " + e.getMessage());
			e.printStackTrace();
		}
	}

}