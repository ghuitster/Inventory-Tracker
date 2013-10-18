package model;

public class ObservableArgs
{
	private Object changedObj;
	private UpdateType updateType;
	
	public ObservableArgs(Object changedObj, UpdateType updateType)
	{
		this.changedObj = changedObj;
		this.updateType = updateType;
	}
	
	/**
	 * Gets the object which changed
	 * @pre none
	 * @post none
	 * @return The changed object
	 */
	public Object getChangedObj()
	{
		return this.changedObj;
	}
	
	/**
	 * Gets what action occurred with the changed object
	 * @pre none
	 * @post none
	 * @return An enumeration representing the update type
	 */
	public UpdateType getUpdateType()
	{
		return this.updateType;
	}
}
