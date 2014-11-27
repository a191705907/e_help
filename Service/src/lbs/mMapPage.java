package lbs;

import com.amap.api.maps.AMap;
import com.amap.api.maps.AMapOptions;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MarkerOptions;

import client.ui.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class mMapPage extends Activity implements OnClickListener {
	private MapView mapView;
	private AMap aMap;
	private UiSettings mUiSettings;
	
	private Bundle bundle;
	private LatLng des;//Ŀ�ĵ�����

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mappage);
		mapView = (MapView) findViewById(R.id.mappage_mapview);
		mapView.onCreate(savedInstanceState);// ����Ҫд

		// Ŀ�ĵ�λ�þ�γ��
		bundle = this.getIntent().getExtras();
		double desla = bundle.getDouble("latitude");
		double deslo = bundle.getDouble("longitude");
		des = new LatLng(desla,deslo);
		
		init();
	}

	private void init() {
		if (aMap == null) {
			aMap = mapView.getMap();
			mUiSettings = aMap.getUiSettings();
			setUpMap();
		}
		Button mappage_walk = (Button) findViewById(R.id.mappage_walk);
		mappage_walk.setOnClickListener(this);
		Button mappage_drive = (Button) findViewById(R.id.mappage_drive);
		mappage_drive.setOnClickListener(this);
		Button mappage_transit = (Button) findViewById(R.id.mappage_transit);
		mappage_transit.setOnClickListener(this);
	}

	/**
	 * amap���һЩ�¼�������
	 */
	private void setUpMap() {
		// ���õ�ͼlogo��ʾ�����·�
		mUiSettings.setLogoPosition(AMapOptions.LOGO_POSITION_BOTTOM_LEFT);
		// ����Ĭ�����ű���
		aMap.moveCamera(CameraUpdateFactory.zoomTo((float) 16));
		// ����Ĭ�ϷŴ���С��ť�Ƿ���ʾ
		mUiSettings.setZoomControlsEnabled(false);
		// ����ָ�����Ƿ���ʾ
		mUiSettings.setCompassEnabled(true);

		// �ڱ��λ�������
		aMap.addMarker(new MarkerOptions().position(des).icon(
				BitmapDescriptorFactory
						.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
		//����ǵص���ʾ����Ļ��
		aMap.animateCamera(CameraUpdateFactory
				.newCameraPosition(new CameraPosition(des, 16,
						30, 0)));
	}

	/**
	 * ����������д
	 */
	@Override
	protected void onResume() {
		super.onResume();
		mapView.onResume();
	}

	/**
	 * ����������д
	 */
	@Override
	protected void onPause() {
		super.onPause();
		mapView.onPause();
	}

	/**
	 * ����������д
	 */
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		mapView.onSaveInstanceState(outState);
	}

	/**
	 * ����������д
	 */
	@Override
	protected void onDestroy() {
		super.onDestroy();
		mapView.onDestroy();
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {

		case R.id.mappage_walk:
			break;

		case R.id.mappage_drive:
			break;

		case R.id.mappage_transit:
			break;
		}
	}
}
