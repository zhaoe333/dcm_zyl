package com.cm.common.utils;

import java.util.ArrayList;
import java.util.List;

public class LocationUtils {
	static double a = 6378245.0;
	static double ee = 0.00669342162296594323;
	private static final double x_pi = 3000.0 * Math.PI / 180.0;

	public static double GPS2WGS84(double val) {
		int d = (int) Math.floor(val);
		double f = val - d;
		return d + f * 100 / 60;
	}

	public static double[] transformFromGCJToBD09(double latitude, double longitude) {
		double z = Math.sqrt(longitude * longitude + latitude * latitude) + 0.00002 * Math.sin(latitude * x_pi);
		double theta = Math.atan2(latitude, longitude) + 0.000003 * Math.cos(longitude * x_pi);
		double lon = z * Math.cos(theta) + 0.0065;
		double lat = z * Math.sin(theta) + 0.006;
		return new double[] { lat, lon };
	}

	public static double[] transformFromBD09ToGCJ(double latitude, double longitude) {
		double x = longitude - 0.0065, y = latitude - 0.006;
		double z = Math.sqrt(x * x + y * y) - 0.00002 * Math.sin(y * x_pi);
		double theta = Math.atan2(y, x) - 0.000003 * Math.cos(x * x_pi);
		double lon = z * Math.cos(theta);
		double lat = z * Math.sin(theta);
		return new double[] { lat, lon };
	}

	public static double[] transformFromWGSToGCJ(double latitude, double longitude) {

		// 如果在国外，则默认不进行转换
		if (outOfChina(latitude, longitude)) {
			return new double[] { latitude, longitude };
		}
		double dLat = transformLat(longitude - 105.0, latitude - 35.0);
		double dLon = transformLon(longitude - 105.0, latitude - 35.0);
		double radLat = latitude / 180.0 * Math.PI;
		double magic = Math.sin(radLat);
		magic = 1 - ee * magic * magic;
		double sqrtMagic = Math.sqrt(magic);
		dLat = (dLat * 180.0) / ((a * (1 - ee)) / (magic * sqrtMagic) * Math.PI);
		dLon = (dLon * 180.0) / (a / sqrtMagic * Math.cos(radLat) * Math.PI);

		return new double[] { latitude + dLat, longitude + dLon };
	}

	public static double transformLat(double x, double y) {
		double ret = -100.0 + 2.0 * x + 3.0 * y + 0.2 * y * y + 0.1 * x * y + 0.2 * Math.sqrt(x > 0 ? x : -x);
		ret += (20.0 * Math.sin(6.0 * x * Math.PI) + 20.0 * Math.sin(2.0 * x * Math.PI)) * 2.0 / 3.0;
		ret += (20.0 * Math.sin(y * Math.PI) + 40.0 * Math.sin(y / 3.0 * Math.PI)) * 2.0 / 3.0;
		ret += (160.0 * Math.sin(y / 12.0 * Math.PI) + 320 * Math.sin(y * Math.PI / 30.0)) * 2.0 / 3.0;
		return ret;
	}

	public static double transformLon(double x, double y) {
		double ret = 300.0 + x + 2.0 * y + 0.1 * x * x + 0.1 * x * y + 0.1 * Math.sqrt(x > 0 ? x : -x);
		ret += (20.0 * Math.sin(6.0 * x * Math.PI) + 20.0 * Math.sin(2.0 * x * Math.PI)) * 2.0 / 3.0;
		ret += (20.0 * Math.sin(x * Math.PI) + 40.0 * Math.sin(x / 3.0 * Math.PI)) * 2.0 / 3.0;
		ret += (150.0 * Math.sin(x / 12.0 * Math.PI) + 300.0 * Math.sin(x / 30.0 * Math.PI)) * 2.0 / 3.0;
		return ret;
	}

	public static boolean outOfChina(double lat, double lon) {
		if (lon < 72.004 || lon > 137.8347)
			return true;
		if (lat < 0.8293 || lat > 55.8271)
			return true;
		return false;
	}

	// 经纬度转墨卡托
	// 经度(lon)，纬度(lat)
	public static double[] lonLat2Mercator(double lon, double lat) {
		double[] xy = new double[2];
		double x = lon * 20037508.342789 / 180;
		double y = Math.log(Math.tan((90 + lat) * Math.PI / 360)) / (Math.PI / 180);
		y = y * 20037508.34789 / 180;
		xy[0] = x;
		xy[1] = y;
		return xy;
	}

	// 墨卡托转经纬度

	public static double[] mercator2lonLat(double mercatorX, double mercatorY) {
		double[] xy = new double[2];
		double x = mercatorX / 20037508.34 * 180;
		double y = mercatorY / 20037508.34 * 180;
		y = 180 / Math.PI * (2 * Math.atan(Math.exp(y * Math.PI / 180)) - Math.PI / 2);
		xy[0] = x;
		xy[1] = y;
		return xy;
	}

	/**
	 * 火星转百度
	 * 
	 * @param gg_lat
	 * @param gg_lon
	 * @param bd_lat
	 * @param bd_lon
	 */
	public static String bd_encrypt(double gg_lat, double gg_lon, double bd_lat, double bd_lon) {
		double x = gg_lon, y = gg_lat;
		double z = Math.sqrt(x * x + y * y) + 0.00002 * Math.sin(y * x_pi);
		double theta = Math.atan2(y, x) + 0.000003 * Math.cos(x * x_pi);
		bd_lon = z * Math.cos(theta) + 0.0065;
		bd_lat = z * Math.sin(theta) + 0.006;
		return bd_lon + "," + bd_lat;
	}

	/**
	 * 百度转火星
	 * 
	 * @param bd_lat
	 * @param bd_lon
	 * @param gg_lat
	 * @param gg_lon
	 */
	public static String bd_decrypt(double bd_lat, double bd_lon, double gg_lat, double gg_lon) {
		double x = bd_lon - 0.0065, y = bd_lat - 0.006;
		double z = Math.sqrt(x * x + y * y) - 0.00002 * Math.sin(y * x_pi);
		double theta = Math.atan2(y, x) - 0.000003 * Math.cos(x * x_pi);
		gg_lon = z * Math.cos(theta);
		gg_lat = z * Math.sin(theta);
		return gg_lon + "," + gg_lat;
	}

	/**
	 * 坐标数组(前端传)转坐标字符串(系统内部)
	 * [119.123456,39.654321] ==> "119.123456,39.654321"
	 * @param point
	 * @return
	 */
	public static String pointToLocationStr(Double[] point) throws Exception {
		if (point == null) {
			return null;
		}
		return String.format("%.5f,%.5f", point[0], point[1]);
	}

	/**
	 * 坐标字符串(系统内部)转坐标数组(给前端)
	 * "119.123456,39.654321" ==> [119.123456,39.654321]
	 * @param str
	 * @return
	 * @throws Exception
	 */
	public static Double[] locationStrToPoint(String str) throws Exception {
		if (str == null) {
			return null;
		}
		String[] s = str.split(",");
		Double[] d = new Double[2];
		d[0] = Double.parseDouble(s[0]);
		d[1] = Double.parseDouble(s[1]);
		return d;
	}

	/**
	 * 坐标数组(前端传)转坐标字符串(系统内部)
	 * [[119.123456,39.654321], [119.123456,39.654321]] ==> "119.123456,39.654321|119.123456,39.654321"
	 * @param points
	 * @return
	 */
	public static String pointsToLocationStr(List<Double[]> points) throws Exception {
		if (points != null) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < points.size(); i++) {
				Double[] p = points.get(i);
				sb.append(p[0]);
				sb.append(",");
				sb.append(p[1]);
				if (i < points.size() - 1) {
					sb.append("|");
				}
			}
			return sb.toString();
		}
		return "";
	}

	/**
	 * 坐标字符串(系统内部)转坐标数组(给前端)
	 * "119.123456,39.654321|119.123456,39.654321" ==> [[119.123456,39.654321], [119.123456,39.654321]]
	 * @param str
	 * @return
	 */
	public static List<Double[]> locationStrToPoints(String str) throws Exception {
		if (str != null && !str.isEmpty()) {
			String[] strPoints = str.split("\\|");
			List<Double[]> points = new ArrayList<>();
			for (int i = 0; i < strPoints.length; i++) {
				String[] strp = strPoints[i].split(",");
				Double[] p = new Double[2];
				p[0] = Double.parseDouble(strp[0]);
				p[1] = Double.parseDouble(strp[1]);
				points.add(p);
			}
			return points;
		}
		return null;
	}

	public static void main(String[] args) throws Exception {
		Double[] dd = new Double[2];
		dd[0] = 119.123456;
		dd[1] = 39.654321;
		String str = pointToLocationStr(dd);
		System.out.println(str);

		System.out.println("//////////////////");
		Double[] ddd = locationStrToPoint("119.123456,39.654321");
		System.out.println(ddd[0] + ", " + ddd[1]);

		System.out.println("//////////////////");
		List<Double[]> list1 = new ArrayList<>();
		list1.add(dd);
		list1.add(ddd);
		list1.add(dd);
		str = pointsToLocationStr(list1);
		System.out.println(str);

		System.out.println("//////////////////");
		List<Double[]> list2 = locationStrToPoints(str);
		for (Double[] d : list2) {
			System.out.println(d[0] + ", " + d[1]);
		}
	}
}
