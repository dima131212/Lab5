package ProgramManagement;

import java.time.ZonedDateTime;
import java.util.Map;

import CheckAndTransformation.CheckInput;
import CheckAndTransformation.InputChecker;
import CheckAndTransformation.IsValidMovie;
import DataStorage.IdGeneratoin;
import DataStorage.Movie;
import DataStorage.*;
import DataStorage.MovieCollection;
/**
 * Класс, реализующий команду добавления нового фильма в коллекцию.
 * Реализует интерфейс {@link CommandWithoutArg}, что означает, что команда не принимает аргументов.
 * 
 * @author Автор класса
 * @version 1.0
 */
public class CommandAdd extends Command<Void> {

	
	
	
	/**
     * Метод для выполнения команды добавления фильма.
     * Запрашивает данные у пользователя, создаёт объект фильма и добавляет его в коллекцию.
     */
	@Override
	public void command(Void arg) {
		
		IdGeneratoin id = new IdGeneratoin();
		Map<String, Object> copyData = inputChecker.checkInput();

		
		try {
		Movie movie = new Movie(id.UpdateId(), (String) copyData.get("Name"), (Coordinates) copyData.get("Coordinates"), ZonedDateTime.now(), (int) copyData.get("OscarsCount"), (double) copyData.get("TotalBoxOffice"), (double) copyData.get("UsaBoxOffice"), (MovieGenre) copyData.get("Genre"), (Person) copyData.get("Operator"));
		if(IsValidMovie.isValidMovie(movie)) {
				MovieCollection.movies.add(movie);
				System.out.println("Фильм добавлен в коллекцию");
		}
		else {
			System.out.println("в введённых данных обнаружена ошибка, фильм не добавлен в коллекцию");
		}
		}
		catch(NullPointerException e) {
			System.out.println("в введённых данных обнаружена ошибка ");
		}
		
	}


	
}
