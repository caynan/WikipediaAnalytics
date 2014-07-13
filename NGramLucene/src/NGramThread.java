import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.analysis.shingle.ShingleAnalyzerWrapper;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.util.Version;

public class NGramThread implements Runnable {

	private static final Logger LOG = Logger.getLogger(NGramThread.class
			.getName());

	int min, max;
	File file;

	public NGramThread(File file, int min, int max) {
		this.file = file;
		this.min = min;
		this.max = max;
	}

	@Override
	public void run() {
		try {
			long startTime = System.currentTimeMillis();

			FileReader reader = new FileReader(file);

			// Parse the file into n-gram tokens
			SimpleAnalyzer simpleAnalyzer = new SimpleAnalyzer(
					Version.LUCENE_4_9);

			ShingleAnalyzerWrapper shingleAnalyzer = new ShingleAnalyzerWrapper(
					simpleAnalyzer, min, max);

			TokenStream stream = shingleAnalyzer
					.tokenStream("contents", reader);

			CharTermAttribute charTermAttribute = stream
					.getAttribute(CharTermAttribute.class);

			stream.reset();

			// Store them in the Map
			Map<String, Integer> gramMap = new HashMap<String, Integer>();
			while (stream.incrementToken()) {
				String gramString = charTermAttribute.toString();

				int count = gramMap.containsKey(gramString) ? gramMap
						.get(gramString) : 0;
				gramMap.put(gramString, count + 1);
			}

			shingleAnalyzer.close();
			
			PrintWriter writer = new PrintWriter("gramMap.txt", "UTF-8");
			for (Map.Entry<String, Integer> e : gramMap.entrySet()) {
				writer.println(e);
			}
			writer.close();

			long stopTime = System.currentTimeMillis();
			long totalTime = stopTime - startTime;

			LOG.log(Level.INFO, file.getName() + " completed. in: " + totalTime
					+ "ms");
		}

		catch (FileNotFoundException e) {
			LOG.log(Level.SEVERE, "Parse Failed.  Reason: " + e.getMessage(), e);
		}

		catch (IOException e) {
			LOG.log(Level.SEVERE, "Parse Failed.  Reason: " + e.getMessage(), e);
		}
	}
}