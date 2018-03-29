package cordova.plugin.DeviceInfo;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.provider.Settings;

import android.telephony.TelephonyManager;

/**
 * This class echoes a string called from JavaScript.
 */
public class DeviceInfo extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("deviceInfo")) {
            JSONObject r = new JSONObject();
            r.put("version", this.getOSVersion());
            r.put("model", this.getModel());
            r.put("manufacturer", this.getManufacturer());
            r.put("imei",this.getDeviceImei());
            r.put("type",this.getPhoneType());
            r.put("Imeis",this.getDeviceImeis());
            r.put("count",this.getDeviceCount());
            callbackContext.success(r);
        }
        else{
            return false;
        }
        return true;
    }
    //是否为双卡手机
    public String getDeviceCount(){
        String count=null;
        TelephonyManager telephonyManager= (TelephonyManager) cordova.getActivity().getSystemService(cordova.getActivity().TELEPHONY_SERVICE);
        count = telephonyManager.getPhoneCount()+"";
        return count;
    }

    //双IMEI
    public String getDeviceImeis(){
        String imeiS=null;
        TelephonyManager telephonyManager= (TelephonyManager) cordova.getActivity().getSystemService(cordova.getActivity().TELEPHONY_SERVICE);
        imeiS = telephonyManager.getDeviceId(2);
        return imeiS;
    }
    //获取手机信息传输类型
    public String getPhoneType(){
        String phoneType=null;
        TelephonyManager telephonyManager= (TelephonyManager) cordova.getActivity().getSystemService(cordova.getActivity().TELEPHONY_SERVICE);
        int type = telephonyManager.getPhoneType();
        switch (type){
            case TelephonyManager.PHONE_TYPE_CDMA:
                phoneType="CDMA";
                break;
            case TelephonyManager.PHONE_TYPE_GSM:
                phoneType="GSM";
                break;
            case TelephonyManager.PHONE_TYPE_SIP:
                phoneType="SIP";
                break;
            case TelephonyManager.PHONE_TYPE_NONE:
                phoneType="NONE";
                break;
        }
        return phoneType;
    }
    //获取手机IMEI
    public String getDeviceImei(){
        String deviceImei;
        TelephonyManager telephonyManager= (TelephonyManager) cordova.getActivity().getSystemService(cordova.getActivity().TELEPHONY_SERVICE);
        deviceImei = telephonyManager.getDeviceId();
        return deviceImei;
    }
    //手机品牌
    public String getManufacturer() {
        String manufacturer = android.os.Build.MANUFACTURER;
        return manufacturer;
    }
    //手机型号
    public String getModel() {
        String model = android.os.Build.MODEL;
        return model;
    }
    //安卓版本
    public String getOSVersion() {
        String osversion = android.os.Build.VERSION.RELEASE;
        return osversion;
    }

    private void deviceInfo(String message, CallbackContext callbackContext) {
        if (message != null && message.length() > 0) {
            callbackContext.success(message);
        } else {
            callbackContext.error("Expected one non-empty string argument.");
        }
    }
}
