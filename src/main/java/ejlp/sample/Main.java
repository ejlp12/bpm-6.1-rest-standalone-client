package ejlp.sample;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.manager.RuntimeEngine;
import org.kie.api.task.TaskService;
import org.kie.api.task.model.Status;
import org.kie.api.task.model.Task;
import org.kie.api.task.model.TaskSummary;
import org.kie.remote.client.api.RemoteRuntimeEngineFactory;

/**
 * This is a very simple Rest Client to test against a running instance of 
 * the KIE Workbench/Business Central
 * Tested on JBoss BPM Suite version 6.2.X 
 * You can parameterize
 *   - the Deployment Unit Id
 *   - the Application URL
 *   - the user/pass to execute operations
 */
public class Main {
	

	/*
		Set the parameters accoring your installation	 
	*/
	private static final String DEPLOYMENT_ID = "example:basicproject:1.0";
	private static final String APP_URL = "http://localhost:8080/business-central/";
	private static final String USER = "bpmsAdmin";
	private static final String PASSWORD = "Passw0rd!";

	public static void main( String[] args ) throws MalformedURLException {
		
		URL url = new URL(APP_URL);
		// create a factory using the installation parameters
		RuntimeEngine engine = RemoteRuntimeEngineFactory.newRestBuilder()
				.addUrl(url).addUserName(USER).addPassword(PASSWORD)
				.addDeploymentId(DEPLOYMENT_ID).build();
		
		
		KieSession session =  engine.getKieSession();
		
		//Create parameters for a process
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("_a1", "_A1_VALUE");
		params.put("_a2", new Integer(111));
		params.put("_a3", new Boolean(true));
		
		//Start a new process "ProcessName" to create a new process instance
		session.startProcess("ProcessName", params);
		
		// The TaskService class allows we to access the server tasks
		TaskService taskService = engine.getTaskService();		
		List<TaskSummary> tasks = taskService.getTasksAssignedAsPotentialOwner(USER, "en-UK");
		if(tasks.size() == 0){
			System.out.printf("No tasks can be claimed by user \"%s\" as owner...\n", USER);
		} else {
			System.out.printf("Tasks where user \"%s\" able to claim...\n", USER);
			for(TaskSummary t : tasks){
				System.out.printf("ID: %d\n", t.getId());
				System.out.printf("Task Name: %s\n", t.getName());
				System.out.printf("Actual Owner: %s\n", t.getActualOwner());
				System.out.printf("Created by: %s\n", t.getCreatedBy());
				System.out.printf("Created on: %s\n", t.getCreatedOn());
				System.out.printf("Status: %s\n", t.getStatus());
				System.out.printf("Description: %s\n", t.getDescription());
				System.out.println("---------------");
			}
		}
	}   
}
