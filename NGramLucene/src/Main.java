import java.io.File;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

	public static void main(String[] args) {
		int min = 2;
		int max = 5;
		String pathToFile = "src/testFiles/";
		
		FileLoader loader = new FileLoader();
		Collection<File> files = loader.getFolderContents(pathToFile);

		// Each document will have a ShingleThread parsing it
		ExecutorService ngramExecutor = Executors.newFixedThreadPool(files.size());

		for (Iterator<File> iter = files.iterator(); iter.hasNext();) {
			ngramExecutor.execute(new NGramThread(iter.next(), min, max));
		}

		ngramExecutor.shutdown();
	}

}
