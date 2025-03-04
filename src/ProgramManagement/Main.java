package ProgramManagement;

import java.io.File;

import CheckAndTransformation.CheckInput;
import CheckAndTransformation.InputChecker;
import CheckAndTransformation.IsValidMovie;
import CheckAndTransformation.ParseToXml;
import DataStorage.FileStack;
import DataStorage.Movie;
import DataStorage.MovieCollection;
import UserInteraction.DataInput;
/**
 * Главный класс программы, который управляет загрузкой данных, обработкой команд и выводом результатов.
 * Программа принимает команды от пользователя и выполняет различные действия с коллекцией фильмов.
 */
public class Main {
	public static boolean programmWork;
	static DataInput dataInput = new DataInput();
	/**
     * Точка входа в программу.
     * Загружает коллекцию фильмов, выполняет команды и управляет историей команд.
     * 
     * @param args аргументы командной строки (ожидается один аргумент - имя файла для загрузки коллекции).
     */
	public static void main(String[] args) {

		CommandParser parseCommand = new CommandParser();
		programmWork = true;
		
		if (args.length != 1) {
	        System.out.println("Ошибка: Укажите XML-файл в аргументах командной строки.");
	        return;
	    }
		
		String fileName = args[0];
		
		File file = new File(fileName);

	       // Проверяем, существует ли файл
	       if (!file.exists() || file.isDirectory()) {
	           System.out.println("Ошибка: Файл \"" + fileName + "\" не найден или является директорией.");
	           return;
	       }
		
	    System.out.println("Загрузка коллекции из файла: " + fileName);
	    try {
	    	ParseToXml.loadMoviesFromXML(fileName);
	    	System.out.println("коллекция загружена из файла "+ fileName);
	    }	
	    catch (Exception e){
	    	System.out.println("ошибка при загрузке из файла "+ fileName);
	    }
	    
	    for(Movie movie:MovieCollection.movies) {
	    	if(!IsValidMovie.isValidMovie(movie)) {
	    		MovieCollection.movies.remove(movie);
	    		System.out.println("ошибка: в файле были фильмы с ошибочными данными, фильм удалён: " + movie);
	    	}
	    }
		
        //CommandManager commandManager = new CommandManager();
       
    	
    	System.out.println("Введите help, чтобы увидеть список доступных команд");
        while(programmWork) {
        	FileStack.fileStack.clear();
        	System.out.print("> ");
            String input = dataInput.input();
            InputChecker checkInput = new CheckInput();
            Command<?> commandRez = CommandManager.commands.get(parseCommand.parseCommandName(input)[0]);
            if(commandRez!= null) {
            	commandRez.inputChecker = checkInput;
            }
            ExecuteCommand.executeCommand(input);
            
            
            
        	
        }
		
		
		
    }


}
