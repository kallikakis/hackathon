package com.doclerholding.hackaton.data.model.airquality;

/**
 * Created by maxim on 3/11/2017.
 */
public enum AirQualityIndexEnum {
	GOOD(6), SATISFACTORY(5), MODERATELY_POLLUTED(4), POOR(3), VERY_POOR(2), SEVERE(1);

	private final Integer level;

	AirQualityIndexEnum(Integer level) {
		this.level = level;
	}

	public static AirQualityIndexEnum valueOf(double pm10,/*[0,50];[51,100];[101,250];[251,350];[351,430]; 430+ */
	                                   double no2,/* [0,40];[41,80]; [81,180]; [181,280];[281,400]; 400+*/
	                                   double o3,/*  [0,50];[51,100];[101,168];[169,208];[209,748]; 748+*/
	                                   double so2,/* [0,40];[41,80]; [81,380]; [381,800];[801,1600];1600+*/
	                                   double co/*   [0,1]; [1.1,2]; [2.1,10]; [10,17];  [17,34];   34+*/) {
		if (pm10 > 430 || no2 > 400 || o3 > 748 || so2 > 1600 || co > 34) {
			return SEVERE;
		} else if ((pm10 <= 430 && pm10 > 350) || (no2 <= 400 && no2 > 280) || (o3 <= 748 && o3 > 208)
				|| (so2 <= 1600 && so2 > 800) || (co <= 34 && co > 17)) {
			return VERY_POOR;
		} else if ((pm10 <= 350 && pm10 > 250) || (no2 <= 280 && no2 > 180) || (o3 <= 208 && o3 > 168)
				|| (so2 <= 800 && so2 > 380) || (co <= 17 && co > 10)) {
			return POOR;
		} else if ((pm10 <= 250 && pm10 > 100) || (no2 <= 180 && no2 > 80) || (o3 <= 168 && o3 > 100)
				|| (so2 <= 380 && so2 > 80) || (co <= 10 && co > 2)) {
			return MODERATELY_POLLUTED;
		} else if ((pm10 <= 100 && pm10 > 50) || (no2 <= 80 && no2 > 40) || (o3 <= 100 && o3 > 50)
				|| (so2 <= 80 && so2 > 40) || (co <= 2 && co > 1)) {
			return SATISFACTORY;
		} else if (pm10 <= 50 || no2 <= 40 || o3 <= 50 || so2 <= 40 || co <= 1) {
			return GOOD;
		} else {
			return null;
		}
	}
}
