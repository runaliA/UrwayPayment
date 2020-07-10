var exec = require('cordova/exec');

module.exports.payment = function (arg0, success, error) {
    exec(success, error, 'PaymentUrway', 'payment', [arg0]);
};
