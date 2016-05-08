package projeto.hospital.gerencia.tipo_sanguineo;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import projeto.exceptions.dados.DadoInvalidoException;
import projeto.util.Constantes;

/**
 * Factory singleton de tipos sanguineos
 * 
 * @author Eric
 */
public class TipoSanguineoFactory implements Serializable {
	/**
	 * Id gerado automaticamente
	 */
	private static final long serialVersionUID = -3212537388952139570L;

	private Map<String, Set<String>> mapaTipos;
	private static TipoSanguineoFactory instancia;

	/**
	 * Construtor
	 */
	private TipoSanguineoFactory() {
		this.mapaTipos = iniciaMapa();
	}

	/**
	 * Recupera a instancia da factory
	 * 
	 * @return Instancia da factory
	 */
	public static TipoSanguineoFactory getInstacia() {
		if (instancia == null)
			instancia = new TipoSanguineoFactory();
		return instancia;
	}

	/**
	 * Cria o tipo sanguineo
	 * 
	 * @param tipo
	 *            Tipo
	 * @return Tipo sanguineo criado
	 * @throws DadoInvalidoException
	 *             Caso o tipo seja invalido
	 */
	public TipoSanguineo criaTipo(String tipo) throws DadoInvalidoException {
		if (!Constantes.TIPOS_SANGUINEOS_VALIDOS.contains(tipo))
			throw new DadoInvalidoException("Tipo sanguineo invalido.");

		return new TipoSanguineo(tipo, this.mapaTipos.get(tipo));
	}

	/**
	 * Metodo responsavel por criar o mapa de tipo - tipos compativeis a receber
	 * 
	 * @return Mapa criado
	 */
	private static Map<String, Set<String>> iniciaMapa() {
		Map<String, Set<String>> mapa = new HashMap<>();

		Set<String> doaParaON = new HashSet<>(Arrays.asList(new String[] { "O-" }));
		Set<String> doaParaOP = new HashSet<>(Arrays.asList(new String[] { "O-", "O+" }));
		Set<String> doaParaAN = new HashSet<>(Arrays.asList(new String[] { "O-", "O+", "A-" }));
		Set<String> doaParaAP = new HashSet<>(Arrays.asList(new String[] { "O-", "O+", "A-", "A+" }));
		Set<String> doaParaBN = new HashSet<>(Arrays.asList(new String[] { "O-", "O+", "B-" }));
		Set<String> doaParaBP = new HashSet<>(Arrays.asList(new String[] { "O-", "O+", "B-", "B+" }));
		Set<String> doaParaABN = new HashSet<>(Arrays.asList(new String[] { "O-", "O+", "B-", "B+", "AB-" }));
		Set<String> doaParaABP = new HashSet<>(Arrays.asList(new String[] { "O-", "O+", "B-", "B+", "AB-", "AB+" }));
		mapa.put("O-", doaParaON);
		mapa.put("O+", doaParaOP);
		mapa.put("A-", doaParaAN);
		mapa.put("A+", doaParaAP);
		mapa.put("B-", doaParaBN);
		mapa.put("B+", doaParaBP);
		mapa.put("AB-", doaParaABN);
		mapa.put("AB+", doaParaABP);

		return mapa;
	}
}
