package net.pureessence.domain;

public class Key {
	private Integer state;
	private Integer postalCode;
	private String storeSymbol;
	private Integer storeNumber;

	public Key(Integer state, Integer postalCode, String storeSymbol, Integer storeNumber) {
		super();
		this.state = state;
		this.postalCode = postalCode;
		this.storeSymbol = storeSymbol;
		this.storeNumber = storeNumber;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(Integer postalCode) {
		this.postalCode = postalCode;
	}

	public String getStoreSymbol() {
		return storeSymbol;
	}

	public void setStoreSymbol(String storeSymbol) {
		this.storeSymbol = storeSymbol;
	}

	public Integer getStoreNumber() {
		return storeNumber;
	}

	public void setStoreNumber(Integer storeNumber) {
		this.storeNumber = storeNumber;
	}

	public String getKeyString() {
		String keyString = String
				.format("state '%s', postalCode '%s', storeSymbol '%s', storeNumber '%s'",
						getState(),
						getPostalCode(),
						getStoreSymbol(),
						getStoreNumber());
		return keyString;
	}
}
