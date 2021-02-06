/**
 * 
 */
package es.us.connector;

import org.bonitasoft.engine.filter.AbstractUserFilter;
import org.bonitasoft.engine.connector.ConnectorValidationException;

/**
 * This abstract class is generated and should not be modified.
 */
public abstract class AbstractFiltro_actor_usuarios_de_grupoImpl extends AbstractUserFilter {

	protected final static String ID_GRUPO_INPUT_PARAMETER = "id_grupo";

	protected final java.lang.Long getId_grupo() {
		return (java.lang.Long) getInputParameter(ID_GRUPO_INPUT_PARAMETER);
	}

	@Override
	public void validateInputParameters() throws ConnectorValidationException {
		try {
			getId_grupo();
		} catch (ClassCastException cce) {
			throw new ConnectorValidationException("id_grupo type is invalid");
		}

	}

}
