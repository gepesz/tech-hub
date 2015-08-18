package com.vh.manchester.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Properties;

import javax.ejb.FinderException;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.vh.manchester.bean.TaskBean;
import com.vh.manchester.ejb.Access;
import com.vh.manchester.ejb.AccessHome;
import com.vh.manchester.ejb.Difficulty;
import com.vh.manchester.ejb.DifficultyHome;
import com.vh.manchester.ejb.Locale;
import com.vh.manchester.ejb.LocaleHome;
import com.vh.manchester.ejb.Priority;
import com.vh.manchester.ejb.PriorityHome;
import com.vh.manchester.ejb.Project;
import com.vh.manchester.ejb.ProjectAccess;
import com.vh.manchester.ejb.ProjectAccessHome;
import com.vh.manchester.ejb.ProjectAccessKey;
import com.vh.manchester.ejb.ProjectHome;
import com.vh.manchester.ejb.Task;
import com.vh.manchester.ejb.TaskHome;
import com.vh.manchester.ejb.TaskStatus;
import com.vh.manchester.ejb.TaskStatusHome;
import com.vh.manchester.ejb.TaskType;
import com.vh.manchester.ejb.TaskTypeHome;
import com.vh.manchester.ejb.User;
import com.vh.manchester.ejb.UserHome;
import com.vh.manchester.ejb.UserType;
import com.vh.manchester.ejb.UserTypeHome;
import com.vh.manchester.service.exception.ServiceException;
import com.vh.manchester.util.ActionUtils;
import com.vh.manchester.util.ConnectionFactory;
import com.vh.manchester.util.Constants;
import com.vh.manchester.util.Constants_Scope;
import com.vh.manchester.util.EJBHomeCache;
import com.vh.manchester.util.MessageUtils;

/**
 * The VH Corporation
 *
 * Copyright (c) 2003 The VH Corporation.  All rights reserved.  
 * Copying or reproduction without prior written approval is prohibited.
 * 
 * @author peter.szocs
 * Nov 2, 2003
 */
public class ManchesterServiceImpl_MySQL implements ManchesterService, Constants {

	private static Logger log=Logger.getLogger(ManchesterServiceImpl_MySQL.class);
	private static final boolean IS_DEBUG = log.isDebugEnabled();

	private final String STYLE = "body {font:.8em verdana,arial,sans-serif; background-color:#ccc; color:#333;}\n" +
										"h1 {font-size:1em; background-color:#093; color:#ccc; padding:.5em .8em; margin:0;}\n" +
										"div#container {width:80%; border:solid 2px #093; text-align:left; margin:auto; background-color:white; color:#333;}\n" +
										".pseudoTable {font-size:10px; margin:4px 1em; padding:0; border:0;}\n" +
										".pseudoTable label {font-weight:bold; float:left; width:15%;}\n" +
										".red {font-weight:bold; color:#cc0000;}";
	// homes:
	private AccessHome			mAccessHome			= null;
	private DifficultyHome		mDifficultyHome		= null;
  	private LocaleHome			mLocaleHome			= null;
  	private PriorityHome		mPriorityHome		= null;
	private ProjectHome			mProjectHome		= null;
	private ProjectAccessHome	mProjectAccessHome	= null;
  	private TaskHome			mTaskHome			= null;
  	private TaskStatusHome		mTaskStatusHome		= null;
  	private TaskTypeHome		mTaskTypeHome		= null;
  	private UserHome			mUserHome			= null;
  	private UserTypeHome		mUserTypeHome		= null;



///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Major functionality:


	/**
	 * @see com.vh.manchester.service.manchester.ManchesterService#authenticate(String, String)
	 */
	public User authenticate(String username, String password) throws ServiceException {
		User theUser = null;
		//log.debug("password="+password);
		try {
			User temp = getUserHome().findByUsername(username);
			if(password.equals(temp.getPassword())) {
				theUser = temp;
			}
		} catch(FinderException fe) {
			if(IS_DEBUG) log.debug("User with username="+username+" could not be found");
		} catch(Exception e) {
			log.error("Authentication error: "+e.toString());
			throw new ServiceException("Authentication error: "+e.getMessage());
		}
		return theUser;
	}



	/**
	 * @see com.vh.manchester.service.manchester.ManchesterService#checkUsername(String)
	 */
	public boolean checkUsername(String username, Integer userId) throws ServiceException {
		boolean temp = true;
		try {
			Iterator it = findAllUser().iterator();
			while(it.hasNext()) {
				User u = (User)it.next();
				if(!u.getId().equals(userId)) {
					if(u.getUsername().equals(username)) temp=false;
				}
			}
		} catch(Exception e) {
			log.error("checkUsername error: "+e.toString());
			throw new ServiceException("checkUsername error: "+e.getMessage());
		}
		return temp;		
	}



	/**
	 * @see com.vh.manchester.service.manchester.ManchesterService#updateLoginInfo(User)
	 */
	public boolean updateLoginInfo(User theUser) throws ServiceException {
		try {
			theUser.setLastLogin(ActionUtils.rightNow());
			theUser.setNofLogins(theUser.getNofLogins()+1);
		} catch(Exception e) {
			log.error("updateLoginInfo error: "+e.toString());
			throw new ServiceException("updateLoginInfo error: "+e.getMessage());
		}			
		return true;
	}



	/**
	 * @see com.vh.manchester.service.manchester.ManchesterService#getUserLocale(User)
	 */
	public java.util.Locale getUserLocale(User theUser) {
		String temp = theUser.getLocale().getLoc();
		String language = temp.substring(0, 2);
		String country  = temp.substring(3);
		java.util.Locale loc = new java.util.Locale(language, country);
		if(IS_DEBUG) log.debug("New locale="+loc.toString());
		return loc;
	}



	/**
	 * @see com.vh.manchester.service.manchester.ManchesterService#findTasks(String, String)
	 */
  	public ArrayList findTasks(Integer projectId, Integer status, Date after, Date before, Integer assignedTo, Integer reportedBy, String searchString) throws Exception {
  		ArrayList al = new ArrayList();
  		
  		// creating SQL query string
  		String mSQLQry = "SELECT a.id, a.tasktype_id, a.title, a.summary, a.plannedDate, b.desc1, c.desc1, d.desc1, e.username, a.progress ";
  		       mSQLQry+= "FROM TASK a, PRIORITY b, TASKSTATUS c, DIFFICULTY d, USER1 e, USER1 e2, PROJECT f ";
  		       mSQLQry+= "WHERE a.priority_id=b.id AND a.taskstatus_id=c.id AND a.difficulty_id=d.id AND a.who_id=e.id AND a.createdby_id=e2.id AND a.project_id=f.id AND f.id="+projectId;
  	
  		if(status!=null && status.intValue()!=-1) mSQLQry+=" AND a.taskstatus_id="+status;	
  		if(assignedTo!=null && assignedTo.intValue()!=-1) mSQLQry+=" AND a.who_id="+assignedTo;	
		if(reportedBy!=null && reportedBy.intValue()!=-1) mSQLQry+=" AND a.createdby_id="+reportedBy;	
		if(after!=null) mSQLQry+=" AND a.createdate>=? AND a.createdate<=?";
		if(searchString!=null && searchString.length()>0) {
			searchString=searchString.toLowerCase();
			mSQLQry+=" AND (LOWER(a.title) like '%"+searchString+"%' OR LOWER(a.summary) like '%"+searchString+"%')";	
		}
  		//if(IS_DEBUG) log.debug("mSQLQry="+mSQLQry);
  	  		  
    	// executing query
		Connection mCon = ConnectionFactory.getConnection();
  		PreparedStatement mPreparedStatement = ConnectionFactory.getStatement(mCon, mSQLQry);
  		ResultSet rs = null;
		try {
			if(after!=null) {
				mPreparedStatement.setDate(1, after);
				mPreparedStatement.setDate(2, before);
			}
	  		rs = mPreparedStatement.executeQuery();
			  
	  		// populating TaskBean
	  		while(rs.next()) {
    			TaskBean tbean = new TaskBean();
    			tbean.setId(new Integer(rs.getInt(1)));
    			tbean.setType(new Integer(rs.getInt(2)));
    			tbean.setTitle(rs.getString(3));
    			tbean.setSummary(rs.getString(4));
    			tbean.setPlannedDate(rs.getDate(5));
    			tbean.setPriority(rs.getString(6));
    			tbean.setStatus(rs.getString(7));
    			tbean.setDifficulty(rs.getString(8));
    			tbean.setWho(rs.getString(9));
    			tbean.setProgress(rs.getInt(10));

				al.add(tbean);
	  		}
		} catch(Exception e) {
      		log.error("Error while retrieving tasks: "+e.toString());
      		throw new ServiceException("Error while retrieving tasks: "+e.getMessage());
		} finally {
	  		ConnectionFactory.close(rs);
	  		ConnectionFactory.close(mPreparedStatement);
	  		ConnectionFactory.close(mCon);	  	
		}	   		  		  	
  		return al;
  	}



///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Difficulty:

    /**
	 * @see com.vh.manchester.service.manchester.ManchesterService#createAccess(String)
	 */
	public Access createAccess(java.lang.String desc) throws ServiceException {
		Access ejb = null;
		try {
			ejb =  getAccessHome().create(null, desc);
		} catch(Exception e) {
			log.error("Access could not be created: "+e.toString());
			throw new ServiceException("Access could not be created: "+e.getMessage());
		}
		return ejb;		
	}

	/**
	 * @see com.vh.manchester.service.manchester.ManchesterService#findAccessById(Integer)
	 */
	public Access findAccessById(java.lang.Integer id) throws ServiceException {
		Access ejb = null;
		try {
			ejb =  getAccessHome().findByPrimaryKey(id);
		} catch(FinderException fe) {
			if(IS_DEBUG) log.debug("Access with id=" + id + " could not be found");
		} catch(Exception e) {
			log.error("findAccessById with id=" + id + " error: "+e.toString());
			throw new ServiceException("findAccessById with id=" + id + " error: "+e.getMessage());
		}
		return ejb;		
	}

	/**
	 * @see com.vh.manchester.service.manchester.ManchesterService#findAllAccess()
	 */
	public Collection findAllAccess() throws ServiceException {
		Collection list = null;
		try {
			list =  getAccessHome().findAll();
		} catch(FinderException fe) {
			if(IS_DEBUG) log.debug("All Access collection could not be found");
		} catch(Exception e) {
			log.error("All Access collection error: "+e.toString());
			throw new ServiceException("All Access collection error: "+e.getMessage());
		}
		return list;		
	}


///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Difficulty:


	/**
	 * @see com.vh.manchester.service.manchester.ManchesterService#createDifficulty(String)
	 */
	public Difficulty createDifficulty(String desc) throws ServiceException {
    	Difficulty ejb = null;
    	try {
      		ejb =  getDifficultyHome().create(null, desc);
    	} catch(Exception e) {
      		log.error("Difficulty could not be created: "+e.toString());
      		throw new ServiceException("Difficulty could not be created: "+e.getMessage());
    	}
    	return ejb;
	}

	/**
	 * @see com.vh.manchester.service.manchester.ManchesterService#findDifficultyById(Integer)
	 */
	public Difficulty findDifficultyById(Integer id) throws ServiceException {
    	Difficulty ejb = null;
    	try {
      		ejb =  getDifficultyHome().findByPrimaryKey(id);
    	} catch(FinderException fe) {
    		if(IS_DEBUG) log.debug("Difficulty with id=" + id + " could not be found");
    	} catch(Exception e) {
      		log.error("findDifficultyById with id=" + id + " error: "+e.toString());
      		throw new ServiceException("findDifficultyById with id=" + id + " error: "+e.getMessage());
    	}
    	return ejb;
	}

	/**
	 * @see com.vh.manchester.service.manchester.ManchesterService#findAllDifficulty()
	 */
	public Collection findAllDifficulty() throws ServiceException {
    	Collection list = null;
    	try {
      		list =  getDifficultyHome().findAll();
    	} catch(FinderException fe) {
    		if(IS_DEBUG) log.debug("All Difficulty collection could not be found");
    	} catch(Exception e) {
      		log.error("All Difficulty collection error: "+e.toString());
      		throw new ServiceException("All Difficulty collection error: "+e.getMessage());
    	}
    	return list;
	}


///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Locale:


	/**
	 * @see com.vh.manchester.service.manchester.ManchesterService#createLocale(String)
	 */
	public Locale createLocale(String desc, String loc) throws ServiceException {
    	Locale ejb = null;
    	try {
      		ejb =  getLocaleHome().create(null, desc, loc);
    	} catch(Exception e) {
      		log.error("Locale could not be created: "+e.toString());
      		throw new ServiceException("Locale could not be created: "+e.getMessage());
    	}
    	return ejb;
	}

	/**
	 * @see com.vh.manchester.service.manchester.ManchesterService#findLocaleById(Integer)
	 */
	public Locale findLocaleById(Integer id) throws ServiceException {
    	Locale ejb = null;
    	try {
      		ejb =  getLocaleHome().findByPrimaryKey(id);
    	} catch(FinderException fe) {
    		if(IS_DEBUG) log.debug("Locale with id=" + id + " could not be found");
    	} catch(Exception e) {
      		log.error("findLocaleById with id=" + id + " error: "+e.toString());
      		throw new ServiceException("findLocaleById with id=" + id + " error: "+e.getMessage());
    	}
    	return ejb;
	}

	/**
	 * @see com.vh.manchester.service.manchester.ManchesterService#findAllLocale()
	 */
	public Collection findAllLocale() throws ServiceException {
    	Collection list = null;
    	try {
      		list =  getLocaleHome().findAll();
    	} catch(FinderException fe) {
    		if(IS_DEBUG) log.debug("All Locale collection could not be found");
    	} catch(Exception e) {
      		log.error("All Locale collection error: "+e.toString());
      		throw new ServiceException("All Locale collection error: "+e.getMessage());
    	}
    	return list;
	}


///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Priority:


	/**
	 * @see com.vh.manchester.service.manchester.ManchesterService#createPriority(String)
	 */
	public Priority createPriority(String desc) throws ServiceException {
    	Priority ejb = null;
    	try {
      		ejb =  getPriorityHome().create(null, desc);
    	} catch(Exception e) {
      		log.error("Priority could not be created: "+e.toString());
      		throw new ServiceException("Priority could not be created: "+e.getMessage());
    	}
    	return ejb;
	}

	/**
	 * @see com.vh.manchester.service.manchester.ManchesterService#findPriorityById(Integer)
	 */
	public Priority findPriorityById(Integer id) throws ServiceException {
    	Priority ejb = null;
    	try {
      		ejb =  getPriorityHome().findByPrimaryKey(id);
    	} catch(FinderException fe) {
    		if(IS_DEBUG) log.debug("Priority with id=" + id + " could not be found");
    	} catch(Exception e) {
      		log.error("findPriorityById with id=" + id + " error: "+e.toString());
      		throw new ServiceException("findPriorityById with id=" + id + " error: "+e.getMessage());
    	}
    	return ejb;
	}

	/**
	 * @see com.vh.manchester.service.manchester.ManchesterService#findAllPriority()
	 */
	public Collection findAllPriority() throws ServiceException {
    	Collection list = null;
    	try {
      		list =  getPriorityHome().findAll();
    	} catch(FinderException fe) {
    		if(IS_DEBUG) log.debug("All Priority collection could not be found");
    	} catch(Exception e) {
      		log.error("All Priority collection error: "+e.toString());
      		throw new ServiceException("All Priority collection error: "+e.getMessage());
    	}
    	return list;
	}


///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Project:


   /**
	* @see com.vh.manchester.service.manchester.ManchesterService#createProject()
	*/
	public Project createProject(java.lang.String desc) throws ServiceException {
		Project ejb = null;
		try {
			ejb =  getProjectHome().create(null, desc);
		} catch(Exception e) {
			log.error("Project could not be created: "+e.toString());
			throw new ServiceException("Project could not be created: "+e.getMessage());
		}
		return ejb;		
	}

	/**
	 * @see com.vh.manchester.service.manchester.ManchesterService#deleteProjects(Integer[], String)
	 */
	public boolean deleteProjects(Integer[] projIds, String userName) throws ServiceException {
		boolean deleted = false;
		try {
			
			/*
			 * 1) all projecAccess entries belonging to all projIds will be deleted
			 * 2) all tasks belonging to all projIds will be deleted
			 * 3) all users lastProject field will be updated IFF they point to any of the projIds
			 * 4) delete the projects themselves 
			 */

			//all lastProjectIds will point to this default project IFF they pointed to any of the projIds
			Project defaultProject = findProjectById(Constants.DEFAULT_PROJECT_ID);

			for(int i=0;i<projIds.length;i++) {
				Integer projId = projIds[i];
				int pacCounter=0;
				int taskCounter=0; 
								
				//deleting all project access-es for this project
				Collection projectAccessColl = findProjectAccessByProject(projId);
				Iterator it = projectAccessColl.iterator();
				while(it.hasNext()) {
					ProjectAccess pac = (ProjectAccess) it.next();
					pac.remove();
					pacCounter++;
				}

				//deleting all tasks in this project
				Collection taskColl = getTaskHome().findByProject(projId);
				it = taskColl.iterator();
				while(it.hasNext()) {
					Task task = (Task) it.next();
					task.remove();
					taskCounter++;
				}
				
				//updating users whose lastProjectId points to this project
				Collection allUsers = findAllUser();
				it = allUsers.iterator();
				while(it.hasNext()) {
					User u = (User) it.next();
					if(projId.equals(u.getLastProject().getId())) u.setLastProject(defaultProject);
				}
				
				//finally, delete the project itself
				Project p = findProjectById(projId);
				p.remove();
				log.debug("Successfully deleted project "+projId+" by user '"+userName+"': also deleted "+pacCounter+" project access entries and "+taskCounter+" tasks");
			}
			deleted=true;
		} catch(Exception e) {
			log.error("Projects could not be deleted: "+e.toString());
			throw new ServiceException("Projects could not be deleted: "+e.getMessage());
		}
		return deleted;
	}

	/**
	 * @see com.vh.manchester.service.manchester.ManchesterService#findProjectById()
	 */
	public Project findProjectById(java.lang.Integer id) throws ServiceException {
		Project ejb = null;
		try {
			ejb =  getProjectHome().findByPrimaryKey(id);
		} catch(FinderException fe) {
			if(IS_DEBUG) log.debug("Project with id=" + id + " could not be found");
		} catch(Exception e) {
			log.error("findProjectById with id=" + id + " error: "+e.toString());
			throw new ServiceException("findProjectById with id=" + id + " error: "+e.getMessage());
		}
		return ejb;		
	}

	/**
	 * @see com.vh.manchester.service.manchester.ManchesterService#findAllProject()
	 */
	public Collection findAllProject() throws ServiceException {
		Collection list = null;
		try {
			list =  getProjectHome().findAll();
		} catch(FinderException fe) {
			if(IS_DEBUG) log.debug("All Project collection could not be found");
		} catch(Exception e) {
			log.error("All Project collection error: "+e.toString());
			throw new ServiceException("All Project collection error: "+e.getMessage());
		}
		return list;		
	}

	/**
	 * @see com.vh.manchester.service.manchester.ManchesterService#getProjectAccess(Integer, Integer)
	 */	
	public Integer getProjectAccess(Integer projectId, Integer userId) throws ServiceException {
		Integer accessId = null; // null means no access
		try {
			ProjectAccess pa = this.findProjectAccessByProjectUser(projectId, userId);
			if((pa!=null) && (pa.getAccess()!=null)) accessId = pa.getAccess().getId();		
			//if(IS_DEBUG) log.debug("Project, user, access:  "+projectId+", "+userId+", "+accessId);
		} catch(Exception e) {
			log.error("getProjectAccess("+projectId+", "+userId+") error: "+e.toString());
			throw new ServiceException("getProjectAccess("+projectId+", "+userId+") error: "+e.getMessage());
		}		
		return accessId;
	}

	/**
	 * @see com.vh.manchester.service.manchester.ManchesterService#updateProject(Integer, String)
	 */	
	public boolean updateProject(Integer projectId, String desc) throws ServiceException {
		boolean updated = false;
		try {
			Project proj = getProjectHome().findByPrimaryKey(projectId);
			updated = proj.update(desc);
		} catch(Exception e) {
			log.error("Project could not be updated: "+e.toString());
			throw new ServiceException("Project could not be updated: "+e.getMessage());
		}
		return updated;
	}
	


///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// ProjectAccess:


   /**
	* @see com.vh.manchester.service.manchester.ManchesterService#createProjectAccess()
	*/
	public ProjectAccess createProjectAccess(java.lang.Integer projectId, java.lang.Integer userId, java.lang.Integer accessId) throws ServiceException {
		ProjectAccess ejb = null;
		Access anAccess = null;     			
		try {
			if(accessId!=null) anAccess = getAccessHome().findByPrimaryKey(accessId);		
			ejb =  getProjectAccessHome().create(projectId, userId, anAccess);
		} catch(Exception e) {
			log.error("ProjectAccess could not be created: "+e.toString());
			throw new ServiceException("ProjectAccess could not be created: "+e.getMessage());
		}
		return ejb;				
	}

	/**
	 * @see com.vh.manchester.service.manchester.ManchesterService#findProjectAccessById()
	 */
	public ProjectAccess findProjectAccessByProjectUser(java.lang.Integer projectId, java.lang.Integer userId) throws ServiceException {
		ProjectAccessKey pak = new ProjectAccessKey(projectId, userId);
		ProjectAccess ejb = null;
		try {
			ejb =  getProjectAccessHome().findByPrimaryKey(pak);
		} catch(FinderException fe) {
			if(IS_DEBUG) log.debug("ProjectAccess with projectId=" + projectId + ", userId=" + userId + " could not be found");
		} catch(Exception e) {
			log.error("findProjectAccessById with projectId=" + projectId + ", userId=" + userId + " error: "+e.toString());
			throw new ServiceException("findProjectAccessById with projectId=" + projectId + ", userId=" + userId + " error: "+e.getMessage());
		}
		return ejb;				
	}

	/**
	 * @see com.vh.manchester.service.manchester.ManchesterService#findAllProjectAccess()
	 */
	public Collection findAllProjectAccess() throws ServiceException {
		Collection list = null;
		try {
			list =  getProjectAccessHome().findAll();
		} catch(FinderException fe) {
			if(IS_DEBUG) log.debug("All ProjectAccess collection could not be found");
		} catch(Exception e) {
			log.error("All ProjectAccess collection error: "+e.toString());
			throw new ServiceException("All ProjectAccess collection error: "+e.getMessage());
		}
		return list;				
	}

	/**
	 * @see com.vh.manchester.service.manchester.ManchesterService#findProjectAccessByProject()
	 */
	public Collection findProjectAccessByProject(java.lang.Integer projectId) throws ServiceException {
		Collection list = null;
		try {
			list =  getProjectAccessHome().findByProject(projectId);
		} catch(FinderException fe) {
			if(IS_DEBUG) log.debug("ProjectAccess collection for projectId="+projectId+" could not be found");
		} catch(Exception e) {
			log.error("ProjectAccess collection for projectId="+projectId+" error: "+e.toString());
			throw new ServiceException("ProjectAccess collection for projectId="+projectId+" error: "+e.getMessage());
		}
		return list;				
	}

	/**
	 * @see com.vh.manchester.service.manchester.ManchesterService#findProjectAccessByUser()
	 */
	public Collection findProjectAccessByUser(java.lang.Integer userId) throws ServiceException {
		Collection list = null;
		try {
			list =  getProjectAccessHome().findByUser(userId);
		} catch(FinderException fe) {
			if(IS_DEBUG) log.debug("ProjectAccess collection for userId="+userId+" could not be found");
		} catch(Exception e) {
			log.error("ProjectAccess collection for userId="+userId+" error: "+e.toString());
			throw new ServiceException("ProjectAccess collection for userId="+userId+" error: "+e.getMessage());
		}
		return list;						
	}

	/**
	 * @see com.vh.manchester.service.manchester.ManchesterService#updateProjectAccess(Integer)
	 */
	public boolean updateProjectAccess(Integer projectId, Integer userId, Integer accessId) throws ServiceException {
		boolean updated = false;
		Access anAccess = null;
		ProjectAccess pa = null;
		ProjectAccessKey pak = new ProjectAccessKey(projectId, userId);
		try {
			Access access = getAccessHome().findByPrimaryKey(accessId);
			if((projectId!=null) && (userId!=null)) pa = getProjectAccessHome().findByPrimaryKey(pak);
			if(pa!=null) updated = pa.update(access);
		} catch(FinderException fe) {
			if(IS_DEBUG) log.debug("ProjectAccess with projectId="+projectId+", userId="+userId+" could not be found");
		} catch(Exception e) {
			log.error("ProjectAccess could not be updated: "+e.toString());
			throw new ServiceException("ProjectAccess could not be updated: "+e.getMessage());
		}
		return updated;
	}


///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Task:


	/**
	 * @see com.vh.manchester.service.manchester.ManchesterService#createTask(String, String, Date, Date, String, int, Difficulty, TaskType, TaskStatus, Priority, User, User)
	 */
	public Task createTask(String title, String summary, Date plannedDate, Date createDate, String comment, Integer progress, Integer difficultyId, Integer taskTypeId, Integer taskStatusId, Integer priorityId, Integer assignedToId, Integer createdById, Integer projectId) throws ServiceException {
    	Task ejb = null;
    	Difficulty 	aDifficulty 	= null;
    	TaskType 	aTaskType 		= null; 
    	TaskStatus 	aTaskStatus 	= null; 
    	Priority 	aPriority 		= null; 
    	User 		assignedTo 		= null; 
    	User 		createdBy 		= null;
    	Project     project	        = null;
    	try {
      		if(difficultyId!=null) aDifficulty = getDifficultyHome().findByPrimaryKey(difficultyId);
      		if(taskTypeId!=null) aTaskType = getTaskTypeHome().findByPrimaryKey(taskTypeId);
      		if(taskStatusId!=null) aTaskStatus = getTaskStatusHome().findByPrimaryKey(taskStatusId);
      		if(priorityId!=null) aPriority = getPriorityHome().findByPrimaryKey(priorityId);
      		if(assignedToId!=null) assignedTo = getUserHome().findByPrimaryKey(assignedToId);
      		if(createdById!=null) createdBy = getUserHome().findByPrimaryKey(createdById);
			if(projectId!=null) project = getProjectHome().findByPrimaryKey(projectId);
      		
      		ejb = getTaskHome().create(1, title, summary, plannedDate, createDate, comment, progress, aDifficulty, aTaskType, aTaskStatus, aPriority, assignedTo, createdBy, project);
    	} catch(Exception e) {
      		log.error("Task could not be created: "+e.toString());
      		throw new ServiceException("Task could not be created: "+e.getMessage());
    	}
    	return ejb;
	}

	/**
	 * @see com.vh.manchester.service.manchester.ManchesterService#deleteTasks(Integer[], String)
	 */
	public boolean deleteTasks(Integer[] taskIds, String userName) throws ServiceException {
		boolean deleted = false;
		try {
			for(int i=0;i<taskIds.length;i++) {
				Task t = findTaskById(taskIds[i]);
				t.remove();
				log.debug("Successfully deleted task "+taskIds[i]+" by user '"+userName+"'");
			}
			deleted=true;
		} catch(Exception e) {
			log.error("Tasks could not be deleted: "+e.toString());
			throw new ServiceException("Tasks could not be deleted: "+e.getMessage());
		}
		return deleted;
	}

	/**
	 * @see com.vh.manchester.service.manchester.ManchesterService#findTaskById(Integer)
	 */
	public Task findTaskById(Integer id) throws ServiceException {
    	Task ejb = null;
    	try {
      		ejb =  getTaskHome().findByPrimaryKey(id);
    	} catch(FinderException fe) {
    		if(IS_DEBUG) log.debug("Task with id=" + id + " could not be found");
    	} catch(Exception e) {
      		log.error("findTaskById with id=" + id + " error: "+e.toString());
      		throw new ServiceException("findTaskById with id=" + id + " error: "+e.getMessage());
    	}
    	return ejb;
	}

	/**
	 * @see com.vh.manchester.service.manchester.ManchesterService#findAllTask()
	 */
	public Collection findAllTask() throws ServiceException {
    	Collection list = null;
    	try {
      		list =  getTaskHome().findAll();
    	} catch(FinderException fe) {
    		if(IS_DEBUG) log.debug("All Task collection could not be found");
    	} catch(Exception e) {
      		log.error("All Task collection error: "+e.toString());
      		throw new ServiceException("All Task collection error: "+e.getMessage());
    	}
    	return list;
	}

	/**
	 * @see com.vh.manchester.service.manchester.ManchesterService#findTaskByAssignedTo(Integer)
	 */
	public Collection findTaskByAssignedTo(java.lang.Integer userId) throws ServiceException {
    	Collection list = null;
    	try {
      		list =  getTaskHome().findByWho(userId);
    	} catch(FinderException fe) {
    		if(IS_DEBUG) log.debug("Task collection assigned to userId="+userId+" could not be found");
    	} catch(Exception e) {
      		log.error("Task collection assigned to userId="+userId+" error: "+e.toString());
      		throw new ServiceException("Task collection assigned to userId="+userId+" error: "+e.getMessage());
    	}
    	return list;		
	}

	/**
	 * @see com.vh.manchester.service.manchester.ManchesterService#findTaskByAssignedToOrCreatedBy(Integer)
	 */
	public Collection findTaskByAssignedToOrCreatedBy(Integer userId) throws ServiceException {
		Collection list = null;
		try {
			list = getTaskHome().findByWhoOrCreatedBy(userId);
		} catch(FinderException fe) {
			if(IS_DEBUG) log.debug("No tasks assigned to OR created by user "+userId);
		} catch(Exception e) {
			log.error("Task collection assigned to/created by userId="+userId+" error: "+e.toString());
			throw new ServiceException("Task collection assigned to/created by userId="+userId+" error: "+e.getMessage());
		}
		return list;		
	}



	/**
	 * @see com.vh.manchester.service.manchester.ManchesterService#updateTask(Integer taskId, String title, String summary, Integer type, Integer priority, Integer difficulty, Integer status, Integer projectId, Integer assignedToId, java.sql.Date plannedDate, String comment, Integer progress)
	 */
	public boolean updateTask(Integer taskId, String title, String summary, Integer type, Integer priority, Integer difficulty, Integer status, Integer projectId, Integer assignedToId, java.sql.Date plannedDate, String comment, Integer progress) throws ServiceException {
    	boolean updated = false;
    	TaskType aTaskType = null;
    	Priority aPriority = null;
    	Difficulty aDifficulty = null;
    	TaskStatus aTaskStatus = null;
    	Project aProject = null;
    	User aUser = null;
    	try {
      		Task task = getTaskHome().findByPrimaryKey(taskId);
      		if(type!=null) aTaskType = getTaskTypeHome().findByPrimaryKey(type);
			if(priority!=null) aPriority = getPriorityHome().findByPrimaryKey(priority);
			if(difficulty!=null) aDifficulty = getDifficultyHome().findByPrimaryKey(difficulty);
      		if(status!=null) aTaskStatus = getTaskStatusHome().findByPrimaryKey(status);
			if(projectId!=null) aProject = getProjectHome().findByPrimaryKey(projectId);
			if(assignedToId!=null) aUser = getUserHome().findByPrimaryKey(assignedToId);  
      		updated = task.update(title, summary, aTaskType, aPriority, aDifficulty, aTaskStatus, aProject, aUser, null, plannedDate, comment, progress);
    	} catch(Exception e) {
      		log.error("Task could not be updated: "+e.toString());
      		throw new ServiceException("Task could not be updated: "+e.getMessage());
    	}
    	return updated;
	}


///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// TaskStatus:


	/**
	 * @see com.vh.manchester.service.manchester.ManchesterService#createTaskStatus(String)
	 */
	public TaskStatus createTaskStatus(String desc) throws ServiceException {
    	TaskStatus ejb = null;
    	try {
      		ejb =  getTaskStatusHome().create(null, desc);
    	} catch(Exception e) {
      		log.error("TaskStatus could not be created: "+e.toString());
      		throw new ServiceException("TaskStatus could not be created: "+e.getMessage());
    	}
    	return ejb;
	}

	/**
	 * @see com.vh.manchester.service.manchester.ManchesterService#findTaskStatusById(Integer)
	 */
	public TaskStatus findTaskStatusById(Integer id) throws ServiceException {
    	TaskStatus ejb = null;
    	try {
      		ejb =  getTaskStatusHome().findByPrimaryKey(id);
    	} catch(FinderException fe) {
    		if(IS_DEBUG) log.debug("TaskStatus with id=" + id + " could not be found");
    	} catch(Exception e) {
      		log.error("findTaskStatusById with id=" + id + " error: "+e.toString());
      		throw new ServiceException("findTaskStatusById with id=" + id + " error: "+e.getMessage());
    	}
    	return ejb;
	}

	/**
	 * @see com.vh.manchester.service.manchester.ManchesterService#findAllTaskStatus()
	 */
	public Collection findAllTaskStatus() throws ServiceException {
    	Collection list = null;
    	try {
      		list =  getTaskStatusHome().findAll();
    	} catch(FinderException fe) {
    		if(IS_DEBUG) log.debug("All TaskStatus collection could not be found");
    	} catch(Exception e) {
      		log.error("All TaskStatus collection error: "+e.toString());
      		throw new ServiceException("All TaskStatus collection error: "+e.getMessage());
    	}
    	return list;
	}


///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// TaskType:


	/**
	 * @see com.vh.manchester.service.manchester.ManchesterService#createTaskType(String)
	 */
	public TaskType createTaskType(String desc) throws ServiceException {
    	TaskType ejb = null;
    	try {
      		ejb =  getTaskTypeHome().create(null, desc);
    	} catch(Exception e) {
      		log.error("TaskType could not be created: "+e.toString());
      		throw new ServiceException("TaskType could not be created: "+e.getMessage());
    	}
    	return ejb;
	}

	/**
	 * @see com.vh.manchester.service.manchester.ManchesterService#findTaskTypeById(Integer)
	 */
	public TaskType findTaskTypeById(Integer id) throws ServiceException {
    	TaskType ejb = null;
    	try {
      		ejb =  getTaskTypeHome().findByPrimaryKey(id);
    	} catch(FinderException fe) {
    		if(IS_DEBUG) log.debug("TaskType with id=" + id + " could not be found");
    	} catch(Exception e) {
      		log.error("findTaskTypeById with id=" + id + " error: "+e.toString());
      		throw new ServiceException("findTaskTypeById with id=" + id + " error: "+e.getMessage());
    	}
    	return ejb;
	}

	/**
	 * @see com.vh.manchester.service.manchester.ManchesterService#findAllTaskType()
	 */
	public Collection findAllTaskType() throws ServiceException {
    	Collection list = null;
    	try {
      		list =  getTaskTypeHome().findAll();
    	} catch(FinderException fe) {
    		if(IS_DEBUG) log.debug("All TaskType collection could not be found");
    	} catch(Exception e) {
      		log.error("All TaskType collection error: "+e.toString());
      		throw new ServiceException("All TaskType collection error: "+e.getMessage());
    	}
    	return list;
	}


///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// User:


	/**
	 * @see com.vh.manchester.service.manchester.ManchesterService#createUser(String, String, String, Timestamp, int, Locale, UserType)
	 */
	public User createUser(String username, String password, String email, Timestamp lastLogin, int nofLogins, Integer localeId, Integer userTypeId, Integer lastProjectId) throws ServiceException {
    	User ejb = null;
    	Locale 		aLocale 	= null;
    	UserType 	aUserType 	= null;  
    	Project     aProject    = null;   	
    	try {
			if(checkUsername(username, null)) {		
      			if(localeId!=null) aLocale = getLocaleHome().findByPrimaryKey(localeId);
      			if(userTypeId!=null) aUserType = getUserTypeHome().findByPrimaryKey(userTypeId);
				if(lastProjectId!=null) aProject = getProjectHome().findByPrimaryKey(lastProjectId);
				
      			ejb =  getUserHome().create(null, username, password, email, lastLogin, nofLogins, aLocale, aUserType, aProject);
			} else {
				log.warn("User with username="+username+" already exists, no new user got created");	
			}
    	} catch(Exception e) {
      		log.error("User could not be created: "+e.toString());
      		throw new ServiceException("User could not be created: "+e.getMessage());
    	}
    	return ejb;
	}

	/**
	 * @see com.vh.manchester.service.manchester.ManchesterService#deleteUsers(Integer[], String)
	 */
	public boolean deleteUsers(Integer[] userIds, String userName) throws ServiceException {
		boolean deleted = false;
		try {
			
			//all tasks assigned to/created by the deleted users will be updated to point to the default user
			User defaultUser = findUserById(Constants.DEFAULT_USER_ID);
			
			for(int i=0;i<userIds.length;i++) {
				Integer userId = userIds[i];
				int pacCounter=0;
				int taskCounter=0; 
				
				//deleting all project access-es by this user
				Collection projectAccessColl = findProjectAccessByUser(userId);
				Iterator it = projectAccessColl.iterator();
				while(it.hasNext()) {
					ProjectAccess pac = (ProjectAccess) it.next();
					pac.remove();
					pacCounter++;
				}
				
				//updating all tasks assigned to/created by this user to the DEFAULT_USER
				Collection taskColl = findTaskByAssignedToOrCreatedBy(userId);
				it = taskColl.iterator();
				while(it.hasNext()) {
					Task task = (Task) it.next();
					task.update(null, null, null, null, null, null, null, defaultUser, defaultUser, null, null, null);
					taskCounter++;
				}
				
				//finally, delete the user itself
				User u = findUserById(userId);
				u.remove();
				log.debug("Successfully deleted user "+userId+" by user '"+userName+"': also deleted "+pacCounter+" project access entries and updated "+taskCounter+" tasks to point to the default user (user with id="+Constants.DEFAULT_USER_ID+")");
			}
			deleted=true;
		} catch(Exception e) {
			log.error("Users could not be deleted: "+e.toString());
			throw new ServiceException("Users could not be deleted: "+e.getMessage());
		}
		return deleted;
	}

	/**
	 * @see com.vh.manchester.service.manchester.ManchesterService#findUserById(Integer)
	 */
	public User findUserById(Integer id) throws ServiceException {
    	User ejb = null;
    	try {
      		ejb =  getUserHome().findByPrimaryKey(id);
    	} catch(FinderException fe) {
    		if(IS_DEBUG) log.debug("User with id=" + id + " could not be found");
    	} catch(Exception e) {
      		log.error("findUserById with id=" + id + " error: "+e.toString());
      		throw new ServiceException("findUserById with id=" + id + " error: "+e.getMessage());
    	}
    	return ejb;
	}

	/**
	 * @see com.vh.manchester.service.manchester.ManchesterService#findUserByUsername(String)
	 */
	public User findUserByUsername(java.lang.String username) throws ServiceException {
		User ejb = null;
		try {
			ejb =  getUserHome().findByUsername(username);
		} catch(FinderException fe) {
			if(IS_DEBUG) log.debug("User with username=" + username + " could not be found");
		} catch(Exception e) {
			log.error("findUserByUsername with username=" + username + " error: "+e.toString());
			throw new ServiceException("findUserByUsername with username=" + username + " error: "+e.getMessage());
		}
		return ejb;		
	}

	/**
	 * @see com.vh.manchester.service.manchester.ManchesterService#findAllUser()
	 */
	public Collection findAllUser() throws ServiceException {
    	Collection list = null;
    	try {
      		list =  getUserHome().findAll();
    	} catch(FinderException fe) {
    		if(IS_DEBUG) log.debug("All User collection could not be found");
    	} catch(Exception e) {
      		log.error("All User collection error: "+e.toString());
      		throw new ServiceException("All User collection error: "+e.getMessage());
    	}
    	return list;
	}

	/**
	 * @see com.vh.manchester.service.manchester.ManchesterService#findUsersByProject()
	 */
	public Collection findUsersByProject(Integer projectId) throws ServiceException {
		Collection list = null;
		try {
			list =  getUserHome().findByProject(projectId);
		} catch(FinderException fe) {
			if(IS_DEBUG) log.debug("Users with projectId="+projectId+" collection could not be found");
		} catch(Exception e) {
			log.error("Users with projectId="+projectId+" collection error: "+e.toString());
			throw new ServiceException("Users with projectId="+projectId+" collection error: "+e.getMessage());
		}
		return list;		
	}

	/**
	 * @see com.vh.manchester.service.manchester.ManchesterService#findDevelopersByProject()
	 */
	public Collection findDevelopersByProject(Integer projectId) throws ServiceException {
		Collection list = null;
		try {
			list =  getUserHome().findDevelopersByProject(projectId);
		} catch(FinderException fe) {
			if(IS_DEBUG) log.debug("Developers with projectId="+projectId+" collection could not be found");
		} catch(Exception e) {
			log.error("Developers with projectId="+projectId+" collection error: "+e.toString());
			throw new ServiceException("Developers with projectId="+projectId+" collection error: "+e.getMessage());
		}
		return list;		
	}

	/**
	 * @see com.vh.manchester.service.manchester.ManchesterService#updateUser(Integer, String, String, String, Integer, Integer)
	 */
	public boolean updateUser(Integer userId, String username, String password, String email, Integer userTypeId, Integer localeId) throws ServiceException {
    	boolean updated = false;
		UserType aUserType = null;
    	Locale aLocale = null;
    	try {
      		User usr = getUserHome().findByPrimaryKey(userId);
      		if(localeId!=null)   aLocale   = getLocaleHome().findByPrimaryKey(localeId);
			if(userTypeId!=null) aUserType = getUserTypeHome().findByPrimaryKey(userTypeId);
      		updated = usr.update(username, password, email, aUserType, aLocale);
    	} catch(Exception e) {
      		log.error("User could not be updated: "+e.toString());
      		throw new ServiceException("User could not be updated: "+e.getMessage());
    	}
    	return updated;
	}

	/**
	 * @see com.vh.manchester.service.manchester.ManchesterService#updateUserLastProject(Integer, Integer)
	 */
	public boolean updateUserLastProject(java.lang.Integer userId, java.lang.Integer projectId) throws ServiceException {
		boolean updated = false;
		Project aProject = null;
		try {
			User usr = getUserHome().findByPrimaryKey(userId);
			if(projectId!=null) aProject = getProjectHome().findByPrimaryKey(projectId);
			updated = usr.updateLastProject(aProject);
		} catch(Exception e) {
			log.error("User could not be updated: "+e.toString());
			throw new ServiceException("User could not be updated: "+e.getMessage());
		}
		return updated;		
	}



///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// UserType:


	/**
	 * @see com.vh.manchester.service.manchester.ManchesterService#createUserType(String)
	 */
	public UserType createUserType(String desc) throws ServiceException {
    	UserType ejb = null;
    	try {
      		ejb =  getUserTypeHome().create(null, desc);
    	} catch(Exception e) {
      		log.error("UserType could not be created: "+e.toString());
      		throw new ServiceException("UserType could not be created: "+e.getMessage());
    	}
    	return ejb;
	}

	/**
	 * @see com.vh.manchester.service.manchester.ManchesterService#findUserTypeById(Integer)
	 */
	public UserType findUserTypeById(Integer id) throws ServiceException {
    	UserType ejb = null;
    	try {
      		ejb =  getUserTypeHome().findByPrimaryKey(id);
    	} catch(FinderException fe) {
    		if(IS_DEBUG) log.debug("UserType with id=" + id + " could not be found");
    	} catch(Exception e) {
      		log.error("findUserTypeById with id=" + id + " error: "+e.toString());
      		throw new ServiceException("findUserTypeById with id=" + id + " error: "+e.getMessage());
    	}
    	return ejb;
	}

	/**
	 * @see com.vh.manchester.service.manchester.ManchesterService#findAllUserType()
	 */
	public Collection findAllUserType() throws ServiceException {
    	Collection list = null;
    	try {
      		list =  getUserTypeHome().findAll();
    	} catch(FinderException fe) {
    		if(IS_DEBUG) log.debug("All UserType collection could not be found");
    	} catch(Exception e) {
      		log.error("All UserType collection error: "+e.toString());
      		throw new ServiceException("All UserType collection error: "+e.getMessage());
    	}
    	return list;
	}


///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Getting EJB Homes:


	private synchronized AccessHome getAccessHome() throws ServiceException {
		if(mAccessHome == null) {
			try {
				mAccessHome = (AccessHome)EJBHomeCache.lookup(Access_HOME_NAME);
			} catch(Exception e) {
				log.error("AccessHome cannot be initiated: "+e.toString());
				throw new ServiceException("AccessHome cannot be initiated: "+e.getMessage());
			}
		}
		return mAccessHome;
	}

	private synchronized DifficultyHome getDifficultyHome() throws ServiceException {
		if(mDifficultyHome == null) {
			try {
				mDifficultyHome = (DifficultyHome)EJBHomeCache.lookup(Difficulty_HOME_NAME);
			} catch(Exception e) {
				log.error("DifficultyHome cannot be initiated: "+e.toString());
				throw new ServiceException("DifficultyHome cannot be initiated: "+e.getMessage());
			}
		}
		return mDifficultyHome;
	}

	private synchronized LocaleHome getLocaleHome() throws ServiceException {
		if(mLocaleHome == null) {
			try {
				mLocaleHome = (LocaleHome)EJBHomeCache.lookup(Locale_HOME_NAME);
			} catch(Exception e) {
				log.error("LocaleHome cannot be initiated: "+e.toString());
				throw new ServiceException("LocaleHome cannot be initiated: "+e.getMessage());
			}
		}
		return mLocaleHome;
	}

	private synchronized PriorityHome getPriorityHome() throws ServiceException {
		if(mPriorityHome == null) {
			try {
				mPriorityHome = (PriorityHome)EJBHomeCache.lookup(Priority_HOME_NAME);
			} catch(Exception e) {
				log.error("PriorityHome cannot be initiated: "+e.toString());
				throw new ServiceException("PriorityHome cannot be initiated: "+e.getMessage());
			}
		}
		return mPriorityHome;
	}

	private synchronized ProjectHome getProjectHome() throws ServiceException {
		if(mProjectHome == null) {
			try {
				mProjectHome = (ProjectHome)EJBHomeCache.lookup(Project_HOME_NAME);
			} catch(Exception e) {
				log.error("ProjectHome cannot be initiated: "+e.toString());
				throw new ServiceException("ProjectHome cannot be initiated: "+e.getMessage());
			}
		}
		return mProjectHome;
	}

	private synchronized ProjectAccessHome getProjectAccessHome() throws ServiceException {
		if(mProjectAccessHome == null) {
			try {
				mProjectAccessHome = (ProjectAccessHome)EJBHomeCache.lookup(ProjectAccess_HOME_NAME);
			} catch(Exception e) {
				log.error("ProjectAccessHome cannot be initiated: "+e.toString());
				throw new ServiceException("ProjectAccessHome cannot be initiated: "+e.getMessage());
			}
		}
		return mProjectAccessHome;
	}

	private synchronized TaskHome getTaskHome() throws ServiceException {
		if(mTaskHome == null) {
			try {
				mTaskHome = (TaskHome)EJBHomeCache.lookup(Task_HOME_NAME);
			} catch(Exception e) {
				log.error("TaskHome cannot be initiated: "+e.toString());
				throw new ServiceException("TaskHome cannot be initiated: "+e.getMessage());
			}
		}
		return mTaskHome;
	}

	private synchronized TaskStatusHome getTaskStatusHome() throws ServiceException {
		if(mTaskStatusHome == null) {
			try {
				mTaskStatusHome = (TaskStatusHome)EJBHomeCache.lookup(TaskStatus_HOME_NAME);
			} catch(Exception e) {
				log.error("TaskStatusHome cannot be initiated: "+e.toString());
				throw new ServiceException("TaskStatusHome cannot be initiated: "+e.getMessage());
			}
		}
		return mTaskStatusHome;
	}

	private synchronized TaskTypeHome getTaskTypeHome() throws ServiceException {
		if(mTaskTypeHome == null) {
			try {
				mTaskTypeHome = (TaskTypeHome)EJBHomeCache.lookup(TaskType_HOME_NAME);
			} catch(Exception e) {
				log.error("TaskTypeHome cannot be initiated: "+e.toString());
				throw new ServiceException("TaskTypeHome cannot be initiated: "+e.getMessage());
			}
		}
		return mTaskTypeHome;
	}

	private synchronized UserHome getUserHome() throws ServiceException {
		if(mUserHome == null) {
			try {
				mUserHome = (UserHome)EJBHomeCache.lookup(User_HOME_NAME);
			} catch(Exception e) {
				log.error("UserHome cannot be initiated: "+e.toString());
				throw new ServiceException("UserHome cannot be initiated: "+e.getMessage());
			}
		}
		return mUserHome;
	}

	private synchronized UserTypeHome getUserTypeHome() throws ServiceException {
		if(mUserTypeHome == null) {
			try {
				mUserTypeHome = (UserTypeHome)EJBHomeCache.lookup(UserType_HOME_NAME);
			} catch(Exception e) {
				log.error("UserTypeHome cannot be initiated: "+e.toString());
				throw new ServiceException("UserTypeHome cannot be initiated: "+e.getMessage());
			}
		}
		return mUserTypeHome;
	}

}
