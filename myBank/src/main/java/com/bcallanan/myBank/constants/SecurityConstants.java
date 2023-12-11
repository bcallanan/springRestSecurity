package com.bcallanan.myBank.constants;

public interface SecurityConstants {

	// Token generation: @see https://mojitocoder.medium.com/generate-a-random-jwt-secret-22a89e8be00d
	// node -e "console.log(require('crypto').randomBytes(32).toString('hex'))"
	//public static final String JWT_KEY = "fbffe0e780b33df66a171c04af338a28fec6249505cc72043582620b46473d30";
    public static final String JWT_KEY = "jxgEQeXHuPq8VdbyYFNkANdudQ53YUn4";
	public static final String JWT_HEADER = "Authorization";

}
