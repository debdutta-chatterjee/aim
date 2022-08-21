package sm.Util;

import java.util.List;
import java.util.stream.Collectors;
public class commonUtil {
	FileActions objFileActions = new FileActions();
	
	
	public String getListItemFromCSV(String strText,int positionInCSV){
		
		try {
			return strText.split(",")[positionInCSV];
		} catch (Exception e) {
			return null;
		}
	}
		
		
	public void removeDuplicateFromFile(String input_filename, String output_filename) {
		
		List<String> existing_records=objFileActions.readFile(input_filename);
		List<String> existing_records_without_duplicates = existing_records.stream()
			     .distinct()
			     .collect(Collectors.toList());
		
		objFileActions.writeToFile_ignoring_duplicates(existing_records_without_duplicates, output_filename);
	}
	
	
	}

