package wordTree.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import wordTree.util.MyLogger.DebugLevel;

/**
 * Helper class to process READ and WRITE operations on file
 * @author suresh
 *
 */
public class FileProcessor {

	/**
	 * Enum that will define the use of FileProcess whether it's a File READ operation or WRITE operation.
	 * @author suresh
	 *
	 */
	public enum Permission {
		READ, WRITE
	}

	String filePath;
	BufferedReader reader;
	BufferedWriter writer;
	Permission permission;
	boolean permitEmptyFile = false;

	public FileProcessor() {
		MyLogger.writeMessage("FileProcessor Constructor is called", DebugLevel.CONSTRUCTOR);
		openFile();
	}

	/**
	 * Parameterized constructor for binding a File Processor instance with file and access permission
	 * i.e Either WRITE or READ
	 * @param filePath
	 * @param permission
	 * @param permitEmptyFile
	 */
	public FileProcessor(String filePath, Permission permission, boolean permitEmptyFile) {
		MyLogger.writeMessage("FileProcessor Parameterized Constructor is called", DebugLevel.CONSTRUCTOR);
		this.filePath = filePath;
		this.permission = permission;
		this.permitEmptyFile = permitEmptyFile;
		openFile();
	} 



	/**
	 * Open file if not already opened
	 * @return
	 */
	private boolean openFile() {

		if(filePath == null || filePath.length() == 0) {
			return false;
		}


		File file = new File(filePath);
		boolean openSuccess = true;
		try {
			switch (permission) {
			case READ:

				/*Exit application if file is empty and empty file is not permitted*/
				if(file.length() == 0 && !permitEmptyFile) {
					System.err.println("\"" + file.getName() + "\"" + " - File is Empty!");
					System.exit(1);
					return false;
				}

				FileReader fileReader = new FileReader(file);
				reader = new BufferedReader(fileReader);
				break;
			case WRITE:
				FileWriter fileWriter = new FileWriter(file);
				writer = new BufferedWriter(fileWriter);
				break;
			default:
				System.err.println("FileProcessor:openFile - Please provide appropriate File Permission");
				System.exit(0);
				break;
			}

		} catch (FileNotFoundException e) {
			openSuccess = false;
			System.err.println("FileProcessor:openFile - File Not Found Exception Occured :: "  + e.getLocalizedMessage());
		} catch(IOException e) {
			openSuccess = false;
			System.err.println("FileProcessor:openFile - IO Exception Occured :: "  + e.getLocalizedMessage());
		} finally {
			if (!openSuccess) {
				closeFile();
				System.exit(0);
			}
		}

		return true;
	}

	/**
	 * Writes list of strings into file
	 * @param lines to write into file
	 */
	public void writeLines(List<String> lines) {
		try {
			for(String line : lines ) {
				writer.append(line+"\n");
			}
		} catch (IOException e) {
			System.err.println("FileProcessor:writeLine - IOException Occured :: "  + e.getLocalizedMessage());
			closeFile();
			System.exit(0);
		} finally {
		}
	}

	/**
	 * Reads the file line by line
	 * @return next line in the file
	 */
	public String readLine() {

		if(this.reader == null) {
			System.out.println("File is not open");
			return null;
		}

		String line = null;

		try {
			line = this.reader.readLine();
		} catch (IOException e) {
			System.err.println("FileProcessor:readLine - IO Exception Occured :: "  + e.getLocalizedMessage());
			closeFile();
			System.exit(0);
		} finally {
		}

		return line;
	}

	/**
	 * Close file if already opened
	 */
	public void closeFile() {
		try {
			switch (permission) {
			case READ:
				if (reader != null) {
					reader.close();
					reader = null;
				}	
				break;
			case WRITE:
				if (writer != null) {
					writer.flush();
					writer.close();
					writer = null;
				}
			default:
				break;
			}

		} catch (IOException e) {
			System.err.println("FileProcessor:closeFile - IO Exception Occured :: "  + e.getLocalizedMessage());
			System.exit(0);
		}
	}


	/**
	 * Setter method for file path
	 * @param filePath
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFilePath() {
		return filePath;
	}

	/**
	 * Setter method for setting permission
	 * @param permission
	 */
	public void setPermission(Permission permission) {
		this.permission = permission;
	}


}
