public class Crust {
    String crust;
    double crustPrice;

    public Crust(String crustInput, String crustSize){
        String type = crustInput.trim().toLowerCase();
        String size = crustSize.trim().toLowerCase();

        this.crust = type;
        switch(size){
            case "small" -> crustPrice = 5.00;
            case "medium" -> crustPrice = 7.00;
            case "large" -> crustPrice = 10.00;
            case "extra-large" -> crustPrice = 12.00;
            default -> crustPrice = 7.00; //default medium
        }


        if(type.equals("suffed-crust")){
            crustPrice += 3.00;
        }
    }

    public String getCrust(){
        return crust;
    }

    public double getCrustPrice(){
        return crustPrice;
    }

    @Override
    public String toString(){
        return "Crust: " + crust + " $" + crustPrice;
    }
}