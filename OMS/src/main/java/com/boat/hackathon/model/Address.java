package com.boat.hackathon.model;

public class Address {
	
	    private String region;

	    public String getRegion() {
			return region;
		}

		public void setRegion(String region) {
			this.region = region;
		}

		public String getStreetAddress() {
			return streetAddress;
		}

		public void setStreetAddress(String streetAddress) {
			this.streetAddress = streetAddress;
		}

		public String getPostalCode() {
			return postalCode;
		}

		public void setPostalCode(String postalCode) {
			this.postalCode = postalCode;
		}

		public String getExtendedAddress() {
			return extendedAddress;
		}

		public void setExtendedAddress(String extendedAddress) {
			this.extendedAddress = extendedAddress;
		}

		public String getLocality() {
			return locality;
		}

		public void setLocality(String locality) {
			this.locality = locality;
		}

		private String streetAddress;

	    private String postalCode;

	    private String extendedAddress;

	    private String locality;

}
