package com.varun.springdemo;

public class TrackCoach implements Coach {
	
	private FortuneService fortuneService;
	
	
	public TrackCoach(FortuneService fortuneService) {
		super();
		this.fortuneService = fortuneService;
	}

	@Override
	public String getDailyWorkout() {
		
		return "Run a hard 5k";
	}

	@Override
	public String getDailyFortune() {
		return "Just do it! "+this.fortuneService.getFortune();
	}

}
