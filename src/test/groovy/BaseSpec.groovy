import spock.lang.*

import org.junit.Rule
import org.junit.rules.TestName

abstract class BaseSpec extends Specification {

	static PROCESS_TIMEOUT_MILLS = 1000 * 60 * 5 // 5 minutes
	static upgradedProjects = []
	
	static grailsHome = requiredSysProp('grailsHome')
	static grailsWorkDir = requiredSysProp('grailsWorkDir')
	static projectWorkDir = requiredSysProp('projectWorkDir')
	static outputDir = requiredSysProp('outputDir')
	
	@Lazy static grailsVersion = {
		new File(grailsHome, "build.properties").withReader { def p = new Properties(); p.load(it); p.'grails.version' }
	}()

	static requiredSysProp(prop) {
		assert System.getProperty(prop) != null
		System.getProperty(prop)
	}
	
	def command
	def exitStatus
	def output
	def dumpCounter = 0

	@Rule testName = new TestName()
	
	def execute(String project, String[] command) {
		if (!(project in upgradedProjects)) { upgradeProject(project) }
		this.command = command
		def completeCommand = ["${grailsHome}/bin/grails", "-Dgrails.work.dir=${grailsWorkDir}"]
		completeCommand.addAll(command.toList())

		def process = new ProcessBuilder(completeCommand as String[]).with {
			redirectErrorStream(true)
			directory(new File(projectWorkDir, project))
			environment()["GRAILS_HOME"] = grailsHome
			start()
		}
		
		def outputBuffer = new StringBuffer()
		process.consumeProcessOutputStream(outputBuffer)
		process.waitForOrKill(PROCESS_TIMEOUT_MILLS)
		
		exitStatus = process.exitValue()
		output = outputBuffer.toString()
		dumpOutput()
		exitStatus == 0
	}

	def upgradeProject(project) {
		upgradedProjects << project
		assert execute(project, 'upgrade', '--force')
		assertHeader()
	}

	def assertHeader() {
		def header = """Welcome to Grails ${grailsVersion} - http://grails.org/\nLicensed under Apache Standard License 2.0\nGrails home is set to: ${grailsHome}"""
		def firstThreeLinesOfOutput = output.split("\n")[0..2].join("\n")
		
		assert firstThreeLinesOfOutput == header
	}
	
	private dumpOutput() {
		def outputLabel = "${this.class.simpleName}-${testName.methodName}-${dumpCounter++}-${command.join('_')}"
		new File(outputDir, "${outputLabel}.txt") << output
		println outputLabel
		println output
	}
}