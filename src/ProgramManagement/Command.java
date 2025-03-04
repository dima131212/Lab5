package ProgramManagement;

import CheckAndTransformation.InputChecker;

public abstract class Command<T> {
	 public InputChecker inputChecker;
	 abstract void command(T arg);

}
