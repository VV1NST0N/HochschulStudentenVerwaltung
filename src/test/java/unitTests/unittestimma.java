package unitTests;


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



public class unittestimma {

	//init

	@ClassRule
	@Rule
	public static ProcessEngineRule rule = TestCoverageProcessEngineRuleBuilder.create().build();

	static {
		LogFactory.useSlf4jLogging();
	}

	@Before
	public void setup() {
		init(rule.getProcessEngine());
	}

	// JUnit-Test für den gesamten Prozess
	@Test
	@Deployment(resources = "immatrikulation-prozess.bpmn")
	public void Start() throws ParseException {

		ProcessInstance pi = processEngine().getRuntimeService().startProcessInstanceByKey("Process_05xnfw5");

		System.out.println("Prozessinstanz mit der Id "+ pi.getId()+ " gestartet");


/////////////////////////////////////////////////////////////////////////////////////
		//User Task Bewerber Bewerber eintragen


		TaskService taskService = processEngine().getTaskService();


		String processistanceId = pi.getId();
		List<Task> Taskliste = taskService.createTaskQuery().processInstanceId(processistanceId).taskDefinitionKey("Task_1axupvt").list();


		Task meinTask = Taskliste.get(0);


		System.out.println("Definition-Key des User-Tasks: "+meinTask.getTaskDefinitionKey());


		System.out.println("Ich bin der Task mit dem Namen: " + meinTask.getName());

		Date datum = new SimpleDateFormat("dd/mm/yyyy").parse("12/12/1799");

		//mapen
		Map<String, Object> variables = new HashMap<String, Object>();

		variables.put("asignee", "test");

		variables.put("vorname", "pjoter");
		variables.put("nachname", "boris");
		variables.put("email", "elite@hans");
		variables.put("geburtsort", "dubai");
		variables.put("nationalitaet", "deutsch");
		variables.put("adresse", "Kaiserstrasse");
		variables.put("geburtsdatum", datum);
		variables.put("sonstigeInformationen", "sachen");
		variables.put("studiengangName", "Maschinenbau");
		variables.put("wohnort", "Friedberg");


		variables.put("abiturnote", (double) 2.1);



		// Task "completen" und Prozessvariablen aus Map übergeben
		taskService.complete(meinTask.getId(), variables);


		/////////////////////////////////////////////////////////////////
		//User Task prüfe Eintrag und bestätige

		TaskService beer = processEngine().getTaskService();


		String processistanceId1 = pi.getId();
		List<Task> beerliste = beer.createTaskQuery().processInstanceId(processistanceId1).taskDefinitionKey("UserTask_02xyejm").list();


		Task beTask = beerliste.get(0);


		System.out.println("Definition-Key des User-Tasks: "+beTask.getTaskDefinitionKey());


		System.out.println("Ich bin der Task mit dem Namen: " + beTask.getName());




		//mapen
		Map<String, Object> variabl = new HashMap<String, Object>();
		Boolean beerfasst = new Boolean (true);


		variabl.put("bewerberErfasst", beerfasst);






		// Task "completen" und Prozessvariablen aus Map übergeben
		taskService.complete(beTask.getId(), variabl);




	}}

	/*@Test
	@Deployment(resources = "immatrikulation-prozess.bpmn")
	public void testTeilvorplus() throws ParseException {


		FileValue KRANKENVERSICHERUNG_DOC = new FileValueImpl(ValueType.FILE,"KRANKENVERSICHERUNG_DOC");
		FileValue HOCHSCHULZEUGNIS_DOC = new FileValueImpl(ValueType.FILE,"HOCHSCHULZEUGNIS_DOCC");
		Boolean richt = new Boolean (true);
		Boolean vollstaendig = new Boolean (false);

		ProcessInstance pi = processEngine().getRuntimeService().createProcessInstanceByKey("Process_05xnfw5")
				.startBeforeActivity("ExclusiveGateway_0yi3wg5")
					.setVariable("bewerberId", 886312122)
					.setVariable("unterlagenId", 1255812974)
					.setVariable("studiengangName", "Maschinenbau")
					.setVariable("HOCHSCHULZEUGNIS_DOC", HOCHSCHULZEUGNIS_DOC)
					.setVariable("KRANKENVERSICHERUNG_DOC", KRANKENVERSICHERUNG_DOC)

					.setVariable("korrektheitDaten", richt)
					.setVariable("vollstaendig" , vollstaendig)


				.execute();

		System.out.println("Prozessinstanz mit der Id "+ pi.getId()+ " gestartet");

		////////////////////////////////////////////////////////////////////
		// User Task Bewerbung prüfen

TaskService taskService = processEngine().getTaskService();


		String processistanceId = pi.getId();
	    List<Task> Taskliste = taskService.createTaskQuery().processInstanceId(processistanceId).taskDefinitionKey("bewerbungPruefen").list();


		Task meinTask = Taskliste.get(0);


		System.out.println("Definition-Key des User-Tasks: "+meinTask.getTaskDefinitionKey());


		System.out.println("Ich bin der Task mit dem Namen: " + meinTask.getName());



		//mapen
		Map<String, Object> variables = new HashMap<String, Object>();


		Boolean hochschulzeugnis = new Boolean (true);
		Boolean krankenversicherung = new Boolean (true);
		Boolean immatrikulationsantrag = new Boolean (false);
		Boolean bewerbungsschreiben = new Boolean (false);


		variables.put("hochschulzeugnis" , hochschulzeugnis);
		variables.put("krankenversicherung", krankenversicherung);
		  variables.put("immatrikulationsantrag", immatrikulationsantrag);
		  variables.put("bewerbungsschreiben", bewerbungsschreiben);



		// Task "completen" und Prozessvariablen aus Map übergeben
		taskService.complete(meinTask.getId(), variables);


////////////////////////////////////////////////////////////////////////////////////////////////////
		//Nachreichen



		TaskService nachr = processEngine().getTaskService();


		String processistanceId1 = pi.getId();
	    List<Task> nachrliste = nachr.createTaskQuery().processInstanceId(processistanceId1).taskDefinitionKey("Task_0e3uq91").list();


		Task nachrTask = nachrliste.get(0);


		System.out.println("Definition-Key des User-Tasks: "+nachrTask.getTaskDefinitionKey());


		System.out.println("Ich bin der Task mit dem Namen: " + nachrTask.getName());




		//mapen
		Map<String, Object> variabl = new HashMap<String, Object>();


		Boolean bewerbungsschreibenn = new Boolean (true);


		FileValue BEWERBUNGSSCHREIBEN_DOC = new FileValueImpl(ValueType.FILE,"BEWERBUNGSSCHREIBEN_DOC");






		variabl.put("bewerbungsschreiben", bewerbungsschreibenn);


		variabl.put("BEWERBUNGSSCHREIBEN_DOC", BEWERBUNGSSCHREIBEN_DOC);





		// Task "completen" und Prozessvariablen aus Map übergeben
		taskService.complete(nachrTask.getId(), variabl); }}
*/




