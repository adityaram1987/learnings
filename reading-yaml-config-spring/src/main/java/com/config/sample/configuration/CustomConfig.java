package com.config.sample.configuration;



import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class CustomConfig {
	private Details details;
	
	@Setter
	@Getter
	@NoArgsConstructor
	public static class Details{
		private String name;
		private String phone;
		private Address address;
	}
	
	@Setter
	@Getter
	@NoArgsConstructor
	public static class Address{
		private int flat;
		private String street;
	}
}
