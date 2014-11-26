package lbs;

/**
 * Location������
 * ����ģʽ
 * ���ڻ�ȡ�û���λ����Ϣ
 * @author Jeese
 */
public class mLocation {
	private double geoLat = (Double) null;// γ��
	private double geoLng = (Double) null;// ����
	private String province = null; // ʡ����
	private String city = null; // ��������
	private String city_code = null; // ���б���
	private String district = null;// ��������
	private String ad_code = null;// �������
	private String street = null;// �ֵ���������Ϣ
	private String address = null;// ��ϸ��ַ
	
	private boolean success = false;//�Ƿ�λ�ɹ�

	/*
	 * ����ģʽ
	 */
	private static mLocation location = null;
	public static mLocation getInstance() {
		if (location == null) {
			location = new mLocation();
		}
		return location;
	}
	
	/*******     get     *************/
	public boolean getSuccess(){
		return success;
	}
	public double getGeoLat(){
		return geoLat;
	}
	public double getGeoLng(){
		return geoLng;
	}
	public String getProvince(){
		return province;
	}
	public String getCity(){
		return city;
	}
	public String getCityCode(){
		return city_code;		
	}
	public String getDistrict(){
		return district;		
	}
	public String getAdCode(){
		return ad_code;	
	}
	public String getStreet(){
		return street;		
	}
	public String getAddress(){
		return address;		
	}
	/*******     get     *************/
	
	/*******     set     *************/
	public void setSuccess(boolean set){
		success = set;
	}
	public void setGeoLat(double set){
		geoLat = set;
	}
	public void setGeoLng(double set){
		geoLng = set;
	}
	public void setProvince(String set){
		province = set;
	}
	public void setCity(String set){
		city = set;
	}
	public void setCityCode(String set){
		city_code = set;		
	}
	public void setDistrict(String set){
		district = set;		
	}
	public void setAdCode(String set){
		ad_code = set;
	}
	public void setStreet(String set){
		street = set;	
	}
	public void setAddress(String set){
		address = set;		
	}
	/*******     set     *************/
}
