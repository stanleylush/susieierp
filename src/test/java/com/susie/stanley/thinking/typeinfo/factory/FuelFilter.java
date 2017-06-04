package com.susie.stanley.interview.typeinfo.factory;

public class FuelFilter extends Filter {

	public static class Factory implements com.susie.stanley.interview.typeinfo.factory.Factory<FuelFilter> {

		@Override
		public FuelFilter create() {
			return new FuelFilter();
		}

	}
}
