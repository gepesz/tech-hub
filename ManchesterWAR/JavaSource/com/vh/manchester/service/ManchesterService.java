package com.vh.manchester.service;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import com.vh.manchester.ejb.Access;
import com.vh.manchester.ejb.Difficulty;
import com.vh.manchester.ejb.Locale;
import com.vh.manchester.ejb.Priority;
import com.vh.manchester.ejb.Project;
import com.vh.manchester.ejb.ProjectAccess;
import com.vh.manchester.ejb.Task;
import com.vh.manchester.ejb.TaskStatus;
import com.vh.manchester.ejb.TaskType;
import com.vh.manchester.ejb.User;
import com.vh.manchester.ejb.UserType;
import com.vh.manchester.service.exception.ServiceException;

/**
 * The VH Corporation
 *
 * Copyright (c) 2003 The VH Corporation.  All rights reserved.  
 * Copying or reproduction without prior written approval is prohibited.
 * 
 * @author peter.szocs
 * Oct 30, 2003
 */
public interface ManchesterService extends Service {



	/**
	 * Major functionality
	 */
	public User authenticate(String username, String password) throws ServiceException;

	public boolean updateLoginInfo(User theUser) throws ServiceException;

	public java.util.Locale getUserLocale(User theUser);

	public boolean checkUsername(String username, Integer userId) throws ServiceException;

	public ArrayList findTasks(Integer projectId, Integer status, Date after, Date before, Integer assignedTo, Integer reportedBy, String searchString) throws Exception;		




	/**
	 * Access
	 */
	public Access createAccess(java.lang.String desc) throws ServiceException;

	public Access findAccessById(java.lang.Integer id) throws ServiceException;

	public Collection findAllAccess() throws ServiceException;




	/**
	 * Difficulty
	 */
	public Difficulty createDifficulty(java.lang.String desc) throws ServiceException;
	
	public Difficulty findDifficultyById(java.lang.Integer id) throws ServiceException;

	public Collection findAllDifficulty() throws ServiceException;




	/**
	 * Locale
	 */
	public Locale createLocale(java.lang.String desc, java.lang.String loc) throws ServiceException;

	public Locale findLocaleById(java.lang.Integer id) throws ServiceException;

	public Collection findAllLocale() throws ServiceException;




	/**
	 * Priority
	 */
	public Priority createPriority(java.lang.String desc) throws ServiceException;

	public Priority findPriorityById(java.lang.Integer id) throws ServiceException;

	public Collection findAllPriority() throws ServiceException;




	/**
	 * Project
	 */
	public Project createProject(java.lang.String desc) throws ServiceException;

	public boolean deleteProjects(Integer[] projIds, String userName) throws ServiceException;

	public Project findProjectById(java.lang.Integer id) throws ServiceException;

	public Collection findAllProject() throws ServiceException;

	public Integer getProjectAccess(Integer projectId, Integer userId) throws ServiceException;

	public boolean updateProject(Integer projectId, String desc) throws ServiceException;




	/**
	 * ProjectAccess
	 */
	public ProjectAccess createProjectAccess(java.lang.Integer projectId, java.lang.Integer userId, java.lang.Integer accessId) throws ServiceException;

	public ProjectAccess findProjectAccessByProjectUser(java.lang.Integer projectId, java.lang.Integer userId) throws ServiceException;

	public Collection findAllProjectAccess() throws ServiceException;

	public Collection findProjectAccessByProject(java.lang.Integer projectId) throws ServiceException;

	public Collection findProjectAccessByUser(java.lang.Integer userId) throws ServiceException;

	public boolean updateProjectAccess(Integer projectId, Integer userId, Integer accessId) throws ServiceException;




	/**
	 * Task
	 */
	public Task createTask(String title, String summary, Date plannedDate, Date createDate, String comment, Integer progress, Integer difficultyId, Integer taskTypeId, Integer taskStatusId, Integer priorityId, Integer assignedToId, Integer createdById, Integer projectId) throws ServiceException;

	public boolean deleteTasks(Integer[] taskIds, String userName) throws ServiceException;

	public Task findTaskById(java.lang.Integer id) throws ServiceException;

	public Collection findAllTask() throws ServiceException;
	
	public Collection findTaskByAssignedTo(Integer userId) throws ServiceException;

	public Collection findTaskByAssignedToOrCreatedBy(Integer userId) throws ServiceException;

	public boolean updateTask(Integer taskId, String title, String summary, Integer type, Integer priority, Integer difficulty, Integer status, Integer projectId, Integer assignedToId, java.sql.Date plannedDate, String comment, Integer progress) throws ServiceException;




	/**
	 * TaskStatus
	 */
	public TaskStatus createTaskStatus(java.lang.String desc) throws ServiceException;

	public TaskStatus findTaskStatusById(java.lang.Integer id) throws ServiceException;

	public Collection findAllTaskStatus() throws ServiceException;




	/**
	 * TaskType
	 */
	public TaskType createTaskType(java.lang.String desc) throws ServiceException;

	public TaskType findTaskTypeById(java.lang.Integer id) throws ServiceException;

	public Collection findAllTaskType() throws ServiceException;




	/**
	 * User
	 */
	public User createUser(String username, String password, String email, Timestamp lastLogin, int nofLogins, Integer localeId, Integer userTypeId, Integer lastProjectId) throws ServiceException;

	public boolean deleteUsers(Integer[] userIds, String userName) throws ServiceException;

	public User findUserById(java.lang.Integer id) throws ServiceException;

	public User findUserByUsername(java.lang.String username) throws ServiceException;

	public Collection findUsersByProject(Integer projectId) throws ServiceException;

	public Collection findDevelopersByProject(Integer projectId) throws ServiceException;

	public Collection findAllUser() throws ServiceException;

	public boolean updateUser(Integer userId, String username, String password, String email, Integer userTypeId, Integer localeId) throws ServiceException;

	public boolean updateUserLastProject(java.lang.Integer userId, java.lang.Integer projectId) throws ServiceException;




	/**
	 * UserType
	 */
	public UserType createUserType(java.lang.String desc) throws ServiceException;

	public UserType findUserTypeById(java.lang.Integer id) throws ServiceException;

	public Collection findAllUserType() throws ServiceException;

}