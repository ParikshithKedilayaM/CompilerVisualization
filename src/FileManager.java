import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileManager {
	private final String FILE_EXT = ".ser";
	
	public void saveFile() {
		FileOutputStream fileOut = null;
		ObjectOutputStream out = null;
		String fileName = null;
		try {
			JFileChooser chosenFile = new JFileChooser();
			int showSaveDialog = chosenFile.showSaveDialog(null);
			if (showSaveDialog == JFileChooser.APPROVE_OPTION) {
				fileName = chosenFile.getSelectedFile().getAbsolutePath().toString() + FILE_EXT;
			}
			if (fileName != null) {
				fileOut = new FileOutputStream(new File(fileName));
				out = new ObjectOutputStream(fileOut);
				
				// to implement
				
				fileOut.flush();
				JOptionPane.showMessageDialog(null, "File Saved!");
			}
		} catch (IOException i) {
			JOptionPane.showMessageDialog(null, "Error occured! Could Not Save your file");
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			if (fileOut != null) {
				try {
					fileOut.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
	
	public void loadFile() {
		FileInputStream fileIn = null;
		ObjectInputStream in = null;
		String fileName = null;
		try {
			JFileChooser chosenFile = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter(FILE_EXT, FILE_EXT);
			chosenFile.setFileFilter(filter);
			int showOpenDialog = chosenFile.showOpenDialog(null);
			if (showOpenDialog == JFileChooser.APPROVE_OPTION) {
				fileName = chosenFile.getSelectedFile().getAbsolutePath();
				fileIn = new FileInputStream(fileName);
				in = new ObjectInputStream(fileIn);
				
				// to implement
				
			}
		} catch (IOException i) {
			JOptionPane.showMessageDialog(null, "Could not load the file. Please select only .ser files!");
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			if (fileIn != null) {
				try {
					fileIn.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
}
