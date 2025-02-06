package com.pjsh.onlinemovierental.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class RentalLoggingAspect {
    @Before("execution(* com.pjsh.onlinemovierental.services.RentalService.rentVideo(..))")
    public void logBeforeRentVideo() {
        System.out.println("< CREATING NEW VIDEO RENTAL >");
    }

    @Before("execution(* com.pjsh.onlinemovierental.services.RentalService.getRentals(..))")
    public void logBeforeGetRentals() {
        System.out.println("< RETRIEVING ALL CUSTOMER RENTALS >");
    }

    @Before("execution(* com.pjsh.onlinemovierental.services.RentalService.getActiveRentals(..))")
    public void logBeforeGetAllActiveRentals() {
        System.out.println("< RETRIEVING ALL ACTIVE RENTALS >");
    }

    @Before("execution(* com.pjsh.onlinemovierental.services.RentalService.getActiveRentals(..))")
    public void logBeforeGetActiveRentals() {
        System.out.println("< RETRIEVING ALL ACTIVE CUSTOMER RENTALS >");
    }

    @Before("execution(* com.pjsh.onlinemovierental.services.RentalService.getActiveRentalsCount(..))")
    public void logBeforeGetActiveRentalsCount() {
        System.out.println("< COUNTING ALL ACTIVE CUSTOMER RENTALS >");
    }

    @Before("execution(* com.pjsh.onlinemovierental.services.RentalService.checkRental(..))")
    public void logBeforeCheckRental() {
        System.out.println("< INITIATING RENTAL VERIFICATION PROCESS >");
    }

    @Before("execution(* com.pjsh.onlinemovierental.services.RentalService.returnVideo(..))")
    public void logBeforeReturnVideo() {
        System.out.println("< RETURNING VIDEO RENTAL >");
    }
}
