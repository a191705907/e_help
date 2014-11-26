package lbs;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.LocationManagerProxy;
import com.amap.api.location.LocationProviderProxy;

import android.app.Service;
import android.content.Intent;
import android.location.Location;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.Toast;

/**
 * ��λ���񣬴�appʱ�������ر�appʱ�ر� ����app����ʱbindservice ��app�ر�ʱunbindservice
 * 
 * @author Jeese
 * 
 */
public class LocationService extends Service implements AMapLocationListener {

	private LocationManagerProxy mLocationManagerProxy = null;

	@Override
	public void onCreate() {
		super.onCreate();
		init();
	}

	@Override
	public void onDestroy() {
		if (mLocationManagerProxy != null) {
			mLocationManagerProxy.removeUpdates(this);
			mLocationManagerProxy.destroy();
		}
		mLocationManagerProxy = null;
		// ���ö�λ����
		mLocation.getInstance().setSuccess(false);
		super.onDestroy();
	}

	/**
	 * ��ʼ����λ
	 */
	private void init() {

		mLocationManagerProxy = LocationManagerProxy.getInstance(this);

		// �˷���Ϊÿ���̶�ʱ��ᷢ��һ�ζ�λ����Ϊ�˼��ٵ������Ļ������������ģ�
		// ע�����ú��ʵĶ�λʱ��ļ���������ں���ʱ�����removeUpdates()������ȡ����λ����
		// �ڶ�λ�������ں��ʵ��������ڵ���destroy()����
		// ����������ʱ��Ϊ-1����λֻ��һ��
		mLocationManagerProxy.requestLocationData(
				LocationProviderProxy.AMapNetwork, 60 * 1000, 15, this);

		mLocationManagerProxy.setGpsEnable(false);
	}

	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onLocationChanged(AMapLocation amapLocation) {
		if (amapLocation != null
				&& amapLocation.getAMapException().getErrorCode() == 0) {
			// ����λ����ϸ��Ϣ			
			mLocation.getInstance().setGeoLat(amapLocation.getLatitude());
			mLocation.getInstance().setGeoLng(amapLocation.getLongitude());
			mLocation.getInstance().setProvince(amapLocation.getProvince());
			mLocation.getInstance().setCity(amapLocation.getCity());
			mLocation.getInstance().setCityCode(amapLocation.getCityCode());
			mLocation.getInstance().setDistrict(amapLocation.getDistrict());
			mLocation.getInstance().setAddress(amapLocation.getAddress());
			mLocation.getInstance().setStreet(amapLocation.getStreet());
			mLocation.getInstance().setAdCode(amapLocation.getAdCode());

			// ���ö�λ�ɹ�
			mLocation.getInstance().setSuccess(true);

			Toast.makeText(
					getApplicationContext(),"ubbhubhuhbu", Toast.LENGTH_LONG)
					.show();
		}
	}

	// �󶨷��񣬷���app�ر�ʱ�رն�λ����
	private MyBinder myBinder = new MyBinder();

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return myBinder;
	}

	public class MyBinder extends Binder {

		public LocationService getService() {
			return LocationService.this;
		}
	}

}
