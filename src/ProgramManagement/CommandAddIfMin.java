package ProgramManagement;

import java.time.ZonedDateTime;
import java.util.Map;

import CheckAndTransformation.CheckInput;
import CheckAndTransformation.IsValidMovie;
import DataStorage.IdGeneratoin;
import DataStorage.Movie;
import DataStorage.*;
import DataStorage.MovieCollection;
/**
 * Класс, реализующий команду добавления нового фильма в коллекцию, если его сборы являются минимальными.
 * Реализует интерфейс {@link CommandWithoutArg}, что означает, что команда не принимает аргументов.
 * 
 */
public class CommandAddIfMin extends Command<Void> {
	/**
     * Метод для выполнения команды добавления фильма, если его сборы являются минимальными в коллекции.
     * Запрашивает данные у пользователя, проверяет, являются ли сборы фильма минимальными,
     * и добавляет фильм в коллекцию, если условие выполняется.
     */
	
	@Override
	public void command(Void arg) {
		IdGeneratoin id = new IdGeneratoin();
		double minTotalBoxOffice=0;
		for(Movie movie: MovieCollection.movies) {
			if (movie.getTotalBoxOffice()> minTotalBoxOffice) {
				minTotalBoxOffice = movie.getTotalBoxOffice();
			}
		}

		Map<String, Object> copyData = inputChecker.checkInput();
		try {
			if((double) copyData.get("TotalBoxOffice") < minTotalBoxOffice) {
				Movie movie = new Movie(id.UpdateId(), (String) copyData.get("Name"), (Coordinates) copyData.get("Coordinates"), ZonedDateTime.now(), (int) copyData.get("OscarsCount"), (double) copyData.get("TotalBoxOffice"), (double) copyData.get("UsaBoxOffice"), (MovieGenre) copyData.get("Genre"), (Person) copyData.get("Operator"));
				if(IsValidMovie.isValidMovie(movie)) {
					MovieCollection.movies.add(movie);
					System.out.println("Фильм добавлен в коллекцию");
				}
				else {
					System.out.println("в введённых данных обнаружена ошибка, фильм не добавлен в коллекцию");
				}
			}
			else System.out.println("Значение сборов фильма не является минимальным");
		}
		catch(NullPointerException e) {
			System.out.println("в введённых данных обнаружена ошибка ");
		}

	}

}
