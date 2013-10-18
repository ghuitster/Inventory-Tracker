package model;

import java.util.Observer;

public interface IObservable
{
		public abstract void addObserver(Observer o);
		
		
		public abstract int countObservers();
		
		
		public abstract void deleteObserver(Observer o);
		
		
		public abstract void deleteObservers();	
}
