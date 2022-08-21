package sm.Util;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class FileActions {
	
	public String translateToFilePath(String code) {
		
		String outputrecords="output/outputrecords.csv";
		String availabilityList="output/outputrecords_advanced.csv";
		
		switch (code) {
		  case "outputrecords":
			return outputrecords;
		    
		  case "availabilityList":
			  return availabilityList;
		  default:
			  return outputrecords;
		}
			  
		
	}
	
	public void writeToFile(List<String> list)  {
		try {
			System.out.println("Adding"+list.size()+" records.");
		FileWriter writer = new FileWriter("output/outputrecords.csv",true); 
		for(String str: list) {
		  
			writer.write(str + System.lineSeparator()); 
		}
		writer.close();}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void write_DTS_ToFile(String strData,String filepath)  {
		try {
			
		FileWriter writer = new FileWriter(filepath,true);
		writer.write(strData+ System.lineSeparator());
		System.out.println("Added data to file-"+strData);
		writer.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void writeToFile(List<String> list,String filepath)  {
		try {
			System.out.println("Adding"+list.size()+" records.");
		FileWriter writer = new FileWriter(filepath,true); 
		for(String str: list) {
		  
			writer.write(str + System.lineSeparator()); 
		}
		writer.close();}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public List<String> readFile(String filepath){
		List<String> lines=null;
		try {
			lines = Files.readAllLines(Paths.get(filepath), StandardCharsets.UTF_8);
		} catch (IOException e) {
			
		}
		//System.out.println("List-"+lines);
		return lines;
	}
	
	
	public void writeToFile_ignoring_duplicates(List<String> list,String filepath)  {
		
		try {
			//remove duplicate within the list
			list=list.stream().distinct().collect(Collectors.toList());
			System.out.println("Requested entries to update-"+list.size());
			
			if(this.check_if_file_exists(filepath)) {
				List<String> existing_records=this.readFile(filepath);
				
				if(existing_records!=null && existing_records.size()>0 && list!=null) {
					list.removeAll(existing_records);}
				System.out.println("New records that are not enlisted-"+list.size());
			}
				
				
				System.out.println("Adding"+list.size()+" records.");
				FileWriter writer = new FileWriter(filepath,true); 
				for(String str: list) {
				  
					writer.write(str + System.lineSeparator()); 
				}
				writer.close();
			}
			
			
		
		catch (Exception e) {
			//System.out.println(""+ e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	public boolean  check_if_file_exists(String filepath){
		Path path = Paths.get(filepath);

		if (Files.exists(path)) {
		  return true;
		}

		else if (Files.notExists(path)) {
		  return false;
		}
		
		else {
			return false;
		}
	}
	
	
}
