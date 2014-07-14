import java.io.File;
import java.util.ArrayList;
import java.util.List;
/**
 * 
 * Classe que permite salvar em uma lista todos os arquivos .txt 
 * que se encontram a partir de um diretório raiz
 *
 */
public class FilesDir {

	private List<File> files = null;

	public FilesDir(String dir) {
		this.files = this.getAllFiles(dir);
	}

	public List<File> getAllFiles(String dir) {
		File directory = new File(dir);

		List<File> resultFiles = new ArrayList<File>();

		File[] allFiles = directory.listFiles();

		for (File f : allFiles) {
			if (f.isFile() && f.getName().endsWith(".txt")) {
				resultFiles.add(f);
			} else if (f.isDirectory()) {
				resultFiles.addAll(getAllFiles(f.getAbsolutePath()));
			}
		}
		return resultFiles;

	}

	public List<File> getFiles() {
		return this.files;
	}
}
