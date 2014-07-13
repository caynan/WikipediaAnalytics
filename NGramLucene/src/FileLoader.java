import java.io.File;
import java.util.Collection;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.CanReadFileFilter;
import org.apache.commons.io.filefilter.DirectoryFileFilter;

public class FileLoader {

	/**
	 * Returns all the files in a directory.
	 * 
	 * @param dir
	 *            Path to the directory that contains the text documents to be
	 *            parsed.
	 * @return
	 */
	public Collection<File> getFolderContents(String dir) {
		// Collect all readable documents
		File file = new File(dir);
		Collection<File> files = FileUtils.listFiles(file,
				CanReadFileFilter.CAN_READ, DirectoryFileFilter.DIRECTORY);
		return files;
	}

}
