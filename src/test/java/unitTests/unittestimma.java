package unitTests;

import jdk.nashorn.internal.parser.JSONParser;
import org.apache.ibatis.logging.LogFactory;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.camunda.bpm.engine.task.Task;

import org.camunda.bpm.engine.variable.impl.value.FileValueImpl;
import org.camunda.bpm.engine.variable.type.ValueType;
import org.camunda.bpm.engine.variable.value.FileValue;
import org.camunda.bpm.extension.process_test_coverage.junit.rules.TestCoverageProcessEngineRuleBuilder;

import org.camunda.bpm.engine.test.Deployment;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import static org.camunda.bpm.engine.test.assertions.ProcessEngineAssertions.processEngine;
import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.*;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;






import org.junit.Test;

public class unittestimma {

	//init
	
	@ClassRule
	@Rule
	public static ProcessEngineRule rule = TestCoverageProcessEngineRuleBuilder.create().build();

	static {
		LogFactory.useSlf4jLogging(); // MyBatis
	}

	@Before
	public void setup() {
		init(rule.getProcessEngine());
	}

	// JUnit-Test für den gesamten Prozess
	@Test
	@Deployment(resources = "immatrikulation-prozess.bpmn") // hier die Prozessmodelle aufführen die getest werden sollen
	public void testProzess() throws ParseException {
	
		ProcessInstance pi = processEngine().getRuntimeService().startProcessInstanceByKey("Process_05xnfw5");
		
		System.out.println("Prozessinstanz mit der Id "+ pi.getId()+ " gestartet");

		
		
		// Usertask
		
		
		TaskService taskService = processEngine().getTaskService();

	
		String processistanceId = pi.getId();
	    List<Task> Taskliste = taskService.createTaskQuery().processInstanceId(processistanceId).taskDefinitionKey("Task_03y0lbe").list();
			

		Task meinTask = Taskliste.get(0);
		
		
		System.out.println("Definition-Key des User-Tasks: "+meinTask.getTaskDefinitionKey());

		
		System.out.println("Ich bin der Task mit dem Namen: " + meinTask.getName());

		Date datum = new SimpleDateFormat("dd/mm/yyyy").parse("12/12/1732");
		
		//mapen
		Map<String, Object> variables = new HashMap<String, Object>();
		
		variables.put("vorname", "pjotr");
		variables.put("nachname", "boris");
		variables.put("email", "elite@hans");
		variables.put("geburtsort", "Dubai");
		variables.put("nationalitaet", "deutsch");
		variables.put("adresse", "Kaiserstrasse");
		variables.put("geburtsdatum", datum);
		variables.put("sonstigeInformationen", "sachen");
		variables.put("studiengangName", "Maschinenbau");
		variables.put("wohnort", "Friedberg");
		variables.put("abiturnote", (long) 2);
		
		

		// Task "completen" und Prozessvariablen aus Map übergeben
		taskService.complete(meinTask.getId(), variables);
		

		
		
	}
	

	//Service Task
	
	@Test
	@Deployment(resources = "immatrikulation-prozess.bpmn")
	public void testTeilanfang() throws ParseException {
		

		// Prozessinstanz starten vor der Aktivität mit dem Key (=Id des Elements im
		// Prozessmodell)
		ProcessInstance pi = processEngine().getRuntimeService().createProcessInstanceByKey("Process_05xnfw5")
				.startBeforeActivity("Task_03y0lbe").execute();
			
		System.out.println("Prozessinstanz mit der Id "+ pi.getId()+ " gestartet");

		//////

		TaskService taskService = processEngine().getTaskService();


		String processistanceId = pi.getId();
		List<Task> Taskliste = taskService.createTaskQuery().processInstanceId(processistanceId).taskDefinitionKey("Task_03y0lbe").list();


		Task meinTask = Taskliste.get(0);


		System.out.println("Definition-Key des User-Tasks: "+meinTask.getTaskDefinitionKey());


		System.out.println("Ich bin der Task mit dem Namen: " + meinTask.getName());

		Date datum = new SimpleDateFormat("dd/mm/yyyy").parse("12/12/1732");

		//mapen
		Map<String, Object> variables = new HashMap<String, Object>();

		variables.put("vorname", "pjotr");
		variables.put("nachname", "boris");
		variables.put("email", "elite@hans");
		variables.put("geburtsort", "Dubai");
		variables.put("nationalitaet", "deutsch");
		variables.put("adresse", "Kaiserstrasse");
		variables.put("geburtsdatum", datum);
		variables.put("sonstigeInformationen", "sachen");
		variables.put("studiengangName", "Maschinenbau");
		variables.put("wohnort", "Friedberg");
		variables.put("abiturnote", (long) 2);



		// Task "completen" und Prozessvariablen aus Map übergeben
		taskService.complete(meinTask.getId(), variables);

		
	}


	//Nach bewerber im System eintragen + gateway
	
	@Test
	@Deployment(resources = { "immatrikulation-prozess.bpmn" })
	public void testteilvorplus() {
	
	
	
		ProcessInstance pi = processEngine().getRuntimeService().createProcessInstanceByKey("Process_05xnfw5")
		  .startBeforeActivity("Task_0x65e39").execute();

		TaskService taskService = processEngine().getTaskService();


		String processistanceId = pi.getId();
		List<Task> Taskliste = taskService.createTaskQuery().processInstanceId(processistanceId).taskDefinitionKey("Task_0x65e39").list();


		Task meinTask = Taskliste.get(0);


		System.out.println("Definition-Key des User-Tasks: "+meinTask.getTaskDefinitionKey());


		System.out.println("Ich bin der Task mit dem Namen: " + meinTask.getName());



		//mapen
		Map<String, Object> variables = new HashMap<String, Object>();
		FileValue file = new FileValueImpl(ValueType.FILE,"test.pdf");

		String s = "test";


		variables.put("KRANKENVERSICHERUNG_DOC", file);




		// Task "completen" und Prozessvariablen aus Map übergeben
		taskService.complete(meinTask.getId(), variables);

	}
		


	
	
}