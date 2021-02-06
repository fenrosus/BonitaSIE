/**
 * 
 */
package es.us.connector;

import java.util.List;
import java.util.ArrayList;

import org.bonitasoft.engine.connector.ConnectorValidationException;
import org.bonitasoft.engine.filter.UserFilterException;

import org.bonitasoft.engine.identity.Group;
import org.bonitasoft.engine.identity.GroupNotFoundException;
import org.bonitasoft.engine.identity.User;
import org.bonitasoft.engine.identity.UserCriterion;

/**
*The actor filter execution will follow the steps
* 1 - setInputParameters() --> the actor filter receives input parameters values
* 2 - validateInputParameters() --> the actor filter can validate input parameters values
* 3 - filter(final String actorName) --> execute the user filter
* 4 - shouldAutoAssignTaskIfSingleResult() --> auto-assign the task if filter returns a single result
*/
public class Filtro_actor_usuarios_de_grupoImpl extends AbstractFiltro_actor_usuarios_de_grupoImpl {
	Group group; 

	@Override
	public void validateInputParameters() throws ConnectorValidationException {
		try {
			group = getAPIAccessor().getIdentityAPI().getGroup((Long)getInputParameter(ID_GRUPO_INPUT_PARAMETER));
		}
		catch(GroupNotFoundException e) {
			throw new ConnectorValidationException("Error en filtro de actor: grupo "+(Long)getInputParameter(ID_GRUPO_INPUT_PARAMETER)+" no encontrado.");
		}
	}

	@Override
	public List<Long> filter(final String actorName) throws UserFilterException {
		//execute the user filter here
		//The method must return a list of user id's
		//you can use getAPIAccessor() and getExecutionContext()
		List<User> usuarios = getAPIAccessor().getIdentityAPI().getActiveUsersInGroup(group.getId(), 0, Integer.MAX_VALUE, UserCriterion.LAST_NAME_ASC);

		List<Long> lista_usuarios_ids = new ArrayList<Long>();
		for (User u: usuarios) {
			lista_usuarios_ids.add(u.getId());
		}
		//if (usuarios.size() == 0)
		//	throw new UserFilterException("Error en filtro de actor: grupo "+ID_GRUPO_INPUT_PARAMETER+" sin usuarios.");

		return lista_usuarios_ids;
	}

	@Override
	public boolean shouldAutoAssignTaskIfSingleResult() {
		// If this method returns true, the step will be assigned to 
		//the user if there is only one result returned by the filter method
		return super.shouldAutoAssignTaskIfSingleResult();
	
	}

}
