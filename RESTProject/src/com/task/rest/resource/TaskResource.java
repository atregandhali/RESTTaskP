package com.task.rest.resource;
 
import java.util.ArrayList;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Request;
import javax.ws.rs.QueryParam;
 import com.task.rest.model.ListTasks;
import com.task.rest.model.TaskList;
 
@Path("/task")
public class TaskResource {

    private TaskList taskOne=new TaskList("Project Submission", "2014-06-06", false,1);
    private TaskList taskTwo=new TaskList("Project Demo", "2014-07-08", false,2);
    private TaskList taskThree=new TaskList("Health Checkup", "2014-05-01", true,3);
    private TaskList taskFour=new TaskList("Insurance Update", "2014-07-08", false,4);
    public ArrayList<TaskList> taskList=new ArrayList<TaskList>();
    
    public void addTaskResource(){
    	taskList.add(taskOne);
    	taskList.add(taskTwo);
    	taskList.add(taskThree);
    	taskList.add(taskFour);
    }
    
      // The @Context annotation allows us to have certain contextual objects
    // injected into this class.
    // UriInfo object allows us to get URI information (no kidding).
    @Context
    UriInfo uriInfo;
 
    // Another "injected" object. This allows us to use the information that's
    // part of any incoming request.
    // We could, for example, get header information, or the requestor's address.
    @Context
    Request request;
     
 
 
    @GET
    @Path("/:id")
    @Produces(MediaType.APPLICATION_JSON)
    public TaskList getSamplePerson(@QueryParam("id") int id) {
    	TaskList taskObj=null;
    	System.out.println("Id" + id);
    	for(int i=0;i<taskList.size();i++){
    		taskObj=taskList.get(i);
            if(taskObj.getId()==id ){ 	
    		System.out.println("Returning sample person: " + taskObj.getTaskDesc());
    		break;
            }
    	}
        return taskObj;
    }
    
    @GET
    @Path("/List")
    @Produces(MediaType.APPLICATION_JSON)
    public ListTasks getTaskList() {
    	addTaskResource();
    	 String taskListString="";
    	System.out.println("Getting list");
        System.out.println("Printing Lists" + taskList.toString());
        for(int i=0;i<taskList.size();i++){
        taskListString= taskListString + taskList.get(i).toString();
        }
        ListTasks li=new ListTasks();
        li.setList(taskList);
        System.out.println("Task List" + taskListString);
        return li;
    }
    
    
  
         
     
    /**
     * This method creates new task
     * @param taskObjFromURI- JSON data from URI
     * @return TaskList - Generated TaskList Object
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON)
    public ListTasks postTask(
           TaskList taskObjFromURI
            ) { 
    	addTaskResource();
        System.out.println("Storing posted " + taskObjFromURI );
       TaskList taskObj=new TaskList();
       taskObj.setId(taskObjFromURI.getId()); 
       taskObj.setTaskDesc(taskObjFromURI.getTaskDesc());
       taskObj.setCompleted(taskObjFromURI.isCompleted());
       taskList.add(taskObj);
       System.out.println("Task Description " + taskObj.getTaskDesc() + " Task Id " + taskObj.getId() + " Completed " + taskObj.isCompleted() );
       ListTasks li=new ListTasks();
       li.setList(taskList);
       return li;
                         
    }
    
    /**
     * This method updates existing task
     * @param taskObjFromURI- JSON data from URI
     * @return TaskList - Generated TaskList Object
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/:id")
    @Produces(MediaType.APPLICATION_JSON)
    public ListTasks upDateTask(
           TaskList taskObjFromURI
            ) { 
        System.out.println("Storing posted " + taskObjFromURI );
        TaskList taskObj=null;
        addTaskResource();
        int id=taskObjFromURI.getId();
       for(int i=0;i<taskList.size();i++){
    	   TaskList listObj=taskList.get(i);
    	   if(id==listObj.getId()){
    		   taskObj=listObj;
    		   break;
    	   }
       }
       taskObj.setId(taskObjFromURI.getId()); 
       taskObj.setTaskDesc(taskObjFromURI.getTaskDesc());
       taskObj.setCompleted(taskObjFromURI.isCompleted());
       System.out.println("Task Description " + taskObj.getTaskDesc() + " Task Id " + taskObj.getId() + " Completed " + taskObj.isCompleted() );
       ListTasks li=new ListTasks();
       li.setList(taskList);
       return li;
                         
    }
    
    /**
     * This method deletes existing task
     * @param taskObjFromURI- JSON data from URI
     * @return TaskList - Generated TaskList Object
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/delete")
    @Produces(MediaType.APPLICATION_JSON)
    public ListTasks deleteTask(
    		@QueryParam("id") int id
            ) { 
       addTaskResource();
      for(int i=0;i<taskList.size();i++){
   	   TaskList listObj=taskList.get(i);
   	   if(id==listObj.getId()){
   		  taskList.remove(i);
   		   break;
   	   }
      }
      ListTasks li=new ListTasks();
      li.setList(taskList);
      return li;
      
                         
    }
}