package com.meta.verse.designPattern.creationalPattern.factoryDesignPattern;

abstract class Plan {
    protected double rate;
    abstract void getRate();

    public void calculateBill(int units){
        System.out.println(units*rate);
    }
}//end of plan class

class DomesticPlan extends Plan{

    @Override
    void getRate() {
        rate = 3.50;
    }
}
class CommercialPlan extends Plan{

    @Override
    void getRate() {
        rate= 7.50;
    }
}
class InstitutionalPlan extends Plan{

    @Override
    void getRate() {
        rate = 10.50;
    }
}

class GetPlanFatory {
    //use getPlan method to get object of type Plan
    public Plan getPlan(String planType) {
        if (planType == null) {
            return null;
        }
        if(planType.equalsIgnoreCase("DomesticPlan")){
            return new DomesticPlan();
        }
        else if(planType.equalsIgnoreCase("COMMERCIALPLAN")){
            return new CommercialPlan();
        }
        else if(planType.equalsIgnoreCase("INSTITUTIONALPLAN")) {
            return new InstitutionalPlan();
        }
        return null;
    }
}
