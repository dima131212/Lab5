package ProgramManagement;

import java.time.ZonedDateTime;
import java.util.Map;

import CheckAndTransformation.CheckInput;
import CheckAndTransformation.InputChecker;
import CheckAndTransformation.IsValidMovie;
import DataStorage.*;
/**
 * Класс {@code CommandUpdate} реализует команду обновления данных фильма по его ID.
 */
public class CommandUpdate extends Command<Long> {
	Movie copyMovie = null;
	boolean correctMovie = false;
	/**
     * Обновляет данные фильма в коллекции, если фильм с заданным ID найден.
     * @param id идентификатор фильма, который требуется обновить
     */
	@Override
	public void command(Long id) {
		Movie copyMovie = null;
		for (Movie item: MovieCollection.movies) {
			if(item.getId() == id) {
				copyMovie = item;
			}
		}
		

		if(copyMovie!= null) {

			Map<String, Object> copyData = inputChecker.checkInput();
			try {
				Movie movieChecker = new Movie(copyMovie.getId(), (String) copyData.get("Name"), (Coordinates) copyData.get("Coordinates"), copyMovie.getCreationDate(), (int) copyData.get("OscarsCount"), (double) copyData.get("TotalBoxOffice"), (double) copyData.get("UsaBoxOffice"), (MovieGenre) copyData.get("Genre"), (Person) copyData.get("Operator"));
				correctMovie = IsValidMovie.isValidMovie(movieChecker);
			}
			catch(NullPointerException e) {
				System.out.println("в введённых данных обнаружена ошибка ");
			}
			
			if(correctMovie) {
					copyMovie.setName((String) copyData.get("Name"));
					copyMovie.setCoordinates((Coordinates) copyData.get("Coordinates"));
					copyMovie.setOscarsCount((int) copyData.get("OscarsCount"));
					copyMovie.setTotalBoxOffice((double) copyData.get("TotalBoxOffice"));
					copyMovie.setUsaBoxOffice((double) copyData.get("UsaBoxOffice"));
					copyMovie.setGenre((MovieGenre) copyData.get("Genre"));
					copyMovie.setOperator((Person) copyData.get("Operator"));
					System.out.println("данные о фильме с id = " + id  + " обновлены");
			}
			
		}
		else {
			System.out.println("фильма с id = " + id + " нет в коллекции");
		}
	}



}
