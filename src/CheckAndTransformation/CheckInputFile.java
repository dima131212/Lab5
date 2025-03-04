package CheckAndTransformation;

import java.util.HashMap;
import java.util.Map;

import DataStorage.Color;
import DataStorage.Coordinates;
import DataStorage.Country;
import DataStorage.Location;
import DataStorage.MovieGenre;
import DataStorage.Person;
import ProgramManagement.CommandExecuteScript;
import ProgramManagement.CommandParser;
import DataStorage.*;
public class CheckInputFile implements InputChecker {
	
	public static Map<String, Object> data = new HashMap<>();
	Coordinates coordinates = new Coordinates();
	Location location = new Location();
	Person operator = new Person();
	MovieGenre[] movieGenre = MovieGenre.values();
	Color[] colors = Color.values();
	Country[] country = Country.values();
	CommandParser parseCommand= new CommandParser();
	
	
	@Override
	public Map<String, Object> checkInput() {
		String[] dataMovie =  parseCommand.parseCommandName(CommandExecuteScript.fileCommands.remove(0));

		try {
	       	data.put("Name",dataMovie[0]);
	
			if(CheckData.isInteger(dataMovie[1]) ) {
	            int x = Integer.parseInt(dataMovie[1]);
	           	if (x > -754) {
	           		coordinates.setX(x);
	               } else return null;
	            } else return null;
			
			
			if(CheckData.isLong(dataMovie[2])) {
	            long y = Long.parseLong(dataMovie[2]);
	            coordinates.setY(y);
	            
			}
			else return null;
			
			data.put("Coordinates",coordinates);
			data.put("OscarsCount", Integer.parseInt(dataMovie[3]));
			data.put("TotalBoxOffice", Double.parseDouble(dataMovie[4]));
			data.put("UsaBoxOffice", Double.parseDouble(dataMovie[5]));
			 
			if(CheckData.isInteger(dataMovie[6]) && Integer.parseInt(dataMovie[6])<=movieGenre.length){
				data.put("Genre", movieGenre[Integer.parseInt(dataMovie[6])-1]);
			}
			else if(dataMovie[6] == null || dataMovie[6].isEmpty()) {
				data.put("Genre", null);
			}
			else {
				try {
					data.put("Genre", MovieGenre.valueOf(dataMovie[6].toUpperCase()));
					
				}
				catch(IllegalArgumentException e) {
					return null;
				}
			}
			
			
			try {
				operator.setName(dataMovie[7]);
			}
			catch(IllegalArgumentException e){
				return null;
			}
			
			
			try {
				operator.setHeight(Integer.parseInt(dataMovie[8]));
			}
			catch(IllegalArgumentException e){
				return null;
			}
			
			if(CheckData.isInteger(dataMovie[9])) {
	        	try {
	        		operator.setEyeColor(colors[Integer.parseInt(dataMovie[9])-1].name());
	        	}
	        	catch(ArrayIndexOutOfBoundsException e) {
	        		return null;
	        	}
	        }
			else {
				try {
					operator.setEyeColor(dataMovie[9].toUpperCase());
				}
				catch(IllegalArgumentException e){
					return null;
				}
	        }
			
			if(CheckData.isInteger(dataMovie[10])) {
	        	try {
	        		operator.setHairColor(colors[Integer.parseInt(dataMovie[10])-1].name());
	        	}
	        	catch(ArrayIndexOutOfBoundsException e) {
	        		return null;
	        	}
	        }
			else {
				try {
					operator.setHairColor(dataMovie[10].toUpperCase());
				}
				catch(IllegalArgumentException e){
					return null;
				}
	        }
			
			if(CheckData.isInteger(dataMovie[11])) {
	        	try {
	        		operator.setNationality(country[Integer.parseInt(dataMovie[11])-1].name());
	        	}
	        	catch(ArrayIndexOutOfBoundsException e) {
	        		return null;
	        	}
	        }
			else {
				try {
					operator.setNationality(dataMovie[11].toUpperCase());
				}
				catch(IllegalArgumentException e){
					return null;
				}
	        }
			
			try {
	            Integer.parseInt(dataMovie[12]);
	            location.setX(Integer.parseInt(dataMovie[12]));
	        } catch (NumberFormatException e) {
	            return null;
	        }
			
			
			try {
	            Long.parseLong(dataMovie[13]);
	            location.setY(Long.parseLong(dataMovie[13]));
	            
	        } catch (NumberFormatException e) {
	        	return null;
	        }
			
			
			try {
	            Integer.parseInt(dataMovie[14]);
	            location.setZ(Integer.parseInt(dataMovie[14]));
	            
	        } catch (NumberFormatException e) {
	            return null;
	        }
			
			
			if(dataMovie[15] == null|| dataMovie[15].isEmpty()) {
	        	return null;
	        }
	        else {
	        	location.setName(dataMovie[15]);
	        	
	        }
			
			operator.setLocation(location);
			data.put("Operator", operator);
			
			
			return data;
			

		}
		catch(ArrayIndexOutOfBoundsException e) {
			return null;
		}
	}
}
