import java.io.*;
import java.util.Properties;
import java.io.File;
import java.io.IOException;

public class ProjectCompiler {
	private static Properties	properties				= null;
	private static void compileAndCreateJar(String projectPath,String commit)throws IOException, InterruptedException {
		String sourceDirectory = ""+properties.get("source.directory.path");//Change the source directory here
		String outputDirectory = "bin";
		String jarName = commit+"_project.jar";
        String jarOutputDirectory= ""+properties.get("target.path");//file where the jar files will generate

		// Change directory to project directory
		ProcessBuilder changeDirProcessBuilder = new ProcessBuilder("bash", "-c", "cd " + projectPath);
		Process changeDirProcess = changeDirProcessBuilder.start();
		changeDirProcess.waitFor();

		// Compile the project
		ProcessBuilder compileProcessBuilder = new ProcessBuilder("bash", "-c", "javac -d " + outputDirectory + " " +sourceDirectory + "/PreparedStatementHelper.java");
		compileProcessBuilder.directory(new File(projectPath));
		Process compileProcess = compileProcessBuilder.start();
		int exitValue=compileProcess.waitFor();

			System.out.println("Project compiled successfully for commit: " + commit);
			// Create JAR file
			ProcessBuilder jarProcessBuilder = new ProcessBuilder("bash", "-c", "jar cf " + jarOutputDirectory + jarName + " -C " + outputDirectory + " .");
	        jarProcessBuilder.directory(new File(projectPath));
			Process jarProcess = jarProcessBuilder.start();
			jarProcess.waitFor();
			System.out.println("JAR file created for commit: " + commit);
		
	}
// load properties from system.properties file
	static void loadProperties() {
		String path=null;
		try {

			path = System.getProperty("user.dir");
			properties = new Properties();
			properties.load(new FileInputStream(path+"\\src\\system.properties"));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			path=null;
		}
	}	
	public static void main(String[] args) {
		loadProperties();
		String projectPath =""+properties.get("project.path");  // configure project path
		String[] issues = {"CASSANDRA-14655","CASSANDRA-16576", "CASSANDRA-16462",
			    "CASSANDRA-16365",
			    "CASSANDRA-16255",
			    "CASSANDRA-16136",
			    "CASSANDRA-15868",
			    "CASSANDRA-15867",
			    "CASSANDRA-15851",
			    "CASSANDRA-15659",
			    "CASSANDRA-15631",
			    "CASSANDRA-15257",
			    "CASSANDRA-15195",
			    "CASSANDRA-15137",
			    "CASSANDRA-14872",
			    "CASSANDRA-14970",
			    "CASSANDRA-14806",
			    "CASSANDRA-14633",
			    "CASSANDRA-14134",
			    "CASSANDRA-14115",
			    "CASSANDRA-13997",
			    "CASSANDRA-13741",
			    "CASSANDRA-13727",
			    "CASSANDRA-13648",
			    "CASSANDRA-13360",
			    "CASSANDRA-13336",
			    "CASSANDRA-13114",
			    "CASSANDRA-13072",
			    "CASSANDRA-12996",
			    "CASSANDRA-12995",
			    "CASSANDRA-12994",
			    "CASSANDRA-12924",
			    "CASSANDRA-12790",
			    "CASSANDRA-12486",
			    "CASSANDRA-12585",
			    "CASSANDRA-12536",
			    "CASSANDRA-12312",
			    "CASSANDRA-12197",
			    "CASSANDRA-12063",
			    "CASSANDRA-12089",
			    "CASSANDRA-12032",
			    "CASSANDRA-12035",
			    "CASSANDRA-11967",
			    "CASSANDRA-11853",
			    "CASSANDRA-11703",
			    "CASSANDRA-3927" ,
			    "CASSANDRA-16079" ,
			    "CASSANDRA-15294" ,
			    "CASSANDRA-15121" ,
			    "CASSANDRA-14611" ,
			    "CASSANDRA-14001" ,
			    "CASSANDRA-13885" ,
			    "CASSANDRA-13909" ,
			    "CASSANDRA-13668" ,
			    "CASSANDRA-13444" ,
			    "CASSANDRA-13290" ,
			    "CASSANDRA-13241" ,
			    "CASSANDRA-13225" ,
			    "CASSANDRA-13039" ,
			    "CASSANDRA-12915" ,
			    "CASSANDRA-12684" ,
			    "CASSANDRA-12104" ,
			    "CASSANDRA-12054" ,
			    "CASSANDRA-11977" ,
			    "CASSANDRA-11965" ,
			    "CASSANDRA-11561" ,
			    "CASSANDRA-11452" ,
			    "CASSANDRA-11327" ,
			    "CASSANDRA-11053" ,
			    "CASSANDRA-11040" ,
			    "CASSANDRA-10378" ,
			    "CASSANDRA-10344" ,
			    "CASSANDRA-10195" ,
			    "CASSANDRA-9876" ,
			    "CASSANDRA-9499" ,
			    "CASSANDRA-9415" ,
			    "CASSANDRA-9302" ,
			    "CASSANDRA-9230" ,
			    "CASSANDRA-9090" ,
			    "CASSANDRA-8929" ,
			    "CASSANDRA-8918" ,
			    "CASSANDRA-8915" ,
			    "CASSANDRA-8901" ,
			    "CASSANDRA-8891" ,
			    "CASSANDRA-8630" ,
			    "CASSANDRA-3434" ,
			    "CASSANDRA-16741" ,
			    "CASSANDRA-16699" ,
			    "CASSANDRA-15785" ,
			    "CASSANDRA-15421" ,
			    "CASSANDRA-15416" ,
			    "CASSANDRA-15411" ,
			    "CASSANDRA-13291" ,
			    "CASSANDRA-10735" ,
			    "CASSANDRA-10575" ,
			    "CASSANDRA-10528" ,
			    "CASSANDRA-10508" ,
			    "CASSANDRA-8977" ,
			    "CASSANDRA-8714" ,
			    "CASSANDRA-8565" ,
			    "CASSANDRA-17059" ,
			    "CASSANDRA-15823" ,
			    "CASSANDRA-15564" ,
			    "CASSANDRA-15553" ,
			    "CASSANDRA-14409" ,
			    "CASSANDRA-13993" ,
			    "CASSANDRA-13910" ,
			    "CASSANDRA-13457" ,
			    "CASSANDRA-1944" ,
			    "CASSANDRA-12345" ,
			    "CASSANDRA-12151" ,
			    "CASSANDRA-12126" ,
			    "CASSANDRA-11559" ,
			    "CASSANDRA-11218" ,
			    "CASSANDRA-11159" ,
			    "CASSANDRA-10091" ,
			    "CASSANDRA-9193" ,
			    "CASSANDRA-8844" ,
			    "CASSANDRA-8358" ,
			    "CASSANDRA-7924" ,
			    "CASSANDRA-7838" ,
			    "CASSANDRA-5887" ,
			    "CASSANDRA-4861" ,
			    "CASSANDRA-4495" ,
			    "CASSANDRA-16499" ,
			    "CASSANDRA-16139" ,
			    "CASSANDRA-16326" ,
			    "CASSANDRA-16138" ,
			    "CASSANDRA-15935" ,
			    "CASSANDRA-15354" ,
			    "CASSANDRA-15299" ,
			    "CASSANDRA-15229" ,
			    "CASSANDRA-15202" ,
			    "CASSANDRA-15106" ,
			    "CASSANDRA-15005" ,
			    "CASSANDRA-14937" ,
			    "CASSANDRA-14821" ,
			    "CASSANDRA-14737" ,
			    "CASSANDRA-14660" ,
			    "CASSANDRA-14556" ,
			    "CASSANDRA-14262" ,
			    "CASSANDRA-14213" ,
			    "CASSANDRA-14121" ,
			    "CASSANDRA-14116" ,
			    "CASSANDRA-13987" ,
			    "CASSANDRA-13924" ,
			    "CASSANDRA-13475" ,
			    "CASSANDRA-13530" ,
			    "CASSANDRA-13474" ,
			    "CASSANDRA-13442" ,
			    "CASSANDRA-13418" ,
			    "CASSANDRA-13304" ,
			    "CASSANDRA-13022" ,
			    "CASSANDRA-12859" ,
			    "CASSANDRA-12615" ,
			    "CASSANDRA-12346" ,
			    "CASSANDRA-12269" ,
			    "CASSANDRA-12268" ,
			    "CASSANDRA-12245" ,
			    "CASSANDRA-12148" ,
			    "CASSANDRA-11521" ,
			    "CASSANDRA-11475" ,
			    "CASSANDRA-11380" ,
			    "CASSANDRA-11366" ,
			    "CASSANDRA-11258" ,
			    "CASSANDRA-11204" ,
			    "CASSANDRA-10956" ,
			    "CASSANDRA-10727" ,
			    "CASSANDRA-10713" ,
			    "CASSANDRA-10404" ,
			    "CASSANDRA-10244" ,
			    "CASSANDRA-9988" ,
			    "CASSANDRA-9975" ,
			    "CASSANDRA-9947" ,
			    "CASSANDRA-9945" ,
			    "CASSANDRA-9755" ,
			    "CASSANDRA-9738" ,
			    "CASSANDRA-9673" ,
			    "CASSANDRA-9669" ,
			    "CASSANDRA-9667" ,
			    "CASSANDRA-9658" ,
			    "CASSANDRA-9459" ,
			    "CASSANDRA-9402" ,
			    "CASSANDRA-9304" ,
			    "CASSANDRA-9231" ,
			    "CASSANDRA-9143" ,
			    "CASSANDRA-8984" ,
			    "CASSANDRA-8971" ,
			    "CASSANDRA-8897" ,
			    "CASSANDRA-8671" ,
			    "CASSANDRA-8868" ,
			    "CASSANDRA-8866" ,
			    "CASSANDRA-8833" ,
			    "CASSANDRA-8803" ,
			    "CASSANDRA-8771" ,
			    "CASSANDRA-8692" ,
			    "CASSANDRA-8609" ,
			    "CASSANDRA-8568" ,
			    "CASSANDRA-8464" ,
			    "CASSANDRA-8193" ,
			    "CASSANDRA-8169" ,
			    "CASSANDRA-8099" ,
			    "CASSANDRA-8098" ,
			    "CASSANDRA-16222" ,
			    "CASSANDRA-13476" ,
			    "CASSANDRA-12229" ,
			    "CASSANDRA-10551" 
			};
		try {
			for (String commitMsg : issues) {
				// search commit message with grep command
				 String gitCommand = "git log --pretty=format:%H --grep=\"" + commitMsg + "\"";
				 Process process = Runtime.getRuntime().exec(gitCommand, null, new File(projectPath));
		            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
		            String line;
		           
		            while ((line = reader.readLine()) != null) {
		                Process process_1 = Runtime.getRuntime().exec("git checkout " + line, null, new File(projectPath));
				        process_1.waitFor();
		            	compileAndCreateJar(projectPath,commitMsg);
		            	Process process_2 = Runtime.getRuntime().exec("git checkout trunk", null, new File(projectPath));
		            	process_2.waitFor();
		            }
		            }
		

		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}
}