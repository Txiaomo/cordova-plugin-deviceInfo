var exec = require('cordova/exec');

exports.deviceInfo = function(arg0, success, error) {
    exec(success, error, "DeviceInfo", "deviceInfo", [arg0]);
};
