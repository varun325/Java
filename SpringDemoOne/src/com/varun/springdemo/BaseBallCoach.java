package com.varun.springdemo;

public class BaseBallCoach implements Coach {
	
	//Define a private field for the dependency
	
	private FortuneService fortuneService;
	
	//Define a constructor for dependency injection
	
	public BaseBallCoach(FortuneService theFortuneService) {
		this.fortuneService = theFortuneService;
	}
	
	@Override
	public String getDailyWorkout() {
		return "Spend 30 minutes on batting practice";
	}

	@Override
	public String getDailyFortune() {
		// use the fortune service to get a fortune
		
		return this.fortuneService.getFortune();
	}
	
	
}
