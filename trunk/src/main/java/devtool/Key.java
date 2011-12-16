package devtool;

public class Key {

    private String category;
    private String product;
    private String version;
    private String subject;
    
    /**
     * @param category
     * @param product
     * @param version
     * @param subject
     */
    public Key(String category, String product, String version, String subject) {
        super();
        this.category = category;
        this.product = product;
        this.version = version;
        this.subject = subject;
    }
    
    /**
     * @return the category
     */
    public String getCategory() {
        return this.category;
    }
    
    /**
     * @param category the category to set
     */
    public void setCategory(String category) {
        this.category = category;
    }
    
    /**
     * @return the product
     */
    public String getProduct() {
        return this.product;
    }
    
    /**
     * @param product the product to set
     */
    public void setProduct(String product) {
        this.product = product;
    }
    
    /**
     * @return the version
     */
    public String getVersion() {
        return this.version;
    }
    
    /**
     * @param version the version to set
     */
    public void setVersion(String version) {
        this.version = version;
    }
    
    /**
     * @return the subject
     */
    public String getSubject() {
        return this.subject;
    }
    
    /**
     * @param subject the subject to set
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((category == null) ? 0 : category.hashCode());
        result = prime * result + ((product == null) ? 0 : product.hashCode());
        result = prime * result + ((subject == null) ? 0 : subject.hashCode());
        result = prime * result + ((version == null) ? 0 : version.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Key other = (Key) obj;
        if (category == null) {
            if (other.category != null)
                return false;
        } else if (!category.equals(other.category))
            return false;
        if (product == null) {
            if (other.product != null)
                return false;
        } else if (!product.equals(other.product))
            return false;
        if (subject == null) {
            if (other.subject != null)
                return false;
        } else if (!subject.equals(other.subject))
            return false;
        if (version == null) {
            if (other.version != null)
                return false;
        } else if (!version.equals(other.version))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Key [category=" + category + ", product=" + product + ", version=" + version
                + ", subject=" + subject + "]";
    }
    
}
