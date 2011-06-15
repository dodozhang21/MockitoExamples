package net.pureessence.domain;

public class Key {
    private Integer division;
    private Integer state;
    private Integer agency;
    private String policySymbol;
    private Integer policyNumber;
    
    
    
    public Key(Integer division, Integer state, Integer agency,
	    String policySymbol, Integer policyNumber) {
	super();
	this.division = division;
	this.state = state;
	this.agency = agency;
	this.policySymbol = policySymbol;
	this.policyNumber = policyNumber;
    }
    
    public Integer getDivision() {
        return division;
    }
    public void setDivision(Integer division) {
        this.division = division;
    }
    public Integer getState() {
        return state;
    }
    public void setState(Integer state) {
        this.state = state;
    }
    public Integer getAgency() {
        return agency;
    }
    public void setAgency(Integer agency) {
        this.agency = agency;
    }
    public String getPolicySymbol() {
        return policySymbol;
    }
    public void setPolicySymbol(String policySymbol) {
        this.policySymbol = policySymbol;
    }
    public Integer getPolicyNumber() {
        return policyNumber;
    }
    public void setPolicyNumber(Integer policyNumber) {
        this.policyNumber = policyNumber;
    }
    public String getKeyString() {
	String keyString = String.format("division '%s', state '%s', agency '%s', policySymbol '%s', policyNumber '%s'",
            		getDivision(),
            		getState(),
            		getAgency(),
            		getPolicySymbol(),
            		getPolicyNumber()
		);
	return keyString;
    }
}
