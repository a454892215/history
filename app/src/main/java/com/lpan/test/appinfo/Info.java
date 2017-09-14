package com.lpan.test.appinfo;

import java.util.Arrays;
/**
 * The type Info.
 */
public class Info {

    /**
     * The type Custom.
     */
    public static class Custom {

        /**
         * The Key.
         */
        public String key;

        /**
         * The Value.
         */
        public String value;
    }

    /**
     * The type Cert.
     */
    public static class Cert {

        /**
         * The Cert id.
         */
        public String certId;

        /**
         * The Cert name.
         */
        public String certName;

        /**
         * The Cert pwd.
         */
        public String certPwd;

        /**
         * The Cert type.
         */
        public String certType;

        /**
         * The Cert url.
         */
        public String certUrl;
    }

    /**
     * The type Basic info.
     */
    public static class BasicInfo {

        /**
         * The Uuid.
         */
        public String uuid;

        /**
         * The Imei.
         */
        public String imei;

        /**
         * The Root.
         */
        public boolean root;

        /**
         * The Kernel version.
         */
        public String kernel_version;
    }

    /**
     * The type Sdcard info.
     */
    public static class SdcardInfo {

        /**
         * The Cid.
         */
        public String cid;

        /**
         * The Total space.
         */
        public long total_space;

        /**
         * The Usable space.
         */
        public long usable_space;

        /**
         * The Path.
         */
        public String path;
    }

    /**
     * The type Os info.
     */
    public static class OsInfo {

        /**
         * The Model.
         */
        public String model;

        /**
         * The Manufacturer.
         */
        public String manufacturer;

        /**
         * The Board.
         */
        public String board;

        /**
         * The Fingerprint.
         */
        public String fingerprint;

        /**
         * The Bootloader.
         */
        public String bootloader;

        /**
         * The Brand.
         */
        public String brand;

        /**
         * The Cpu abi.
         */
        public String cpu_abi;

        /**
         * The Cpu abi 2.
         */
        public String cpu_abi2;

        /**
         * The Device.
         */
        public String device;

        /**
         * The Display.
         */
        public String display;

        /**
         * The Hardware.
         */
        public String hardware;

        /**
         * The Host.
         */
        public String host;

        /**
         * The Id.
         */
        public String id;

        /**
         * The Product.
         */
        public String product;

        /**
         * The Serial.
         */
        public String serial;

        /**
         * The Tags.
         */
        public String tags;

        /**
         * The Type.
         */
        public String type;

        /**
         * The User.
         */
        public String user;

        /**
         * The Time.
         */
        public long time;

        /**
         * The Version sdk int.
         */
        public int version_sdk_int;

        /**
         * The Version codename.
         */
        public String version_codename;

        /**
         * The Version release.
         */
        public String version_release;

        /**
         * The Version incremental.
         */
        public String version_incremental;

        /**
         * The Net hostname.
         */
        public String net_hostname;
    }

    /**
     * The type Wifi info.
     */
    public static class WifiInfo {

        /**
         * The State.
         */
        public int state;//

        /**
         * The Bssid.
         */
        public String bssid;//获取BSSID地址。

        /**
         * The Ssid.
         */
        public String ssid;//获取SSID地址。  需要连接网络的ID

        /**
         * The Ip address.
         */
        public int ipAddress;//获取IP地址。4字节Int, XXX.XXX.XXX.XXX 每个XXX为一个字节

        /**
         * The Mac address.
         */
        public String macAddress;//获取MAC地址。

        /**
         * The Network id.
         */
        public int networkId;//获取网络ID。

        /**
         * The Link speed.
         */
        public int linkSpeed;//获取连接速度，可以让用户获知这一信息。

        /**
         * The Rssi.
         */
        public int rssi;//获取RSSI，RSSI就是接受信号强度指示
    }

    /**
     * The type Battery info.
     */
    public static class BatteryInfo {

        /**
         * The Status.
         */
        public int status;

        /**
         * The Health.
         */
        public int health;

        /**
         * The Present.
         */
        public boolean present;

        /**
         * The Level.
         */
        public int level;

        /**
         * The Scale.
         */
        public int scale;

        /**
         * The Icon small.
         */
        public int icon_small;

        /**
         * The Plugged.
         */
        public int plugged;

        /**
         * The Voltage.
         */
        public int voltage;

        /**
         * The Temperature.
         */
        public int temperature;

        /**
         * The Technology.
         */
        public String technology;
    }

    /**
     * The type Cpu info.
     */
    public static class CpuInfo {

        /**
         * The Processor.
         */
        public String processor;

        /**
         * The Features.
         */
        public String features;

        /**
         * The Cpu implementer.
         */
        public String cpu_implementer;

        /**
         * The Cpu architecture.
         */
        public String cpu_architecture;

        /**
         * The Cpu variant.
         */
        public String cpu_variant;

        /**
         * The Cpu part.
         */
        public String cpu_part;

        /**
         * The Cpu revision.
         */
        public String cpu_revision;

        /**
         * The Hardware.
         */
        public String hardware;

        /**
         * The Revision.
         */
        public String revision;

        /**
         * The Serial.
         */
        public String serial;
    }

//    @KeepAll
//    public static class SimSdcardInfo {
//
//        public boolean simChange;
//
//        public boolean sdcardChange;
//    }

    /**
     * The type Telephony info.
     */
    public static class TelephonyInfo {

        /**
         * The Device software version.
         */
        public String deviceSoftwareVersion;

        /**
         * The Device id.
         */
        public String deviceId;

//        public List<NeighboringCellInfo> neighboringCellInfo;

//        public CellLocation cellLocation;

        /**
         * The Phone type.
         */
        public int phoneType;

        /**
         * The Network operator name.
         */
        public String networkOperatorName;

        /**
         * The Network operator.
         */
        public String networkOperator;

        /**
         * The Is network roaming.
         */
        public boolean isNetworkRoaming;

        /**
         * The Network country iso.
         */
        public String networkCountryIso;

        /**
         * The Network type.
         */
        public int networkType;

        /**
         * The Has icc card.
         */
        public boolean hasIccCard;

        /**
         * The Sim state.
         */
        public int simState;

        /**
         * The Sim operator.
         */
        public String simOperator;

        /**
         * The Sim operator name.
         */
        public String simOperatorName;

        /**
         * The Sim country iso.
         */
        public String simCountryIso;

        /**
         * The Sim serial number.
         */
        public String simSerialNumber;

        /**
         * The Subscriber id.
         */
        public String subscriberId;

        /**
         * The Group id level 1.
         */
        public String groupIdLevel1;

        /**
         * The Line 1 number.
         */
        public String line1Number;

        /**
         * The Voice mail number.
         */
        public String voiceMailNumber;

        /**
         * The Voice mail alpha tag.
         */
        public String voiceMailAlphaTag;

        /**
         * The Call state.
         */
        public int callState;

        /**
         * The Data activity.
         */
        public int dataActivity;

        /**
         * The Data state.
         */
        public int dataState;

//        public List<android.telephony.CellInfo> allCellInfo;

        /**
         * The Mms user agent.
         */
        public String mmsUserAgent;

        /**
         * The Mms ua prof url.
         */
        public String mmsUAProfUrl;

    }

    /**
     * The type Apk info.
     */
    public static class ApkInfo {

        /**
         * The Package name.
         */
        public String packageName;

        /**
         * The Version code.
         */
        public int versionCode;

        /**
         * The Version name.
         */
        public String versionName;

        /**
         * The App name.
         */
        public String appName;

        /**
         * The First install time.
         */
        public long firstInstallTime;

        /**
         * The Last update time.
         */
        public long lastUpdateTime;

        /**
         * The Size.
         */
        public long size;

        /**
         * The Requested permissions.
         */
        public String[] requestedPermissions;

        @Override
        public String toString() {
            return "ApkInfo{" +
                    "packageName='" + packageName + '\'' +
                    ", versionCode=" + versionCode +
                    ", versionName='" + versionName + '\'' +
                    ", appName='" + appName + '\'' +
                    ", firstInstallTime=" + firstInstallTime +
                    ", lastUpdateTime=" + lastUpdateTime +
                    ", size=" + size +
                    ", requestedPermissions=" + Arrays.toString(requestedPermissions) +
                    '}';
        }
    }
}