class Payment {
    private String paymentType;
    private boolean isCredit;
    private String cardNumber;

    // Return Methods:
    public String getPaymentType() {
        return paymentType;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public boolean getIsCredit() {
        return isCredit;
    }

    // Mutator Methods:
    private void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    private void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    private void setIsCredit(boolean isCredit) {
        this.isCredit = isCredit;
    }

    // Constructors:
    // Cash
    public Payment(String paymentType) {
        this.paymentType = paymentType;
        this.isCredit = false;
        this.cardNumber = "";
    }

    // Credit / debit
    public Payment(String paymentType, String cardNumber) {
        this.paymentType = paymentType;
        this.cardNumber = cardNumber;
        this.isCredit = true;
    }

    // Payment creation method:
    // Note: used to streamline creation
    public Payment createPayment(boolean isCredit, String cardNumber, String paymentType) {
        if (!isCredit) {
            return new Payment("Cash");
        } else {
            return new Payment(paymentType, cardNumber);
        }
    }

    @Override
    public String toString() {
        if (isCredit) {
            return paymentType + " (card ending in " +
                    (cardNumber.length() >= 4 ? cardNumber.substring(cardNumber.length() - 4) : cardNumber) + ")";
        } else {
            return paymentType;
        }
    }
}