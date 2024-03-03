package model;

// TODO: Auto-generated Javadoc
/**
 * The Class JavaBeans.
 */
public class JavaBeans {
	
	/** The idcon. */
	private String idcon;
	
	/** The nome. */
	private String nome;
	
	/** The telemovel. */
	private String telemovel;
	
	/** The email. */
	private String email;
	
	/**
	 * Instantiates a new java beans.
	 */
	// Constructors
	public JavaBeans() { super(); }
	
	/**
	 * Instantiates a new java beans.
	 *
	 * @param idcon the idcon
	 * @param nome the nome
	 * @param telemovel the telemovel
	 * @param email the email
	 */
	public JavaBeans(String idcon, String nome, String telemovel, String email) {
		super();
		this.idcon = idcon;
		this.nome = nome;
		this.telemovel = telemovel;
		this.email = email;
	}

	/**
	 * Gets the idcon.
	 *
	 * @return the idcon
	 */
	// Getters and Setters
	public String getIdcon() { return idcon; }
	
	/**
	 * Sets the idcon.
	 *
	 * @param idcon the new idcon
	 */
	public void setIdcon(String idcon) { this.idcon = idcon; }
	
	/**
	 * Gets the nome.
	 *
	 * @return the nome
	 */
	public String getNome() { return nome; }
	
	/**
	 * Sets the nome.
	 *
	 * @param nome the new nome
	 */
	public void setNome(String nome) { this.nome = nome; }
	
	/**
	 * Gets the telemovel.
	 *
	 * @return the telemovel
	 */
	public String getTelemovel() { return telemovel; }
	
	/**
	 * Sets the telemovel.
	 *
	 * @param telemovel the new telemovel
	 */
	public void setTelemovel(String telemovel) { this.telemovel = telemovel; }
	
	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() { return email; }
	
	/**
	 * Sets the email.
	 *
	 * @param email the new email
	 */
	public void setEmail(String email) { this.email = email; }
}
