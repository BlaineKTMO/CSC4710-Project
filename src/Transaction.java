public class Transaction {
    private int transid;
    private String origin;
    private int nftid;
    private int transtype;
    private String timestamp;
    private Double price;
    private String recipient;
    
    private String nftName;


	public int getTransid() {
        return transid;
    }

    public void setTransid(int transid) {
        this.transid = transid;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public int getNftid() {
        return nftid;
    }

    public void setNftid(int nftid) {
        this.nftid = nftid;
    }

    public int getTranstype() {
        return transtype;
    }

    public void setTranstype(int transtype) {
        this.transtype = transtype;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
    
    public String getRecipient() {
    	return recipient;
    }

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}
	
    public String getNftName() {
		return nftName;
	}

	public void setNftName(String nftName) {
		this.nftName = nftName;
	}


}