/**
 * 
 */
package org.javarosa.core.api;

/**
 * A state represents a particular state of the application. Each state has an
 * associated view and controller and a set of transitions to new states.
 * 
 * @see org.javarosa.core.util.TrivialTransitions
 * @author ctsims
 * 
 */
public interface State {

	public void start();

}
